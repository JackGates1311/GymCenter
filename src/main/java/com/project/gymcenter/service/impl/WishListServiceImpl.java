package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.WishListDAO;
import com.project.gymcenter.model.WishList;
import com.project.gymcenter.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    WishListDAO wishListDAO;

    @Override
    public List<WishList> getWishListByUserId(Long id) {

        return wishListDAO.getWishListByUserId(id);
    }

    @Override
    public void deleteById(Long id) {

        wishListDAO.deleteById(id);
    }

    @Override
    public boolean checkIfAlreadyExistsInWishList(Long id, Long userId) {

        return wishListDAO.checkIfAlreadyExistsInWishList(id, userId);
    }

    @Override
    public void addWorkoutToWishList(Long id, Long userId) {

        wishListDAO.addWorkoutToWishList(id, userId);
    }
}
