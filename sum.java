package com.codechall1.demo;

public class sum {
    int summ (int ... a){
        int curr = 0;
        
        for(int i=0; i < a.length; i++){
                curr= a[i] + curr;

        }
        return curr;
    }
}
