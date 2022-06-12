package com.project.gymcenter.service;

import com.project.gymcenter.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    public List<ShoppingCart> findAllByUserId(Long id);

    public boolean checkIfAlreadyAddedToShoppingCart(ShoppingCart shoppingCart);

    public void addWorkoutToShoppingCart(ShoppingCart shoppingCart);

    public void deleteById(Long id);

}
