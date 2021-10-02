package com.podium.mydictionary.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.podium.mydictionary.R;
import com.podium.mydictionary.database.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater inflater;
    private List<Word> mWords;

    public WordListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.word_card_view, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if(mWords!=null){
            Word currentWord = mWords.get(position);
            holder.textWord.setText(currentWord.getWord());
            holder.textDefinition.setText(currentWord.getDefinition());
        }else{
            holder.textWord.setText("No Word found");
            holder.textDefinition.setText("add words to view by clicking on button below");
        }
    }

    @Override
    public int getItemCount() {
        if (mWords != null) {
            return mWords.size();
        }
        return 1;
    }

    public void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }


    public class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView textWord;
        private final TextView textDefinition;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textWord = itemView.findViewById(R.id.textWord);
            textDefinition = itemView.findViewById(R.id.textDefinition);
        }
    }
}
