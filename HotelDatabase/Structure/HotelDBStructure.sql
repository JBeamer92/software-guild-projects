/*
	CREATE STRUCTURE
*/

DROP DATABASE IF EXISTS HotelDB;

CREATE DATABASE HotelDB;

USE HotelDB;

CREATE TABLE RoomTypes
(
	RoomTypeID INT NOT NULL auto_increment,
    RoomTypeName VARCHAR(30) NOT NULL,
    PRIMARY KEY (RoomTypeID)
);

CREATE TABLE RoomRates
(
	RoomRateID INT NOT NULL auto_increment,
    RoomTypeID INT NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    Price DECIMAL(6,2) NOT NULL,
    PRIMARY KEY (RoomRateID),
    FOREIGN KEY (RoomTypeID) REFERENCES RoomTypes(RoomTypeID)
);

CREATE TABLE Rooms
(
	RoomID INT NOT NULL auto_increment,
    RoomNumber INT NOT NULL,
    Floor INT NOT NULL,
    MaxCapacity INT NOT NULL,
    RoomTypeID INT NOT NULL,
    PRIMARY KEY (RoomID),
    FOREIGN KEY (RoomTypeID) REFERENCES RoomTypes(RoomTypeID)
);

CREATE TABLE ContactInfo
(
	ContactID INT NOT NULL auto_increment,
    PhoneNumber VARCHAR(14) NOT NULL,
    Email VARCHAR(30) NOT NULL,
    PRIMARY KEY (ContactID)
);

CREATE TABLE Guests
(
	GuestID INT NOT NULL auto_increment,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    DateOfBirth DATE NULL,
    ContactID INT NULL,
    PRIMARY KEY (GuestID),
    FOREIGN KEY (ContactID) REFERENCES ContactInfo(ContactID)
);

CREATE TABLE Reservations
(
	ReservationID INT NOT NULL auto_increment,
    CustomerID INT NOT NULL,
    PRIMARY KEY (ReservationID),
    FOREIGN KEY (CustomerID) REFERENCES Guests(GuestID)
);

CREATE TABLE GuestReservations
(
	ReservationID INT NOT NULL,
    GuestID INT NOT NULL,
    RoomID INT NULL,
    FOREIGN KEY (ReservationID) REFERENCES Reservations(ReservationID),
    FOREIGN KEY (GuestID) REFERENCES Guests(GuestID),
    FOREIGN KEY (RoomID) REFERENCES Rooms(RoomID)
);

CREATE TABLE ReservationRooms
(
	ReservationID INT NOT NULL,
    RoomID INT NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    FOREIGN KEY (ReservationID) REFERENCES Reservations(ReservationID),
    FOREIGN KEY (RoomID) REFERENCES Rooms(RoomID)
);

CREATE TABLE Amenities
(
	AmenityID INT NOT NULL auto_increment,
    AmenityType VARCHAR(30) NOT NULL,
    Price DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (AmenityID)
);

CREATE TABLE AmenitiesForRoomType
(
	RoomTypeID INT NOT NULL,
    AmenityID INT NOT NULL,
    FOREIGN KEY (RoomTypeID) REFERENCES RoomTypes(RoomTypeID),
    FOREIGN KEY (AmenityID) REFERENCES Amenities(AmenityID)
);

CREATE TABLE Bills
(
	BillID INT NOT NULL auto_increment,
    ReservationID INT NOT NULL,
    Total DECIMAL(11,2) NOT NULL,
    Tax DECIMAL(11,2) NOT NULL,
    PRIMARY KEY (BillID),
    FOREIGN KEY (ReservationID) REFERENCES Reservations(ReservationID)
);

CREATE TABLE BillDetails
(
	BillDetailID INT NOT NULL auto_increment,
    BillID INT NOT NULL,
    Description VARCHAR(50) NOT NULL,
    Price DECIMAL(9,2) NOT NULL,
    PRIMARY KEY (BillDetailID),
    FOREIGN KEY (BillID) REFERENCES Bills(BillID)
);

CREATE TABLE AddOns
(
	AddOnID INT NOT NULL auto_increment,
    AddOnType VARCHAR(30) NOT NULL,
    Price DECIMAL(6,2) NOT NULL,
    PRIMARY KEY (AddOnID)
);

CREATE TABLE Promotions
(
	PromotionID INT NOT NULL auto_increment,
    PromotionType VARCHAR(45) NOT NULL,
    Discount DECIMAL(6,2) NOT NULL,
    IsPercent BOOL NOT NULL,
    PRIMARY KEY (PromotionID),
    StartDate DATE NULL,
    EndDate DATE NULL
);

CREATE TABLE ReservationAddOns
(
	ReservationID INT NOT NULL,
    AddOnID INT NOT NULL,
    RoomID INT NOT NULL DEFAULT 0,
    FOREIGN KEY (ReservationID) REFERENCES Reservations(ReservationID),
    FOREIGN KEY (AddOnID) REFERENCES AddOns(AddOnID),
    FOREIGN KEY (RoomID) REFERENCES Rooms(RoomID)
);

CREATE TABLE ReservationPromotions
(
	ReservationID INT NOT NULL,
    PromotionID INT NOT NULL,
    FOREIGN KEY (ReservationID) REFERENCES Reservations(ReservationID),
    FOREIGN KEY (PromotionID) REFERENCES Promotions(PromotionID)
);


/*

	INSERT DATA

*/
