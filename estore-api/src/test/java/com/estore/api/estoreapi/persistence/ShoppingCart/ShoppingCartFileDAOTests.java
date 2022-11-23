package com.estore.api.estoreapi.persistence.ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.EstoreApiApplication;
import com.estore.api.estoreapi.persistence.Inventory.InventoryFileDAO;
import com.estore.api.estoreapi.persistence.shoppingCart.ShoppingCartFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=EstoreApiApplication.class)
class ShoppingCartFileDAOTests {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String name = "data/inventory.json";
    String cartname = "data/shoppingCart.json";
    
    @Test 
    void testShoppingCartFileDAO() throws IOException{

        InventoryFileDAO store = new InventoryFileDAO(name, objectMapper);
        ShoppingCartFileDAO cart = new ShoppingCartFileDAO(cartname, objectMapper, store);
        store.createItem("jordans", 12, 10);
        cart.addItem("jordans");
        store.createItem("nikes", 10, 1);
        cart.addItem("nikes");
        store.createItem("airpods", 5, 100);
        cart.addItem("airpods");

       // Checks to see if all the items were added properly
       assertEquals(cart.getCart().length, 5);

       // Checks to see if nikes was deleted properly
       cart.deleteItem("nikes");    
       assertEquals(cart.getCart().length, 4);

       //tests purchase item class
       cart.purchaseItem("airpods");
       assertEquals(cart.getCart().length, 3);
       assertEquals(store.getItems().length, 15);
    }
    
}