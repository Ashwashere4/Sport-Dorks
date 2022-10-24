package com.estore.api.estoreapi.controller.Teams;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Teams.Player;
import com.estore.api.estoreapi.persistence.Teams.TeamDAO;

@RestController
@RequestMapping("Inventory")
public class TeamController {
    private static final Logger LOG = Logger.getLogger(TeamController.class.getName());
    private TeamDAO teamDAO;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param teamDAO The {@link TeamDAO team Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public TeamController(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    /**
     * Responds to the GET request for a {@linkplain Player player} for the given name
     * 
     * @param name The string used to locate the {@link Player player}
     * 
     * @return ResponseEntity with {@link Player player} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{name}")
    public ResponseEntity<Player> getItem(@PathVariable String name) {
        LOG.info("GET /inventory/" + name);
        try {
            Player player = teamDAO.getPlayer(name);
            if (player != null)
                return new ResponseEntity<Player>(player,HttpStatus.OK);
            else
                System.out.println("Item does not exist.");
                return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Player player}
     * 
     * @return ResponseEntity with array of {@link Player player} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Player[]> getPlayers() {
        LOG.info("GET /inventory");
        Player[] players;
        try {
            players = teamDAO.getPlayers();

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Player[]>(players, HttpStatus.OK);
    }

    /**
     * Responds to the GET request for all {@linkplain Player player} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link Player player}
     * 
     * @return ResponseEntity with array of {@link Player player} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/")
    public ResponseEntity<Player[]> searchTeam(@RequestParam String name) {
        LOG.info("GET /inventory/?name="+name);
        try {
            Player[] players = teamDAO.searchTeam(name);
            return new ResponseEntity<Player[]>(players,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


        // Replace below with your implementation
    }

    /**
     * Creates a {@linkplain Player player} with the provided item object
     * 
     * @param player - The {@link Player player} to create
     * 
     * @return ResponseEntity with created {@link Player player} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Player player} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        LOG.info("POST /inventory " + player);
        try {
            Player newPlayer = teamDAO.createPlayer(player);
            if (this.teamDAO.createPlayer(newPlayer) == null){
                return new ResponseEntity<Player>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<Player>(newPlayer,HttpStatus.CREATED);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<Player>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    /**
     * Updates the {@linkplain Player player} with the provided {@linkplain Player player} object, if it exists
     * 
     * @param item The {@link Player player} to update
     * 
     * @return ResponseEntity with updated {@link Player player} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of OK if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * @throws IOException
     */
    @PutMapping("")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player, String name, int age, int rating) throws IOException {
        LOG.info("PUT /inventory " + player);
        if(this.teamDAO.getPlayer(player.getName()) != null) {
            try {
                Player newPlayer = teamDAO.updatePlayer(player, name, age, rating);
                return new ResponseEntity<Player>(newPlayer,HttpStatus.OK);
            }
            catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<Player>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        } else {
            System.out.println("Player does not exist.");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
    }

    /**
     * Deletes a {@linkplain Player player} with the given name
     * 
     * @param name The name of the {@link Player player} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> deletePlayer(@PathVariable String name) {
        LOG.info("DELETE /inventory/" + name);
        try {
            this.teamDAO.deletePlayer(name);
            if (this.teamDAO.deletePlayer(name) == false){
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("Item not found.");
            return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
