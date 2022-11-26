package com.estore.api.estoreapi.model.Users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("userName") private String username;
    @JsonProperty("pass") private String password;
    @JsonProperty("admin") private Boolean admin;
    @JsonProperty("towner") private Boolean owner;
    //@JsonProperty("shoppingcart") private ShoppingCart shoppingcart; 
    //@JsonProperty("wishlist") private WishList wishlist;

    /**
     * Constructor for an item object
     * @param username a label for the username as a string
     * @param password the password for the account as a string
     * @param admin boolean to keep so we know weather account is admin acount
     * @param tOwner boolean to keep so we know weather account is a team owner
     */
    public User(@JsonProperty("userName") String username, @JsonProperty("pass") String password, @JsonProperty("admin") Boolean admin, @JsonProperty("towner") Boolean owner) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.owner = owner;
    }

    /**
     * Accessor for the username
     * @return the username as a string
     */
    public String getUserName() {
        return this.username;
    }

    /**
     * Accessor for the password
     * @return the string representation of the password
     */
    public String getPass() {
        return this.password;
    }

    /**
     * Accessor for the admin
     * @return a boolean of weather this account is an admin
     */
    public boolean isAdmin() {
        return this.admin;
    }

    /**
     * Accessor for the team owner
     * @return a boolean of weather this account is an owner of a team
     */
    public boolean isTOwner() {
        return this.owner;
    }

    /**
     * Method to get the string representation of an account
     */
    public String toString(){
        String admin = "is not admin";
        String owner = "is not an owner";
        if(this.admin){
            admin = "is an admin";
        }
        if(this.owner){
            owner = "is a team owner";
        }
        return ("username = " + username + "\nPassword = " + password + "\n" + admin + "\n" + owner);
    }
}
