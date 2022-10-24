package com.estore.api.estoreapi.controller.Teams;

import java.util.HashMap;

import com.estore.api.estoreapi.model.Teams.Player;

public class Team {
    /** A hashmap representation of the inventory where the key is the name
     * of the item and the value is the item object */
    HashMap<String, Player> team;

    /**
     * 
     * @param inventory
     */
    public Team(HashMap<String, Player> team) {
        this.team = team;
    }
}
