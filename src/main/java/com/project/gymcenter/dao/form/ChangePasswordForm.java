package com.project.gymcenter.dao.form;

public class ChangePasswordForm {

    private String userNewPassword;

    private String userNewPasswordRepeated;

    public ChangePasswordForm() {

        super();

    }

    public String getUserNewPassword() {

        return userNewPassword;
    }

    public void setUserNewPassword(String userNewPassword) {

        this.userNewPassword = userNewPassword;
    }

    public String getUserNewPasswordRepeated() {

        return userNewPasswordRepeated;
    }

    public void setUserNewPasswordRepeated(String userNewPasswordRepeated) {

        this.userNewPasswordRepeated = userNewPasswordRepeated;
    }
}
