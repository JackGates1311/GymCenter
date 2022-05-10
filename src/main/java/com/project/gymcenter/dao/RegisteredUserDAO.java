package com.project.gymcenter.dao;

import com.project.gymcenter.model.RegisteredUser;

import java.util.List;

public interface RegisteredUserDAO {

    public List<RegisteredUser> findAll();

    public RegisteredUser findOne(String userName, String password);

    public void add(RegisteredUser registeredUser);

    public void update(RegisteredUser registeredUser, int userId);

    public int delete(RegisteredUser registeredUser);

    public int generateUserId();

    public RegisteredUser findById(int userId);

    public void changePassword(String newPassword, int userId);

    public List<RegisteredUser> find(String userName, String userRole, String userSortBy);

    public void updateAccountStatus(RegisteredUser registeredUser, int userId);
}
