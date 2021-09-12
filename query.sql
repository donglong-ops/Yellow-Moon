

SELECT COUNT(cakeId) as totalRows from Cake C 
WHERE C.statusId = 1 AND C.quantity >  0 And C.quantity > (SELECT Count(B.Amount) AS Amount From BookingDetail B  Where B.CakeId = C.cakeId) 



SELECT C.cakeId, C.cakeName , C.cakePrice , C.statusId, C.quantity, C.description, C.createDate , C.expirationDate , C.categoriId , C.imageLink 
From Cake C 
WHERE  C.cakeId =  4 and C.statusId = 1


SELECT quantity FROM Cake Where cakeId = 2

select c.Name from Category c where c.Id = 2


SELECT COUNT(cakeId) as totalRows from Cake C 
WHERE C.statusId = 1 AND C.quantity >  0 
And C.quantity > (SELECT Count(B.Amount) AS Amount 
                    From BookingDetail B 
                    Where B.CakeId = C.cakeId)


					 where status like '1' and Quanity >  0 and DATEDIFF(day , GETDATE(), ExpirationDate ) > 0
	order by DATEDIFF(day , GETDATE(), ExpirationDate ) ASC


select r.Fullname from Registration r where r.Id = 1

SELECT Re.ID , Email ,Password,Fullname,R.ID  AS RoleID, R.Name AS RoleName 
FROM Registration Re JOIN Role R ON Re.RoleID  = R.ID 
WHERE Email = 'long' And Password = '123'


SELECT C.cakeId, C.cakeName , C.cakePrice , C.quantity, C.description, C.createDate, C.expirationDate ,C.categoriId , C.imageLink 
From Cake C 
WHERE C.statusId = 1 AND C.quantity >  0 And DATEDIFF(day , GETDATE(), C.expirationDate ) > 0 
And C.quantity > (SELECT Count(B.Amount) AS Amount From BookingDetail B Where B.CakeId = C.cakeId) 
ORDER BY DATEDIFF(day , GETDATE(), C.expirationDate ) ASC 
OFFSET 0 ROWS 
FETCH NEXT 5 ROWS ONLY


select bk.CakeId , bk.Amount
from BookingDetail bk , Booking b
where b.BookingId = bk.BookingId and b.BookingId = 13


SELECT DISTINCT c.Id, c.Name FROM Status c


SELECT b.CakeId, SUM(b.Amount) as TotalBooked 
FROM BookingDetail b 
WHERE b.CakeId = 16
GROUP BY b.CakeId


SELECT r.Id, r.Email, r.Fullname, r.Address,r.RoleID, rl.Name AS RoleName
FROM Booking b , Registration r , Role rl
WHERE r.Id = b.UserId and rl.Id = r.RoleID and b.BookingId = 36




select b.BookingId, b.ImportedDate , b.Total, b.PayWith, b.PaymentStatus 
from Booking b  
where b.UserId = 2

SELECT b.BookingId, b.ImportedDate , b.Total , b.PayWith, b.PaymentStatus 
FROM Booking b  
WHERE b.UserId = '2' and b.BookingId = '18' and b.ImportedDate > '2021-09-09' and b.ImportedDate <= '2021-09-10' 

SELECT b.BookingId, b.ImportedDate , b.Total , b.PayWith, b.PaymentStatus 
FROM Booking b  
WHERE b.UserId = '2' and b.BookingId = 18


SELECT C.cakeId, C.cakeName , C.cakePrice , C.quantity, C.description, C.createDate, C.expirationDate ,C.categoriId ,C.statusId , C.imageLink 
From Cake C 
WHERE C.statusId = 1 AND C.quantity > 0 And DATEDIFF(day , GETDATE(), C.expirationDate ) > 0 
ORDER BY DATEDIFF(day , GETDATE(), C.expirationDate ) ASC

SELECT COUNT(cakeId) as totalRows FROM Cake C 
WHERE C.statusId = 1 AND C.quantity >  0 And DATEDIFF(day , GETDATE(), C.expirationDate ) > 0