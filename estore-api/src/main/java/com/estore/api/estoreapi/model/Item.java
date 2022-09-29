package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("quantity") private int quantity;

    // @JsonProperty("id") private int id;
    // @JsonProperty("name") private String name;

    /**
     * Constructor for an item object
     * @param id an identifying number for an item to make it easier to find
     * @param name a label for the item as a string
     * @param quantity the amount of the item in stock as an int
     */
    public Item( @JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("quantity") int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    };

    /**
     * Accessor for the id of an item
     * @return the identifying number for an item
     */
    public int getId() {
        return this.id;
    }

    /**
     * Accessor for the name of an item
     * @return the string representation of the object's label
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor for the quantity of an item
     * @return the amount of that item in stock
     */
    public int getQuantity() {
        return this.quantity;
    }

    public String toString(){
        return ("Product id = " + id + "\n Product name = " + name + " \n Product Quantity = " + quantity);
    }
}
