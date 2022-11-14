package com.estore.api.estoreapi.controller.leagues;

import java.io.IOException;
import java.util.ArrayList;
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
import com.estore.api.estoreapi.model.Teams.Team;
import com.estore.api.estoreapi.persistence.League.LeagueDAO;

@RestController
@RequestMapping("League")
public class LeagueController {
    private static final Logger LOG = Logger.getLogger(LeagueController.class.getName());
    private LeagueDAO leagueDAO;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param leagueDAO The {@link LeagueDAO league Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public LeagueController(LeagueDAO leagueDAO) {
        this.leagueDAO = leagueDAO;
    }

    /**
     * Responds to the GET request for a {@linkplain Team team} for the given id
     * 
     * @param id The integer used to locate the {@link Team team}
     * 
     * @return ResponseEntity with {@link Team team} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable int id) {
        LOG.info("GET /League/" + id);
        try {
            Team team = leagueDAO.getTeam(id);
            if (team != null)
                return new ResponseEntity<Team>(team,HttpStatus.OK);
            else
                System.out.println("Team does not exist.");
                return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Team team} objects
     * 
     * @return ResponseEntity with array of {@link Team team} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Team[]> getTeams() {
        LOG.info("GET /League");
        Team[] teams;
        try {
            teams = leagueDAO.getTeams();
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Team[]>(teams, HttpStatus.OK);
    }

    /**
     * Responds to the GET request for all {@linkplain Team team} whose id contains
     * the text in id
     * 
     * @param term The term parameter which contains the text used to find the {@link Team team}
     * 
     * @return ResponseEntity with array of {@link Team team} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/")
    public ResponseEntity<Team[]> searchLeague(@RequestParam String term) {
        LOG.info("GET /League/?id="+term);
        try {
            Team[] teams = leagueDAO.searchLeague(term);
            return new ResponseEntity<Team[]>(teams,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a {@linkplain Team team} with the provided item object
     * 
     * @param Team - The {@link Team team} to create
     * 
     * @return ResponseEntity with created {@link Team team} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Team team} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        LOG.info("POST /League " + team);
        try {
            Team newTeam = leagueDAO.createTeam(team);
            if (newTeam == null){
                return new ResponseEntity<Team>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<Team>(newTeam,HttpStatus.CREATED);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<Team>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    /**
     * Updates the {@linkplain Team team} with the provided {@linkplain Team team} object, if it exists
     * 
     * @param team The {@link Team team} to update
     * 
     * @return ResponseEntity with updated {@link Team team} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of OK if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * @throws IOException
     */
    @PutMapping("")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team, ArrayList<Player> roster, int id) throws IOException {
        LOG.info("PUT /League " + team);
        if(this.leagueDAO.getTeam(team.getId()) != null) {
            try {
                Team newTeam = leagueDAO.updateTeam(team, roster, id);
                return new ResponseEntity<Team>(newTeam,HttpStatus.OK);
            }
            catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<Team>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        } else {
            System.out.println("Team does not exist.");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
    }

    /**
     * Deletes a {@linkplain Team team} with the given name
     * 
     * @param id The id of the {@link Team team} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTeam(@PathVariable int id) {
        LOG.info("DELETE /League/" + id);
        try {
            this.leagueDAO.deleteTeam(id);
            if (this.leagueDAO.deleteTeam(id) == false){
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("Team not found.");
            return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
