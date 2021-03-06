package com.example.multimedia.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import com.example.multimedia.Class.JPEG;
import com.example.multimedia.Class.LZ78;
import com.example.multimedia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentJPEG extends Fragment {

    EditText editText;
    TextView textViewEncode;
    TextView textViewDecode;
    String string;
    Button compress , deCompress;
    JPEG jpeg;

    public FragmentJPEG() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jpeg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editText       = view.findViewById(R.id.jepg_et);
        textViewEncode   = view.findViewById(R.id.encode_tv);
        textViewDecode = view.findViewById(R.id.decode_tv);
        compress       = view.findViewById(R.id.compress_jpeg_bt);
        deCompress     = view.findViewById(R.id.decompress_jpeg_bt);
        jpeg = new JPEG();

        compress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jpeg.buildCategoriesTable();
                string = editText.getText().toString();
                if(string.contains(" ")||string.isEmpty()){
                    Toast.makeText(getActivity()," the text is wrong :)",Toast.LENGTH_SHORT).show();
                }
                else {
                    jpeg.createDescriptor(string);
                    jpeg.ratio();
                    jpeg.encode();
                    for (int i=0 ;i<jpeg.compress.size();i++){
                        textViewEncode.append(jpeg.compress.get(i).getPositive()+ jpeg.compress.get(i).getNegative()+"  ");
                    }
                }

            }
        });
        deCompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jpeg.decode();

                for (int i=0 ;i<jpeg.decompress.size();i++){
                    textViewDecode.append(jpeg.decompress.get(i));
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
            textViewEncode.setText("");
            textViewDecode.setText("");
            editText.setText("");
            jpeg.decompress.clear();
            jpeg.compress.clear();
            jpeg.decimal.clear();
            jpeg.probabilities .clear();
            jpeg.huffmanTable .clear();
            jpeg.descriptor .clear();
            jpeg = new JPEG();


            Toast.makeText(getActivity(),"Reset is clicked",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
