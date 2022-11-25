package com.estore.api.estoreapi.persistence.League;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Teams.Player;
import com.estore.api.estoreapi.model.Teams.Team;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LeagueFileDAO implements LeagueDAO{
    /**
     * The current league team list.
     */
    private ArrayList<Team> league;

    /**
     * The file name of the league file.
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
        return new ArrayList<Team>(league);
    }

    @Override
    public Team[] getTeams() throws IOException {
        return getLeagueArray().toArray(new Team[0]);
    }


    @Override 
    public Team getTeam(int id) throws IOException{
        for(Team team: league) {
            if (team.getId() == id) {
                return team;
            }
        }
        return null;
    }

    @Override
    public Team createTeam(Team team) throws IOException {
        league.add(team);
        saveLeague();
        return team;
    }

    @Override
    public Team createTeam(ArrayList<Player> roster, int id) throws IOException {
        Team newTeam = new Team(roster, id);
        league.add(newTeam);
        saveLeague();
        return newTeam;
    }

    @Override
    public boolean deleteTeam(int id) throws IOException{
        for(Team team: league) {
            if(team.getId() == id) {
                league.remove(team);
                saveLeague();
                return true;
            }
        }
        return false;
    }


    @Override
    public Team[] searchLeague(String text) throws IOException {
        if (text.length() == 0)
            return new Team[0];

        ArrayList<Team> teams = new ArrayList<>();
        for (Team team : league) {
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
    public Team updateTeam(Team team, ArrayList<Player> roster, int id) throws IOException {
        Team newTeam = new Team(roster, id);
        if(getTeam(team.getId()) != null) {
            deleteTeam(team.getId());
            createTeam(roster, id);
            return newTeam;
        }
        else {
            return null;
        }
    }

    /**
     * Load the inventory from the file.
     */
    private void loadLeague() throws IOException {
        league = new ArrayList<Team>();
        Team[] leagueArray = objectMapper.readValue(new File(filename), Team[].class);
        for (Team team : leagueArray) {
            league.add(team);
            nextId();
        }
    }
}
