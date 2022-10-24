package com.estore.api.estoreapi.persistence.shoppingCart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ShoppingCartFileDAO {
     /**
     * The current shoppingCart.
     */
    private Map<String, Item> shoppingCart;

    /**
     * The file name of the inventory file.
     */
    private String filename;

    /**
     * The object mapper.
     */

    private ObjectMapper objectMapper;

    /**
     * 
     * @param shoppingCart
     */
    public ShoppingCartFileDAO(@Value("${shoppingCart.filename}") String filename, ObjectMapper objectMapper) throws IOException {
            this.filename = filename;
            this.objectMapper = objectMapper;
            loadShoppingCart();
        }

    private ArrayList<Item> getShoppingCartArray() {
        return new ArrayList<>(shoppingCart.values());
    }

    public Item[] getItems() throws IOException {
        return getShoppingCartArray().toArray(new Item[0]);
    }

    public Item addItem(Item item) throws IOException {
        shoppingCart.put(item.getName(), item);
        saveShoppingCart();
        return item;
    }

    public boolean deleteItem(String name) throws IOException{
        shoppingCart.remove(name);
            return true;
    }
    
    /**
     * save the inventory to the file.
    **/
    private void saveShoppingCart() throws IOException {
        objectMapper.writeValue(new File(filename), getShoppingCartArray());
    }
    
    /**
     * Load the inventory from the file.
     */
    private void loadShoppingCart() throws IOException {
        shoppingCart = new TreeMap<>();
        Item[] shoppingCartArray = objectMapper.readValue(new File(filename), Item[].class);
        for (Item item : shoppingCartArray) {
            shoppingCart
            .put(item.getName(), item);
        }
    }
}
