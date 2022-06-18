package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.ShoppingCartDAO;
import com.project.gymcenter.service.ShoppingCartService;
import com.project.gymcenter.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    @Override
    public List<ShoppingCart> findAllByUserId(Long id) {

        return shoppingCartDAO.findAllByUserId(id);
    }

    @Override
    public boolean checkIfAlreadyAddedToShoppingCart(ShoppingCart shoppingCart) {

        return shoppingCartDAO.checkIfAlreadyAddedToShoppingCart(shoppingCart);
    }

    @Override
    public void addWorkoutToShoppingCart(ShoppingCart shoppingCart) {

        shoppingCartDAO.addWorkoutToShoppingCart(shoppingCart);
    }

    @Override
    public void deleteById(Long id) {

        shoppingCartDAO.deleteById(id);
    }

    @Override
    public ShoppingCart findByShoppingCartId(Long id) {

        return shoppingCartDAO.findByShoppingCartId(id);
    }

    @Override
    public ShoppingCart findPeriodByShoppingCartId(Long id) {

        return shoppingCartDAO.findPeriodByShoppingCartId(id);
    }
}
