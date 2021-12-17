package com.revature.designpatterns.practice;

import java.util.Scanner;

public class ScanPrac {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Add value");

        if (scan.hasNextInt()) {
            int added = scan.nextInt();
            while (added <= 0) {
                System.out.println("Invalid. Try Again");
                scan.nextLine();
                added = scan.nextInt();
            }
            System.out.println(("Good Job"));

    }
}
}