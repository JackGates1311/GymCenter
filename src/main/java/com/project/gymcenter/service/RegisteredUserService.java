package com.project.gymcenter.service;

import com.project.gymcenter.model.RegisteredUser;

import java.util.List;

public interface RegisteredUserService {

    public List<RegisteredUser> findAll();

    public List<RegisteredUser> find(String userName, String userRole, String userSortBy);

    public RegisteredUser findOne(String userName, String password);

    public RegisteredUser findById (int userId);

    public void add(RegisteredUser registeredUser);

    public void update(RegisteredUser registeredUser, int userId);

    public void changePassword(String newPassword, int userId);

    public int delete(RegisteredUser registeredUser);

}
