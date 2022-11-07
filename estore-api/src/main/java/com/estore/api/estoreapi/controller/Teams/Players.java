package com.estore.api.estoreapi.controller.Teams;

import java.util.HashMap;

import com.estore.api.estoreapi.model.Teams.Player;

public class Players {
    /** A hashmap representation of the team roster where the key is the name
     * of the player and the value is the player object */
    HashMap<String, Player> team;

    /**
     * 
     * @param inventory
     */
    public Players(HashMap<String, Player> team) {
        this.team = team;
    }
}
