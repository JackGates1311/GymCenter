package com.project.gymcenter.dao;

import com.project.gymcenter.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDAO {

    public void addWorkoutToShoppingCart(com.project.gymcenter.model.ShoppingCart shoppingCart);

    public int generateShoppingCartId();

    public boolean checkIfAlreadyAddedToShoppingCart(ShoppingCart shoppingCart);

    public List<ShoppingCart> findAllByUserId(Long id);

    public void deleteById(Long id);

}
