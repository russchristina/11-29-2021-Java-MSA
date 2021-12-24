package com.revature.project;

import java.util.HashMap;
import java.util.Map;

public class UserAccounts {
    final private static Map<String, String> accountHash = new HashMap<>();
    String a;
public UserAccounts(){
    accountHash.put("Glizzard Wizzard","GlizzWizz");
    a = accountHash.get("Glizzard Wizzard");

}

//    public static void main(String[] args) {
//       UserAccounts userAccounts = new UserAccounts();
//        System.out.println(userAccounts.a);
//    }
    public static Map<String, String> getAccountHash() {
        return accountHash;
    }

    @Override
    public String toString() {
        return "UserAccounts{" + " accountHash=" + accountHash + '}';
    }

    public static void setAccountHash(String a, String b) {
        UserAccounts.getAccountHash().put(a, b);
    }

    protected void newEntry(String a, String b) {
        Map<String, String> accountHash = UserAccounts.getAccountHash();
        accountHash.putIfAbsent(a, b);
        UserAccounts userAccounts = new UserAccounts();
        accountHash.put("bitch", "boi");
    }

}




