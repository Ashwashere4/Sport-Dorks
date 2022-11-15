package com.estore.api.estoreapi.persistence.Users;

import java.io.IOException;

import com.estore.api.estoreapi.model.Users.User;

/**
 * Defines the interface for Inventory object persistence
 * 
 * @author SWEN Faculty
 */
public interface UserDAO {
    /**
     * Retrieves all {@linkplain User users}
     * 
     * @return An array of {@link User users} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    User[] getUsers() throws IOException;

    /**
     * Finds all {@linkplain User Users} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link User Users} whose nemes contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    User[] seachUsers(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain User Users} with the given name
     * 
     * @param name The name of the {@link User Users} to get
     * 
     * @return a {@link User User} object with the matching name
     * <br>
     * null if no {@link User User} with a matching name is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    User getUser(String userName) throws IOException;

    /**
     * Creates and saves a {@linkplain User User}
     * 
     * @param name {@linkplain User User} object to be created and saved
     * <br>
     * The name of the User object is ignored and a new unique name is assigned
     *
     * @return new {@link User User} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    User creatUser(String userName, String password) throws IOException;

    User creatUser(User User) throws IOException;

    /**
     * Updates and saves a {@linkplain User User}
     * 
     * @param {@link User User} object to be updated and saved
     * 
     * @return updated {@link User User} if successful, null if
     * {@link User User} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    User updateUser(User User, String UserName, String password, Boolean admin, Boolean tOwner ) throws IOException;

    /**
     * Deletes a {@linkplain User user} with the given name
     * 
     * @param userName The userName of the {@link User user}
     * 
     * @return true if the {@link User User} was deleted
     * <br>
     * false if User with the given name does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteUser(String userName) throws IOException;
}