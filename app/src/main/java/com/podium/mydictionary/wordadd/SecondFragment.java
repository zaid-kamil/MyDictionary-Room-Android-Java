package com.podium.mydictionary.wordadd;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.podium.mydictionary.R;
import com.podium.mydictionary.database.Word;
import com.podium.mydictionary.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private AddWordViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(AddWordViewModel.class);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSubmit.setOnClickListener(v -> {
            String meaning = binding.editMeaning.getText().toString();
            String word = binding.editWord.getText().toString();
            if (TextUtils.isEmpty(meaning) && TextUtils.isEmpty(word)) {
                Toast.makeText(getActivity(), "word or meaning is missing", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.insert(new Word(word, meaning));
                NavHostFragment.findNavController(this).navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}