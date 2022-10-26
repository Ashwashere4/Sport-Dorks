package com.estore.api.estoreapi.controller.wishlist;

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
import com.estore.api.estoreapi.persistence.wishList.WishListDAO;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("WishList")
public class WishListController {
    private static final Logger LOG = Logger.getLogger(WishListController.class.getName());
    private WishListDAO wishListDAO;

    public WishListController(WishListDAO wishListDAO){
        this.wishListDAO = wishListDAO;
    }

    @GetMapping("")
    public ResponseEntity<Item[]> getItems() {
        LOG.info("GET /wishList");
        Item[] items;
        try {
            items = wishListDAO.getItems();
            return new ResponseEntity<Item[]>(items, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{name}")
    public ResponseEntity<Item> addItem(@PathVariable String name){
        LOG.info("POST /wishList/" + name);
        try{
            Item newItem = wishListDAO.addItem(name);
            return new ResponseEntity<Item>(newItem,HttpStatus.CREATED);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<Item>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> deleteItem(@PathVariable String name) {
        LOG.info("DELETE /wishList/" + name);
        try {
            this.wishListDAO.deleteItem(name);
            if (this.wishListDAO.deleteItem(name) == false){
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("Item not found.");
            return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<Boolean> addItemToCart(@PathVariable String name) {
        LOG.info("PUT /ShoppingCart/" + name);
        try {
            this.wishListDAO.addItemToCart(name);
            if (this.wishListDAO.deleteItem(name) == false){
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("Item not found.");
            return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
