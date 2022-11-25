package com.estore.api.estoreapi.model.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.Teams.Team;
import com.estore.api.estoreapi.model.Teams.Player;

@Tag("Model-tier")
public class TeamTest {


    @Test
    public void testCtor(){
        ArrayList<Player> roster = new ArrayList<Player>();

        int expected_age = 19;
        int expected_rating = 74;
        String expected_name = "Sam";

        Player player = new Player(expected_name,expected_age,expected_rating);

        int expected_age2 = 20;
        int expected_rating2 = 55;
        String expected_name2 = "Ben";

        Player player2 = new Player(expected_name2,expected_age2,expected_rating2);

        roster.add(player);
        roster.add(player2);

        Team team = new Team(roster, 50);

        assertEquals(player, roster.get(0));
        assertEquals(player2, roster.get(1));
        assertEquals(expected_name2, roster.get(1).getName());
        assertEquals(expected_age, roster.get(0).getAge());
        assertEquals(roster, team.getTeam());
    }

    
}
