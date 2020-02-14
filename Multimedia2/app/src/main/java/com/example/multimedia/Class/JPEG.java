package com.example.multimedia.Class;

import java.io.FileNotFoundException;

import java.util.ArrayList;

public class JPEG {
    static ArrayList<Pair<String,String>> categoriesTable = new ArrayList<>();
    public static ArrayList<Pair<String,String>> descriptor      = new ArrayList<>();
    static public ArrayList<String> decimal      = new ArrayList<String>();
    static public ArrayList<Node> probabilities   = new ArrayList<>();
    public static ArrayList<Node> huffmanTable    = new ArrayList<>();
    static public ArrayList<Pair<String,String>> compress = new ArrayList<>();
    static public ArrayList<String> decompress = new ArrayList<>();

    static public void buildCategoriesTable() { /* numbers from 1 to 9 */
        categoriesTable.add(new Pair("1", "0"));
        categoriesTable.add(new Pair("10", "01"));
        categoriesTable.add(new Pair("11", "00"));
        categoriesTable.add(new Pair("100", "011"));
        categoriesTable.add(new Pair("101", "010"));
        categoriesTable.add(new Pair("110", "001"));
        categoriesTable.add(new Pair("111", "000"));
        categoriesTable.add(new Pair("1000", "0111"));
        categoriesTable.add(new Pair("1001", "0110"));
        categoriesTable.add(new Pair("***", "***"));
        categoriesTable.add(new Pair("***", "***"));

    }

    //************************(Function to get length)***************************
    /*...comment about function length (^_^)
     to get the length of the number ex the length of (2)
     2 in the binary is 10 the the length of (10) is 2
    */
    static String length(String number) {
        String len = null;

        for (int i = 1; i <= categoriesTable.size(); i++) {
            if (Integer.parseInt(number) == i) {
                len = String.valueOf(categoriesTable.get(i - 1).getPositive().length());
                break;
            }
        }
        return len;
    }
    //(Function to createDescriptor)*************************************************

    static public void createDescriptor(String input) {

        String[] split = new String[input.length()];
        for (int i = 0; i < input.length(); i++) {
            split[i] = input.charAt(i) + "";
        }

        //String[] split = input.split("");
        int count = 0; // 34an el zero
        String newNumber = null;
        for (int i = 0; i < split.length; i++) {
            if (i == 0 && split[i] != "0") {
                if (split[i].contains("-")) {
                    newNumber = split[i].replaceFirst("-", "");
                    descriptor.add(new Pair(String.valueOf(count), length(newNumber)));
                    decimal.add(split[i]);
                } else {
                    descriptor.add(new Pair(String.valueOf(count), length(split[i])));
                    decimal.add(split[i]);
                }
            } else {
                if (split[i].equals("0")) {
                    count++;
                } else if (!split[i].equals("0")) {
                    if (split[i].contains("-")) {
                        newNumber = split[i].replaceFirst("-", "");
                        descriptor.add(new Pair(String.valueOf(count), length(newNumber)));
                        decimal.add(split[i]);
                        count = 0;
                    } else {
                        descriptor.add(new Pair(String.valueOf(count), length(split[i])));
                        decimal.add(split[i]);
                        count = 0;
                    }
                }
            }
        }
        descriptor.add(new Pair(String.valueOf(count), "0"));

        decimal.add("0");
    }

    //(Function to make probabilities)*****************************************************
    static public void ratio(){
        float size = Float.parseFloat(String.valueOf(descriptor.size()));
        float count = 0, probability = 0;
        String first, second;

        for (int i = 0; i < size; i++) {
            first = descriptor.get(i).getPositive() + "," + descriptor.get(i).getNegative();
            for (int j = 0; j < size; j++) {
                second = descriptor.get(j).getPositive() + "," + descriptor.get(j).getNegative();
                if (first.equals(second)) {
                    count++;
                }
            }
            probability = (count / size);
            boolean flag = search("(" + first + ")");
            if (flag == false) {
                probabilities.add(new Node("(" + first + ")", probability));
            }
            count = 0;
        }
        buildTree(probabilities); /// 34aaaaaan el treee
    }
    //(Function to search)**********************************************************
    static boolean search(String name) {
        for (int i = 0; i < probabilities.size(); i++) {
            if (probabilities.get(i).name.equals(name))
                return true;
        }
        return false;
    }
    //(Function to get the min node in ArrayList (input))***************************
    static Node getMinNode(ArrayList<Node> list) {
        float min = list.get(0).frequency;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).frequency < min) {
                min = list.get(i).frequency; }
        }
        Node minNode = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).frequency == min) {
                minNode = list.get(i);
                list.remove(i);
                return minNode;
            }
        }
        return minNode;
    }
    //(Function to build The tree )**************************************************
    static void buildTree(ArrayList<Node> list) {
        while (list.size() != 1) {
            Node fNode = getMinNode(list);
            //System.out.println(fNode.name+" "+fNode.frequency);
            Node sNode = getMinNode(list);
            // System.out.println(sNode.name+" "+sNode.frequency);
            float freq = fNode.frequency + sNode.frequency;
            Node pNode = new Node(freq);
            pNode.left = fNode;
            pNode.right = sNode;
            list.add(pNode);
        }
        for (int i = 0; i < list.size(); i++) {
            Node root = list.get(i);
            makeHuffCode(root, "");
            makeTable(root);

        }
    }
    //(Function to make huffCode for all nodes in tree)******************************
    static void makeHuffCode(Node node, String s) {
        if (node == null)
            return;
        if (node.left == null && node.right == null) {
            node.huffCode = s;
            return;
        }
        makeHuffCode(node.left, s + "0");
        makeHuffCode(node.right, s + "1");
    }
    //(Function to makeTable)*******************************************************
    static void makeTable(Node root) {
        if (root == null)
            return;
        makeTable(root.left);
        if(root.name != "*"){huffmanTable.add(root);}
        makeTable(root.right);
    }

    //(Function to compress)********************************************************
    static public void encode(){

        for (int i = 0 ; i < descriptor.size() ; i++){
            String des = "("+descriptor.get(i).getPositive()+","
                    +descriptor.get(i).getNegative()+")";
            compress.add(new Pair(searchByDescriptor(des),searchByDecimal(decimal.get(i))));
        }

    }
    //(Function to decompress)*******************************************************
    static public void decode(){

        int count = 0;
        String number = null;
        //ArrayList<String> decompress = new ArrayList<>();

        for (int i = 0 ; i < compress.size()-1 ; i++){
            count  = searchByHuffCode(compress.get(i).getPositive());
            number = searchByBinary(compress.get(i).getNegative());

            while(count !=0){
                decompress.add("0");
                count--;
            }
            decompress.add(number);

        }
        //this line to get zero number
        count = Integer.parseInt(descriptor.get(descriptor.size()-1).getPositive());
        while(count !=0){ decompress.add("0"); count--;}


    }

    //(Functions search)*************************************************************
       /*
    ...
    This function take descriptor like (2,2) and return
    the huffCode of this descriptor example
    descriptor like (2,2) --> function after search ar table huffmanCode return --> 10
    ..
     */
    static String searchByDescriptor(String descriptor){

        for (int i = 0 ; i < huffmanTable.size() ; i++){
            if(huffmanTable.get(i).name.equals(descriptor))
                return huffmanTable.get(i).huffCode;
        }
        return null;
    }
    /*
   ...
   This function take decimal number like 3
   after search at categoriesTable it returns hte binary of this number
   example decimal number is -2 --> function will return 01
   ..
    */
    static String searchByDecimal(String number){
        if(number.contains("-")){
            String tmp = number.replaceFirst("-", "");
            for(int i = 1 ; i < categoriesTable.size() ; i++){
                if(String.valueOf(i).equals(tmp)){
                    return categoriesTable.get(i-1).getNegative(); }
            }
        }
        else{
            for(int i = 1 ; i < categoriesTable.size() ; i++){
                if(String.valueOf(i).equals(number)){
                    return categoriesTable.get(i-1).getPositive();} }
        }
        return null;
    }
    /*
    ...
    this function taken huffCode and return
    the count of the zero
    ...
     */
    static Integer searchByHuffCode(String huffCode){
        String name = null;
        int count = 0;
        for (int i = 0 ; i < huffmanTable.size() ; i++){
            if(huffmanTable.get(i).huffCode.equals(huffCode)){
                name = huffmanTable.get(i).name;
            }
        }
        String[] split = new String[name.length()];
        for (int i = 0; i < name.length(); i++) {
            split[i] = name.charAt(i) + "";
        }
//        String [] split = name.split("");//[(,1,1,)] --> array of string
        count = Integer.parseInt(split[1]);    // return index 1 --> (1)
        return count;
    }

    /*
   ...
   this function taken binary and return
   the decimal number of this binary
   ex --> 11 the decimal is 3 and if binary is 00 that
   is the complement of (11) thus hte return value is -3
   ...
    */
    static String searchByBinary(String binary){
        for (int i = 1 ; i <= categoriesTable.size() ; i++){
            if(categoriesTable.get(i-1).getPositive().equals(binary)){
                return String.valueOf(i); }
            else if(categoriesTable.get(i-1).getNegative().equals(binary)){
                return "-"+String.valueOf(i);}
        }
        return null;
    }

}
