DROP DATABASE IF EXISTS SuperHeroSightings;

CREATE DATABASE SuperHeroSightings;

USE SuperHeroSightings;

CREATE TABLE Heroes
(
	HeroID INT NOT NULL AUTO_INCREMENT,
    HeroName VARCHAR(75) NOT NULL,
    Description VARCHAR(100) NOT NULL,
    PRIMARY KEY (HeroID)
);

CREATE TABLE Powers
(
	PowerID INT NOT NULL AUTO_INCREMENT,
    PowerName VARCHAR(100) NOT NULL,
    Description VARCHAR(100) NOT NULL,
    PRIMARY KEY (PowerID)
);


CREATE TABLE Locations
(
	LocationID INT NOT NULL AUTO_INCREMENT,
    LocationName VARCHAR(75) NOT NULL,
    Description VARCHAR(100) NOT NULL,
    Address VARCHAR(50) NOT NULL DEFAULT 'Unknown',
    City VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    Region VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    Country VARCHAR(50) NOT NULL DEFAULT 'Unknown',
    Latitude DECIMAL(6,4) NOT NULL DEFAULT 00.0000,
    Longitude DECIMAL(7,4) NOT NULL DEFAULT 000.0000,
    PRIMARY KEY (LocationID)
);

CREATE TABLE Organizations
(
	OrgID INT NOT NULL AUTO_INCREMENT,
    OrgName VARCHAR(75) NOT NULL,
    Description VARCHAR(100) NOT NULL,
    LocationID INT NOT NULL,
    PhoneType VARCHAR(20) NOT NULL,
    PhoneNumber VARCHAR(15) NOT NULL,
    PRIMARY KEY (OrgID),
    FOREIGN KEY (LocationID) REFERENCES Locations(LocationID)
);

CREATE TABLE Sightings
(
	SightingID INT NOT NULL AUTO_INCREMENT,
    LocationID INT NOT NULL,
    SightingDate DATE NOT NULL,
    PRIMARY KEY (SightingID),
    FOREIGN KEY (LocationID) REFERENCES Locations(LocationID)
);

CREATE TABLE HeroPowers
(
	HeroID INT NOT NULL,
    PowerID INT NOT NULL,
    FOREIGN KEY (HeroID) REFERENCES Heroes(HeroID),
    FOREIGN KEY (PowerID) REFERENCES Powers(PowerID)
);

CREATE TABLE HeroesInOrganizations
(
	HeroID INT NOT NULL,
    OrgID INT NOT NULL,
    FOREIGN KEY (HeroID) REFERENCES Heroes(HeroID),
    FOREIGN KEY (OrgID) REFERENCES Organizations(OrgID)
);

CREATE TABLE HeroSightings
(
	HeroID INT NOT NULL,
    SightingID INT NOT NULL,
    FOREIGN KEY (HeroID) REFERENCES Heroes(HeroID),
    FOREIGN KEY (SightingID) REFERENCES Sightings(SightingID)
);
