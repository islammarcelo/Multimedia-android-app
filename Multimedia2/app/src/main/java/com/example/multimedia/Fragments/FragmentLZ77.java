package com.example.multimedia.Fragments;

import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.multimedia.Class.JPEG;
import com.example.multimedia.Class.LZ77;
import com.example.multimedia.R;

public class FragmentLZ77 extends Fragment {

    EditText editText;
    TextView textViewTags;
    TextView textViewDecode;
    String string;
    Button compress , deCompress;
    LZ77 lz77;
    String text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lz77,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        textViewTags   = view.findViewById(R.id.lz77_tags_tv);
        textViewDecode = view.findViewById(R.id.lz77_decode_tv);
        editText       = view.findViewById(R.id.lz77_et);
        compress       = view.findViewById(R.id.lz77_compress_bt);
        deCompress     = view.findViewById(R.id.lz77_decode_bt);
        lz77 = new LZ77();

        compress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = editText.getText().toString();
                if(text.contains(" ")||text.isEmpty()){
                    Toast.makeText(getActivity()," the text is wrong :)",Toast.LENGTH_SHORT).show();
                }
                else {
                    encode(text);
                }
            }
        });

        deCompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LZ77.decompress();
                for (int i = 0; i < lz77.decode.size(); i++) {
                    textViewDecode.append(lz77.decode.get(i));
                }

            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
    private void encode(String text) {
        lz77.compress(text);

        for (int i = 0; i < lz77.encode.size(); i++) {
            textViewTags.append("<"+lz77.encode.get(i).getPosition()+ ","+lz77.encode.get(i).getLength()+","+lz77.encode.get(i).getSymbol()+">"+" ");
        }
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
            textViewTags.setText("");
            textViewDecode.setText("");
            editText.setText("");
            LZ77.decode.clear();
            LZ77.encode.clear();
            lz77 = new LZ77();


            Toast.makeText(getActivity(),"Reset is clicked",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
