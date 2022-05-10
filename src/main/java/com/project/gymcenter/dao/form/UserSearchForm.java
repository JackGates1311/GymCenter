package com.project.gymcenter.dao.form;

public class UserSearchForm {


    private String userName;
    private String userRole;
    private String userSortBy;

    public UserSearchForm() {

        super();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserSortBy() {
        return userSortBy;
    }

    public void setUserSortBy(String userSortBy) {
        this.userSortBy = userSortBy;
    }
}
