package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.Teams.Player;

@Tag("Model-tier")
public class PlayerTest {
    @Test
    public void testCtor(){

        int expected_age = 19;
        int expected_rating = 74;
        String expected_name = "Sam";

        Player player = new Player(expected_name,expected_age,expected_rating);

        assertEquals(expected_name, player.getName());
        assertEquals(expected_age, player.getAge());
        assertEquals(expected_rating, player.getRating());
    }

    
}
