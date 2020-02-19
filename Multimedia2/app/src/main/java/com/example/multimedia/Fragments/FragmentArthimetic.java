package com.example.multimedia.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multimedia.Class.Arthimetic;
import com.example.multimedia.Class.LZ78;
import com.example.multimedia.Class.Pair;
import com.example.multimedia.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentArthimetic extends Fragment {

    Button steps;
    String[] chars, probability, example;
    Button compress , deCompress;
    EditText editTextChar ;
    EditText editTextPro ;
    EditText editTextExample ;
    TextView textView;
    int size;
    Arthimetic arthimetic;
    boolean flag,check, flagDecompress, flagCompress;


    public FragmentArthimetic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_arthimetic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        steps = view.findViewById(R.id.steps_bt);
        final NavController navController = Navigation.findNavController(view);
        editTextChar    = view.findViewById(R.id.char_et);
        editTextPro     = view.findViewById(R.id.pro_et);
        editTextExample = view.findViewById(R.id.text_et);
        compress        = view.findViewById(R.id.compress_arth_bt);
        deCompress      = view.findViewById(R.id.decompress_arth_bt);
        textView   = view.findViewById(R.id.tv);
        arthimetic = new Arthimetic();

        steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flagDecompress){
                    navController.navigate(R.id.action_fragmentArthimetic_to_steps);
                }
                else {
                    Toast.makeText(getActivity(),"You Must Do Compress And Decompress Operation First :)",Toast.LENGTH_SHORT).show();
                }
            }
        });

        compress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chars       = editTextChar.getText().toString().split("-");
                probability = editTextPro.getText().toString().split("-");
                example     = editTextExample.getText().toString().split("");
                if(chars.length == 1 || example.length == 1 || probability.length == 1){
                    Toast.makeText(getActivity()," Wrong:)",Toast.LENGTH_SHORT).show();
                }
                else {
                    String string = "";
                    for (int i = 0; i < example.length; i++) {
                        string += example[i];
                    }
                    size = chars.length;
                    if (chars.length == probability.length) {
                        for (int i = 0; i < size; i++) {
                            arthimetic.data.add(new Pair(chars[i].charAt(0), Float.parseFloat(probability[i])));
                        }

                        flag = arthimetic.checkTheProbability(size);
                        check = match(chars, example);

                        if (!flag) {
                            Toast.makeText(getActivity(), "The summation of Probabilities not Equal one :)", Toast.LENGTH_SHORT).show();
                        }
                        if (!check) {
                            Toast.makeText(getActivity(), "The Example has Symbols not found in Characters :)", Toast.LENGTH_SHORT).show();
                        } else {
                            arthimetic.bulidArrayRange(size);
                            arthimetic.compress(string);
                            flagCompress = true;
                            Toast.makeText(getActivity(), " Done Operation :)", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(getActivity(), "Characters and Probabilities numbers not Equal", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        deCompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagCompress) {
                    flagDecompress = true;
                    arthimetic.decompress();
                    Toast.makeText(getActivity(), " Done Operation :)", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), " You Must Do Compress Operation First :)", Toast.LENGTH_SHORT).show();
                }

            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.reset) {
        editTextExample.setText("");
        editTextPro.setText("");
        editTextChar.setText("");
        arthimetic.result="";
        arthimetic.count=0;
        arthimetic.data.clear();
        arthimetic.range.clear();
        arthimetic.steps.clear();
        arthimetic.r=1;arthimetic.high=1;arthimetic.low=0;
        arthimetic = new Arthimetic();
            Toast.makeText(getActivity(),"Reset is clicked",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    private static boolean match(String[]arr1,String[]arr2) {
        ArrayList<String> tmp = new ArrayList<>();
        for (int i = 0 ; i < arr1.length ; i++){
            tmp.add(arr1[i]);
        }
        for (int i = 1 ; i < arr2.length ; i++){
            if(!tmp.contains(arr2[i])){
                return false;
            }
        }
        return true;
    }
}
