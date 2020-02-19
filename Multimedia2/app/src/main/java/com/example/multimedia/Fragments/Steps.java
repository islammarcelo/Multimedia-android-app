package com.example.multimedia.Fragments;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.multimedia.Class.Arthimetic;
import com.example.multimedia.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Steps extends Fragment {


    public Steps() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steps,container,false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.ListRecycleView);
        LisAdapter lisAdapter = new LisAdapter();
        recyclerView.setAdapter(lisAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        return view;

    }




}
