/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author levan
 */
public class UserService {
    
    public User get(String email) throws Exception {
        UserDB  userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }
    public List<User> getAll() throws Exception{
        UserDB  userDB = new UserDB();
        List <User> users = userDB.getAll();
        return users;
    }
    public void insert (String email, boolean active, String fname, String lname, String password, int roleId) throws Exception{
        User user = new User (email, active, fname, lname, password);
        // attach the role
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.getRole(roleId);
        user.setRole(role);
        UserDB  userDB = new UserDB();
        userDB.insert(user);
        
    }
    public void update (String email, boolean active, String fname, String lname, String password, int roleId) throws Exception{
        UserDB  userDB = new UserDB();
        //retrieve a old user from database
        User user = userDB.get(email);
        //setting a new information
        user.setActive(active);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setPassword(password);
        // if the role change, retreive new object
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.getRole(roleId);
        user.setRole(role);
        //update user        
        userDB.update(user);
        
    }
    public void delete (String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        userDB.delete(user);
    }
}
