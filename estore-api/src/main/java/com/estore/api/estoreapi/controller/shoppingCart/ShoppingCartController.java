package com.estore.api.estoreapi.controller.shoppingCart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Inventory.Item;
import com.estore.api.estoreapi.persistence.shoppingCart.ShoppingCartDAO;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("ShoppingCart")
public class ShoppingCartController {
    private static final Logger LOG = Logger.getLogger(ShoppingCartController.class.getName());
    private ShoppingCartDAO shoppingCartDAO;

    public ShoppingCartController(ShoppingCartDAO shoppingCartDAO){
        this.shoppingCartDAO = shoppingCartDAO;
    }

    @GetMapping("")
    public ResponseEntity<Item[]> getCart() {
        LOG.info("GET /shoppingCart");
        Item[] items;
        try {
            items = shoppingCartDAO.getCart();
            return new ResponseEntity<Item[]>(items, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{name}")
    public ResponseEntity<Item> addItem(@PathVariable String name){
        LOG.info("POST /shoppingCart/" + name);
        try{
            Item newItem = shoppingCartDAO.addItem(name);
            return new ResponseEntity<Item>(newItem,HttpStatus.CREATED);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<Item>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> deleteItem(@PathVariable String name) {
        LOG.info("DELETE /ShoppingCart/" + name);
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

    @PutMapping("/{name}")
    public ResponseEntity<Boolean> purchaseItem(@PathVariable String name) {
        LOG.info("PUT /ShoppingCart/" + name);
        try {
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
