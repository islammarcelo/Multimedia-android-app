package com.example.multimedia.Class;

import java.util.ArrayList;

public class LZ77 {
    static public ArrayList<Pair2> encode = new ArrayList<>();

    static public ArrayList<String> decode = new ArrayList<>();

    public static void compress(String input){
        int pos1 = 0; int indexText = 0; int position = 0;int counter = 0;
        String nextSymbol; String str; String text = "";
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
           data.add(input.charAt(i) + "");
        }
        for(int i = 1 ; i <= data.size() ; i++){
            pos1 = i;
            if (i == 1){
                encode.add(new Pair2(0,0,data.get(i-1)));
            }
            else{
                str = convert(data,i);
                for (int j = i ; j <= data.size() ; j++){
                    text+= data.get(j-1);
                    if(str.matches("(.*)"+text+"(.*)")){
                        indexText = str.indexOf(text);
                        counter++;
                    }
                    else {  break; }
                }
                if(indexText == 0 && counter == 0){ position = 0; }
                else {position = pos1 - (indexText+1);}
                i+=counter;
                if (i > data.size()){
                    encode.add(new Pair2(position,(text.length()),"NULL"));
                    break;
                }
                else {
                    encode.add(new Pair2(position, (text.length()) - 1, data.get(i - 1)));
                }
                counter=0;
                text = "";
            }
        }
        for (int i = 0 ; i < encode.size(); i++){
            System.out.println(encode.get(i).getPosition()+","+encode.get(i).getLength()+","
                    + encode.get(i).getSymbol());
        }
    }

    private static String convert(ArrayList<String> data, int i) {
        String str = "";
        for (int j = 1 ; j < i ; j++){
            str+= data.get(j-1);
        }
        return str;
    }
    public static void decompress(){

        for(int i = 0 ; i < encode.size() ; i++){
            int pos     = encode.get(i).getPosition();
            int len     = encode.get(i).getLength();
            String next = encode.get(i).getSymbol();
            getSymbols(pos,len,next);
        }
    }

    private static void getSymbols(int pos, int len, String next) {
        String str;
        int n = decode.size() - pos;
        if(pos == 0 && len == 0){decode.add(next);return;}
        for(int i = n ; i < n + len ; i++){
            str = decode.get(i);
            decode.add(str);
        }
        if(next.contains("NULL")){ return; }
        decode.add(next);
    }

}
