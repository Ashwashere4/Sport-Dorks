package com.estore.api.estoreapi.controller.shoppingCart;
import com.estore.api.estoreapi.model.Inventory.Item;

import java.util.HashMap;

public class ShoppingCart {
    /** A hashmap representation of the shopping cart where the key is the name
     * of the item and the value is the item object */
    HashMap<String, Item> shoppingCart;

    public ShoppingCart(HashMap<String, Item> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
