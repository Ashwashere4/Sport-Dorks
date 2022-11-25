package com.estore.api.estoreapi.persistence.WishList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.EstoreApiApplication;
import com.estore.api.estoreapi.persistence.Inventory.InventoryFileDAO;
import com.estore.api.estoreapi.persistence.shoppingCart.ShoppingCartFileDAO;
import com.estore.api.estoreapi.persistence.wishList.WishListFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=WishListFileDAOTests.class)
class WishListFileDAOTests {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String name = "data/inventory.json";
    String cartname = "data/shoppingCart.json";
    String listname = "data/wishlist.json";
    
    @Test 
    void testShoppingCartFileDAO() throws IOException{

        InventoryFileDAO store = new InventoryFileDAO(name, objectMapper);
        ShoppingCartFileDAO cart = new ShoppingCartFileDAO(cartname, objectMapper, store);
        WishListFileDAO list = new WishListFileDAO(listname, objectMapper, cart, store);
        store.createItem("jordans", 12, 10);
        list.addItem("jordans");
        store.createItem("nikes", 10, 1);
        list.addItem("nikes");
        store.createItem("airpods", 5, 100);
        list.addItem("airpods");

       // Checks to see if all the items were added properly
       assertEquals(list.getItems().length, 4);

       // Checks to see if nikes was deleted properly
       list.deleteItem("nikes");    
       assertEquals(list.getItems().length, 3);

       //tests purchase item class
       list.addItemToCart("airpods");
       assertEquals(cart.getCart().length, 4);
       assertEquals(list.getItems().length, 2);
    }
    
}