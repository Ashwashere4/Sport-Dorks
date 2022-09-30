package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Item;


/**
 * Defines the interface for Hero object persistence
 * 
 * @author SWEN Faculty
 */
public interface InventoryDAO {
    /**
     * Retrieves all {@linkplain Item items}
     * 
     * @return An array of {@link Item items} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Item[] getitems() throws IOException;

    /**
     * Finds all {@linkplain Item items} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Item items} whose nemes contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Item[] findItems(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain Item items} with the given name
     * 
     * @param name The name of the {@link Item items} to get
     * 
     * @return a {@link Item item} object with the matching name
     * <br>
     * null if no {@link Item item} with a matching name is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Item getItem(String name) throws IOException;

    /**
     * Creates and saves a {@linkplain Item item}
     * 
     * @param item {@linkplain Item item} object to be created and saved
     * <br>
     * The name of the item object is ignored and a new unique name is assigned
     *
     * @return new {@link Item item} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Item createItem(Item item) throws IOException;

    /**
     * Updates and saves a {@linkplain Item item}
     * 
     * @param {@link Item item} object to be updated and saved
     * 
     * @return updated {@link Item item} if successful, null if
     * {@link Item item} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Item updateItem(Item item) throws IOException;

    /**
     * Deletes a {@linkplain Item item} with the given id
     * 
     * @param id The id of the {@link Item item}
     * 
     * @return true if the {@link Item item} was deleted
     * <br>
     * false if item with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteItem(int id) throws IOException;
}
