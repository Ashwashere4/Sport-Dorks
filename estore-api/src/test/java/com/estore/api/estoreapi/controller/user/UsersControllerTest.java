package com.estore.api.estoreapi.controller.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.controller.users.UsersController;
import com.estore.api.estoreapi.model.Users.User;
import com.estore.api.estoreapi.persistence.Users.UserDAO;

public class UsersControllerTest {

    private UsersController usersController;
    private UserDAO mockUserDAO;

    @BeforeEach
    public void setupInventoryController(){
        mockUserDAO = mock(UserDAO.class);
        usersController = new UsersController(mockUserDAO);
    }

    @Test
    public void testGetUsers() throws IOException{
        User[] users = new User[2];
        users[0] = new User("testuser1", "testuserpass", false, false);
        users[1] = new User("testuser2", "testuserpass", false, false);

        when(mockUserDAO.getUsers()).thenReturn(users);

        ResponseEntity<User[]> response = usersController.getUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    public void testGetUsersHandleException() throws IOException{
        doThrow(new IOException()).when(mockUserDAO).getUsers();

        ResponseEntity<User[]> response = usersController.getUsers();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetUser() throws IOException{
        User user = new User("testuser1", "testuserpass", false, false);

        when(mockUserDAO.getUser(user.getUserName())).thenReturn(user);

        ResponseEntity<User> response = usersController.getUser(user.getUserName());
        //add for pass??

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetUserNotFound() throws Exception {
        String name = "testuser1";

        when(mockUserDAO.getUser(name)).thenReturn(null);

        ResponseEntity<User> response = usersController.getUser(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void testGetUserHandleException() throws Exception{
        String name = "testuser1";

        doThrow(new IOException()).when(mockUserDAO).getUser("testuser1");

        ResponseEntity<User> response = usersController.getUser(name);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void testCreateUser() throws IOException{

        User user = new User("testuser1", "testuserpass", false, false);

        when(mockUserDAO.creatUser(user)).thenReturn(user);

        ResponseEntity<User> response = usersController.creatUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());

    }   

    @Test
    public void testCreateUserFailed() throws IOException{
        User user = new User("testuser1", "testuserpass", false, true);

        when(mockUserDAO.creatUser(user)).thenReturn(null);

        ResponseEntity<User> response = usersController.creatUser(user);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateUserHandleException() throws IOException{
        User user = new User("testuser1", "testuserpass", false, true);

        doThrow(new IOException()).when(mockUserDAO).creatUser(user);

        ResponseEntity<User> response = usersController.creatUser(user);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    public void testUpdateUser() throws IOException {

        User user = new User("testuser1", "testuserpass", false, false);

        when(mockUserDAO.updateUser(user, "testuser2", "testuserpass", false, false)).thenReturn(user);

        ResponseEntity<User> response = usersController.updateUser(user, "testuser2", "testuserpass", false, false);
        
        response = usersController.updateUser(user, "testuser2", "testuserpass", false, false);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }   

    @Test 
    public void testUpdateUserHandleException() throws IOException{
        User user = new User("testuser1", "testuserpass", false, false);

        doThrow(new IOException()).when(mockUserDAO).updateUser(user, "testuser2", "testuserpass", false, false);

        ResponseEntity<User> response = usersController.updateUser(user, "testuser2", "testuserpass", false, false);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeleteUser() throws IOException { 
        
        String name = "testuser1";
        
        when(mockUserDAO.deleteUser(name)).thenReturn(true);

     
        ResponseEntity<Boolean> response = usersController.deleteUser(name);

  
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteUserNotFound() throws IOException { 
       
        String name = "testuser1";
       
        when(mockUserDAO.deleteUser(name)).thenReturn(false);

        
        ResponseEntity<Boolean> response = usersController.deleteUser(name);

        
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteUserHandleException() throws IOException { 
        
        String name = "testuser1";
        
        doThrow(new IOException()).when(mockUserDAO).deleteUser(name);

        
        ResponseEntity<Boolean> response = usersController.deleteUser(name);

        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
