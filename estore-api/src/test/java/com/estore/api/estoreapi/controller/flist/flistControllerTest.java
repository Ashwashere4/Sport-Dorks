package com.estore.api.estoreapi.controller.flist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.controller.facilities_list.flistController;
import com.estore.api.estoreapi.model.Facilities.Facilities;
import com.estore.api.estoreapi.persistence.FacilitiesList.FlistDAO;

public class flistControllerTest {

    private flistController flistController;
    private FlistDAO mockFlistDAO;


    @BeforeEach
    public void setupFlistController(){
        mockFlistDAO = mock(FlistDAO.class);
        flistController = new flistController(mockFlistDAO);

    }

    @Test
    public void testgetFacility() throws IOException{
        Facilities x = new Facilities("Jim", "you're mom", 0);

        when(mockFlistDAO.getFacility(0)).thenReturn(x);

        ResponseEntity<Facilities> response = flistController.getFacility(x.getFacility_id());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(x, response.getBody());
    }

    @Test
    public void testgetFacilityNotFOund() throws Exception {
        int code = 21;

        when(mockFlistDAO.getFacility(code)).thenReturn(null);

        ResponseEntity<Facilities> response = flistController.getFacility(code);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetFacilityHandleException() throws Exception{
        int code = 21;

        doThrow(new IOException()).when(flistController).getFacility(code);

        ResponseEntity<Facilities> response = flistController.getFacility(code);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    public void testgetFacilities() throws IOException{
        Facilities[] facilities = new Facilities[2];
        facilities[0] = new Facilities("Jim", "you're mom", 0);
        facilities[1] = new Facilities("Gym", "you're day", 21);

        when(mockFlistDAO.getFacilities()).thenReturn(facilities);
        ResponseEntity<Facilities[]> response = flistController.getFacilities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(facilities, response.getBody());
    }

    public void testgetFacilitiesHandleException() throws IOException{
        doThrow(new IOException()).when(mockFlistDAO).getFacilities();

        ResponseEntity<Facilities[]> response = flistController.getFacilities();

        assertEquals((HttpStatus.INTERNAL_SERVER_ERROR), response.getStatusCode());
    }
    

    public void testsearchFacilities() throws IOException{}

    @Test
    public void testcreateFacility() throws IOException{
        Facilities x = new Facilities("George", "Bronx", 21);

        when(mockFlistDAO.createFacility("George", "Bronx", 21)).thenReturn(x);

        ResponseEntity<Facilities> response = flistController.createFacility("George", "Bronx", 21);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(x, response.getBody());
    }

    @Test
    public void testcreateFacilityFailed() throws IOException{
        // Facilities x = new Facilities("Jays", "Bronx", 0);

        when(mockFlistDAO.createFacility("Jays", "Bronx", 0)).thenReturn(null);

        ResponseEntity<Facilities> response = flistController.createFacility("Jays", "Bronx", 0);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test 
    public void testcreateFacilityHandleException() throws IOException{
    
        doThrow(new IOException()).when(mockFlistDAO).createFacility("Jays", "Bronx", 0);
        ResponseEntity<Facilities> response = flistController.createFacility("Jays", "Bronx", 0);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testUpdateFacility() throws IOException{

        Facilities facilities = new Facilities("Jays", "Bronx", 12);

        when(mockFlistDAO.updateFacility(facilities, "Gym", "Bronx", 12)).thenReturn(facilities);

        ResponseEntity<Facilities> response = flistController.updateFacility(facilities, "Gym", "Bronx", 12);

        response = flistController.updateFacility(facilities, "Gym", "Bronx", 12);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(facilities, response.getBody());
    }

    @Test 
    public void testUpdatefacilitiesHandleException() throws IOException{
        Facilities facilities = new Facilities("jays", "Bronx", 12);

        doThrow(new IOException()).when(flistController).updateFacility(facilities, "Gym", "Bronx", 12);

        ResponseEntity<Facilities> response = flistController.updateFacility(facilities, "Gym", "Bronx", 12);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    public void testdeleteTeam(){}


    public void testreserveFacility(){}
}
