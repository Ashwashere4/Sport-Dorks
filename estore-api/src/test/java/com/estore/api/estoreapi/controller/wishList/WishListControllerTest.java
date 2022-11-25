package com.estore.api.estoreapi.controller.wishList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.controller.wishlist.WishListController;
import com.estore.api.estoreapi.model.Inventory.Item;
import com.estore.api.estoreapi.persistence.wishList.WishListDAO;

public class WishListControllerTest {

    private WishListController wishListController;
    private WishListDAO mockWishListDAO;



    @BeforeEach
    public void setupInventoryController(){
        mockWishListDAO = mock(WishListDAO.class);
        wishListController = new WishListController(mockWishListDAO);
        
    }

    @Test
    public void testAddItem() throws IOException{

        Item item = new Item("jordans", 12, 12);

        when(mockWishListDAO.addItem(item.getName())).thenReturn(item);

        ResponseEntity<Item> response = wishListController.addItem(item.getName());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(item, response.getBody());

    }   

    @Test
    public void testAddItemFailed() throws IOException{
        Item item = new Item("Jays", 0, 0);

        when(mockWishListDAO.addItem(item.getName())).thenReturn(null);

        ResponseEntity<Item> response = wishListController.addItem(item.getName());

        assertNotEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testAddItemHandleException() throws IOException{
        Item item = new Item("Jays", 0, 0);

        doThrow(new IOException()).when(mockWishListDAO).addItem(item.getName());

        ResponseEntity<Item> response = wishListController.addItem(item.getName());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    public void testGetItems() throws IOException{
        Item[] items = new Item[2];
        items[0] = new Item("jays", 0, 0);
        items[1] = new Item("Nike", 0, 0);

        when(mockWishListDAO.getItems()).thenReturn(items);

        ResponseEntity<Item[]> response = wishListController.getItems();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());
    }

    @Test
    public void testGetItemsHandleException() throws IOException{
        doThrow(new IOException()).when(mockWishListDAO).getItems();

        ResponseEntity<Item[]> response = wishListController.getItems();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeleteItem() throws IOException { 
        
        String name = "Jays";
        
        when(mockWishListDAO.deleteItem(name)).thenReturn(true);

     
        ResponseEntity<Boolean> response = wishListController.deleteItem(name);

  
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteItemNotFound() throws IOException { 
       
        String name = "Jays";
       
        when(mockWishListDAO.deleteItem(name)).thenReturn(false);

        
        ResponseEntity<Boolean> response = wishListController.deleteItem(name);

        
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteItemHandleException() throws IOException { 
        
        String name = "Jays";
        
        doThrow(new IOException()).when(mockWishListDAO).deleteItem(name);

        
        ResponseEntity<Boolean> response = wishListController.deleteItem(name);

        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testaddItemToCart() throws IOException { 
        
        String name = "Jays";
        
        when(mockWishListDAO.addItemToCart(name)).thenReturn(true);

        ResponseEntity<Boolean> response = wishListController.addItemToCart(name);

        assertNotEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testaddItemToCartNotFound() throws IOException { 
       
        String name = "Jays";
       
        when(mockWishListDAO.addItemToCart(name)).thenReturn(false);

        
        ResponseEntity<Boolean> response = wishListController.addItemToCart(name);

        
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testaddItemToCartHandleException() throws IOException { 
        
        String name = "Jays";
        
        doThrow(new IOException()).when(mockWishListDAO).addItemToCart(name);

        
        ResponseEntity<Boolean> response = wishListController.addItemToCart(name);

        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

}