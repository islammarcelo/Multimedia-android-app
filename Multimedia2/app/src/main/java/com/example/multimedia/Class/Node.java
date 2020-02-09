package com.example.multimedia.Class;

public class Node {

    String name;
    float frequency;
    String huffCode;
    Node left;
    Node right;
    //Constructor for node
    Node(String name , float frequency){

        this.frequency = frequency;
        this.name      = name;
    }
    //Constructor for parent node
    Node(float frequency){

        this.frequency = frequency;
        this.name    = "*";
    }
}
