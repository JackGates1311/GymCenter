package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.RegisteredUserDAO;
import com.project.gymcenter.model.RegisteredUser;
import com.project.gymcenter.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {

    @Autowired
    private RegisteredUserDAO registeredUserDAO;

    @Override
    public List<RegisteredUser> findAll() {

        return registeredUserDAO.findAll();
    }

    @Override
    public List<RegisteredUser> find(String userName, String userRole, String userSortBy) {

        return registeredUserDAO.find(userName, userRole, userSortBy);
    }

    @Override
    public RegisteredUser findOne(String userName, String password) {

        return registeredUserDAO.findOne(userName, password);
    }

    @Override
    public RegisteredUser findById(int userId) {

        return registeredUserDAO.findById(userId);
    }

    @Override
    public void add(RegisteredUser registeredUser) {

        registeredUserDAO.add(registeredUser);
    }

    @Override
    public void update(RegisteredUser registeredUser, int userId) {

        registeredUserDAO.update(registeredUser, userId);
    }

    @Override
    public void updateAccountStatus(RegisteredUser registeredUser, int userId) {

        registeredUserDAO.updateAccountStatus(registeredUser, userId);
    }

    @Override
    public void changePassword(String newPassword, int userId) {

        registeredUserDAO.changePassword(newPassword, userId);
    }

    @Override
    public int delete(RegisteredUser registeredUser) {
        return 0;
    }
}
