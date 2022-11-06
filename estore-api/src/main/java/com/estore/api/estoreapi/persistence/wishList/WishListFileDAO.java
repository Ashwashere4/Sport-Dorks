package com.estore.api.estoreapi.persistence.wishList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Inventory.Item;
import com.estore.api.estoreapi.persistence.Inventory.InventoryDAO;
import com.estore.api.estoreapi.persistence.shoppingCart.ShoppingCartDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WishListFileDAO implements WishListDAO{
    private Map<String,Item> wishList;
    private ShoppingCartDAO shoppingCartDAO;
    private InventoryDAO inventoryDAO;

    private String filename;

    private ObjectMapper objectMapper;
    
    public WishListFileDAO(@Value("${wishList.filename}") String filename, ObjectMapper objectMapper, ShoppingCartDAO shoppingCartDAO, InventoryDAO inventoryDAO) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        this.shoppingCartDAO = shoppingCartDAO;
        this.inventoryDAO = inventoryDAO;
        loadWishList();
    }

    private void loadWishList() throws IOException {
        wishList = new HashMap<>();
        Item[] wishListArray = objectMapper.readValue(new File(filename), Item[].class);
        for (Item item : wishListArray) {
            wishList.put(item.getName(), item);
        }
    }

    private ArrayList<Item> getWishListArray() {
        return new ArrayList<>(wishList.values());
    }
    
    @Override
    public Item[] getItems() throws IOException {
        return getWishListArray().toArray(new Item[0]);
    }

    private void saveList() throws IOException {
        objectMapper.writeValue(new File(filename), getWishListArray());
    }

    @Override
    public Item addItem(String name) throws IOException {
        Item newItem = new Item(name, 1, inventoryDAO.getItem(name).getCost());
        wishList.put(name, newItem);
        saveList();
        return newItem;
    }

    @Override
    public boolean deleteItem(String name) throws IOException {
        wishList.remove(name);
        saveList();
        return true;
    }

    @Override
    public boolean addItemToCart(String name) throws IOException {
        shoppingCartDAO.addItem(name);
        deleteItem(name);
        return true;
    }
}