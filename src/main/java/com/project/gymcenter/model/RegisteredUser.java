package com.project.gymcenter.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegisteredUser {

    private Long userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private LocalDate userDateBirth;
    private String userAddress;
    private String userPhoneNumber;
    private LocalDateTime userDateTimeRegistration;
    private UserRole userRole;
    private Boolean isRemoved;

    public RegisteredUser() {

        this.userId = null;
        this.userName = null;
        this.userPassword = null;
        this.userEmail = null;
        this.userFirstName = null;
        this.userLastName = null;
        this.userDateBirth = null;
        this.userAddress = null;
        this.userPhoneNumber = null;
        this.userDateTimeRegistration = null;
        this.userRole = null;
        this.isRemoved = null;
    }

    public RegisteredUser(Long userId, String userName, String userPassword, String userEmail, String userFirstName,
                          String userLastName, LocalDate userDateBirth, String userAddress, String userPhoneNumber,
                          LocalDateTime userDateTimeRegistration, UserRole userRole, Boolean isRemoved) {

        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userDateBirth = userDateBirth;
        this.userAddress = userAddress;
        this.userPhoneNumber = userPhoneNumber;
        this.userDateTimeRegistration = userDateTimeRegistration;
        this.userRole = userRole;
        this.isRemoved = isRemoved;
    }

    public RegisteredUser(String userName, String userPassword, String userEmail, String userFirstName,
                          String userLastName, LocalDate userDateBirth, String userAddress, String userPhoneNumber,
                          LocalDateTime userDateTimeRegistration) {

        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userDateBirth = userDateBirth;
        this.userAddress = userAddress;
        this.userPhoneNumber = userPhoneNumber;
        this.userDateTimeRegistration = userDateTimeRegistration;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public LocalDate getUserDateBirth() {
        return userDateBirth;
    }

    public void setUserDateBirth(LocalDate userDateBirth) {
        this.userDateBirth = userDateBirth;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public LocalDateTime getUserDateTimeRegistration() {
        return userDateTimeRegistration;
    }

    public void setUserDateTimeRegistration(LocalDateTime userDateTimeRegistration) {
        this.userDateTimeRegistration = userDateTimeRegistration;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Boolean getRemoved() {
        return isRemoved;
    }

    public void setRemoved(Boolean removed) {
        isRemoved = removed;
    }

    @Override
    public String toString() {
        return "RegisteredUser{" + "\n" +
                "userId=" + userId + "\n" +
                ", userName='" + userName + '\'' + "\n" +
                ", userPassword='" + userPassword + '\'' + "\n" +
                ", userEmail='" + userEmail + '\'' + "\n" +
                ", userFirstName='" + userFirstName + '\'' + "\n" +
                ", userLastName='" + userLastName + '\'' + "\n" +
                ", userDateBirth=" + userDateBirth + "\n" +
                ", userAddress='" + userAddress + '\'' + "\n" +
                ", userPhoneNumber='" + userPhoneNumber + '\'' + "\n" +
                ", userDateTimeRegistration=" + userDateTimeRegistration + "\n" +
                ", userRole=" + userRole + "\n" +
                ", isRemoved=" + isRemoved + "\n" +
                '}';
    }
}
