package com.estore.api.estoreapi.persistence.FacilityList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.model.Teams.Player;
import com.estore.api.estoreapi.model.facilities.Facilities;
import com.estore.api.estoreapi.persistence.FacilitiesList.FlistFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=FlistFileDAOTests.class)
public class FlistFileDAOTests {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String name = "data/flist.json";
    String name1 = "data/league.json";
    
    @Test 
    void testFlistfileDAO() throws IOException{

        FlistFileDAO flist = new FlistFileDAO(name, objectMapper);

        flist.deleteFacility(001);
        flist.deleteFacility(002);
        flist.deleteFacility(003);
        int beforeTests = flist.getFacilities().length;

        flist.createFacility("That one stadium", "Bronx", 001);
        Facilities dummy_test = flist.createFacility("something cool", "Bronx", 002);
        flist.createFacility("something boring", "Bronx", 003);

        // Checks to see if a facility exists for something (should return 3 because i am really original with names)
        assertEquals(flist.searchFacilities("something").length, 2);


        // Checks to see if all the faciltites  were added properly (3 stadiums)
        assertEquals(flist.getFacilities().length, beforeTests + 3);

        //deletes the one facility with a semi unique name because originally should be punished 
        flist.deleteFacility(001);
        assertEquals(flist.getFacilities().length, beforeTests + 2);
        assertEquals(flist.getFacility(001), null);

        //Since the facilities are now boycotting, something cool should become "The amazing stadium"
        flist.updateFacility(dummy_test, "The amazing stadium", dummy_test.getLocation(), dummy_test.getFacility_id());

        assertEquals(dummy_test.getName(), "The amazing stadium");
        assertEquals(dummy_test.getLocation(), "Bronx");
        assertEquals(dummy_test.getFacility_id(), 002);

        flist = new FlistFileDAO(name, objectMapper);
        
        // LeagueFileDAO team = new LeagueFileDAO(name1, objectMapper);
        ArrayList<Player> roster = new ArrayList<>();
        roster.add(new Player("Jordan", 17, 86));
        roster.add(new Player("Mike", 20, 55));
        roster.add(new Player("Aaron", 19, 99));
    
        ArrayList<Player> roster2 = new ArrayList<>();
        roster2.add(new Player("Ben", 19, 75));
        roster2.add(new Player("Kyle", 19, 67));
        roster2.add(new Player("Jamse", 18, 88));

        // Team team1 = new Team(roster, 0);
        // Team team2 = new Team(roster2, 1);

        // // Some dude wants to reserve the Yankee Stadium for a more realistic practice

        // Facilities test_facility = flist.getFacility(1);

        // // reserve status should default to false always
        // assertEquals(test_facility.getTeamReserve(), false);

        // // Jordan reserves the facility
        // test_facility.addTeamReserve(team1);

        // //this becomes true
        // assertEquals(test_facility.getTeamReserve(), true);

        // //this is false because jordan already reserved it
        // assertEquals(test_facility.addTeamReserve(team2), false);

        // test_facility.removeTeamReserve();

        // //with the removal of the team reserve, it is now available
        // assertEquals(test_facility.getTeamReserve(), false);

    }
    
}
    

