package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.controller.Teams.TeamController;
import com.estore.api.estoreapi.model.Teams.Player;
import com.estore.api.estoreapi.persistence.Teams.TeamDAO;

public class TeamControllerTest {

    private TeamController teamController;
    private TeamDAO mockTeamDAO;



    @BeforeEach
    public void setupTeamController(){
        mockTeamDAO = mock(TeamDAO.class);
        teamController = new TeamController(mockTeamDAO);
        
    }

    @Test
    public void testGetPlayer() throws IOException{
        Player player = new Player("Micheal", 19, 88);

        when(mockTeamDAO.getPlayer(player.getName())).thenReturn(player);

        ResponseEntity<Player> response = teamController.getPlayer(player.getName());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(player, response.getBody());
    }

    @Test
    public void testGetPlayerNotFound() throws Exception {
        String name = "Micheal";

        when(mockTeamDAO.getPlayer(name)).thenReturn(null);

        ResponseEntity<Player> response = teamController.getPlayer(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void testGetPlayerHandleException() throws Exception{
        String name = "Micheal";

        doThrow(new IOException()).when(mockTeamDAO).getPlayer("Micheal");

        ResponseEntity<Player> response = teamController.getPlayer(name);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void testCreatePlayer() throws IOException{

        Player player = new Player("Micheal", 19, 88);

        when(mockTeamDAO.createPlayer(player)).thenReturn(player);

        ResponseEntity<Player> response = teamController.createPlayer(player);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(player, response.getBody());

    }   

    @Test
    public void testCreatePlayerFailed() throws IOException{
        Player player = new Player("Jays", 0, 0);

        when(mockTeamDAO.createPlayer(player)).thenReturn(null);

        ResponseEntity<Player> response = teamController.createPlayer(player);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreatePlayerHandleException() throws IOException{
        Player player = new Player("Jays", 0, 0);

        doThrow(new IOException()).when(mockTeamDAO).createPlayer(player);

        ResponseEntity<Player> response = teamController.createPlayer(player);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    // @Test
    // public void testUpdatePlayer() throws IOException {

    //     Player player = new Player("Jays", 0, 0);

    //     when(mockTeamDAO.updatePlayer(player, "Nike", 0, 0)).thenReturn(player);

    //     ResponseEntity<Player> response = teamController.updatePlayer(player, "Nike", 0, 0);
        
    //     response = teamController.updatePlayer(player, "Nike", 0, 0);

    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertEquals(player, response.getBody());
    // }   

    // @Test 
    // public void testUpdatePlayerHandleException() throws IOException{
    //     Player player = new Player("jays", 0, 0);

    //     doThrow(new IOException()).when(mockTeamDAO).updatePlayer(player, "Nike", 0, 0);

    //     ResponseEntity<Player> response = teamController.updatePlayer(player, "Nike", 0, 0);

    //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    // }

    @Test
    public void testGetPlayers() throws IOException{
        Player[] players = new Player[2];
        players[0] = new Player("jays", 0, 0);
        players[1] = new Player("Nike", 0, 0);

        when(mockTeamDAO.getPlayers()).thenReturn(players);

        ResponseEntity<Player[]> response = teamController.getPlayers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(players, response.getBody());
    }

    @Test
    public void testGetPlayersHandleException() throws IOException{
        doThrow(new IOException()).when(mockTeamDAO).getPlayers();

        ResponseEntity<Player[]> response = teamController.getPlayers();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testSearchPlayers() throws IOException{
        String name = "Jays";
        Player[] players = new Player[2];
        players[0] = new Player("Jays", 0, 0);
        players[1] = new Player("Nikes",0,0);

        when(mockTeamDAO.searchTeam(name)).thenReturn(players);

        ResponseEntity<Player[]> response = teamController.searchTeam(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(players, response.getBody());
    }

    @Test
    public void testSearchPlayersHandleException() throws IOException { 
       
        String searchString = "Jays";
       
        doThrow(new IOException()).when(mockTeamDAO).searchTeam(searchString);

        
        ResponseEntity<Player[]> response = teamController.searchTeam(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteItems() throws IOException { 
        
        String name = "Jays";
        
        when(mockTeamDAO.deletePlayer(name)).thenReturn(true);

     
        ResponseEntity<Boolean> response = teamController.deletePlayer(name);

  
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeletePlayerNotFound() throws IOException { 
       
        String name = "Jays";
       
        when(mockTeamDAO.deletePlayer(name)).thenReturn(false);

        
        ResponseEntity<Boolean> response = teamController.deletePlayer(name);

        
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeletePlayerHandleException() throws IOException { 
        
        String name = "Jays";
        
        doThrow(new IOException()).when(mockTeamDAO).deletePlayer(name);

        
        ResponseEntity<Boolean> response = teamController.deletePlayer(name);

        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}

