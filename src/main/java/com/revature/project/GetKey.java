package com.revature.project;

public class GetKey {
   // MainDisplay display = new MainDisplay();
   // UserAccounts allAccounts = new UserAccounts();


//    protected String getUsername(String a) {
//        GetKey key = new GetKey();
//        GetUser user = new GetUser();
//        MainDisplay main = new MainDisplay();
//       // if (!key.getKey(main.username).equals(" ")) ;
//    //} else

    /*

     */

    public boolean hasKey(String display, UserAccounts allAccounts  ){
        //Tried to make username case insensitive below :(
        //      String ignoreUsernameCase = MainDisplay.getUsername();
//        if (MainDisplay.getUsername().equalsIgnoreCase(ignoreUsernameCase)){
//        }
        display = MainDisplay.getUsername();

        return UserAccounts.getAccountHash().containsKey(MainDisplay.getUsername());


    }

}
