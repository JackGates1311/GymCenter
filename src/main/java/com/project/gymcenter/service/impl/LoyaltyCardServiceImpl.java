package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.LoyaltyCardDAO;
import com.project.gymcenter.model.LoyaltyCard;
import com.project.gymcenter.service.LoyaltyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoyaltyCardServiceImpl implements LoyaltyCardService {

    @Autowired
    LoyaltyCardDAO loyaltyCardDAO;

    @Override
    public void sendRequestForLoyaltyCard(Long userId) {

        loyaltyCardDAO.sendRequestForLoyaltyCard(userId);
    }

    @Override
    public void approveLoyaltyCard(Long id) {

        loyaltyCardDAO.approveLoyaltyCard(id);
    }

    @Override
    public void declineLoyaltyCard(Long id) {

        loyaltyCardDAO.declineLoyaltyCard(id);

    }

    @Override
    public List<LoyaltyCard> findLoyaltyCardRequests() {

        return loyaltyCardDAO.findLoyaltyCardRequests();
    }

    @Override
    public boolean findIfUserIsAlreadyHaveLoyaltyCard(Long userId) {

        return loyaltyCardDAO.findIfUserIsAlreadyHaveLoyaltyCard(userId);
    }
}
