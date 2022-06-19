package com.project.gymcenter.dao;

import com.project.gymcenter.model.WishList;

import java.util.List;

public interface WishListDAO {

    public List<WishList> getWishListByUserId(Long id);

    public void deleteById(Long id);

    public boolean checkIfAlreadyExistsInWishList(Long id, Long userId);

    public void addWorkoutToWishList(Long id, Long userId);
}
