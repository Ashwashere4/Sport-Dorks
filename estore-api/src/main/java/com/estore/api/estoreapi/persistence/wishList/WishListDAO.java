package com.estore.api.estoreapi.persistence.wishList;

import java.io.IOException;

import com.estore.api.estoreapi.model.Inventory.Item;

public interface WishListDAO {
    /**
     * Retrieves all {@linkplain Item items}
     * 
     * @return An array of {@link Item items} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Item[] getItems() throws IOException;

    Item addItem(String name) throws IOException;

    boolean deleteItem(String name) throws IOException;

    boolean addItemToCart(String name) throws IOException;
}
