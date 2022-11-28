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

import com.estore.api.estoreapi.model.Inventory.Item;
import com.estore.api.estoreapi.persistence.Inventory.InventoryDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("Inventory")
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
     * @param name The string used to locate the {@link Item item}
     * 
     * @return ResponseEntity with {@link Item item} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{name}")
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
        Item[] items;
        try {
            items = inventoryDAO.getItems();

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Item[]>(items, HttpStatus.OK);
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
        try {
            Item[] items = inventoryDAO.searchItems(name);
            return new ResponseEntity<Item[]>(items,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


        // Replace below with your implementation
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
        try {
            Item newItem = inventoryDAO.createItem(item);
            if (this.inventoryDAO.createItem(newItem) == null){
                return new ResponseEntity<Item>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<Item>(newItem,HttpStatus.CREATED);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<Item>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    /**
     * Updates the {@linkplain Item item} with the provided {@linkplain Item item} object, if it exists
     * 
     * @param item The {@link Item item} to update
     * 
     * @return ResponseEntity with updated {@link Item item} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of OK if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * @throws IOException
     */
    @PutMapping("")
    public ResponseEntity<Item> updateItem(@RequestBody Item item, String name, int quantity, int cost) throws IOException {

        
        
        LOG.info("PUT /inventory " + item);
        if(item!= null) {
            try {
                Item newItem = inventoryDAO.updateItem(item, name, quantity, cost);
                return new ResponseEntity<Item>(newItem,HttpStatus.OK);
            }
            catch (IOException e) {
                return new ResponseEntity<Item>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        } else {
            System.out.println("Item does not exist.");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
    }

    /**
     * Deletes a {@linkplain Item item} with the given name
     * 
     * @param name The name of the {@link Item item} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> deleteItem(@PathVariable String name) {
        LOG.info("DELETE /inventory/" + name);
        try {
            this.inventoryDAO.deleteItem(name);
            if (this.inventoryDAO.deleteItem(name) == false){
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("Item not found.");
            return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}