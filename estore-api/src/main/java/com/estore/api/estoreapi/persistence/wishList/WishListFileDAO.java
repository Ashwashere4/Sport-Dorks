package com.estore.api.estoreapi.persistence.wishList;

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
public class WishListFileDAO implements WishListDAO{
    private Map<String,Item> wishList;

    private String filename;

    private ObjectMapper objectMapper;
    
    public WishListFileDAO(@Value("${wishlist.filename}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        loadWishList();
    }

    private void loadWishList() throws IOException {
        wishList = new TreeMap<>();
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
}