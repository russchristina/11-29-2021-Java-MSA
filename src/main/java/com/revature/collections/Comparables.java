package com.revature.collections;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


    /*
     In order  to be able to sort a type, it must implement the Comparable interface
      */
    class Doggo implements Comparable<Doggo> {
        private int age;
        private String name;
        private String barkSound;

        public Doggo (int age, String name, String barkSound){
            age = this.age;
            name =this.name;
            barkSound = this.barkSound;
            Set <Doggo> doggos = new TreeSet<>();
            doggos.add(new Doggo(1, "Scooby,", "Human talking"));
            doggos.add(new Doggo(2, "Snoopy", "Silence"));
            System.out.println(doggos);
        }

        /*
        When a class implements the Comparable interface, it must override the "compareTo" method
         */
        @Override
        public int compareTo(Doggo o) {
         if(this.age == o.age)  return 0;
         else return 1; //or return this.age - o.age;
        }
    }
public class Comparables {
    public static void main(String[] args) {
        Set<Integer> mySet = new TreeSet<>();
        mySet.add(3);
        mySet.add(6);
        mySet.add(5);
        mySet.add(6);
        System.out.println(mySet);

        /*
        AS a review, you can use an iterator to iterate over the elements in a set (or any implementation of
        collection)
         */
        for (Integer integer : mySet) System.out.println(integer);

    }
}
