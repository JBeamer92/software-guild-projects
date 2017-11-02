USE HotelDB;

-- RETURN ALL ROOMS FOR GUEST
SELECT g.FirstName, g.LastName, CONCAT(r.Floor,'-',r.RoomNumber) AS Room
FROM GuestReservations gr
	INNER JOIN Guests g ON g.GuestID = gr.GuestID
    INNER JOIN Rooms r ON gr.RoomID = r.RoomID
WHERE gr.GuestID = 2;


-- QUERY ALL PROMO CODES AND NUM OF TIMES USED
SELECT p.PromotionID, p.PromotionType, COUNT(rp.PromotionID) AS NumTimesUsed
FROM Promotions p
    LEFT JOIN ReservationPromotions rp ON rp.PromotionID = p.PromotionID
GROUP BY p.PromotionID;

-- RETURN RESERVATIONS THAT END ON DATE
SELECT *
FROM ReservationRooms
WHERE EndDate = '2017-10-18';

/*
	
*/