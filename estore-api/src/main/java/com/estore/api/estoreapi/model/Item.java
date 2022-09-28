package com.estore.api.estoreapi.model;

public class Item {
    int itemId;
    String itemName;
    int quantity; 

    /**
     * Constructor for an item object
     * @param id an identifying number for an item to make it easier to find
     * @param name a label for the item as a string
     * @param quantity the amount of the item in stock as an int
     */
    public Item(int id, String name, int quantity) {
        this.itemId = id;
        this.itemName = name;
        this.quantity = quantity;
    };

    /**
     * Accessor for the id of an item
     * @return the identifying number for an item
     */
    public int getId() {
        return this.itemId;
    }

    /**
     * Accessor for the name of an item
     * @return the string representation of the object's label
     */
    public String getName() {
        return this.itemName;
    }

    /**
     * Accessor for the quantity of an item
     * @return the amount of that item in stock
     */
    public int getQuantity() {
        return this.quantity;
    }
}
