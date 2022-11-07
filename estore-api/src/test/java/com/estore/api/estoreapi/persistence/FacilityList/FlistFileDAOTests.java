package com.estore.api.estoreapi.persistence.FacilityList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.EstoreApiApplication;
import com.estore.api.estoreapi.controller.leagues.League;
import com.estore.api.estoreapi.model.Facilities.Facilities;
import com.estore.api.estoreapi.model.League.Team;
import com.estore.api.estoreapi.model.Teams.Player;
import com.estore.api.estoreapi.persistence.FacilitiesList.FlistFileDAO;
import com.estore.api.estoreapi.persistence.League.LeagueFileDAO;
import com.estore.api.estoreapi.persistence.Teams.TeamFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=EstoreApiApplication.class)
public class FlistFileDAOTests {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String name = "data/flist.json";
    
    @Test 
    void testFlistfileDAO() throws IOException{

        FlistFileDAO flist = new FlistFileDAO(name, objectMapper);
        flist.createFacility("That one stadium", "Bronx", 001);
        Facilities dummy_test = flist.createFacility("something cool", "Bronx", 002);
        flist.createFacility("something boring", "Bronx", 003);

        // Checks to see if a facility exists for something (should return 3 because i am really original with names)
        assertEquals(flist.searchFacilities("something").length, 3);


        // Checks to see if all the faciltites  were added properly (4 stadiums)
        assertEquals(flist.getFacilities().length, 4);

        //deletes the one facility with a semi unique name because originally should be punished 
        flist.deleteFacility(001);
        assertEquals(flist.getFacilities().length, 3);
        assertEquals(flist.getFacility(001), null);

        //Since the facilities are now boycotting, something cool should become "The amazing stadium"
        flist.updateFacility(dummy_test, "The amazing stadium", dummy_test.getLocation(), dummy_test.getFacility_id());

        assertEquals(dummy_test.getName(), "The amazing stadium");
        assertEquals(dummy_test.getLocation(), "Bronx");
        assertEquals(dummy_test.getFacility_id(), 002);

        flist = new FlistFileDAO(name, objectMapper);
        
        LeagueFileDAO team = new LeagueFileDAO(name, objectMapper);
        com.estore.api.estoreapi.model.Teams.Team team1 = team.createTeam(null, 001);
        com.estore.api.estoreapi.model.Teams.Team team2 = team.createTeam(null, 002);

        // Some dude wants to reserve the Yankee Stadium for a more realistic practice

        Facilities test_facility = flist.getFacility(1);

        // reserve status should default to false always
        assertEquals(test_facility.getReservestatus(), false);

        // Jordan reserves the facility
        test_facility.addTeam_reserve(team1);

        //this becomes true
        assertEquals(test_facility.getReservestatus(), true);

        //this is false because jordan already reserved it
        assertEquals(test_facility.addTeam_reserve(team2), false);

        test_facility.removeTeam_reserve();

        //with the removal of the team reserve, it is now available
        assertEquals(test_facility.getReservestatus(), false);

    }
    
}
    

