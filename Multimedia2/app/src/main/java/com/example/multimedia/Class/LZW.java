package com.example.multimedia.Class;

import com.example.multimedia.Interface.Operation;

import java.util.ArrayList;

public class LZW implements Operation {

    static public ArrayList<String> dic = new ArrayList<>();
    static public ArrayList<Integer> index = new ArrayList<>();
    static public ArrayList<String> result = new ArrayList<>();
    static public ArrayList<String> dictionary = new ArrayList<>();
    static int counter = 128 ;

    static void fillTheDic() {
        for(int i = 0 ; i < counter ; i++) {
            String s = Character.toString((char)i);
            dic.add(s);
        }
    }


    private static int Search (String str) {
        for(int i = 0 ; i < dic.size() ; i++) {
            if (dic.get(i).equals(str)) {
                return i ;
            }
        }
        return -1;
    }
    @Override
    public void compress(String str) {
        fillTheDic();
        String[] arr = str.split("");
        String tmp;
        for(int i = 0 ; i < arr.length ; i++) {
            tmp = arr[i];
            int pos = Search(arr[i]);
            boolean flag = true;
            for(int j = i + 1 ; j < arr.length ; j++) {
                if(flag == true) {
                    tmp+= arr[j];
                    int n = Search(tmp);
                    if (n == -1) {
                        dic.add(tmp);
                        index.add(pos);
                        flag = false;
                    }

                    else if (n != -1 && j+1 >= arr.length){
                        pos = n ;
                        index.add(pos);
                    }

                    else {
                        pos = n ;
                    }
                }
                else {
                    i = (j - 2);
                    break;
                }
            }
        }

    }

    public static String searchByIndex(int index) {

        for(int i = 0 ; i < dic.size() ; i++) {

            if(index > dic.size()) {
                return null;
            }
            else if(index == i) {
                return dic.get(i);
            }

        }
        return null;
    }

    @Override
    public void decompress() {
        String s;
        String tmp;
        char felement;

        dic.subList(128, dic.size()).clear();
        for(int i = 0 ; i < index.size() ; i++) {

            int n = index.get(i);
            if(i == 0) {
                s = Character.toString((char)n);
                result.add(s);

            }

            else if(128 > n && n >= 0 && i != 0) {
                s = Character.toString((char)n);
                result.add(s);
                tmp = searchByIndex(index.get(i-1));
                s = tmp + s;
                dic.add(s);
            }
            else if(i != 0) {
                tmp = searchByIndex(index.get(i));
                if (tmp == null) {
                    tmp = searchByIndex(index.get(i-1));
                    felement = tmp.charAt(0);
                    s = tmp + felement;
                    result.add(s);
                    dic.add(s);
                }
                else  {
                    result.add(tmp);
                    felement = tmp.charAt(0);
                    tmp = searchByIndex(index.get(i-1));
                    s = tmp + felement;
                    dic.add(s);
                }
            }



        }

    }
}
