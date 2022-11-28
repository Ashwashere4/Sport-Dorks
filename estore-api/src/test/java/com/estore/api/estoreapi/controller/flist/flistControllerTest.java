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
import com.estore.api.estoreapi.model.facilities.Facilities;
import com.estore.api.estoreapi.persistence.FacilitiesList.FlistDAO;
import com.fasterxml.jackson.core.sym.Name;

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
    public void testgetFacilityNotFound() throws Exception {
        int code = 21;

        when(mockFlistDAO.getFacility(code)).thenReturn(null);

        ResponseEntity<Facilities> response = flistController.getFacility(code);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetFacilityHandleException() throws Exception{
        int code = 21;

        doThrow(new IOException()).when(mockFlistDAO).getFacility(code);

        ResponseEntity<Facilities> response = flistController.getFacility(code);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testgetFacilities() throws IOException{
        Facilities[] facilities = new Facilities[2];
        facilities[0] = new Facilities("Jim", "you're mom", 0);
        facilities[1] = new Facilities("Gym", "you're day", 21);

        when(mockFlistDAO.getFacilities()).thenReturn(facilities);
        ResponseEntity<Facilities[]> response = flistController.getFacilities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(facilities, response.getBody());
    }

    @Test
    public void testgetFacilitiesHandleException() throws IOException{
        doThrow(new IOException()).when(mockFlistDAO).getFacilities();

        ResponseEntity<Facilities[]> response = flistController.getFacilities();

        assertEquals((HttpStatus.INTERNAL_SERVER_ERROR), response.getStatusCode());
    }
    
    @Test
    public void testsearchFacilities() throws IOException{
        String name = "Jim";
        Facilities[] facilities = new Facilities[2];
        facilities[0] = new Facilities("Jim", "you're mom", 0);
        facilities[1] = new Facilities("Gym", "you're day", 21);

        when(mockFlistDAO.searchFacilities(name)).thenReturn(facilities);

        ResponseEntity<Facilities[]> response = flistController.searchFacilities(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(facilities, response.getBody());
    }

    @Test
    public void testsearchFacilitiesHandleException() throws IOException{
        String name = "Jim";

        doThrow(new IOException()).when(mockFlistDAO).searchFacilities(name);

        ResponseEntity<Facilities[]> response = flistController.searchFacilities(name);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testcreateFacility() throws IOException{
        Facilities x = new Facilities("George", "Bronx", 21);

        when(mockFlistDAO.createFacility(x)).thenReturn(x);

        ResponseEntity<Facilities> response = flistController.createFacility(x);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(x, response.getBody());
    }

    @Test
    public void testcreateFacilityFailed() throws IOException{

        when(mockFlistDAO.createFacility("Jays", "Bronx", 0)).thenReturn(null);
        Facilities facility = mockFlistDAO.createFacility("Jays", "Bronx", 0);
        ResponseEntity<Facilities> response = flistController.createFacility(facility);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test 
    public void testcreateFacilityHandleException() throws IOException{

        Facilities facility = mockFlistDAO.createFacility("Jays", "Bronx", 0);
    
        doThrow(new IOException()).when(mockFlistDAO).createFacility(facility);
        ResponseEntity<Facilities> response = flistController.createFacility(facility);

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

        doThrow(new IOException()).when(mockFlistDAO).updateFacility(facilities, "Gym", "Bronx", 12);

        ResponseEntity<Facilities> response = flistController.updateFacility(facilities, "Gym", "Bronx", 12);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    public void testDeleteItems() throws IOException { 
        
        int code = 12;
        
        when(mockFlistDAO.deleteFacility(code)).thenReturn(true);

     
        ResponseEntity<Boolean> response = flistController.deleteFacility(code);

  
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }


    @Test
    public void testDeleteHeroNotFound() throws IOException { 
       
        int code = 12;
       
        when(mockFlistDAO.deleteFacility(code)).thenReturn(false);

        
        ResponseEntity<Boolean> response = flistController.deleteFacility(code);

        
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteHeroHandleException() throws IOException { 
        
        int code = 12;
        
        doThrow(new IOException()).when(mockFlistDAO).deleteFacility(code);

        
        ResponseEntity<Boolean> response = flistController.deleteFacility(code);

        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
    

    
