package com.estore.api.estoreapi.controller.shoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.Inventory.Item;
import com.estore.api.estoreapi.persistence.shoppingCart.ShoppingCartDAO;

public class ShoppingcartControllerTest {

    private ShoppingCartController shoppingCartController;
    private ShoppingCartDAO mockShoppingCartDAO;



    @BeforeEach
    public void setupInventoryController(){
        mockShoppingCartDAO = mock(ShoppingCartDAO.class);
        shoppingCartController = new ShoppingCartController(mockShoppingCartDAO);
        
    }

    @Test
    public void testAddItem() throws IOException{

        Item item = new Item("jordans", 12, 12);

        when(mockShoppingCartDAO.addItem(item.getName())).thenReturn(item);

        ResponseEntity<Item> response = shoppingCartController.addItem(item.getName());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(item, response.getBody());

    }   

    @Test
    public void testAddItemFailed() throws IOException{
        Item item = new Item("Jays", 0, 0);

        when(mockShoppingCartDAO.addItem(item.getName())).thenReturn(null);

        ResponseEntity<Item> response = shoppingCartController.addItem(item.getName());

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testAddItemHandleException() throws IOException{
        Item item = new Item("Jays", 0, 0);

        doThrow(new IOException()).when(mockShoppingCartDAO).addItem(item.getName());

        ResponseEntity<Item> response = shoppingCartController.addItem(item.getName());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    public void testGetCart() throws IOException{
        Item[] items = new Item[2];
        items[0] = new Item("jays", 0, 0);
        items[1] = new Item("Nike", 0, 0);

        when(mockShoppingCartDAO.getCart()).thenReturn(items);

        ResponseEntity<Item[]> response = shoppingCartController.getCart();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());
    }

    @Test
    public void testGetCartHandleException() throws IOException{
        doThrow(new IOException()).when(mockShoppingCartDAO).getCart();

        ResponseEntity<Item[]> response = shoppingCartController.getCart();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeleteItem() throws IOException { 
        
        String name = "Jays";
        
        when(mockShoppingCartDAO.deleteItem(name)).thenReturn(true);

     
        ResponseEntity<Boolean> response = shoppingCartController.deleteItem(name);

  
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteItemNotFound() throws IOException { 
       
        String name = "Jays";
       
        when(mockShoppingCartDAO.deleteItem(name)).thenReturn(false);

        
        ResponseEntity<Boolean> response = shoppingCartController.deleteItem(name);

        
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteItemHandleException() throws IOException { 
        
        String name = "Jays";
        
        doThrow(new IOException()).when(mockShoppingCartDAO).deleteItem(name);

        
        ResponseEntity<Boolean> response = shoppingCartController.deleteItem(name);

        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testPurchaseItem() throws IOException { 
        
        String name = "Jays";
        
        when(mockShoppingCartDAO.purchaseItem(name)).thenReturn(true);

     
        ResponseEntity<Boolean> response = shoppingCartController.purchaseItem(name);

  
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testPurchaseItemNotFound() throws IOException { 
       
        String name = "Jays";
       
        when(mockShoppingCartDAO.purchaseItem(name)).thenReturn(false);

        
        ResponseEntity<Boolean> response = shoppingCartController.purchaseItem(name);

        
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testPurchaseItemHandleException() throws IOException { 
        
        String name = "Jays";
        
        doThrow(new IOException()).when(mockShoppingCartDAO).purchaseItem(name);

        
        ResponseEntity<Boolean> response = shoppingCartController.purchaseItem(name);

        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

}

