/* CREATE DATABASE gymCenter; */
USE gymCenter;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS workout;
DROP TABLE IF EXISTS workoutIncludedTypes;
DROP TABLE IF EXISTS workoutType;
DROP TABLE IF EXISTS auditorium;
DROP TABLE IF EXISTS period;
DROP TABLE IF EXISTS periodReserved;
DROP TABLE IF EXISTS registeredUser;
DROP TABLE IF EXISTS loyaltyCard;
DROP TABLE IF EXISTS workoutComment;
DROP TABLE IF EXISTS shoppingCart;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE workout (

	workoutId LONG NOT NULL,
	workoutTypeName NVARCHAR(255) NOT NULL,
    workoutCoaches LONGTEXT NOT NULL, /* Store data here AS {Pera Peric, Mika Mikic....} */
    workoutDescription LONGTEXT NOT NULL,
    workoutPrice DOUBLE NOT NULL,
    workoutOrganizationType ENUM('Group','Individual'),
    workoutLevel ENUM('Amateur', 'Medium', 'Advanced'),
    workoutLength INT NOT NULL,
    workoutAverageRate DOUBLE,
    isDeleted BOOLEAN,
    workoutName NVARCHAR(255) NOT NULL,
    workoutImage LONGTEXT NOT NULL
    
);

CREATE TABLE workoutIncludedTypes (

    workoutId LONG NOT NULL,
    workoutTypeName NVARCHAR(255) NOT NULL

);

CREATE TABLE workoutType(

    workoutTypeName NVARCHAR(100) NOT NULL,
    workoutTypeDetailedDescription LONGTEXT NOT NULL

);

CREATE TABLE auditorium (

	auditoriumId NVARCHAR(255) PRIMARY KEY,
    capacity INT NOT NULL,
    isDeleted BOOLEAN

);

CREATE TABLE period (

    periodId LONG NOT NULL,
	auditoriumId NVARCHAR(255) NOT NULL,
    workoutId LONG NOT NULL,
    workoutDateTimeStart DATETIME NOT NULL,
    workoutDateTimeEnd DATETIME NOT NULL

);

CREATE TABLE periodReserved (

    periodReservationId LONG NOT NULL,
	periodId LONG NOT NULL,
    userId LONG NOT NULL,
    periodDateTimeReservation DATETIME NOT NULL

);

CREATE TABLE registeredUser (
	
    userId LONG NOT NULL,
    userName NVARCHAR(30) NOT NULL,
    userPassword NVARCHAR(255) NOT NULL,
    userEmail NVARCHAR(255) NOT NULL,
    userFirstName NVARCHAR(40) NOT NULL,
    userLastName NVARCHAR(40) NOT NULL,
    userDateBirth DATE NOT NULL,
    userAddress NVARCHAR(120) NOT NULL,
    userPhoneNumber NVARCHAR(20) NOT NULL,
    userDateTimeRegistration DATETIME NOT NULL,
    userRole ENUM('Administrator', 'Customer') NOT NULL,
    isDeleted BOOLEAN
    
);

CREATE TABLE loyaltyCard (

    loyaltyCardId LONG NOT NULL,
    workoutId LONG NOT NULL,
	userId LONG NOT NULL,
	discount INT NOT NULL,
    points INT NOT NULL,
    isApproved BOOLEAN
    
);

CREATE TABLE workoutComment (

    commentId LONG NOT NULL,
    userId LONG NOT NULL,
    workoutId LONG NOT NULL,
    workoutRate DOUBLE NOT NULL,
    commentDateTimePosted DATETIME NOT NULL,
    commentContent LONGTEXT NOT NULL,
    commentStatus ENUM('OnWaiting', 'Approved', 'Declined'),
    postCommentAsGuest BOOLEAN NOT NULL

);

CREATE TABLE shoppingCart (

	shoppingCartId LONG NOT NULL,
    periodId LONG NOT NULL,
    userId LONG NOT NULL

);

CREATE TABLE wishList (

    wishListId LONG NOT NULL,
    workoutId LONG NOT NULL,
    userId LONG NOT NULL

);