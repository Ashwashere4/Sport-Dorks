package com.estore.api.estoreapi.persistence.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.model.Teams.Player;
import com.estore.api.estoreapi.persistence.Teams.TeamFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=TeamFileDAOTests.class)
class TeamFileDAOTests {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String name = "data/team.json";
    
    @Test
    void testTeamFileDAO() throws IOException{

        Player[] players = new Player[4];
        TeamFileDAO team = new TeamFileDAO(name, objectMapper);
        int BeforeTest = team.getPlayers().length;
        Player jordan = new Player("Jordan", 17, 86);
        assertEquals(jordan, team.createPlayer(jordan));
        Player mike = new Player("Mike", 18, 72);
        assertEquals(mike, team.createPlayer(mike));
        Player aaron = new Player("Aaron", 19, 66);
        assertEquals(aaron, team.createPlayer(aaron));
        Player kyle = new Player("Kyle", 16, 70);
        assertEquals(kyle.getName(), team.createPlayer("Kyle", 16, 70).getName());
        assertNull(team.createPlayer(kyle));


        Player[] foundJordan = new Player[1];
        foundJordan[0] = jordan;

        assertNotNull(team.getPlayer("Jordan"));

        assertEquals(BeforeTest + 4, team.getPlayers().length);
        
        players = new Player[3];

        players[0] = jordan;
        players[1] = mike;
        players[2] = kyle;

        assertEquals(true, team.deletePlayer("Aaron"));
        assertEquals(BeforeTest + 3, team.getPlayers().length);
        assertEquals(null, team.getPlayer("Aaron"));
        assertEquals(false, team.deletePlayer("NotAPlayer"));

        assertEquals(75, team.updatePlayer(kyle, "Kyle", 16, 75).getRating());

        assertEquals(mike, team.getPlayer("Mike"));

        assertEquals(18, mike.getAge());
        assertEquals("Mike", mike.getName());
        assertEquals(72, mike.getRating());
        
        assertNotNull(team.searchTeam(""));
        assertNotNull(team.searchTeam("Mike"));
        assertNotNull(team.searchTeam("NotARealPlayer"));

        assertNull(team.updatePlayer(aaron, "Aaron", 19, 66));


        // Cleaning up
        team.deletePlayer("Jordan");
        team.deletePlayer("Mike");
        team.deletePlayer("Kyle");
        team.deletePlayer("Aaron");
    } 
}
