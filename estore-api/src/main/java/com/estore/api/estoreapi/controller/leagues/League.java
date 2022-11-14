package com.estore.api.estoreapi.controller.leagues;

import java.util.ArrayList;

import com.estore.api.estoreapi.model.Teams.Team;

public class League {
    /** A hashmap representation of the team roster where the key is the name
     * of the player and the value is the player object */
    ArrayList<Team> league;

    /**
     * 
     * @param inventory
     */
    public League(ArrayList<Team> league) {
        this.league = league;
    }
}
