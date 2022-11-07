package com.estore.api.estoreapi.controller;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.controller.shoppingCart.ShoppingCart;
import com.estore.api.estoreapi.controller.shoppingCart.ShoppingCartController;
import com.estore.api.estoreapi.persistence.shoppingCart.ShoppingCartDAO;
import com.estore.api.estoreapi.persistence.shoppingCart.ShoppingCartDAO;

public class ShoppingCartControllerTest {

    private ShoppingCartController shoppingCartController;
    private ShoppingCartDAO mockShoppingCartDAO;


    @BeforeEach
    public void setupShoppingCartController() {
        // mockshoppingDAO = mock(ShoppingCartDAO.class)
        mockShoppingCartDAO = mock(ShoppingCartDAO.class);
        shoppingCartController = new ShoppingCartController(mockShoppingCartDAO);
    }



    // @Test
    // public void testGetPlayer() throws IOException{
    //     Player player = new Player("Micheal", 19, 88);

    //     when(mockTeamDAO.getPlayer(player.getName())).thenReturn(player);

    //     ResponseEntity<Player> response = teamController.getPlayer(player.getName());

    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertEquals(player, response.getBody());
    // }

    @Test
    public void testGetShoppingCart() throws IOException{
        // HashMap<String, 

        ShoppingCart shoppingCart = new ShoppingCart(null);
    }


    
}
