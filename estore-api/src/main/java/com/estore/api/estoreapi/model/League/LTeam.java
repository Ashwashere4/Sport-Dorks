package com.estore.api.estoreapi.model.League;

import java.util.ArrayList;

import com.estore.api.estoreapi.model.Teams.Player;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LTeam {
    @JsonProperty("team_name") private String team_name;
    @JsonProperty("id") private int id;
    @JsonProperty("player_list") private ArrayList<Player> players;
    // @JsonProperty("name") private String name;

    /**
     * Constructor for an item object
     * @param team a hashmap with all the player objects, the name of the player is the key
     */
    public LTeam(@JsonProperty("team_name") String team_name, @JsonProperty("id") int id) {
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
