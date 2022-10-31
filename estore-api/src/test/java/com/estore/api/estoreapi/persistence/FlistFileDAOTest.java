package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.controller.facilities_list.flist;
import com.estore.api.estoreapi.model.Facilities;
import com.estore.api.estoreapi.model.Teams.Player;
import com.estore.api.estoreapi.persistence.Teams.TeamFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class FlistFileDAOTest {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String name = "data/flist.json";
    
@Test 
void testFlistfileDAO() throws IOException{

        FlistFileDAO flist = new FlistFileDAO(name, objectMapper);
        flist.createFacility("That one stadium", "Bronx", 001);
        Facilities dummy_test = flist.createFacility("something cool", "Bronx", 002);
        flist.createFacility("something boring", "Bronx", 003);

        // Checks to see if a facility exists for something (should return 2 because i am really original with names)
        assertEquals(flist.searchFacilities("something").length, 2);


        // Checks to see if all the faciltites  were added properly (7, 4 from original file, 3 from test)
        assertEquals(flist.getFacilities().length, 7);

        //deletes the one facility with a semi unique name because originally should be punished 
        flist.deleteFacility(001);
        assertEquals(flist.getFacilities().length, 6);
        assertEquals(flist.getFacility(001), null);

        //Since the facilities are now boycotting, something cool should become "The amazing stadium"
        flist.updateFacility(dummy_test, "The amazing stadium", dummy_test.getLocation(), dummy_test.getFacility_id());

        assertEquals(dummy_test.getName(), "The amazing stadium");
        assertEquals(dummy_test.getLocation(), "Bronx");
        assertEquals(dummy_test.getFacility_id(), 002);

        }

    @Test
    void testreservefacilities() throws IOException{

        FlistFileDAO flist = new FlistFileDAO(name, objectMapper);
        
        TeamFileDAO team = new TeamFileDAO(name, objectMapper);
        Player test_player = team.createPlayer("Jordan", 17, 86);

        // Some dude wants to reserve the Yankee Stadium for a more realistic practice

        Facilities test_facility = flist.getFacility(1);

        assertEquals(test_facility.getReserveStatus(), false);

        test_facility.addTeamReserve(test_player)

        

    }
    
}
    

