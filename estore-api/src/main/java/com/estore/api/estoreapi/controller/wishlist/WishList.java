package com.estore.api.estoreapi.controller.wishlist;
import com.estore.api.estoreapi.model.Inventory.Item;

import java.util.HashMap;

public class WishList {
    /** A hashmap representation of the shopping cart where the key is the name
     * of the item and the value is the item object */
    HashMap<String, Item> wishList;

    public WishList(HashMap<String, Item> wishList) {
        this.wishList = wishList;
    }
}
