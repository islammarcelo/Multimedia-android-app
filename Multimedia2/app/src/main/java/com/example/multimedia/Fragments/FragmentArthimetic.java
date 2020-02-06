package com.example.multimedia.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.multimedia.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentArthimetic extends Fragment {


    public FragmentArthimetic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_arthimetic, container, false);
    }

}
