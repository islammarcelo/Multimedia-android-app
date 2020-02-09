package com.example.multimedia.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.multimedia.Class.LZ78;
import com.example.multimedia.Class.LZW;
import com.example.multimedia.Interface.Operation;
import com.example.multimedia.R;

import static com.example.multimedia.Class.LZW.result;


public class FragmentLZW extends Fragment {
    //declaration
    EditText editText ;
    TextView textViewDict ;
    TextView textViewIndex ;
    TextView textViewDecode;
    Button compress , deCompress;
    LZW lzw;
    String text;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lzw,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        editText       = view.findViewById(R.id.lzw_et);
        textViewDict   = view.findViewById(R.id.dic_lzw_tv);
        textViewIndex  = view.findViewById(R.id.index_lzw_tv);
        textViewDecode = view.findViewById(R.id.decode_lzw_tv);
        compress       = view.findViewById(R.id.compress_lzw_bt);
        deCompress     = view.findViewById(R.id.decompress_lzw_bt);
        lzw = new LZW();

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
                if(lzw.index.isEmpty()){
                    Toast.makeText(getActivity(),"Please do compress first then decompress :)",Toast.LENGTH_SHORT).show();
                }
                else {
                    decode();
                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
    private void encode(String text) {
        lzw.compress(text);


    }
    private void decode(){
        lzw.decompress();
        for(int i = 0 ; i < result.size() ; i++) {
            textViewDecode.append(result.get(i));
        }
    }

}
