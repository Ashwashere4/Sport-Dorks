package com.estore.api.estoreapi.controller.Teams;

import java.util.HashMap;

import com.estore.api.estoreapi.model.Teams.Team;

public class League {
    /** A hashmap representation of the team roster where the key is the name
     * of the player and the value is the player object */
    HashMap<Integer, Team> league;

    /**
     * 
     * @param inventory
     */
    public League(HashMap<Integer, Team> league) {
        this.league = league;
    }
}
