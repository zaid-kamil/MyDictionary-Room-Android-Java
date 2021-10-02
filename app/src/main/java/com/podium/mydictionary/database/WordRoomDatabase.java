package com.podium.mydictionary.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    private static WordRoomDatabase INSTANCE;
    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    //singleton approach
    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordRoomDatabase.class,
                            "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WordDao wordDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao asyncDao;

        List<Word> wordList;

        public PopulateDbAsync(WordRoomDatabase instance) {
            asyncDao = instance.wordDao();
            wordList = new ArrayList<>();
            wordList.add(new Word("fascinating", "extremely interesting."));
            wordList.add(new Word("appreciate", "recognize the full worth of."));
            wordList.add(new Word("segment", "each of the parts into which something is or may be divided."));
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncDao.deleteAll();
            for (Word word : wordList) {
                asyncDao.insert(word);
            }
            return null;
        }
    }
}
