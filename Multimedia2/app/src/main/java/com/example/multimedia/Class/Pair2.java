package com.example.multimedia.Class;

public class Pair2 {
    private int position;
    private int length;
    private String symbol;


    public Pair2(int position , int length , String symbol){
        this.position = position;
        this.length   = length;
        this.symbol   = symbol;
    }
    public int getPosition(){ return this.position; }
    public int getLength(){ return this.length; }
    public String getSymbol(){ return this.symbol; }

    public void setPosition(int position){ this.position = position; }
    public void setLength(int Length){ this.length = Length; }
    public void setSymbol(String symbol){ this.symbol = symbol; }
}
