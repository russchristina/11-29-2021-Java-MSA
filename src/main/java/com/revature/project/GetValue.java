package com.revature.project;

public class GetValue {
    public static String getAccountPassword() {
        return accountPassword;
    }

    private static String accountPassword = "null";

    protected String getValue(String a) {
        if (UserAccounts.getAccountHash().containsKey(a)){
    GetValue.accountPassword = UserAccounts.getAccountHash().get(a);
        }
        return accountPassword;

    }


}
