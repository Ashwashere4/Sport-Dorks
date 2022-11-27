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

import com.estore.api.estoreapi.model.facilities.Facilities;
import com.estore.api.estoreapi.persistence.FacilitiesList.FlistDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;




@RestController
@RequestMapping("Facilities")
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
    @GetMapping("/{code}") //works 
    public ResponseEntity<Facilities> getFacility(@PathVariable int code) {
        LOG.info("GET /Facilities/" + code);
        try {
            Facilities team = flistDAO.getFacility(code);
            if (team != null)
                return new ResponseEntity<Facilities>(team,HttpStatus.OK);
            else
                System.out.println("Facility does not exist.");
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
    @GetMapping("") //works
    public ResponseEntity<Facilities[]> getFacilities() {
        LOG.info("GET /Facilities");
        Facilities[] facilicies;
        try {
            facilicies = flistDAO.getFacilities();

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Facilities[]>(facilicies, HttpStatus.OK);
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
    @GetMapping("/")//works
    public ResponseEntity<Facilities[]> searchFacilities(@RequestParam String name) {
        LOG.info("GET /Facilities/?name="+name);
        try {
            Facilities[] teams = flistDAO.searchFacilities(name);
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
    @PostMapping("")//doesnt
    public ResponseEntity<Facilities> createFacility(@RequestBody Facilities facility){
        LOG.info("POST /Facilities"+ facility);

        try {
            Facilities newFacilities = flistDAO.createFacility(facility);

            if (newFacilities == null){
                return new ResponseEntity<Facilities>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<Facilities>(newFacilities,HttpStatus.CREATED);
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
    @PutMapping("/update")//doesnt
    public ResponseEntity<Facilities> updateFacility(@RequestBody Facilities team, String name, String location, int facility_id) {
        LOG.info("PUT /Facilities "+ team);
        if(getFacility(team.getFacility_id()) != null) {
            try {
                Facilities updatedteam = flistDAO.updateFacility(team, name, location, facility_id);
                return new ResponseEntity<Facilities>(updatedteam,HttpStatus.OK);
            }
            catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<Facilities>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        } else {
            System.out.println("Facility does not exist.");
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
    @DeleteMapping("/delete/{code}")//Works
    public ResponseEntity<Boolean> deleteFacility(@PathVariable int code) {
        LOG.info("DELETE /Facilities/" + code);
        try {
            boolean deleted = this.flistDAO.deleteFacility(code);
            if (deleted == false){
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("Facility not found.");
            return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // @PutMapping("/reserve/{name}")
    // public ResponseEntity<Boolean> addTeamReserve(@PathVariable Team team, Facilities facility){
    //     LOG.info("PUT /reserve" + facility + team);

    //     try{
    //         this.flistDAO.addTeam_reserve(team, facility);
    //         if (this.flistDAO.addTeam_reserve(team, facility)==false){
    //             return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
    //         }

    //         return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    //         } catch (IOException e){
    //             System.out.print("Facility or Team not found");
    //             return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    //         }
    //     }

    // @DeleteMapping("/reserve/{code}")
    // public ResponseEntity<Boolean> removeTeamReserve(@PathVariable Facilities facility){
    //     LOG.info("DELETE /reserve" + facility);
    
    //     try{
    //         this.flistDAO.removeTeam_reserve(facility);
    //         if (this.flistDAO.removeTeam_reserve(facility)==false){
    //             return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
    //             }
    
    //         return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    //         } catch (IOException e){
    //             System.out.print("Facility or Team not found");
    //             return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    //         }
    //     }
}
