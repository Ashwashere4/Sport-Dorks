package com.estore.api.estoreapi.persistence.Teams;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Teams.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TeamFileDAO implements TeamDAO {
    /**
     * The current team roster.
     */
    private ArrayList<Player> team;

    /**
     * The file name of the team file.
     */
    private String filename;

    /**
     * The object mapper.
     */

    private ObjectMapper objectMapper;

    /**
     * 
     * @param inventory
     */
    public TeamFileDAO(@Value("${team.filename}") String filename, ObjectMapper objectMapper) throws IOException {
            this.filename = filename;
            this.objectMapper = objectMapper;
            loadTeam();
        }

    private ArrayList<Player> getTeamArray() {
        return new ArrayList<>(team);
    }

    @Override
    public Player[] getPlayers() throws IOException {

        return getTeamArray().toArray(new Player[0]);
    }


    @Override 
    public Player getPlayer(String name) throws IOException{
        for(Player player : team) {
            if(player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public Player createPlayer(Player player) throws IOException {
        for(Player aPlayer: team) {
            if(aPlayer.getName().equals(player.getName())) {
                return null;
            }
        }
        team.add(player);
        saveTeam();
        return player;
    }

    @Override
    public Player createPlayer(String name, int age, int rating) throws IOException {
        Player newPlayer = new Player(name, age, rating);
        team.add(newPlayer);
        saveTeam();
        return newPlayer;
    }

    @Override
    public boolean deletePlayer(String name) throws IOException{
        for(Player player: team) {    
            if(player.getName().equals(name)) {
                team.remove(player);
                saveTeam();
                return true;
            } 
        }
        return false;
    }

    
    @Override
    public Player[] searchTeam(String text) throws IOException {
        if (text.length() == 0)
            return new Player[0];

        ArrayList<Player> players = new ArrayList<>();
        for (Player player : team) {
            if (player.getName().toLowerCase().contains(text.toLowerCase())) {
                players.add(player);
            }
        }

        return players.toArray(new Player[0]);
    }
    
    private void saveTeam() throws IOException {
        objectMapper.writeValue(new File(filename), getTeamArray());
    }

    @Override
    public Player updatePlayer(Player player, String name, int age, int rating) throws IOException {
        for(Player aPlayer: team) {
            if(aPlayer.getName().equals(player.getName())) {
                aPlayer.name = name;
                aPlayer.age = age;
                aPlayer.rating = rating;
                this.saveTeam();
                team.set(team.indexOf(aPlayer), aPlayer);
                return aPlayer;
            }
        }
        return null;
    }


    
    /**
     * Load the inventory from the file.
     */
    private void loadTeam() throws IOException {
        team = new ArrayList<Player>();
        Player[] teamArray = objectMapper.readValue(new File(filename), Player[].class);
        for (Player player : teamArray) {
            team.add(player);
        }
    }
}
