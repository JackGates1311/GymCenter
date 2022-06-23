package com.project.gymcenter.service;

import com.project.gymcenter.model.LoyaltyCard;

import java.util.List;

public interface LoyaltyCardService {

    public void sendRequestForLoyaltyCard(Long userId);

    public void approveLoyaltyCard(Long id);

    public void declineLoyaltyCard(Long id);

    public List<LoyaltyCard> findLoyaltyCardRequests();

    public boolean findIfUserIsAlreadyHaveLoyaltyCard(Long userId);
}
