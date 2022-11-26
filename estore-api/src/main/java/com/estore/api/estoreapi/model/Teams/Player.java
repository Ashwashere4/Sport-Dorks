package com.estore.api.estoreapi.model.Teams;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    @JsonProperty("name")
    public String name;
    @JsonProperty("age")
    public int age;
    @JsonProperty("rating")
    public int rating;

    // @JsonProperty("name") private String name;

    /**
     * Constructor for an item object
     * @param name a label for the item as a string
     * @param quantity the amount of the item in stock as an int
     * @param cost the price of a product as an int
     */
    public Player(@JsonProperty("name") String name, @JsonProperty("age") int age, @JsonProperty("rating") int rating) {
        this.name = name;
        this.age = age;
        this.rating = rating;
    };

    /**
     * Accessor for the cost of a product
     * @return the price of an item as an int
     */
    public int getAge() {
        return this.age;
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
    public int getRating() {
        return this.rating;
    }

    /**
     * Method to get the string representation of an item object
     */
    public String toString(){
        return ("Player's name = " + name + "\nPlayer's age = " + age + "\nPlayer's rating = " + rating);
    }
}
