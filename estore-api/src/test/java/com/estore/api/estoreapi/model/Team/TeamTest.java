package com.estore.api.estoreapi.model.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.Teams.Player;

@Tag("Model-tier")
public class TeamTest {


    @Test
    public void testCtor(){
        HashMap<String,Player> roster = new HashMap<>();

        int expected_age = 19;
        int expected_rating = 74;
        String expected_name = "Sam";

        Player player = new Player(expected_name,expected_age,expected_rating);

        int expected_age2 = 20;
        int expected_rating2 = 55;
        String expected_name2 = "Ben";

        Player player2 = new Player(expected_name2,expected_age2,expected_rating2);

        roster.put(player.getName(), player);
        roster.put(player2.getName(), player2);

        assertEquals(player, roster.get(expected_name));
        assertEquals(player2, roster.get(expected_name2));
        assertEquals(expected_name2, roster.get(expected_name2).getName());
        assertEquals(expected_age, roster.get(expected_name).getAge());
    }

    
}
