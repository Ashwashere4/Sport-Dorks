package com.estore.api.estoreapi.persistence.League;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
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
        
        when(league.createTeam(team1)).thenReturn(team1);
        when(league.createTeam(team2)).thenReturn(team2);

        league.createTeam(team1);
        league.createTeam(team2);

        ArrayList<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        assertEquals(league.searchLeague("1").length, 1);

        assertEquals(league.getTeams().length, 2);

        when(league.deleteTeam(1)).thenReturn(true);
        assertEquals(true, league.deleteTeam(1));
        assertEquals(1, league.getTeams().length);
        assertNull(league.getTeam(1));

        roster.add(new Player("Sam", 18, 4));

        // Tests update team
        Team updatedTeam = new Team(roster, 1);
        when(league.getTeam(1)).thenReturn(team1);
        when(league.updateTeam(team1, roster, 1)).thenReturn(updatedTeam);
        assertEquals(updatedTeam, league.updateTeam(team1, roster, 1));

        when(league.getTeam(90)).thenReturn(team1);
        when(league.updateTeam(team1, roster, 90)).thenThrow(new IOException());
        assertNull(league.updateTeam(team1, roster, 90));

        when(league.getTeam(1).getTeam()).thenReturn(roster);
        assertEquals(true, league.getTeam(1).getTeam().contains(new Player("Sam", 18, 4)));
    
        ArrayList<Player> roster3 = new ArrayList<>();
        roster3.add(new Player("Ben", 19, 75));
        roster3.add(new Player("Kyle", 19, 67));
        roster3.add(new Player("Jamse", 18, 88));
        Team team3 = new Team(roster3, 3);
        teams.add(team3);
        when(league.createTeam(roster3, 3)).thenReturn(team3);
        assertEquals(team3, league.createTeam(roster3, 3));

        assertEquals(league.searchLeague("3").length, 1);
        when(league.getTeam(3)).thenReturn(team3);        
        assertEquals(team3, league.getTeam(3));

        //tests delete team class
        when(league.deleteTeam(3)).thenReturn(true);
        assertEquals(true, league.deleteTeam(3));
        when(league.getTeam(3)).thenReturn(null);
        assertNull(league.getTeam(3)); 
        assertNotEquals(league.searchLeague("3").length, 1);

        //tests getLeagueArray()
        assertEquals(teams.toArray(), league.getTeams());
    }
}
