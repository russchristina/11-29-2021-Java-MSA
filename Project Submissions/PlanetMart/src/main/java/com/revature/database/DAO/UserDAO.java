package com.revature.database.DAO;


import com.revature.models.users.User;

import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers();
    public List<User> getUsersByLocation(String location);
    public void updateUserById(int id);
    public void deleteUserById(int id);
    public void addUser(User user);


}
