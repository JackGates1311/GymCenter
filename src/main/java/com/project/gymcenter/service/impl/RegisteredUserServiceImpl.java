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
    public RegisteredUser findOne(String userName, String password) {

        return registeredUserDAO.findOne(userName, password);
    }

    @Override
    public int add(RegisteredUser registeredUser) {
        return 0;
    }

    @Override
    public int update(RegisteredUser registeredUser) {
        return 0;
    }

    @Override
    public int delete(RegisteredUser registeredUser) {
        return 0;
    }
}
