package com.podium.mydictionary.wordadd;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.podium.mydictionary.database.Word;
import com.podium.mydictionary.database.WordRepository;

public class AddWordViewModel extends AndroidViewModel {
    private final WordRepository mRepository;

    public AddWordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }
}
