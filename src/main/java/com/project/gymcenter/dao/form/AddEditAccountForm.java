package com.project.gymcenter.dao.form;

public class AddEditAccountForm {

    private String userName;
    private String userPassword;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private int dayOfUserDateBirth;
    private int monthOfUserDateBirth;
    private int yearOfUserDateBirth;
    private String userAddress;
    private String userPhoneNumber;

    public AddEditAccountForm() {

        super();
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

    public int getDayOfUserDateBirth() {
        return dayOfUserDateBirth;
    }

    public void setDayOfUserDateBirth(int dayOfUserDateBirth) {
        this.dayOfUserDateBirth = dayOfUserDateBirth;
    }

    public int getMonthOfUserDateBirth() {
        return monthOfUserDateBirth;
    }

    public void setMonthOfUserDateBirth(int monthOfUserDateBirth) {
        this.monthOfUserDateBirth = monthOfUserDateBirth;
    }

    public int getYearOfUserDateBirth() {
        return yearOfUserDateBirth;
    }

    public void setYearOfUserDateBirth(int yearOfUserDateBirth) {
        this.yearOfUserDateBirth = yearOfUserDateBirth;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
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

}
