package com.revature;

public class StringBuilderandBuffer {
    public static void main(String[] args) {
        String myName = "Christina";
        myName =  myName.concat("Russ");
        System.out.println(myName);

        /*
        Strings are immutable. You cannot change String once you've created it.
        This is fine, but sometimes it is easier to work with what feels like a mutable string
        Enter StringBuilder and StringBuffer. They behave as mutable Strings.
         */
        StringBuilder builder = new StringBuilder("yo");
        builder.append("bitch"); //Replacement for String.concat(); with StringBuilder
        System.out.println(builder);
        /*
        StringBugger is the original StringBuilder. The difference between StringBuilder
        and StringBuffer lies in performance. StringBuffer is thread-safe, which means that it is safe for use in multi
        threaded environments. Unfortunately, this makes it slower than StringBuilder. As a result, it is generally
        recommended that you use StringBuilder unless you absolutely need StringBuffer (meaning you're working in a
        multi-threaded environment).
         */
        StringBuffer buffer = new StringBuffer("yo");
        buffer.append("bitch");
        System.out.println(buffer);


    }
}
