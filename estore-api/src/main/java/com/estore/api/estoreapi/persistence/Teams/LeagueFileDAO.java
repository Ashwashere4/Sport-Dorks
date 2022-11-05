package com.estore.api.estoreapi.persistence.Teams;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;

import com.estore.api.estoreapi.model.Teams.Player;
import com.estore.api.estoreapi.model.Teams.Team;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LeagueFileDAO implements LeagueDAO{
    /**
     * The current team roster.
     */
    private Map<Integer, Team> league;

    /**
     * The file name of the inventory file.
     */
    private String filename;

    /**
     * The object mapper.
     */

    private ObjectMapper objectMapper;
    
    private static int nextId;

    /**
     * Generates the next id for a new {@linkplain Team team}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * 
     * @param inventory
     */
    public LeagueFileDAO(@Value("${league.filename}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        loadLeague();
    }

    private ArrayList<Team> getLeagueArray() {
        return new ArrayList<Team>(league.values());
    }

    @Override
    public Team[] getTeams() throws IOException {
        return getLeagueArray().toArray(new Team[0]);
    }


    @Override 
    public Team getTeam(int id) throws IOException{
        Team team = league.get(id);
            if (team != null)
                return team;
            else
                System.out.println("Team does not exist.");
                return null;
    }

    @Override
    public Team createTeam(Team team) throws IOException {
        Team newTeam = new Team(team.getTeam(), team.getId());
        league.put(team.getId(), newTeam);
        saveLeague();
        return newTeam;
    }

    @Override
    public Team createTeam(HashMap<String,Player> roster, int id) throws IOException {
        Team newTeam = new Team(roster, nextId());
        league.put(newTeam.getId(), newTeam);
        saveLeague();
        return newTeam;
    }

    @Override
    public boolean deleteTeam(int id) throws IOException{
            if(league.containsKey(id)) {
                league.remove(id);
                saveLeague();
                return true;
            } else {
                return false;
            }
        }


    @Override
    public Team[] searchLeague(String text) throws IOException {
        if (text.length() == 0)
            return new Team[0];

        ArrayList<Team> teams = new ArrayList<>();
        for (Team team : league.values()) {
            if (String.valueOf(team.getId()).contains(text)) {
                teams.add(team);
            }
        }
        return teams.toArray(new Team[0]);
    }

    private void saveLeague() throws IOException {
        objectMapper.writeValue(new File(filename), getLeagueArray());
    }

    @Override
    public Team updateTeam(Team team, HashMap<String,Player> roster, int id) throws IOException {
        Team newTeam = new Team(roster, team.getId());
        league.put(newTeam.getId(), newTeam);
        return newTeam;
    }

    /**
     * Load the inventory from the file.
     */
    private void loadLeague() throws IOException {
        league = new HashMap<>();
        Team[] leagueArray = objectMapper.readValue(new File(filename), Team[].class);
        for (Team team : leagueArray) {
            league.put(nextId(), team);
        }
    }
}
