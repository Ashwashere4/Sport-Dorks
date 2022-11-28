package com.estore.api.estoreapi.persistence.ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.persistence.Inventory.InventoryFileDAO;
import com.estore.api.estoreapi.persistence.shoppingCart.ShoppingCartFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=ShoppingCartFileDAOTests.class)
class ShoppingCartFileDAOTests {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String name = "data/inventory.json";
    String cartname = "data/shoppingCart.json";
    
    @Test 
    void testShoppingCartFileDAO() throws IOException{

        InventoryFileDAO store = new InventoryFileDAO(name, objectMapper);
        ShoppingCartFileDAO cart = new ShoppingCartFileDAO(cartname, objectMapper, store);

        store.deleteItem("jordans");
        cart.deleteItem("jordans");
        store.deleteItem("nikes");
        cart.deleteItem("nikes");
        store.deleteItem("airpods");
        cart.deleteItem("airpods");

        int beforeTestStore = store.getItems().length;
        int beforeTestsCart = cart.getCart().length;

        store.createItem("jordans", 12, 10);
        cart.addItem("jordans");
        store.createItem("nikes", 10, 1);
        cart.addItem("nikes");
        store.createItem("airpods", 5, 100);
        cart.addItem("airpods");

       // Checks to see if all the items were added properly
       assertEquals(cart.getCart().length, beforeTestsCart + 3);

       // Checks to see if nikes was deleted properly
       cart.deleteItem("nikes");    
       assertEquals(cart.getCart().length, beforeTestsCart + 2);

       //tests purchase item class
       cart.purchaseItem("airpods");
       assertEquals(cart.getCart().length, beforeTestsCart + 1);
       assertEquals(beforeTestStore + 3, store.getItems().length);
    }
    
}