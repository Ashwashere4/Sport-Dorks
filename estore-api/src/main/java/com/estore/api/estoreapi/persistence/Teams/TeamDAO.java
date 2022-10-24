package com.estore.api.estoreapi.persistence.Teams;

import java.io.IOException;

import com.estore.api.estoreapi.model.Teams.Player;

public interface TeamDAO {
    /**
     * Retrieves all {@linkplain Player players}
     * 
     * @return An array of {@link Player player} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Player[] getPlayers() throws IOException;

    /**
     * Finds all {@linkplain Player player} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Player player} whose names contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Player[] searchTeam(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain Player player} with the given name
     * 
     * @param name The name of the {@link Player player} to get
     * 
     * @return a {@link Player player} object with the matching name
     * <br>
     * null if no {@link Player player} with a matching name is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Player getPlayer(String name) throws IOException;

    /**
     * Creates and saves a {@linkplain Player player}
     * 
     * @param name {@linkplain Player player} object to be created and saved
     * <br>
     * The name of the item object is ignored and a new unique name is assigned
     *
     * @return new {@link Player player} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Player createPlayer(String name, int age, int rating) throws IOException;

    Player createPlayer(Player player) throws IOException;

    /**
     * Updates and saves a {@linkplain Player player}
     * 
     * @param {@link Player player} object to be updated and saved
     * 
     * @return updated {@link Player player} if successful, null if
     * {@link Player player} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Player updatePlayer(Player player, String name, int age, int rating) throws IOException;

    /**
     * Deletes a {@linkplain Player player} with the given name
     * 
     * @param name The name of the {@link Player player}
     * 
     * @return true if the {@link Player player} was deleted
     * <br>
     * false if item with the given name does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deletePlayer(String name) throws IOException;
}
