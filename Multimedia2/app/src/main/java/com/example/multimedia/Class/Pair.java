package com.example.multimedia.Class;

public class Pair<Key,Value> {
    private Key key;
    private Value value;

    public Pair(Key key, Value value){
        this.key = key;
        this.value = value;
    }

    public Key getPositive(){ return this.key; }
    public Value getNegative(){ return this.value; }

    public void setPositive(Key key){ this.key = key; }
    public void setNegative(Value value){ this.value = value; }
}


//    private int key;
//    private char value;