package com.estore.api.estoreapi.controller.wishlist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping()
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
}
