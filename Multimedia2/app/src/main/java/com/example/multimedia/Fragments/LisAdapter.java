package com.example.multimedia.Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multimedia.Class.Arthimetic;
import com.example.multimedia.R;

class LisAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_step,parent,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return Arthimetic.steps.size();
    }
    private class ListViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public ListViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.step_tv);

        }

        public void bindView(int position){
            textView.setText(Arthimetic.steps.get(position));
        }
        public void onClick(View view){

        }
    }
}
