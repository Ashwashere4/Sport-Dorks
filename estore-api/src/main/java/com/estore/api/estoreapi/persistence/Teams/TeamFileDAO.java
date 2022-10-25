package com.estore.api.estoreapi.persistence.Teams;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Teams.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TeamFileDAO implements TeamDAO {
     /**
     * The current team roster.
     */
    private Map<String, Player> team;

    /**
     * The file name of the inventory file.
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
        return new ArrayList<>(team.values());
    }

    @Override
    public Player[] getPlayers() throws IOException {

        return getTeamArray().toArray(new Player[0]);
    }


    @Override 
    public Player getPlayer(String name) throws IOException{
        Player player = team.get(name);
            if (player != null)
                return player;
            else
                System.out.println("Item does not exist.");
                return null;
    }

    @Override
    public Player createPlayer(Player player) throws IOException {
        Player newPlayer = new Player(player.getName(), player.getAge(), player.getRating());
        team.put(player.getName(), newPlayer);
        saveTeam();
        return newPlayer;
    }

    @Override
    public Player createPlayer(String name, int age, int rating) throws IOException {
        Player newPlayer = new Player(name, age, rating);
        team.put(name, newPlayer);
        saveTeam();
        return newPlayer;
    }

    @Override
    public boolean deletePlayer(String name) throws IOException{
            team.remove(name);
            return true;
        }

    
    @Override
    public Player[] searchTeam(String text) throws IOException {
        if (text.length() == 0)
            return new Player[0];

        ArrayList<Player> players = new ArrayList<>();
        for (Player player : team.values()) {
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
        Player newPlayer = new Player(name, age, rating);
        team.put(newPlayer.getName(), newPlayer);
        return newPlayer;
    }


    
    /**
     * Load the inventory from the file.
     */
    private void loadTeam() throws IOException {
        team = new TreeMap<>();
        Player[] teamArray = objectMapper.readValue(new File(filename), Player[].class);
        for (Player player : teamArray) {
            team.put(player.getName(), player);
        }
    }
}
