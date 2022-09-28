package com.estore.api.estoreapi.controller;

import java.util.HashMap;

public class Inventory {
    /** A hashmap representation of the inventory where the key is the name
     * of the item and the value is the item object */
    HashMap<String, Item> inventory;

    /**
     * 
     * @param inventory
     */
    public Inventory(HashMap<String, Item> inventory) {
        this.inventory = inventory;
    }
}