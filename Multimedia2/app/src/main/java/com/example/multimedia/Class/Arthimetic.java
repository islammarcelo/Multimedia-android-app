package com.example.multimedia.Class;

import com.example.multimedia.Interface.Operation;

import java.util.ArrayList;
import java.util.Random;

public class Arthimetic implements Operation {
    static public ArrayList<Pair<Character,Float>> data = new ArrayList<>();
    static public ArrayList<Pair<Float,Float>> range = new ArrayList<>();
    static public ArrayList<String> steps = new ArrayList<>();
    static public String result="";
    static String symbol;

    public static float r = 1 , high = 1 , low = 0;
    public static int count = 0;

    @Override
    public void compress(String text) {
        steps.add("Compress Steps :)");
        for(int i = 0 ; i < text.length() ; i++) {
            char ch = text.charAt(i);
            int pos = search(ch);
            symbol = String.valueOf(text.charAt(i));
            steps.add("Symbol is : "+ symbol);
            high = low + r * range.get(pos).getNegative() ;
            steps.add("Upper: "+high);
            low  = low + r * range.get(pos).getPositive();
            steps.add("Lower: "+low);
            r    = high - low;
            count++;
        }

    }

    @Override
    public void decompress() {
        Random random = new Random();
        float value =  low + random.nextFloat() * (high - low);
        float hg = 1 , lw = 0 , r = 1;
        int pos;
        float code = value;

        steps.add("Decompress Steps :)");
        steps.add("Compression Code is :"+ value);
        for(int i = 0 ; i < count ; i++) {
            pos  = searchAtrange(code);
            symbol = String.valueOf(data.get(pos).getPositive());
            steps.add("Symbol is "+ symbol);
            result+=data.get(pos).getPositive();
            hg   = lw + r * range.get(pos).getNegative();
            steps.add("UPPER("+symbol+"): "+ hg);
            lw   = lw + r * range.get(pos).getPositive();
            steps.add("LOWER("+symbol+"): "+ lw);
            r    = hg - lw;
            code = (value-lw)/(r);
            steps.add("CODE :" +code);

        }
        steps.add(result);

    }

    static int search(char ch) {
        for(int i =  0 ; i < data.size() ; i++) {
            if(ch == data.get(i).getPositive()) {
                return i;
            }
        }
        return -1;
    }

    static int searchAtrange(float code) {
        for(int i = 0 ; i < range.size() ; i++) {
            if(range.get(i).getNegative() > code && range.get(i).getPositive() < code) {
                return i;
            }
        }
        return -1;
    }

    static public void bulidArrayRange(int size) {
        for(int i = 0 ; i < size ; i++) {
            float lw = 0 , hg = 0;
            if (i == 0) {
                range.add(new Pair(lw, data.get(i).getNegative()));
            }
            else{
                lw = range.get(i-1).getNegative();
                hg = lw + data.get(i).getNegative();
                range.add(new Pair(lw,hg));
            }
        }
    }

    static public boolean checkTheProbability(int size) {
        float sum = 0;
        for(int i = 0 ; i < size ; i++) {
            sum+=data.get(i).getNegative();
        }
        if(sum == 1) {
            return true;
        }
        else {
           return false;
        }
    }
}
