package com.estore.api.estoreapi.persistence.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.EstoreApiApplication;
import com.estore.api.estoreapi.model.Teams.Player;
import com.estore.api.estoreapi.persistence.Teams.TeamFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=EstoreApiApplication.class)
class TeamFileDAOTests {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String name = "data/team.json";
    
    @Test 
    void testTeamFileDAO() throws IOException{

        TeamFileDAO team = new TeamFileDAO(name, objectMapper);
        team.createPlayer("Jordan", 17, 86);
        team.createPlayer("Mike", 20, 55);
        team.createPlayer("Aaron", 19, 99);
        Player testPlayer = team.createPlayer("Kyle", 16, 75);

        // Checks to see if the item exists for jordans
        //assertEquals(team.searchTeam("Jordan").length, 1);

        // Checks to see if all the items were added properly (11 from original file, 4 from test = 15)
        //assertEquals(team.getPlayers().length, 15);

        // Checks to see if nikes was deleted properly (15-1 = 14), since nike doesn't exist it returns null
        team.deletePlayer("Aaron");
        //assertEquals(team.getPlayers().length, 15);
        assertEquals(team.getPlayer("Aaron"), null);

        //Finally, checks to see if idkman is updated into the ultimate drip, with the quantity of 100, and the price of 10,000
        team.updatePlayer(testPlayer, "Kyle", 16, 75);

        Player mike = team.getPlayer("Mike");

        assertNotNull(team.getPlayer("Mike"));
        assertEquals(mike.getAge(), 20);
        assertEquals(mike.getName(), "Mike");
        assertEquals(mike.getRating(), 55);

        team = new TeamFileDAO(name, objectMapper);
        //tests create player class
        team.createPlayer("Pablo", 19, 88);

        assertEquals(team.searchTeam("Pablo").length, 1);        
        assertNotNull(team.getPlayer("Pablo"));
        assertNotNull(team.getPlayers());

        //tests delete player class
        team.deletePlayer("Pablo");
        assertNull(team.getPlayer("Pablo")); 
        assertNotEquals(team.searchTeam("Pablo").length, 1);

        team.deletePlayer("Mike");
        team.deletePlayer("Jordan");
    }
    
}
