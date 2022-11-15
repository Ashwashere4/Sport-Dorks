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
    public UserFileDAO(@Value("${users.filename}") String filename, ObjectMapper objectMapper) throws IOException {
            this.filename = filename;
            this.objectMapper = objectMapper;
            loadInventory();
        }

    private ArrayList<User> getInventoryArray() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User[] getUsers() throws IOException {

        return getInventoryArray().toArray(new User[0]);
    }

    @Override 
    public User getUser(String name) throws IOException{
        User user = users.get(name);
            if (user != null)
                return user;
            else
                System.out.println("User does not exist.");
                return null;
    }

    @Override
    public User creatUser(User user) throws IOException {
        User newUser = new User(user.getUserName(), user.getPass(), false, false);
        users.put(user.getUserName(), newUser);
        saveInventory();
        return newUser;
    }

    @Override
    public User creatUser(String userName, String password) throws IOException {
        User newUser = new User(userName, password, false, false);
        users.put(userName, newUser);
        saveInventory();
        return newUser;
    }

    @Override
    public boolean deleteUser(String name) throws IOException{
            users.remove(name);
            return true;
        }

    
    @Override
    public User[] seachUsers(String text) throws IOException {
        if (text.length() == 0)
            return new User[0];

        ArrayList<User> Users = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getUserName().toLowerCase().contains(text.toLowerCase())) {
                Users.add(user);
            }
        }

        return Users.toArray(new User[0]);
    }
    
    private void saveInventory() throws IOException {
        objectMapper.writeValue(new File(filename), getInventoryArray());
    }

    @Override
    public User updateUser(User user, String userName, String password, Boolean admin, Boolean Towner) throws IOException {
        // User localItem = users.get(user.getName());
        User updatedItem = new User(userName, password, admin, Towner);
        deleteUser(user.getUserName());
        users.put(updatedItem.getUserName(), updatedItem);
        return updatedItem;
    }

    
    /**
     * Load the users from the file.
     */
    private void loadInventory() throws IOException {
        users = new HashMap<>();
        User[] inventoryArray = objectMapper.readValue(new File(filename), User[].class);
        for (User user : inventoryArray) {
            users.put(user.getUserName(), user);
        }
    }

}
