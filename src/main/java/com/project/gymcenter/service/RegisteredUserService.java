package com.project.gymcenter.service;

import com.project.gymcenter.model.RegisteredUser;

import java.util.List;

public interface RegisteredUserService {

    public List<RegisteredUser> findAll();

    public int add(RegisteredUser registeredUser);

    public int update(RegisteredUser registeredUser);

    public int delete(RegisteredUser registeredUser);

}
