package com.estore.api.estoreapi.persistence.League;

import java.io.IOException;
import java.util.ArrayList;

import com.estore.api.estoreapi.model.Teams.Player;
import com.estore.api.estoreapi.model.Teams.Team;

public interface LeagueDAO {
    /**
     * Retrieves all {@linkplain Team players}
     * 
     * @return An array of {@link Team team} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Team[] getTeams() throws IOException;

    /**
     * Finds all {@linkplain Team team} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Team team} whose names contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Team[] searchLeague(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain Team team} with the given name
     * 
     * @param id The id of the {@link Team team} to get
     * 
     * @return a {@link Team team} object with the matching name
     * <br>
     * null if no {@link Team team} with a matching name is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Team getTeam(int id) throws IOException;

    /**
     * Creates and saves a {@linkplain Team team}
     * 
     * @param roster {@linkplain Team team} roster arraylist
     * @param id {@linkplain Team team} for team 
     * <br>
     *
     * @return new {@link Team team} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Team createTeam(ArrayList<Player> roster, int id ) throws IOException;

    Team createTeam(Team team) throws IOException;

    /**
     * Updates and saves a {@linkplain Team team}
     * 
     * @param {@link Team team} object to be updated and saved
     * 
     * @return updated {@link Team team} if successful, null if
     * {@link Team team} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Team updateTeam(Team team, ArrayList<Player> roster, int id ) throws IOException;

    /**
     * Deletes a {@linkplain Team team} with the given id
     * 
     * @param id The id of the {@link Team team}
     * 
     * @return true if the {@link Team team} was deleted
     * <br>
     * false if item with the given name does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteTeam(int id) throws IOException;
}
