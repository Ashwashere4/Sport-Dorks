package com.estore.api.estoreapi.persistence.Users;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Users.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserFileDAO implements UserDAO {
     /**
     * The current users.
     */
    private Map<String, User> users;

    /**
     * The file name of the users file.
     */
    private String filename;

    /**
     * The object mapper.
     */

    private ObjectMapper objectMapper;

    /**
     * 
     * @param users
     */
    public UserFileDAO(@Value("${user.filename}") String filename, ObjectMapper objectMapper) throws IOException {
            this.filename = filename;
            this.objectMapper = objectMapper;
            loadUsers();
        }

    private ArrayList<User> getUsersArray() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User[] getUsers() throws IOException {
        return getUsersArray().toArray(new User[0]);
    }

    @Override 
    public User getUser(String name) throws IOException{
        User user = users.get(name);
            if (user != null)
                return user;
            else
                //System.out.println("User does not exist.");
                return null;
    }

    @Override
    public User creatUser(User user) throws IOException {
        User newUser = new User(user.getUserName(), user.getPass(), user.isAdmin(), user.isTOwner());
        users.put(user.getUserName(), newUser);
        saveUsers();
        return newUser;
    }

    @Override
    public User creatUser(String username, String password, Boolean admin, Boolean owner) throws IOException {
        User newUser = new User(username, password, admin, owner);
        users.put(username, newUser);
        saveUsers();
        return newUser;
    }

    @Override
    public boolean deleteUser(String username) throws IOException{
            users.remove(username);
            saveUsers();
            return true;
        }


    
    private void saveUsers() throws IOException {
        objectMapper.writeValue(new File(filename), getUsersArray());
    }

    @Override
    public User updateUser(User user, String userName, String password, Boolean admin, Boolean Towner) throws IOException {
        User updatedUser = new User(userName, password, admin, Towner);
        deleteUser(user.getUserName());
        users.put(updatedUser.getUserName(), updatedUser);
        saveUsers();
        return updatedUser;
    }

    /**
     * Load the users from the file.
     */
    private void loadUsers() throws IOException {
        users = new HashMap<>();
        User[] UsersArray = objectMapper.readValue(new File(filename), User[].class);
        for (User user : UsersArray) {
            users.put(user.getUserName(), user);
        }
        saveUsers();
    }

}
