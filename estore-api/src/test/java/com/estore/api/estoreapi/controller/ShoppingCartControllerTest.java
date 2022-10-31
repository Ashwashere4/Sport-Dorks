package com.estore.api.estoreapi.controller;

import com.estore.api.estoreapi.controller.shoppingCart.ShoppingCart;
import com.estore.api.estoreapi.persistence.shoppingCart.ShoppingCartDAO;
import com.estore.api.estoreapi.persistence.shoppingCart.ShoppingCartDAO;

public class ShoppingCartControllerTest {

    @Test
    public void testGetPlayer() throws IOException{
        Player player = new Player("Micheal", 19, 88);

        when(mockTeamDAO.getPlayer(player.getName())).thenReturn(player);

        ResponseEntity<Player> response = teamController.getPlayer(player.getName());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(player, response.getBody());
    }
    
}
