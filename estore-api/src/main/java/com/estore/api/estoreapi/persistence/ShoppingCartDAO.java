package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Item;


/**
 * Defines the interface for ShoppingCart object persistence
**/
public interface ShoppingCartDAO {
    /**
     * Retrieves all {@linkplain Item items}
     * 
     * @return An array of {@link Item items} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Item[] getItems() throws IOException;

    /**
     * adds and saves a {@linkplain Item item}
     * 
     * @param name {@linkplain Item item} object to be created and saved
     * <br>
     * The name of the item object is ignored and a new unique name is assigned
     *
     * @return new {@link Item item} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Item addItem(Item item) throws IOException;

    /**
     * Deletes a {@linkplain Item item} with the given name
     * 
     * @param name The name of the {@link Item item}
     * 
     * @return true if the {@link Item item} was deleted
     * <br>
     * false if item with the given name does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteItem(String name) throws IOException;
}
