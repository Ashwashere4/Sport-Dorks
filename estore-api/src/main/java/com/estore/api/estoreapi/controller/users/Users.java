package com.estore.api.estoreapi.controller.users;
import java.util.HashMap;

import com.estore.api.estoreapi.model.Users.User;

public class Users {
    /** A hashmap representation of all the users where the key is the username
     * of the user and the value is the user object */
    HashMap<String, User> allUsers;

    /**
     * 
     * @param allUsers
     */
    public Users(HashMap<String, User> allUsers) {
        this.allUsers = allUsers;
    }
}
