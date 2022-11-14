package com.estore.api.estoreapi.persistence.League;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
class LeagueFileDAOtests {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String fileName = "data/league.json";
    
    @Test 
    void testLeagueFileDAO() throws IOException{

        LeagueFileDAO league = new LeagueFileDAO(fileName, objectMapper);
        ArrayList<Player> roster = new ArrayList<>();
        roster.add(new Player("Jordan", 17, 86));
        roster.add(new Player("Mike", 20, 55));
        roster.add(new Player("Aaron", 19, 99));
    
        ArrayList<Player> roster2 = new ArrayList<>();
        roster2.add(new Player("Ben", 19, 75));
        roster2.add(new Player("Kyle", 19, 67));
        roster2.add(new Player("Jamse", 18, 88));

        Team team1 = new Team(roster, 1);
        Team team2 = new Team(roster2, 2);
        league.createTeam(team1);
        league.createTeam(team2);

        assertEquals(league.searchLeague("1").length, 1);

        assertEquals(league.getTeams().length, 2);

        // Checks to see if nikes was deleted properly (15-1 = 14), since nike doesn't exist it returns null
        league.deleteTeam(1);
        assertEquals(league.getTeams().length, 1);
        assertEquals(league.getTeam(1), null);

        //Finally, checks to see if idkman is updated into the ultimate drip, with the quantity of 100, and the price of 10,000
        roster.add(new Player("Sam", 18, 4));
        league.updateTeam(team1, roster, 1);

        assertNotNull(league.getTeam(1).getTeam().contains(new Player("Sam", 18, 4)));
    
        league = new LeagueFileDAO(fileName, objectMapper);
        //tests create team class
        ArrayList<Player> roster3 = new ArrayList<>();
        roster3.add(new Player("Ben", 19, 75));
        roster3.add(new Player("Kyle", 19, 67));
        roster3.add(new Player("Jamse", 18, 88));

        league.createTeam(roster3, 3);

        assertEquals(league.searchLeague("3").length, 1);        
        assertNotNull(league.getTeam(3));
        assertNotNull(league.getTeams());

        //tests delete team class
        league.deleteTeam(3);
        assertNull(league.getTeam(3)); 
        assertNotEquals(league.searchLeague("3").length, 1);
    }
}
