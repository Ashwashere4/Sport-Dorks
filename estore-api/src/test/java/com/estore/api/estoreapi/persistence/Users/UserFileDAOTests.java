package com.estore.api.estoreapi.persistence.Users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.model.Users.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=UserFileDAOTests.class)
class UserFileDAOTests {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String name = "data/user.json";
    
    @Test 
    void testUserFileDAO() throws IOException{

        UserFileDAO users = new UserFileDAO(name, objectMapper);
        users.deleteUser("user1");
        users.deleteUser("user2");
        users.deleteUser("user3");
        users.deleteUser("useridk");
        users.deleteUser("newuser");
        users.deleteUser("newuser2");
                
        int beforeTests = users.getUsers().length;
        users.creatUser("user1", "pass", false,false);
        users.creatUser("user2", "pass", false, false);
        users.creatUser("user3", "pass", false, false);
        User useridk = users.creatUser("useridk", "pass", false, false);

        // Checks to see if the item exists for user1
        assertEquals(users.getUser("user1").getUserName(), "user1");

        // Checks to see if all the users were added properly 
        assertEquals(users.getUsers().length, 4+beforeTests);

        // Checks to see if user2 was deleted properly
        users.deleteUser("user2");
        assertEquals(users.getUsers().length, 3+beforeTests);
        assertEquals(users.getUser("user2"), null);

        //Finally, checks to see if useridk is updated
        users.updateUser(useridk, "updateduser", "updatedpass", true, true);

        User expectedUser = users.getUser("updateduser");

        assertNotNull(users.getUser("updateduser"));
        assertEquals(expectedUser.getPass(), "updatedpass");
        assertEquals(expectedUser.getUserName(), "updateduser");
        assertEquals(expectedUser.isAdmin(), true);
        assertEquals(expectedUser.isTOwner(), true);

        users = new UserFileDAO(name, objectMapper);

        //tests create user class
        users.creatUser("newuser", "pass", false,false);
        User newuser2 = users.creatUser("newuser2", "name", false, false);

        users.creatUser(newuser2);

        assertEquals(users.getUser("newuser").getUserName(), "newuser");        
        assertNotNull(users.getUser("newuser"));
        assertEquals(users.getUser("newuser2").getUserName(), "newuser2");        
        assertNotNull(users.getUser("newuser2"));
        assertNotNull(users.getUsers());

        //tests delete item class
        users.deleteUser("newuser");
        assertNull(users.getUser("newuser")); 
    }
    
}
