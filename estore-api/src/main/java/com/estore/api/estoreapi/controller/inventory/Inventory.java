package com.estore.api.estoreapi.controller;

<<<<<<< HEAD
public class Inventory {
    
    public static void new_product(String product_name, int product_price, int product_stock_amount){

        
    }
    
}
=======
import java.util.HashMap;

public class Inventory {
    /** A hashmap representation of the inventory where the key is the name
     * of the item and the value is the item object */
    HashMap<String, Item> inventory;

    /**
     * 
     * @param inventory
     */
    public Inventory(HashMap<String, Item> inventory) {
        this.inventory = inventory;
    }
}
>>>>>>> 942af11a5a7c317cbb75b390b5579fdf3b4e0efd
