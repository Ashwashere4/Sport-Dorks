package com.estore.api.estoreapi.persistence.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Player[] players = new Player[4];
        TeamFileDAO team = mock(TeamFileDAO.class);
        Player jordan = new Player("Jordan", 17, 86);
        when(team.createPlayer(jordan)).thenReturn(jordan);
        assertEquals(jordan, team.createPlayer(jordan));
        Player mike = new Player("Mike", 18, 72);
        when(team.createPlayer(mike)).thenReturn(mike);
        team.createPlayer(mike);
        Player aaron = new Player("Aaron", 19, 66);
        when(team.createPlayer(aaron)).thenReturn(aaron);
        team.createPlayer(aaron);
        Player kyle = new Player("Kyle", 16, 70);
        when(team.createPlayer(kyle)).thenReturn(kyle);
        assertEquals(kyle, team.createPlayer(kyle));

        players[0] = jordan;
        players[1] = mike;
        players[2] = aaron;
        players[3] = kyle;

        Player[] foundJordan = new Player[1];
        foundJordan[0] = jordan;

        when(team.searchTeam("Jordan")).thenReturn(foundJordan);
        assertEquals(1, team.searchTeam("Jordan").length);


        when(team.getPlayers()).thenReturn(players);
        assertEquals(4, team.getPlayers().length);
        
        players = new Player[3];

        players[0] = jordan;
        players[1] = mike;
        players[2] = kyle;

        when(team.getPlayers()).thenReturn(players);
        when(team.deletePlayer("Aaron")).thenReturn(true);
        when(team.getPlayer("Aaron")).thenReturn(null);
        assertEquals(true, team.deletePlayer("Aaron"));
        assertEquals(3, team.getPlayers().length);
        assertEquals(null, team.getPlayer("Aaron"));

        Player updatedKyle = new Player("Kyle", 16, 75);
        when(team.getPlayer("Kyle")).thenReturn(kyle);
        when(team.updatePlayer(kyle, "Kyle", 16, 75)).thenReturn(updatedKyle);
        assertEquals(updatedKyle, team.updatePlayer(kyle, "Kyle", 16, 75));

        when(team.getPlayer("Mike")).thenReturn(mike);
        assertEquals(mike, team.getPlayer("Mike"));

        assertEquals(18, mike.getAge());
        assertEquals("Mike", mike.getName());
        assertEquals(72, mike.getRating());        
    } 
}
