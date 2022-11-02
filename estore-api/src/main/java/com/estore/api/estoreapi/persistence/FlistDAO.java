package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Inventory.Item;
import com.estore.api.estoreapi.model.Facilities;


/**
 * Defines the interface for Hero object persistence
 * 
 * @author SWEN Faculty
 */
public interface FlistDAO {
    /**
     * Retrieves all {@linkplain Facilities facilities}
     * 
     * @return An array of {@link Facilities  facilities} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Facilities[] getFacilities() throws IOException;

    /**
     * Finds all {@linkplain Facilities facilities} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Facilities facilities} whose names contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Facilities[] searchFacilities(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain Facilities facilities} with the given name
     * 
     * @param name The name of the {@link Facilities facilities} to get
     * 
     * @return a {@link Facilities facilities} object with the matching name
     * <br>
     * null if no {@link Facilities facilities} with a matching name is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Facilities getFacility(int code) throws IOException;

    /**
     * Creates and saves a {@linkplain Facilities facilities}
     * 
     * @param name {@linkplain Facilities facilities} object to be created and saved
     * <br>
     * The name of the item object is ignored and a new unique name is assigned
     *
     * @return new {@link Facilities facilities} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Facilities createFacility(String name, String location, int facility_id) throws IOException;

    /**
     * Updates and saves a {@linkplain Facilities facilities}
     * 
     * @param {@link Facilities facilities} object to be updated and saved
     * 
     * @return updated {@link Facilities facilities} if successful, null if
     * {@link Item item} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Facilities updateFacility(Facilities facilities, String name, String location, int facility_id) throws IOException;

    /**
     * Deletes a {@linkplain Facilities facilities} with the given name
     * 
     * @param name The name of the {@link Facilities facilities}
     * 
     * @return true if the {@link Facilities facilities} was deleted
     * <br>
     * false if facilities with the given name does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteFacility(int facility_id) throws IOException;
}

