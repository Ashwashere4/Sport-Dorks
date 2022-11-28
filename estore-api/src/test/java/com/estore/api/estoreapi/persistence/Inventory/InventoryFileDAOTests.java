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
        store.createItem("jordans", 12, 10);
        store.createItem("nikes", 10, 1);
        store.createItem("airpods", 5, 100);
        Item idkman = store.createItem("the ultimate drip", 16, 25);

        assertEquals(store.searchItems("jordans").length, 1);

        assertEquals(15, store.getItems().length); //watch
        store.deleteItem("nikes");
        assertEquals(14, store.getItems().length); //notice this one
        assertEquals(store.getItem("nikes"), null);

        Item updatedItem = store.updateItem(idkman, "Nikez", 21, 10);

        assertEquals("Nikez", updatedItem.getName());

        Item drip = store.getItem("the ultimate drip");



        assertNotNull(drip);
        assertEquals(10, drip.getCost());
        assertEquals("Nikez", drip.getName());
        assertEquals(21, drip.getQuantity());

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
