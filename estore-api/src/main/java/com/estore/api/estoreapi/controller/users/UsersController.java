package com.estore.api.estoreapi.controller.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Users.User;
import com.estore.api.estoreapi.persistence.Users.UserDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("Users")
public class UsersController {
    private static final Logger LOG = Logger.getLogger(UsersController.class.getName());
    private UserDAO userDAO;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param userDAO The {@link UserDAO all users Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Responds to the GET request for a {@linkplain User user} for the given name
     * 
     * @param userName The string used to locate the {@link User user}
     * 
     * @return ResponseEntity with {@link User user} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{name}")
    public ResponseEntity<User> getUser(@PathVariable String name) {
        LOG.info("GET /users/" + name);
        try {
            User user = userDAO.getUser(name);
            if (user != null)
                return new ResponseEntity<User>(user,HttpStatus.OK);
            else
                System.out.println("User does not exist.");
                return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Item item}
     * 
     * @return ResponseEntity with array of {@link Item item} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<User[]> getUsers() {
        LOG.info("GET /users");
        User[] users;
        try {
            users = userDAO.getUsers();

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<User[]>(users, HttpStatus.OK);
    }

    // /**
    //  * Responds to the GET request for all {@linkplain Item item} whose name contains
    //  * the text in name
    //  * 
    //  * @param name The name parameter which contains the text used to find the {@link Item item}
    //  * 
    //  * @return ResponseEntity with array of {@link Item item} objects (may be empty) and
    //  * HTTP status of OK<br>
    //  * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
    //  * <p>
    //  * Example: Find all heroes that contain the text "ma"
    //  * GET http://localhost:8080/heroes/?name=ma
    //  */
    // @GetMapping("/")
    // public ResponseEntity<User[]> seachUsers(@RequestParam String name) {
    //     LOG.info("GET /users/?name="+name);
    //     try {
    //         User[] items = userDAO.seachUsers(name);
    //         return new ResponseEntity<User[]>(items,HttpStatus.OK);

    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }


    //     // Replace below with your implementation
    // }

    /**
     * Creates a {@linkplain Item item} with the provided item object
     * 
     * @param item - The {@link Item item} to create
     * 
     * @return ResponseEntity with created {@link Item item} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Item item} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<User> creatUser(@RequestBody User user) {
        LOG.info("POST /users " + user);
        try {
            User newUser = userDAO.creatUser(user);
            if (this.userDAO.creatUser(newUser) == null){
                return new ResponseEntity<User>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    /**
     * Updates the {@linkplain Item item} with the provided {@linkplain Item item} object, if it exists
     * 
     * @param item The {@link Item item} to update
     * 
     * @return ResponseEntity with updated {@link Item item} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of OK if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User item, String name, String password, Boolean admin, Boolean tOwner) {
        LOG.info("PUT /inventory " + item);
        if(getUser(item.getUserName()) != null) {
            try {
                User newItem = userDAO.updateUser(item, name, password, admin, tOwner);
                return new ResponseEntity<User>(newItem,HttpStatus.OK);
            }
            catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        } else {
            System.out.println("User does not exist.");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
    }

    /**
     * Deletes a {@linkplain Item item} with the given name
     * 
     * @param name The name of the {@link Item item} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String name) {
        LOG.info("DELETE /users/" + name);
        try {
            this.userDAO.deleteUser(name);
            if (this.userDAO.deleteUser(name) == false){
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("User not found.");
            return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}