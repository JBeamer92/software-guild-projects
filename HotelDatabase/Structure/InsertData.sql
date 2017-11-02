USE HotelDB;


-- ROOM TYPES
INSERT INTO RoomTypes(RoomTypeName)
VALUES ('Single'), ('Double'), ('King');

-- ROOM RATES
INSERT INTO RoomRates(RoomTypeID, StartDate, EndDate, Price)
VALUES (1,'2016/1/1','2016/6/30',89.99),(1,'2016/7/1','2016/12/31',99.99),
(2,'2016/1/1','2016/6/30',99.99),(2,'2016/7/1','2016/12/31',109.99),
(3,'2017/1/1','2016/6/30',119.99),(3,'2016/7/1','2016/12/31',129.99),
(1,'2017/1/1','2016/6/30',89.99),(1,'2017/7/1','2016/12/31',99.99),
(2,'2017/1/1','2016/6/30',99.99),(2,'2017/7/1','2016/12/31',109.99),
(3,'2017/1/1','2016/6/30',119.99),(3,'2017/7/1','2016/12/31',129.99),
(1,'2018/1/1','2016/6/30',91.99),(1,'2018/7/1','2016/12/31',99.99),
(2,'2018/1/1','2016/6/30',109.99),(2,'2018/7/1','2016/12/31',118.99),
(3,'2018/1/1','2016/6/30',123.99),(3,'2018/7/1','2016/12/31',134.99);

-- ROOMS
INSERT INTO Rooms(RoomNumber, Floor, MaxCapacity, RoomTypeID)
VALUES (10,1,4,1),(11,1,3,3),(12,1,4,3),(13,1,4,2),(14,1,4,2),(15,1,3,1),(10,2,4,2),(11,2,3,1),(12,2,4,3),(13,2,4,2),(14,2,4,2),(15,2,3,1);

-- CONTACT INFO
INSERT INTO ContactInfo(PhoneNumber, Email)
VALUES ('215-756-1118', 'mothersmilk@gmail.com'),('215-473-9987', 'lionmcp@gmail.com'),
('215-999-7420', 'feastonyoureyes@yahoo.com'),('215-666-6666', 'mothermcpoyle@hotmail.com'),
('276-786-4785', 'docmcp@gmail.com');
insert into ContactInfo (PhoneNumber, Email) values ('742-398-0279', 'bbellamy0@umn.edu');
insert into ContactInfo (PhoneNumber, Email) values ('901-900-9081', 'esimeonov1@photobucket.com');
insert into ContactInfo (PhoneNumber, Email) values ('572-820-5699', 'cgutierrez2@cbc.ca');
insert into ContactInfo (PhoneNumber, Email) values ('761-588-1868', 'cklimentyev3@feedburner.com');
insert into ContactInfo (PhoneNumber, Email) values ('427-537-7210', 'bnote4@sogou.com');
insert into ContactInfo (PhoneNumber, Email) values ('187-774-1461', 'cshuter5@theglobeandmail.com');
insert into ContactInfo (PhoneNumber, Email) values ('579-172-1464', 'csloat6@washingtonpost.com');
insert into ContactInfo (PhoneNumber, Email) values ('679-962-7809', 'cdewerk7@hugedomains.com');
insert into ContactInfo (PhoneNumber, Email) values ('940-957-8805', 'gfarry8@eventbrite.com');
insert into ContactInfo (PhoneNumber, Email) values ('693-309-9612', 'dmorin9@home.pl');
insert into ContactInfo (PhoneNumber, Email) values ('814-563-8923', 'dlutena@surveymonkey.com');
insert into ContactInfo (PhoneNumber, Email) values ('278-752-1921', 'gspaceyb@opensource.org');
insert into ContactInfo (PhoneNumber, Email) values ('567-877-3197', 'jfaradyc@aboutads.info');
insert into ContactInfo (PhoneNumber, Email) values ('428-625-6244', 'gabercrombyd@intel.com');
insert into ContactInfo (PhoneNumber, Email) values ('569-386-4872', 'mmccraye@w3.org');

-- GUESTS
INSERT INTO Guests (FirstName, LastName, DateOfBirth, ContactID)
VALUES ('Liam','McPoyle','1979-11-11',1),('Ryan','McPoyle','1979-11-11',1),('Margaret','McPoyle','1982-03-14',null),
('Doyle','McPoyle','1976-11-17',2),('Lion','McPoyle','1978-04-30',null),('Pappy','McPoyle','1928-06-19',3),
('Royal','McPoyle','1994-11-30',3),('Carl','McPoyle','1988-07-29',4),('Mother','McPoyle','1999-09-11',4),
('Doc','McPoyle','1966-05-21',5);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Johnath', 'Franck', '1952/08/09', 11);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Sascha', 'Pahlsson', '1949/08/19', 13);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Ursa', 'Surfleet', '1991/03/15', 19);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Gerladina', 'O''Currane', '1965/11/20', 7);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Linda', 'Urquhart', '1953/06/07', 20);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Boigie', 'Pockey', '1951/07/22', 18);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Oneida', 'Karolczyk', '1983/01/25', 13);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Benoit', 'Kaplan', '2002/11/18', 18);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Jerry', 'O''Donnelly', '2008/07/16', 13);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Arturo', 'Francino', '1970/02/11', 15);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Karna', 'Lumsdaine', '1975/01/18', 8);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Grantley', 'Fidler', '1941/09/06', 19);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Aili', 'Adds', '1985/01/06', 11);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Ezekiel', 'Cutchee', '1977/08/17', 7);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Torrence', 'Dunckley', '1982/05/25', 9);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Stacee', 'Blomfield', '2002/10/15', 15);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Shannon', 'Lynthal', '1995/05/18', 20);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Felice', 'Gossling', '1953/12/08', 8);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Darwin', 'Reading', '2002/03/29', 12);
insert into Guests (FirstName, LastName, DateOfBirth, ContactID) values ('Bethena', 'Savatier', '1949/01/03', 10);

-- RESERVATIONS
INSERT INTO Reservations(CustomerID)
VALUES (1),(4),(6),(8),(10);
insert into Reservations (CustomerID) values (17);
insert into Reservations (CustomerID) values (20);
insert into Reservations (CustomerID) values (8);
insert into Reservations (CustomerID) values (22);
insert into Reservations (CustomerID) values (24);
INSERT INTO Reservations(CustomerID)
VALUES (1),(2);

-- RESERVATION ROOMS
INSERT INTO ReservationRooms(ReservationID, RoomID, StartDate, EndDate)
VALUES (1,1,'2016/10/26','2016/11/1'),(1,2,'2016/10/26','2016/11/1'),(2,3,'2016/10/28','2016/11/1'),(3,4,'2016/10/29','2016/11/2'),
(4,5,'2016/10/28','2016/11/1'),(5,6,'2016/10/26','2016/11/1');
insert into ReservationRooms (ReservationID, RoomID, StartDate, EndDate) values (6, 10, '2016/01/02','2016/1/5');
insert into ReservationRooms (ReservationID, RoomID, StartDate, EndDate) values (6, 11, '2016/01/02','2016/1/5');
insert into ReservationRooms (ReservationID, RoomID, StartDate, EndDate) values (7, 7, '2016/03/18','2016/3/20');
insert into ReservationRooms (ReservationID, RoomID, StartDate, EndDate) values (8, 6, '2016/11/02','2016/11/3');
insert into ReservationRooms (ReservationID, RoomID, StartDate, EndDate) values (9, 11, '2016/08/24','2016/8/30');
insert into ReservationRooms (ReservationID, RoomID, StartDate, EndDate) values (9, 12, '2016/08/24','2016/8/30');
insert into ReservationRooms (ReservationID, RoomID, StartDate, EndDate) values (10, 10, '2016/05/20','2016/5/25');
INSERT INTO ReservationRooms(ReservationID, RoomID, StartDate, EndDate)
VALUES (11,8,'2017-10-10','2017-10-18'),(12,9,'2017-10-10','2017-10-18');


-- GUEST RESERVATIONS
INSERT INTO GuestReservations(ReservationID, GuestID, RoomID)
VALUES (1,1,1),(1,2,1),(1,3,2),(2,4,3),(2,5,3),(3,6,4),(3,7,4),(4,8,5),(4,9,5),(5,10,6);
INSERT INTO GuestReservations(ReservationID, GuestID, RoomID)
VALUES (6,17,10),(6,8,10),(6,2,11),(7,20,7),(8,8,6),(9,22,11),(9,3,11),(9,5,12),(10,24,10);
INSERT INTO GuestReservations(ReservationID, GuestID, RoomID)
VALUES (11,1,8),(12,2,9);

-- AMENITIES
INSERT INTO Amenities(AmenityType, Price)
VALUES ('Fridge',5.00),('Spa Bath',6.50),('Massage Chair',9.99),('Couch',6.99);

-- AMENITIES FOR ROOM TYPE
INSERT INTO AmenitiesForRoomType(RoomTypeID,AmenityID)
VALUES (1,1),(2,1),(3,1),(3,2),(3,4);

-- ADD ONS
INSERT INTO AddOns(AddOnType, Price)
VALUES ('Champagne',19.99),('Entree',13.99),('Pay-Per-View',4.99);

-- BILLS
INSERT INTO Bills(ReservationID, Total, Tax)
VALUES (1,599.99,109.88),(2,354.96,69.88),(3,209.99,40.88),(4,397.99,59.88),(5,540.99,69.88),(6,490.99,101.88),
(7,299.99,87.88),(8,307.99,109.88),(9,294.99,68.88),(10,300.99,56.88);

-- RESERVATION ADD ONS
INSERT INTO ReservationAddOns(ReservationID, AddOnID, RoomID)
VALUES (1,1,2),(1,1,2),(1,1,2),(1,3,1),(1,3,1),(2,3,3),(4,3,5),(7,1,7),(10,3,10),(10,1,10);
INSERT INTO ReservationAddOns(ReservationID, AddOnID, RoomID)
VALUES (11,1,8),(11,3,8),(11,3,8),(11,3,8);

-- PROMOTIONS
INSERT INTO Promotions(PromotionType, Discount, IsPercent, StartDate, EndDate)
VALUES ('Military Discount',10,1,null,null),('AAA Discount',5,1,null,null),('Birthday Discount',50,0,null,null),
('Cool Customer Discount',15,1,'2016-03-21','2016-05-30');

-- RESERVATION PROMOTIONS
INSERT INTO ReservationPromotions(ReservationID, PromotionID)
VALUES (4,2),(4,1),(6,3),(8,3);

-- BILL DETAILS
INSERT INTO BillDetails(BillID,Description,Price)
VALUES (1,'Champagne',19.99),(1,'Champagne',19.99),(1,'Champagne',19.99),
(1,'Pay-Per-View',4.99),(1,'Pay-Per-View',4.99),(2,'Pay-Per-View',4.99),
(4,'Pay-Per-View',4.99),(7,'Champagne',19.99),(10,'Pay-Per-View',4.99),(10,'Champagne',0.00);