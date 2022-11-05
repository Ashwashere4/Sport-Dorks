package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.controller.Teams.LeagueController;
import com.estore.api.estoreapi.model.Teams.Player;
import com.estore.api.estoreapi.model.Teams.Team;
import com.estore.api.estoreapi.persistence.Teams.LeagueDAO;

public class LeagueControllerTest {

    private LeagueController leagueController;
    private LeagueDAO mockLeagueDAO;



    @BeforeEach
    public void setupTeamController(){
        mockLeagueDAO = mock(LeagueDAO.class);
        leagueController = new LeagueController(mockLeagueDAO);
        
    }

    @Test
    public void testGetTeam() throws IOException{
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

        Team team = new Team(roster,50);

        when(mockLeagueDAO.getTeam(team.getId())).thenReturn(team);

        ResponseEntity<Team> response = leagueController.getTeam(team.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(team, response.getBody());
    }

    @Test
    public void testGetTeamNotFound() throws Exception {
        when(mockLeagueDAO.getTeam(99)).thenReturn(null);

        ResponseEntity<Team> response = leagueController.getTeam(99);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void testGetTeamHandleException() throws Exception{
        doThrow(new IOException()).when(mockLeagueDAO).getTeam(-1);

        ResponseEntity<Team> response = leagueController.getTeam(-1);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void testCreateTeam() throws IOException{
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

        Team team = new Team(roster, 51);

        when(mockLeagueDAO.createTeam(roster, 51)).thenReturn(team);

        ResponseEntity<Team> response = leagueController.createTeam(team);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(team, response.getBody());
    }   

    @Test
    public void testCreateTeamFailed() throws IOException{
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

        when(mockLeagueDAO.createTeam(roster, 1)).thenReturn(null);
        
        Team team = new Team(roster, 1);

        ResponseEntity<Team> response = leagueController.createTeam(team);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateTeamHandleException() throws IOException{
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

        when(mockLeagueDAO.createTeam(roster, 1)).thenReturn(null);
        
        Team team = new Team(roster, 1);

        doThrow(new IOException()).when(mockLeagueDAO).createTeam(team);

        ResponseEntity<Team> response = leagueController.createTeam(team);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    public void testUpdatePlayer() throws IOException {

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
        
        Team team = new Team(roster, 52);

        Player new_player = new Player("Josh", 18, 55);
        roster.put("Josh", new_player);

        ResponseEntity<Team> response = leagueController.updateTeam(team, roster, 52);
        
        response = leagueController.updateTeam(team, roster, 52);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }   

    @Test 
    public void testUpdateTeamHandleException() throws IOException{
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
        
        Team team = new Team(roster, 98);

        Player new_player = new Player("Josh", 18, 55);
        roster.put("Josh", new_player);

        doThrow(new IOException()).when(mockLeagueDAO).updateTeam(team, roster, 98);

        ResponseEntity<Team> response = leagueController.updateTeam(team, roster, 98);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testGetTeams() throws IOException{
        HashMap<String,Player> roster = new HashMap<>();
        HashMap<String,Player> roster2 = new HashMap<>();

        roster.put("Ben",new Player("Ben",18,78));
        roster.put("Sam",new Player("Sam",20,53));
        roster.put("Josh",new Player("Josh",19,82));

        roster2.put("Brandon",new Player("Brandon",19,91));
        roster2.put("Alex",new Player("Alex",18,55));
        roster2.put("Jeremy",new Player("Jeremy",19,73));

        Team[] teams = new Team[2];
        teams[0] = new Team(roster, 1);
        teams[1] = new Team(roster, 2);

        when(mockLeagueDAO.getTeams()).thenReturn(teams);

        ResponseEntity<Team[]> response = leagueController.getTeams();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teams, response.getBody());
    }

    @Test
    public void testGetTeamsHandleException() throws IOException{
        doThrow(new IOException()).when(mockLeagueDAO).getTeams();

        ResponseEntity<Team[]> response = leagueController.getTeams();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testSearchTeams() throws IOException{
        HashMap<String,Player> roster = new HashMap<>();
        HashMap<String,Player> roster2 = new HashMap<>();

        roster.put("Ben",new Player("Ben",18,78));
        roster.put("Sam",new Player("Sam",20,53));
        roster.put("Josh",new Player("Josh",19,82));

        roster2.put("Brandon",new Player("Brandon",19,91));
        roster2.put("Alex",new Player("Alex",18,55));
        roster2.put("Jeremy",new Player("Jeremy",19,73));

        Team[] teams = new Team[2];
        teams[0] = new Team(roster, 1);
        teams[1] = new Team(roster, 10);

        when(mockLeagueDAO.searchLeague("1")).thenReturn(teams);

        ResponseEntity<Team[]> response = leagueController.searchLeague("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teams, response.getBody());
    }

    @Test
    public void testSearchTeamsHandleException() throws IOException { 
       
        String searchString = "Team";
       
        doThrow(new IOException()).when(mockLeagueDAO).searchLeague(searchString);

        
        ResponseEntity<Team[]> response = leagueController.searchLeague(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteTeams() throws IOException { 
        when(mockLeagueDAO.deleteTeam(1)).thenReturn(true);

     
        ResponseEntity<Boolean> response = leagueController.deleteTeam(1);

  
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteTeamNotFound() throws IOException {        
        when(mockLeagueDAO.deleteTeam(100)).thenReturn(false);

        
        ResponseEntity<Boolean> response = leagueController.deleteTeam(100);

        
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteTeamHandleException() throws IOException { 
        doThrow(new IOException()).when(mockLeagueDAO).deleteTeam(30);

        
        ResponseEntity<Boolean> response = leagueController.deleteTeam(30);

        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}