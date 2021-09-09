USE [master]
GO
/****** Object:  Database [[lab1_yellow_moon]]    Script Date: 9/6/2021 12:15:48 PM ******/
CREATE DATABASE [lab1_yellow_moon]
Use lab1_yellow_moon;
 
CREATE TABLE [dbo].[Booking](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[ImportedDate] [datetime] NOT NULL,
	[Total] [float] NOT NULL,
	[PayWith] [nvarchar](50) NULL)
CREATE TABLE [dbo].[BookingDetail](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[BookingId] [int] NOT NULL,
	[CakeId] [int] NOT NULL,
	[Amount] [float] NOT NULL)
CREATE TABLE [dbo].[Cake](
	[cakeId] [int] IDENTITY(1,1) NOT NULL,
	[cakeName] [nvarchar](50) NOT NULL,
	[cakePrice] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[categoriId] [int] NOT NULL,
	[description] [nvarchar](350) NOT NULL,
	[createDate] [datetime] NOT NULL,
	[expirationDate] [datetime] NULL,
	[imageLink] [nvarchar](50) NULL,
	[statusId] [int] NOT NULL,
	[updateDate] [datetime] NULL,
	[userId] [int] NULL)
CREATE TABLE [dbo].[Category](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL)
CREATE TABLE [dbo].[Registration](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](250) NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Phone] [nvarchar](50) NULL,
	[Avatar] [nvarchar](250) NULL,
	[Address] [nvarchar](50) NULL,
	[RoleID] [int] NOT NULL,
	[StatusID] [int] NOT NULL)
CREATE TABLE [dbo].[Role](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL)
CREATE TABLE [dbo].[Status](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL)

INSERT [dbo].[Booking] ([Id], [UserId], [ImportedDate], [Total], [PayWith]) VALUES (1011, 2, CAST(N'2021-01-10T00:33:46.610' AS DateTime), 1600, N'CASH')
INSERT [dbo].[Booking] ([Id], [UserId], [ImportedDate], [Total], [PayWith]) VALUES (1012, 2, CAST(N'2021-01-10T00:36:23.693' AS DateTime), 246, N'CASH')
INSERT [dbo].[Booking] ([Id], [UserId], [ImportedDate], [Total], [PayWith]) VALUES (1013, 2, CAST(N'2021-01-15T00:36:23.693' AS DateTime), 1554, N'CASH')
INSERT [dbo].[Booking] ([Id], [UserId], [ImportedDate], [Total], [PayWith]) VALUES (1023, 1003, CAST(N'2021-01-16T01:01:29.460' AS DateTime), 76000, N'CASH')
INSERT [dbo].[Booking] ([Id], [UserId], [ImportedDate], [Total], [PayWith]) VALUES (1024, 3, CAST(N'2021-01-16T01:06:23.747' AS DateTime), 60000, N'CASH')
SET IDENTITY_INSERT [dbo].[Booking] OFF
GO
SET IDENTITY_INSERT [dbo].[BookingDetail] ON 

INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (1007, 1011, 16, 13)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (1008, 1011, 18, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (1009, 1012, 16, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (1010, 1011, 10, 5)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (1011, 1023, 13, 5)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (1012, 1024, 4, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (1013, 1024, 6, 5)
SET IDENTITY_INSERT [dbo].[BookingDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[Cake] ON 

INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (2, N'Noodle', 10000, 55, 1, N'Good', CAST(N'2021-01-15T00:00:00.000' AS DateTime), NULL, N'img/banh2.jpg', 2, CAST(N'2021-01-11T23:43:11.260' AS DateTime), 1)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (3, N'bun', 10000, 5, 1, N'Nice', CAST(N'2021-01-15T00:00:00.000' AS DateTime), NULL, N'img/banh2.jpg', 2, CAST(N'2021-01-11T23:45:40.450' AS DateTime), 1)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (4, N'banh quy', 10000, 6, 1, N'cool', CAST(N'2021-01-15T00:00:00.000' AS DateTime), NULL, N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (5, N'Da tuyết', 10000, 7, 1, N'ngon', CAST(N'2021-01-15T00:00:00.000' AS DateTime), NULL, N'img/banh1.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (6, N'Năm nhân và thịt', 10000, 8, 1, N'them', CAST(N'2021-01-15T00:00:00.000' AS DateTime), NULL, N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (7, N'Nhân hạt sen', 10000, 9, 1, N'Good', CAST(N'2021-01-15T00:00:00.000' AS DateTime), NULL, N'img/banh3.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (8, N'Rau và trái cây', 10000, 10, 1, N'Good', CAST(N'2021-01-15T00:00:00.000' AS DateTime), NULL, N'img/banh4.JPG', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (9, N'keo deo', 10000, 10, 2, N'Good', CAST(N'2021-01-15T00:00:00.000' AS DateTime), NULL, N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (10, N'keo ho lo', 10000, 10, 2, N'Good', CAST(N'2021-01-15T00:00:00.000' AS DateTime), NULL, N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (11, N'keo chuoi', 10000, 10, 2, N'Good', CAST(N'2021-01-15T00:00:00.000' AS DateTime), NULL, N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (12, N'banh trang nuong', 66666, 10, 1, N'very good', CAST(N'2021-01-15T11:02:47.733' AS DateTime), NULL, N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (13, N'banh me cuoi', 15200, 5, 1, N'Good For Everyone', CAST(N'2021-01-20T11:18:26.900' AS DateTime), NULL, N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (14, N'Hi cc', 55555, 5555, 1, N'5555', CAST(N'2021-01-20T10:28:13.937' AS DateTime), NULL, N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (15, N'HI ban', 6666, 456, 2, N'NGON LAM', CAST(N'2021-01-15T09:42:32.290' AS DateTime), NULL, N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (16, N'van', 123, 15, 3, N'ngon', CAST(N'2021-01-18T19:55:17.713' AS DateTime), NULL, N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (17, N'LONG', 1, 1, 1, N'dep zai', CAST(N'2021-01-09T20:00:34.053' AS DateTime), NULL, N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (18, N'vkl', 1, 1, 2, N'ngon nghe', CAST(N'2021-01-09T20:04:57.710' AS DateTime), NULL, N'img/banh2.jpg', 1, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Cake] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([Id], [Name]) VALUES (1, N'Cake')
INSERT [dbo].[Category] ([Id], [Name]) VALUES (2, N'Candy')
INSERT [dbo].[Category] ([Id], [Name]) VALUES (3, N'Coffee')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Registration] ON 

INSERT [dbo].[Registration] ([Id], [Email], [Password], [Fullname], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (1, N'long', N'123', N'Đồng Hữu Long', N'0931182303', N'deptrai.jpg', N'Quan 9', 1, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Fullname], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (2, N'thu', N'123', N'Minh Thư', N'0931182303', N'deptrai.jpg', N'Quan 9', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Fullname], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (3, N'donglong453@gmail.com', NULL, N'Đồng Long', NULL, N'https://lh3.googleusercontent.com/a-/AOh14Gg7NXllU9hOO43XOlloI-8pY2TKD9dtL0GJF29f2w=s96-c', NULL, 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Fullname], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (1003, N'longdhse130623@fpt.edu.vn', NULL, N'Dong Huu Long K13_HCM', NULL, N'https://lh3.googleusercontent.com/a-/AOh14GjhS8GKX_cnCqAr8M8FPA_UAkoxGA_ws0R-ewXTUg=s96-c', NULL, 2, 1)
SET IDENTITY_INSERT [dbo].[Registration] OFF
GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([Id], [Name]) VALUES (1, N'Admin')
INSERT [dbo].[Role] ([Id], [Name]) VALUES (2, N'User')
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
SET IDENTITY_INSERT [dbo].[Status] ON 

INSERT [dbo].[Status] ([Id], [Name]) VALUES (1, N'Active')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (2, N'Inactive')
SET IDENTITY_INSERT [dbo].[Status] OFF
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD  CONSTRAINT [FK_Booking_Registration] FOREIGN KEY([UserId])
REFERENCES [dbo].[Registration] ([Id])
GO
ALTER TABLE [dbo].[Booking] CHECK CONSTRAINT [FK_Booking_Registration]
GO
ALTER TABLE [dbo].[BookingDetail]  WITH CHECK ADD  CONSTRAINT [FK_BookingDetail_Booking] FOREIGN KEY([BookingId])
REFERENCES [dbo].[Booking] ([Id])
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [FK_BookingDetail_Booking]
GO
ALTER TABLE [dbo].[BookingDetail]  WITH CHECK ADD  CONSTRAINT [FK_BookingDetail_Food] FOREIGN KEY([CakeId])
REFERENCES [dbo].[Cake] ([cakeId])
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [FK_BookingDetail_Food]
GO
USE [master]
GO
ALTER DATABASE [lab1_hanaShop] SET  READ_WRITE 
GO
