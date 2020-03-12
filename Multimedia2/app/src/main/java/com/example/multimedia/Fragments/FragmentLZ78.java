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

import com.example.multimedia.Class.LZ78;
import com.example.multimedia.Interface.Operation;
import com.example.multimedia.R;

public class FragmentLZ78 extends Fragment {
    //declaration
    EditText editText ;
    TextView textViewDict ;
    TextView textViewTags ;
    TextView textViewDecode;
    Button compress , deCompress;
    LZ78 lz78;
    String text;
    Operation operation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lz78,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText       = view.findViewById(R.id.et_78);
        textViewDict   = view.findViewById(R.id.dic_78_tv);
        textViewTags   = view.findViewById(R.id.tags_78_tv);
        textViewDecode = view.findViewById(R.id.decode_78_tv);
        compress       = view.findViewById(R.id.compress_78_bt);
        deCompress     = view.findViewById(R.id.decompress_78_bt);
        lz78 =  new LZ78();


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
                if(lz78.pair.isEmpty()){
                    Toast.makeText(getActivity(),"Please do compress first then decompress :)",Toast.LENGTH_SHORT).show();
                }
                else {
                    decode();
                }
            }


        });



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
            textViewDict.setText("");
            textViewTags.setText("");
            textViewDecode.setText("");
            editText.setText("");
            lz78.result = "";
            lz78 = new LZ78();
            Toast.makeText(getActivity(),"Reset is clicked",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void encode(String text) {
        lz78.compress(text);
        for (int i = 0; i < lz78.dict.size(); i++) {
            if (i + 1 >= lz78.dict.size()) {
                textViewDict.append("("+lz78.dict.get(i)+")");
            } else {
                textViewDict.append("("+lz78.dict.get(i)+")" + " " );
            }
        }
        for (int i = 0; i < lz78.pair.size(); i++) {
                textViewTags.append("<"+" "+ lz78.pair.get(i).getPositive() +" "+ "," +" "+lz78.pair.get(i).getNegative() +" "+ ">"+" ");
        }
    }

    private void decode() {
        lz78.decompress();
        textViewDict.setText("");
        //textViewTags.setText("");

        for (int i = 0; i < lz78.dict.size(); i++) {
            if (i + 1 >= lz78.dict.size()) {
                textViewDict.append("("+lz78.dict.get(i)+")");
            } else {
                textViewDict.append("("+lz78.dict.get(i)+")" + " " );
            }
        }

        textViewDecode.setText(lz78.result);
        String string = lz78.result;

        if (text.equals(string)) {
            Toast.makeText(getActivity(), "Successful operation", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "failed Please make the operation again", Toast.LENGTH_SHORT).show();

        }
    }
}
