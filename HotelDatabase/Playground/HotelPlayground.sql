USE HotelDB;

-- RETURN ALL ROOMS FOR GUEST
SELECT g.GuestID, g.FirstName, g.LastName, CONCAT(r.Floor,'-',r.RoomNumber) AS Room, rr.StartDate, rr.EndDate
FROM Guests g
	INNER JOIN GuestReservations gr ON g.GuestID = gr.GuestID
    INNER JOIN Rooms r ON gr.RoomID = r.RoomID
    INNER JOIN ReservationRooms rr ON r.RoomID = rr.RoomID
WHERE g.GuestID = 3;


/*
SELECT b.BillID, CONCAT(g.LastName,', ',g.FirstName) AS BilledName, b.total
FROM Bills b
	INNER JOIN Reservations r ON b.ReservationID = r.ReservationID
    INNER JOIN Guests g ON r.CustomerID = g.GuestID;
*/

/*
-- RETURN GUESTS IN ROOM PER RESERVATION
SELECT gr.ReservationID, CONCAT(r.Floor,'-',r.RoomNumber) AS Room, rt.RoomTypeName, CONCAT(g.FirstName,' ',g.LastName) AS GuestName 
FROM GuestReservations gr
	INNER JOIN Guests g ON gr.GuestID = g.GuestID
	INNER JOIN Rooms r ON gr.RoomID = r.RoomID
	INNER JOIN RoomTypes rt ON r.RoomTypeID = rt.RoomTypeID
ORDER BY Room;
*/

/*
-- GET ROOMS USED ON A CERTAIN DATE
SELECT rr.ReservationID, CONCAT(r.Floor,'-',r.RoomNumber) AS Room, rt.RoomTypeName
FROM ReservationRooms rr
	INNER JOIN Rooms r ON rr.RoomID = r.RoomID
	INNER JOIN RoomTypes rt ON r.RoomTypeID = rt.RoomTypeID
WHERE StartDate <= '2016-11-1' AND EndDate >= '2016-11-1';
*/

/*
-- RETURN AVAILABLE ROOMS FOR A DATE RANGE
SELECT RoomID
FROM Rooms
WHERE RoomID NOT IN (
	SELECT RoomID
    FROM ReservationRooms
    WHERE StartDate BETWEEN '2016-10-31' AND '2016-11-2')
ORDER BY RoomID;
*/

/*
-- SEE RESERVATIONS FOR EACH GUEST
SELECT DISTINCT(gr.ReservationID), rm.RoomID, rr.StartDate, rr.EndDate
FROM GuestReservations gr
	INNER JOIN Rooms rm ON rm.RoomID = gr.RoomID
    INNER JOIN Reservations rv ON rv.ReservationID = gr.ReservationID
    INNER JOIN ReservationRooms rr ON rr.ReservationID = rv.ReservationID
WHERE GuestID = 3
ORDER BY rr.StartDate;
*/

