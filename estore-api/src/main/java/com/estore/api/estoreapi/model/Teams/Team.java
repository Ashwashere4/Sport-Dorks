package com.estore.api.estoreapi.model.Teams;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Team {
    @JsonProperty("team") private ArrayList<Player> team;
    @JsonProperty("id") private int id;

    // @JsonProperty("name") private String name;

    /**
     * Constructor for an item object
     * @param team a hashmap with all the player objects, the name of the player is the key
     */
    public Team(@JsonProperty("team") ArrayList<Player> team, @JsonProperty("id") int id) {
        this.team = team;
        this.id = id;
    };

    /**
     * Accessor for the team roster
     * @return the price of an item as an int
     */
    public ArrayList<Player> getTeam() {
        return this.team;
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
        String team = "";
        for(Player player : this.team) {
            team += player.toString();
        }
        return team;
    }
}
