DROP DATABASE IF EXISTS SuperHeroSightings_test;

CREATE DATABASE SuperHeroSightings_test;

USE SuperHeroSightings_test;

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
    LocationName VARCHAR(75) NOT NULL DEFAULT 'Unknown',
    Description VARCHAR(100) NOT NULL DEFAULT 'Unknown',
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
    LocationID INT NULL,
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

/*
INSERT INTO Heroes (HeroName, Description)
VALUES ('Worm Man', 'Half worm, half man'), ('Leg Day Man', 'He puts the LEG in legendary.');

INSERT INTO Powers (PowerName, Description)
VALUES ('Burrow','Not exactly helpful to any bystanders.'),
('Lazer Beam Vision','Don\'t look now! No seriously, please don\'t.'),
('Killer Quads','Really useful when using public restrooms.');

INSERT INTO HeroPowers (HeroID, PowerID)
VALUES (1,1),(2,3);

INSERT INTO Locations (LocationName, Description, Address, City, Region, Country)
VALUES ('ALH Lair','Leg Day Man\'s mom\'s basement.','520 Main St, Apt 5A','Atlantic City','NJ','USA'),
('Office Depot', 'I hear they have a sale on paper.','6940 Mass Ave','Indianapolis','IN','USA');

INSERT INTO Organizations (OrgName, Description, LocationID, PhoneType, PhoneNumber)
VALUES ('Association of Lame Heroes','Not even the Island of Misfit Toys would take them...',1,'Office','1-800-555-5885');

INSERT INTO HeroesInOrganizations (HeroID, OrgID)
VALUES (1,1),(2,1);


INSERT INTO Sightings (LocationID, SightingDate)
VALUES (2, '2017-05-21'),(1,'2016-10-25');

INSERT INTO HeroSightings (HeroID, SightingID)
VALUES (1,1),(1,2),(2,2);
*/
