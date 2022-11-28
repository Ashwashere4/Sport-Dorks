package com.estore.api.estoreapi.controller.Inventory;

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
import com.estore.api.estoreapi.model.Inventory.Item;
import com.estore.api.estoreapi.persistence.Inventory.InventoryDAO;

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


    @Test
    public void testCreateItem() throws IOException{

        Item item = new Item("jordans", 12, 12);

        when(mockinventoryDAO.createItem(item)).thenReturn(item);

        ResponseEntity<Item> response = inventoryController.createItem(item);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(item, response.getBody());

    }   

    @Test
    public void testCreateItemFailed() throws IOException{
        Item item = new Item("Jays", 0, 0);

        when(mockinventoryDAO.createItem(item)).thenReturn(null);

        ResponseEntity<Item> response = inventoryController.createItem(item);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateItemHandleException() throws IOException{
        Item item = new Item("Jays", 0, 0);

        doThrow(new IOException()).when(mockinventoryDAO).createItem(item);

        ResponseEntity<Item> response = inventoryController.createItem(item);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    public void testUpdateItem() throws IOException {

        Item item = new Item("Jays", 0, 0);

        when(mockinventoryDAO.updateItem(item, "Nike", 0, 0)).thenReturn(item);

        ResponseEntity<Item> response = inventoryController.updateItem(item, "Nike", 0, 0);
        
        response = inventoryController.updateItem(item, "Nike", 0, 0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(item, response.getBody());
    }   

    @Test 
    public void testUpdateHeroHandleException() throws IOException{
        Item item = new Item("jays", 0, 0);

        doThrow(new IOException()).when(mockinventoryDAO).updateItem(item, "Nike", 0, 0);

        ResponseEntity<Item> response = inventoryController.updateItem(item, "Nike", 0, 0);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetItems() throws IOException{
        Item[] items = new Item[2];
        items[0] = new Item("jays", 0, 0);
        items[1] = new Item("Nike", 0, 0);

        when(mockinventoryDAO.getItems()).thenReturn(items);

        ResponseEntity<Item[]> response = inventoryController.getItems();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());
    }

    @Test
    public void testGetItemsHandleException() throws IOException{
        doThrow(new IOException()).when(mockinventoryDAO).getItems();

        ResponseEntity<Item[]> response = inventoryController.getItems();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testSearchItems() throws IOException{
        String name = "Jays";
        Item[] items = new Item[2];
        items[0] = new Item("Jays", 0, 0);
        items[1] = new Item("Nikes",0,0);

        when(mockinventoryDAO.searchItems(name)).thenReturn(items);

        ResponseEntity<Item[]> response = inventoryController.searchItem(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());
    }

    @Test
    public void testSearchItemsHandleException() throws IOException { 
       
        String searchString = "Jays";
       
        doThrow(new IOException()).when(mockinventoryDAO).searchItems(searchString);

        
        ResponseEntity<Item[]> response = inventoryController.searchItem(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteItems() throws IOException { 
        
        String name = "Jays";
        
        when(mockinventoryDAO.deleteItem(name)).thenReturn(true);

     
        ResponseEntity<Boolean> response = inventoryController.deleteItem(name);

  
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteHeroNotFound() throws IOException { 
       
        String name = "Jays";
       
        when(mockinventoryDAO.deleteItem(name)).thenReturn(false);

        
        ResponseEntity<Boolean> response = inventoryController.deleteItem(name);

        
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteHeroHandleException() throws IOException { 
        
        String name = "Jays";
        
        doThrow(new IOException()).when(mockinventoryDAO).deleteItem(name);

        
        ResponseEntity<Boolean> response = inventoryController.deleteItem(name);

        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}

