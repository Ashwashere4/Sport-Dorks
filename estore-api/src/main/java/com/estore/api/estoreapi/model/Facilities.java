package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Facilities {
    @JsonProperty("name") private String name;
    @JsonProperty("Team Code") private int team_code;
    @JsonProperty("Player Count") private int player_count;

    // @JsonProperty("name") private String name;

    /**
     * Constructor for an item object
     * @param name a label for the item as a string
     * @param quantity the amount of the item in stock as an int
     * @param cost the price of a product as an int
     */
    public Facilities(@JsonProperty("name") String name, @JsonProperty("Team Code") int team_code, @JsonProperty("Player Count") int player_count) {
        this.name = name;
        this.team_code = team_code;
        this.player_count = player_count;
    };

    /**
     * Accessor for the name of a team
     * @return the price of an item as an int
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor for the code of a team
     * @return the string representation of the object's label
     */
    public int getTeamcode() {
        return this.team_code;
    }

    /**
     * Accessor for the count of a team
     * @return the amount of that item in stock
     */
    public int getPlayerCount() {
        return this.player_count;
    }

    /**
     * Method to get the string representation of an item object
     */
    public String toString(){
        return ("Team Name = " + name + "\nTeam Code= " + team_code + "\nPlayers Joined = " + player_count);
    }
}

