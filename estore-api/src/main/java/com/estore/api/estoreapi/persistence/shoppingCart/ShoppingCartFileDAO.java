package com.estore.api.estoreapi.persistence.shoppingCart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Inventory.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ShoppingCartFileDAO implements ShoppingCartDAO{
    private Map<String,Item> shoppingCart;

    private String filename;

    private ObjectMapper objectMapper;
    
    public ShoppingCartFileDAO(@Value("${shoppingCart.filename}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        loadShoppingCart();
    }

    private void loadShoppingCart() throws IOException {
        shoppingCart = new TreeMap<>();
        Item[] shoppingCartArray = objectMapper.readValue(new File(filename), Item[].class);
        for (Item item : shoppingCartArray) {
            shoppingCart.put(item.getName(), item);
        }
    }

    private ArrayList<Item> getShoppingCartArray() {
        return new ArrayList<>(shoppingCart.values());
    }
    
    @Override
    public Item[] getCart() throws IOException {
        return getShoppingCartArray().toArray(new Item[0]);
    }
}