package com.estore.api.estoreapi.persistence.Inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.estore.api.estoreapi.EstoreApiApplication;
import com.estore.api.estoreapi.model.Inventory.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=InventoryFileDAOTests.class)
class InventoryFileDAOTests {
    
    ObjectMapper objectMapper = new ObjectMapper();
    String name = "data/inventory.json";
    
    @Test 
    void testInventoryFileDAO() throws IOException{

        InventoryFileDAO store = new InventoryFileDAO(name, objectMapper);
        store.createItem("jordans", 12, 10);
        store.createItem("nikes", 10, 1);
        store.createItem("airpods", 5, 100);
        Item idkman = store.createItem("idkman", 16, 25);

        // Checks to see if the item exists for jordans
        assertEquals(store.searchItems("jordans").length, 1);

        // Checks to see if all the items were added properly (11 from original file, 4 from test = 15)
        assertEquals(store.getItems().length, 15);

        // Checks to see if nikes was deleted properly (15-1 = 14), since nike doesn't exist it returns null
        store.deleteItem("nikes");
        assertEquals(store.getItems().length, 14);
        assertEquals(store.getItem("nikes"), null);

        //Finally, checks to see if idkman is updated into the ultimate drip, with the quantity of 100, and the price of 10,000
        store.updateItem(idkman, "the ultimate drip", 100, 10000);

        Item drip = store.getItem("the ultimate drip");

        assertNotNull(store.getItem("the ultimate drip"));
        assertEquals(drip.getCost(), 10000);
        assertEquals(drip.getName(), "the ultimate drip");
        assertEquals(drip.getQuantity(), 100);

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
