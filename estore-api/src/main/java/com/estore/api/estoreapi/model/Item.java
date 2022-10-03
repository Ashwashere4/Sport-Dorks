package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty("name") private String name;
    @JsonProperty("quantity") private int quantity;
    @JsonProperty("cost") private int cost;
    @JsonProperty("id") private int id;

    // @JsonProperty("id") private int id;
    // @JsonProperty("name") private String name;

    /**
     * Constructor for an item object
     * @param name a label for the item as a string
     * @param quantity the amount of the item in stock as an int
     * @param cost the price of a product as an int
     */
    public Item(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("quantity") int quantity, @JsonProperty("cost") int cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    };

    public int getID() {
        return this.id;
    }

    /**
     * Accessor for the cost of a product
     * @return the price of an item as an int
     */
    public int getCost() {
        return this.cost;
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

    /**
     * Method to get the string representation of an item object
     */
    public String toString(){
        return ("Product name = " + name + "\nProduct Quantity = " + quantity + "\nProduct Price = " + cost);
    }
}
