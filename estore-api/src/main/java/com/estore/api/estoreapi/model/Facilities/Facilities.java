package com.estore.api.estoreapi.model.Facilities;

import com.estore.api.estoreapi.model.Teams.Player;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)


public class Facilities {
    @JsonProperty("name") private String name;
    @JsonProperty("location") private String location;
    @JsonProperty("facility_id") private int facility_id;
    @JsonProperty("team_reserve") private Player team_reserve;

    /**
     * Constructor for an item object
     * @param name a label for the item as a string
     * @param quantity the amount of the item in stock as an int
     * @param cost the price of a product as an int
     */

    public Facilities(@JsonProperty("name") String name, @JsonProperty("location") String location, @JsonProperty("facility_id") int facility_id) {
        this.name = name;
        this.location = location;
        this.facility_id = facility_id;
        this.team_reserve = null;
    };

    /**
     * Accessor for the name of a team
     * @return the price of an item as an int
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor for the code of a team
     * @return the string representation of the object's label
     */
    public int getFacility_id() {
        return this.facility_id;
    }

    /**
     * Accessor for the count of a team
     * @return the amount of that item in stock
     */
    public String getLocation() {
        return this.location;
    }

    public Boolean getReservestatus(){
        return this.team_reserve != null;
    }

    public Player getTeam_reserve(){

        return this.team_reserve;
    }

    public Boolean removeTeam_reserve(){
        this.team_reserve = null;

        return true;
    }

    public Boolean addTeam_reserve(Player player){

        if (getReservestatus() == false){

            this.team_reserve = player;

            return true;
        }

        return false;
    }

    public void setName(String name){
        this.name = name;

    }

    public void setLocation(String location) {
        this.location = location;
        
    }

    public void setId(int id){
        this.facility_id = id;
    }
    /**
     * Method to get the string representation of an item object
     */
    public String toString(){
        return ("Facility Name = " + name + "\nFacility Location= " + location + "\nFacility Id = " + facility_id + "Reserved by: " + getReservestatus());
    }
}

