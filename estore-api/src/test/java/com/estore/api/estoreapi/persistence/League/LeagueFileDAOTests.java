package com.estore.api.estoreapi.persistence.League;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.EstoreApiApplication;
import com.estore.api.estoreapi.model.Teams.Player;
import com.estore.api.estoreapi.model.Teams.Team;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=EstoreApiApplication.class)
class LeagueFileDAOTests {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String fileName = "data/league.json";
    
    @Test 
    void testLeagueFileDAO() throws IOException {
        LeagueFileDAO league = new LeagueFileDAO(fileName, objectMapper);
        ArrayList<Player> roster = new ArrayList<>();
        roster.add(new Player("Jordan", 17, 86));
        roster.add(new Player("Mike", 20, 55));
        roster.add(new Player("Aaron", 19, 99));
    
        ArrayList<Player> roster2 = new ArrayList<>();
        roster2.add(new Player("Ben", 19, 75));
        roster2.add(new Player("Kyle", 19, 67));
        roster2.add(new Player("Jamse", 18, 88));

        Team team1 = new Team(roster, 5);
        Team team2 = new Team(roster2, 6);
        
        assertEquals(team1, league.createTeam(team1));
        assertEquals(team2.getTeam(), league.createTeam(roster2, 6).getTeam());

        assertEquals(1, league.searchLeague("1").length);

        assertEquals(6, league.getTeams().length);

        assertEquals(true, league.deleteTeam(6));
        assertEquals(5, league.getTeams().length);
        assertNull(league.getTeam(6));
        assertNotNull(league.searchLeague(""));

        roster.add(new Player("Sam", 18, 4));

        // Tests update team
        Team updatedTeam = new Team(roster, 5);
        assertEquals(updatedTeam.getTeam(), league.updateTeam(team1, roster, 5).getTeam());

        Team notRealTeam = new Team(roster, 90);
        assertNull(league.updateTeam(notRealTeam, roster, 90));
    
        ArrayList<Player> roster3 = new ArrayList<>();
        roster3.add(new Player("Ben", 19, 75));
        roster3.add(new Player("Kyle", 19, 67));
        roster3.add(new Player("Jamse", 18, 88));
        Team team3 = new Team(roster3, 7);
        assertEquals(team3, league.createTeam(team3));

        assertEquals(1, league.searchLeague("3").length);
        assertEquals(team3.getTeam(), league.getTeam(7).getTeam());

        //tests delete team class
        assertEquals(true, league.deleteTeam(5));
        assertEquals(true, league.deleteTeam(7));
        assertEquals(false, league.deleteTeam(7));
    }
}
