package com.podium.mydictionary.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.podium.mydictionary.database.Word;
import com.podium.mydictionary.database.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private final WordRepository mRepository;
    private final LiveData<List<Word>> allWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        allWords = mRepository.getAllWords();
    }

    public void deleteAll(){
        // todo add a repository function deleteALL and add a call here
        //mRepository.
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }
}
