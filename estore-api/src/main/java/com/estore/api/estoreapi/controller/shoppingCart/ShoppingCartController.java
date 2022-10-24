package com.estore.api.estoreapi.controller.shoppingCart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Item;
import com.estore.api.estoreapi.persistence.InventoryDAO;
import com.estore.api.estoreapi.persistence.shoppingCart.ShoppingCartDAO;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Handles the REST API requests for the ShoppingCart resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author SWEN Faculty
 */

@RestController
@RequestMapping("ShoppingCart")
public class ShoppingCartController {
    private static final Logger LOG = Logger.getLogger(ShoppingCartController.class.getName());
    private ShoppingCartDAO shoppingCartDAO;
    private InventoryDAO inventoryDAO;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param shoppingCartDAO The {@link ShoppingCartDAO inventory Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public ShoppingCartController(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
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
        LOG.info("GET /shoppingCart");
        Item[] items;
        try {
            items = shoppingCartDAO.getItems();

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Item[]>(items, HttpStatus.OK);
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
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        LOG.info("POST /shoppingCart " + item);
        try {
            Item newItem = shoppingCartDAO.addItem(item);
            if (this.shoppingCartDAO.addItem(item) == null){
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
        LOG.info("DELETE /shoppingCart/" + name);
        try {
            this.shoppingCartDAO.deleteItem(name);
            if (this.shoppingCartDAO.deleteItem(name) == false){
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("Item not found.");
            return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * purchases a {@linkplain Item item} with the given name
     * 
     * @param name The name of the {@link Item item} to purchased and removes from inventory and shopping cart
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> purchaseItem(@PathVariable String name) {
        LOG.info("PURCHASE /shoppingCart/" + name);
        try {
            this.shoppingCartDAO.deleteItem(name);
            this.inventoryDAO.deleteItem(name);
            this.shoppingCartDAO.purchaseItem(name);
            if (this.shoppingCartDAO.deleteItem(name) == false){
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("Item not found.");
            return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
