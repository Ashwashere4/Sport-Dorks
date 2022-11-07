package com.estore.api.estoreapi.model.League;

import java.util.ArrayList;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Team {
    @JsonProperty("team_name") private String team_name;
    @JsonProperty("id") private int id;
    @JsonProperty("player_list") private ArrayList<Player> players;
    @JsonProperty("wins") private int wins;
    @JsonProperty("loses") private int loses;
    @JsonProperty("record") private ArrayList<String> record;
    // @JsonProperty("name") private String name;

    /**
     * Constructor for an item object
     * @param team a hashmap with all the player objects, the name of the player is the key
     */
    public Team(@JsonProperty("team_name") String team_name, @JsonProperty("id") int id) {
        this.team_name = team_name;
        this.id = id;
        this.players = new ArrayList<Player>();
    };


    /**
     * Accessor for the team roster
     * @return the price of an item as an int
     */
    public ArrayList<Player> getTeam() {
        return this.players;
    }

    public String getName(){
        return this.team_name;
    }

    public int getWins(){
        return this.wins;

    }

    public int getLoses(){
        return this.loses;
    }

    public void startCMatch(Team otherTeam, int outcome){
        if (outcome == 0){
            this.wins +=1;
            otherTeam.loses +=1;
            this.record.add(team_name + "won against " + otherTeam.team_name);
            otherTeam.record.add(otherTeam + "lost against " + team_name);
            
        }

        else{
            this.loses +=1;
            otherTeam.wins +=1;
            otherTeam.record.add(otherTeam + "won against " + this.team_name);
            this.record.add(team_name + "lost against " + otherTeam.team_name);
        }
    }

    public void startRMatch(Team otherTeam){
        Random rand = new Random();
        int outcome = rand.nextInt(2);

        startCMatch(otherTeam, outcome);
    }

    /**
     * Accessor for the id of a team
     * @return the number id of a team
     */
    public int getId() {
        return this.id;
    }


    /**
     * Method to get the string representation of an item object
     */
    public String toString(){
        return players.toString();
    }
}
