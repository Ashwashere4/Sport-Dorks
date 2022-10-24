package com.estore.api.estoreapi.controller.facilities_list;

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

import com.estore.api.estoreapi.model.Facilities;
import com.estore.api.estoreapi.persistence.FlistDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;




@RestController
@RequestMapping("Facilities List")
public class flistController {
    private static final Logger LOG = Logger.getLogger(flistController.class.getName());
    private FlistDAO flistDAO;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param flistDAO The {@link flistDAO inventory Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public flistController(FlistDAO flistDAO) {
        this.flistDAO = flistDAO;
    }

    /**     * 
     * @param name The string used to locate the {@link Facilities Team}
     * 
     * @return ResponseEntity with {@link Facilities Team} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{name}")
    public ResponseEntity<Facilities> getTeam(@PathVariable int code) {
        LOG.info("GET /flist/" + code);
        try {
            Facilities team = flistDAO.getTeam(code);
            if (team != null)
                return new ResponseEntity<Facilities>(team,HttpStatus.OK);
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
     * Responds to the GET request for all {@linkplain Facilities Team}
     * 
     * @return ResponseEntity with array of {@link Facilities Team} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Facilities[]> getTeams() {
        LOG.info("GET /flist");
        Facilities[] team;
        try {
            team = flistDAO.getTeams();

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Facilities[]>(team, HttpStatus.OK);
    }

    /**
     * Responds to the GET request for all {@linkplain Facilities Team} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link Facilities Team}
     * 
     * @return ResponseEntity with array of {@link Facilities Team} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all heroes that contain the text "ma"
     * GET http://localhost:8080/heroes/?name=ma
     */
    @GetMapping("/")
    public ResponseEntity<Facilities[]> searchTeam(@RequestParam String name) {
        LOG.info("GET /flist/?name="+name);
        try {
            Facilities[] teams = flistDAO.searchTeams(name);
            return new ResponseEntity<Facilities[]>(teams,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


        // Replace below with your implementation
    }

    /**
     * Creates a {@linkplain Facilities teams} with the provided item object
     * 
     * @param item - The {@link Facilities Team} to create
     * 
     * @return ResponseEntity with created {@link Facilities Team} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Facilities Team} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Facilities> createTeam(@RequestBody String name, int team_code, int player_count) {
        LOG.info("POST /flist " + name + team_code + player_count);
        try {
            Facilities newteam = flistDAO.createTeam(name, team_code, player_count);
            if (this.flistDAO.createTeam(name, team_code, player_count) == null){
                return new ResponseEntity<Facilities>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<Facilities>(newteam,HttpStatus.CREATED);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<Facilities>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    /**
     * Updates the {@linkplain Facilities team} with the provided {@linkplain Facilties team} object, if it exists
     * 
     * @param item The {@link Facilities team} to update
     * 
     * @return ResponseEntity with updated {@link Facilities team} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of OK if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Facilities> updateTeam(@RequestBody Facilities team, String name, int team_code, int player_count) {
        LOG.info("PUT /inventory " + team);
        if(getTeam(team.getTeamcode()) != null) {
            try {
                Facilities updatedteam = flistDAO.updateTeam(team, name, team_code, player_count);
                return new ResponseEntity<Facilities>(updatedteam,HttpStatus.OK);
            }
            catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<Facilities>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        } else {
            System.out.println("Item does not exist.");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
    }

    /**
     * Deletes a {@linkplain Facilities Team} with the given name
     * 
     * @param name The name of the {@link Facilities team} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> deleteTeam(@PathVariable int code) {
        LOG.info("DELETE /inventory/" + code);
        try {
            this.flistDAO.deleteTeam(code);
            if (this.flistDAO.deleteTeam(code) == false){
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("Item not found.");
            return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        

    }
}