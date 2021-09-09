

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