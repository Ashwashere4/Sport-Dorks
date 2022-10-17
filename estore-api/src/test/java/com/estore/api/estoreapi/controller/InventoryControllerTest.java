package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.controller.inventory.InventoryController;
import com.estore.api.estoreapi.model.Item;
import com.estore.api.estoreapi.persistence.InventoryDAO;

public class InventoryControllerTest {

    private InventoryController inventoryController;
    private InventoryDAO mockinventoryDAO;



    @BeforeEach
    public void setupInventoryController(){
        mockinventoryDAO = mock(InventoryDAO.class);
        inventoryController = new InventoryController(mockinventoryDAO);
        
    }

    @Test
    public void testGetItem() throws IOException{
        Item item = new Item("Jordans", 10, 100);

        when(mockinventoryDAO.getItem(item.getName())).thenReturn(item);

        ResponseEntity<Item> response = inventoryController.getItem(item.getName());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(item, response.getBody());
    }

    @Test
    public void testGetItemNotFound() throws Exception {
        String name = "Jordans";

        when(mockinventoryDAO.getItem(name)).thenReturn(null);

        ResponseEntity<Item> response = inventoryController.getItem(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void testGetItemHandleException() throws Exception{
        String name = "Jordans";

        doThrow(new IOException()).when(mockinventoryDAO).getItem("Jordans");

        ResponseEntity<Item> response = inventoryController.getItem(name);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
