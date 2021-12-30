package com.revature.test;

public class Challenge1 {
    int passedVariable = 37;

        public void starMethod (int a)  {
            Challenge1 challenge = new Challenge1();
            String row = "*";
            for (int i = 0; i < a; i++) {
                System.out.println(row);
                row +=("*");

            }
        }
//
                public int passVariable ( int passedVariable){
                    passedVariable = this.passedVariable;
                    return passedVariable;
                }
                
            public static void main(String[] args)  {
              Challenge1 challenge = new Challenge1();
              challenge.starMethod(0);

    }
            }


