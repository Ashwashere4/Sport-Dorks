package com.estore.api.estoreapi.persistence.Inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.model.Inventory.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=InventoryFileDAOTests.class)
class InventoryFileDAOTests {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String name = "data/inventory.json";
    
    @Test 
    void testInventoryFileDAO() throws IOException{
        InventoryFileDAO store = new InventoryFileDAO(name, objectMapper);

        store.deleteItem("jordans");
        store.deleteItem("nikes");
        store.deleteItem("airpods");
        store.deleteItem("the ultimate drip");
        store.deleteItem("baseballHat");
        int beforeTests = store.getItems().length;

        store.createItem("jordans", 12, 10);
        store.createItem("nikes", 10, 1);
        store.createItem("airpods", 5, 100);
        Item idkman = store.createItem("the ultimate drip", 16, 25);

        assertEquals(store.searchItems("jordans").length, 1);

<<<<<<< HEAD
        assertEquals(beforeTests + 4, store.getItems().length);

        store.deleteItem("nikes");
        assertEquals(beforeTests + 3, store.getItems().length);
=======
        assertEquals(15, store.getItems().length); //watch
        store.deleteItem("nikes");
        assertEquals(14, store.getItems().length); //notice this one
>>>>>>> d59402e65a8c3b498cf79c460e18f22d741f5a8b
        assertEquals(store.getItem("nikes"), null);

        Item updatedItem = store.updateItem(idkman, "Nikez", 21, 10);

        assertEquals("Nikez", updatedItem.getName());

        Item drip = store.getItem("the ultimate drip");



        assertNotNull(drip);
<<<<<<< HEAD
        assertEquals(drip.getCost(), 25);
        assertEquals(drip.getName(), "the ultimate drip");
        assertEquals(drip.getQuantity(), 16);
=======
        assertEquals(10, drip.getCost());
        assertEquals("Nikez", drip.getName());
        assertEquals(21, drip.getQuantity());
>>>>>>> d59402e65a8c3b498cf79c460e18f22d741f5a8b

        store = new InventoryFileDAO(name, objectMapper);
        //tests create item class
        store.createItem("baseballHat", 12, 10);

        assertEquals(store.searchItems("baseballHat").length, 1);        
        assertNotNull(store.getItem("baseballHat"));
        assertNotNull(store.getItems());

        //tests delete item class
        store.deleteItem("baseballHat");
        assertNull(store.getItem("baseballHat")); 
        assertNotEquals(store.searchItems("baseballHat").length, 1);

    }
    
}
