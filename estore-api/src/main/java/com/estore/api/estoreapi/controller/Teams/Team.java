package com.estore.api.estoreapi.controller.Teams;

import java.util.ArrayList;

import com.estore.api.estoreapi.model.Teams.Player;

public class Team {
    /** A hashmap representation of the team roster where the key is the name
     * of the player and the value is the player object */
    ArrayList<Player> team;

    /**
     * 
     * @param inventory
     */
    public Team(ArrayList<Player> team) {
        this.team = team;
    }
}
