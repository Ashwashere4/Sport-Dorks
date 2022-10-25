// package com.estore.api.estoreapi.persistence.wishList;

// import java.io.File;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Map;
// import java.util.TreeMap;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import com.estore.api.estoreapi.model.Inventory.Item;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @Component
// public class WishListFileDAO {
//      /**
//      * The current wishlist.
//      */
//     private Map<String, Item> wishList;

//     /**
//      * The file name of the wishList file.
//      */
//     private String filename;

//     /**
//      * The object mapper.
//      */

//     private ObjectMapper objectMapper;

//     /**
//      * 
//      * @param wishList
//      */
//     public WishListFileDAO(@Value("${wishList.filename}") String filename, ObjectMapper objectMapper) throws IOException {
//             this.filename = filename;
//             this.objectMapper = objectMapper;
//             loadWishList();
//         }

//     private ArrayList<Item> getWishListArray() {
//         return new ArrayList<>(wishList.values());
//     }

//     public Item[] getItems() throws IOException {
//         return getWishListArray().toArray(new Item[0]);
//     }

//     public Item addItem(Item item) throws IOException {
//         wishList.put(item.getName(), item);
//         saveWishList();
//         return item;
//     }

//     public boolean deleteItem(String name) throws IOException{
//         wishList.remove(name);
//             return true;
//     }
    
//     /**
//      * save the inventory to the file.
//     **/
//     private void saveWishList() throws IOException {
//         objectMapper.writeValue(new File(filename), getWishListArray());
//     }
    
//     /**
//      * Load the inventory from the file.
//      */
//     private void loadWishList() throws IOException {
//         wishList = new TreeMap<>();
//         Item[] wishListArray = objectMapper.readValue(new File(filename), Item[].class);
//         for (Item item : wishListArray) {
//             wishList
//             .put(item.getName(), item);
//         }
//     }
// }
