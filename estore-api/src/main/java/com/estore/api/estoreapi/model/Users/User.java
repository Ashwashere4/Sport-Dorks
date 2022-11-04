package com.estore.api.estoreapi.model.Users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("userName") private String username;
    @JsonProperty("password") private String pass;
    @JsonProperty("admin") private Boolean admin;
    @JsonProperty("teamOwner") private Boolean tOwner;

    /**
     * Constructor for an item object
     * @param username a label for the username as a string
     * @param password the password for the account as a string
     * @param admin boolean to keep so we know weather account is admin acount
     */
    public User(@JsonProperty("userName") String username, @JsonProperty("password") String pass){
        this.username = username;
        this.pass = pass;
        this.admin = false;
        this.tOwner = false;
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
        return this.pass;
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
        return this.tOwner;
    }

    /**
     * Method to get the string representation of an account
     */
    public String toString(){
        String admin = "is not admin";
        String owner = "is not owner";
        if(this.admin){
            admin = "is an admin";
        }
        if(this.tOwner){
            owner = "is a team owner";
        }
        return ("username = " + username + "\nPassword = " + pass + "\n" + admin + "\n" + owner);
    }
}
