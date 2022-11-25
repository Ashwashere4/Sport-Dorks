package com.estore.api.estoreapi.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.Users.User;

@Tag("Model-tier")
public class UserTest {


    @Test
    public void testCtor(){

        Boolean expected_owner = false ;
        Boolean expected_admin = true;
        String expected_pass = "password10000";
        String expected_name = "UserName 10000";
        String expected_toString = "username = UserName 10000" + "\n" + 
        "Password = password10000" + "\n" +
        "is an admin" + "\n" +
        "is not an owner";

        User user = new User(expected_name,expected_pass,expected_admin,expected_owner);

        assertEquals(expected_name, user.getUserName());
        assertEquals(expected_pass, user.getPass());
        assertEquals(expected_admin, user.isAdmin());
        assertEquals(expected_owner, user.isTOwner());
        assertEquals(expected_toString, user.toString());

        User owner = new User(expected_name,expected_pass,false,true);

        String expected_toStringTwo = "username = UserName 10000" + "\n" + 
        "Password = password10000" + "\n" +
        "is not admin" + "\n" +
        "is a team owner";

        assertEquals(expected_toStringTwo, owner.toString());
    }

    
}
