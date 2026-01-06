package org.example;

public class NumberComparator {

    public int compareNumber(int a, int b){
        if(a<b){
            return -1;
        }

        else if(a>b){
            return 1;
        }

        else{
            return 0;
        }
    }
}
