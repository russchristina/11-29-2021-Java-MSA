package com.revature.lambdas;

public class LambdaPractice {
    public int selectMathOp(int num1, int num2, MathOps o){
        return o.doMath(num1, num2);
    }
    public static void main(String[] args) {

        MathOps multiplication = (x,y) -> {
            int result = x *y;
            return result;
        };
        System.out.println(new LambdaPractice()
                .selectMathOp(40, 60, (x,y)-> x * y));
    }

    interface MathOps{
        int doMath(int a, int b);
    }
}
