package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-tier")
public class ItemTest {


    @Test
    public void testCtor(){

        int expected_cost = 12;
        int expected_quantity = 10;
        String expected_name = "This wild Drip";

        Item item = new Item(expected_name,expected_quantity,expected_cost);

        assertEquals(expected_name, item.getName());
        assertEquals(expected_cost, item.getCost());
        assertEquals(expected_quantity, item.getQuantity());
    }

    
}
