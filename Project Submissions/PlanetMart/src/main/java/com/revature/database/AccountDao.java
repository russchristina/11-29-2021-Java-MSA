package com.revature.database;

import com.revature.models.accounts.Account;

public class AccountDao {

    //add, modify, read, delete

    public Account getAccount(String username){
        return DummyData.accountMap.get(username);
    }


}
