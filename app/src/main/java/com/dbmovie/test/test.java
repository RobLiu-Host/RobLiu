package com.dbmovie.test;

public class test {
    static Boolean FOP(char c){
        System.out.print(c);
        return true;
    }
    public static void main(String[]args){
        int i=0;
        for(FOP('A');FOP('B')&&(i<2);FOP('C')){
            i++;
            FOP('D');
        }
    }

}
