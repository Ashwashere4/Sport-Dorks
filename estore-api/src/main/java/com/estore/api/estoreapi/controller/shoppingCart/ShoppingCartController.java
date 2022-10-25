package com.estore.api.estoreapi.controller.shoppingCart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping()
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
}
