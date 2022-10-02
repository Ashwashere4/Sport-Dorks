package com.estore.api.estoreapi.controller.inventory;

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

import com.estore.api.estoreapi.model.Item;
import com.estore.api.estoreapi.persistence.Inventory;
import com.estore.api.estoreapi.persistence.InventoryDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Handles the REST API requests for the Hero resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author SWEN Faculty
 */

@RestController
@RequestMapping("heroes")
public class InventoryController {
    private static final Logger LOG = Logger.getLogger(InventoryController.class.getName());
    private InventoryDAO inventoryDAO;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param inventoryDAO The {@link InventoryDAO inventory Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public InventoryController(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    /**
     * Responds to the GET request for a {@linkplain Item item} for the given name
     * 
     * @param name The id used to locate the {@link Item item}
     * 
     * @return ResponseEntity with {@link Item item} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable String name) {
        LOG.info("GET /inventory/" + name);
        try {
            Item item = inventoryDAO.getItem(name);
            if (item != null)
                return new ResponseEntity<Item>(item,HttpStatus.OK);
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
     * Responds to the GET request for all {@linkplain Item item}
     * 
     * @return ResponseEntity with array of {@link Item item} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Item[]> getItems() {
        LOG.info("GET /inventory");

        // Replace below with your implementation
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Responds to the GET request for all {@linkplain Item item} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link Item item}
     * 
     * @return ResponseEntity with array of {@link Item item} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all heroes that contain the text "ma"
     * GET http://localhost:8080/heroes/?name=ma
     */
    @GetMapping("/")
    public ResponseEntity<Item[]> searchItem(@RequestParam String name) {
        LOG.info("GET /inventory/?name="+name);

        // Replace below with your implementation
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Creates a {@linkplain Item item} with the provided item object
     * 
     * @param item - The {@link Item item} to create
     * 
     * @return ResponseEntity with created {@link Item item} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Item item} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        LOG.info("POST /inventory " + item);
        Item newItem = new Item(item.getName(), item.getQuantity(), item.getCost());
        Inventory.inventory.put(item.getName(), newItem);
        return new ResponseEntity<Item>(item,HttpStatus.CREATED);
    }

    /**
     * Updates the {@linkplain Item item} with the provided {@linkplain Item item} object, if it exists
     * 
     * @param item The {@link Item item} to update
     * 
     * @return ResponseEntity with updated {@link Item item} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of OK if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Item> updateHero(@RequestBody Item item) {
        LOG.info("PUT /inventory " + item);
        if(getItem(item.getName()) != null) {
            Item newItem = new Item(item.getName(), item.getQuantity(), item.getCost());
            Inventory.inventory.put(item.getName(), newItem);
            return new ResponseEntity<Item>(newItem,HttpStatus.OK);
        } else {
            System.out.println("Item does not exist.");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        
    }

    /**
     * Deletes a {@linkplain Item item} with the given id
     * 
     * @param id The id of the {@link Item item} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Item> deleteHero(@PathVariable int id) {
        LOG.info("DELETE /inventory/" + id);

        // Replace below with your implementation
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}