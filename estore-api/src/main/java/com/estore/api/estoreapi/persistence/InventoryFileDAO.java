package com.estore.api.estoreapi.persistence;


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
public class InventoryFileDAO implements InventoryDAO {
     /**
     * The current inventory.
     */
    private Map<String, Item> inventory;

    /**
     * The file name of the inventory file.
     */
    private String filename;

    /**
     * The object mapper.
     */

    private ObjectMapper objectMapper;

    /**
     * The next id to assign to a product.
     */
    private int nextId;

    /**
     * 
     * @param inventory
     */
    public InventoryFileDAO(@Value("${inventory.filename}") String filename, ObjectMapper objectMapper) throws IOException {
            this.filename = filename;
            this.objectMapper = objectMapper;
            loadInventory();
        }

     /**
     * Gets the next id to assign to a product.
     * 
     * @return the next id to assign to a product
     */
    private synchronized int nextId() {
        return nextId++;
    }

    private ArrayList<Item> getInventoryArray() {
        return new ArrayList<>(inventory.values());
    }

    @Override
    public Item[] getItems() throws IOException {

        return getInventoryArray().toArray(new Item[0]);
    }


    @Override 
    public Item getItem(String name) throws IOException{
        Item item = inventory.get(name);
            if (item != null)
                return item;
            else
                System.out.println("Item does not exist.");
                return null;
    }

    @Override
    public Item createItem(Item item) throws IOException {
        Item newItem = new Item(nextId(), item.getName(), item.getQuantity(), item.getCost());
        inventory.put(item.getName(), newItem);
        saveInventory();
        return newItem;
    }

    @Override
    public boolean deleteItem(String name) throws IOException{
            inventory.remove(name);
            
            return true;
        }

    
    @Override
    public Item[] searchItems(String text) throws IOException {
        if (text.length() == 0)
            return new Item[0];

        ArrayList<Item> items = new ArrayList<>();
        for (Item item : inventory.values()) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                items.add(item);
            }
        }

        return items.toArray(new Item[0]);
    }
    
    private void saveInventory() throws IOException {
        objectMapper.writeValue(new File(filename), getInventoryArray());
    }

    @Override
    public Item updateItem(Item item) throws IOException {
        Item localItem = inventory.get(item.getName());
        Item newItem = new Item(localItem.getID(), item.getName(), item.getQuantity(), item.getCost());
        inventory.put(localItem.getName(), newItem);
        return newItem;
    }


    
    /**
     * Load the inventory from the file.
     */
    private void loadInventory() throws IOException {
        inventory = new TreeMap<>();
        Item[] inventoryArray = objectMapper.readValue(new File(filename), Item[].class);
        for (Item item : inventoryArray) {
            inventory.put(item.getName(), item);
            if (item.getID() > nextId()) {
                nextId = item.getID();
            }
            ++nextId;
        }
    }
    }
