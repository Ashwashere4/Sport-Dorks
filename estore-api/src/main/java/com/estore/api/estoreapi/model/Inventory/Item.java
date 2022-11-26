package com.estore.api.estoreapi.model.Inventory;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty("name") private String name;
    @JsonProperty("quantity") private int quantity;
    @JsonProperty("cost") private int cost;

    // @JsonProperty("name") private String name;

    /**
     * Constructor for an item object
     * @param name a label for the item as a string
     * @param quantity the amount of the item in stock as an int
     * @param cost the price of a product as an int
     */
    public Item(@JsonProperty("name") String name, @JsonProperty("quantity") int quantity, @JsonProperty("cost") int cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    };

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

    public void setName(String name){
        this.name = name;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String toString(){
        return ("Product name = " + name + "\nProduct Quantity = " + quantity + "\nProduct Price = " + cost);
    }
}
