USE [master]
GO
/****** Object:  Database [lab1_yellow_moon]    Script Date: 9/19/2021 10:09:04 PM ******/
CREATE DATABASE [lab1_yellow_moon]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'lab1_yellow_moon', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\lab1_yellow_moon.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'lab1_yellow_moon_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\lab1_yellow_moon_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [lab1_yellow_moon] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [lab1_yellow_moon].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [lab1_yellow_moon] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET ARITHABORT OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [lab1_yellow_moon] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [lab1_yellow_moon] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET  ENABLE_BROKER 
GO
ALTER DATABASE [lab1_yellow_moon] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [lab1_yellow_moon] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET RECOVERY FULL 
GO
ALTER DATABASE [lab1_yellow_moon] SET  MULTI_USER 
GO
ALTER DATABASE [lab1_yellow_moon] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [lab1_yellow_moon] SET DB_CHAINING OFF 
GO
ALTER DATABASE [lab1_yellow_moon] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [lab1_yellow_moon] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [lab1_yellow_moon] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [lab1_yellow_moon] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'lab1_yellow_moon', N'ON'
GO
ALTER DATABASE [lab1_yellow_moon] SET QUERY_STORE = OFF
GO
USE [lab1_yellow_moon]
GO
/****** Object:  Table [dbo].[Booking]    Script Date: 9/19/2021 10:09:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Booking](
	[BookingId] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[ImportedDate] [datetime] NOT NULL,
	[Total] [float] NOT NULL,
	[PayWith] [nvarchar](50) NULL,
	[PaymentStatus] [nvarchar](50) NULL,
	[MomoCode] [nvarchar](50) NULL,
 CONSTRAINT [PK_Booking] PRIMARY KEY CLUSTERED 
(
	[BookingId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BookingDetail]    Script Date: 9/19/2021 10:09:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookingDetail](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[BookingId] [int] NOT NULL,
	[CakeId] [int] NOT NULL,
	[Amount] [float] NOT NULL,
 CONSTRAINT [PK_BookingDetail] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cake]    Script Date: 9/19/2021 10:09:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
	[userId] [int] NULL,
 CONSTRAINT [PK_Cake] PRIMARY KEY CLUSTERED 
(
	[cakeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 9/19/2021 10:09:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 9/19/2021 10:09:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](250) NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Phone] [nvarchar](50) NULL,
	[Avatar] [nvarchar](250) NULL,
	[Address] [nvarchar](50) NULL,
	[RoleID] [int] NOT NULL,
	[StatusID] [int] NOT NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 9/19/2021 10:09:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 9/19/2021 10:09:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Booking] ON 

INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (1, 2, CAST(N'2021-09-10T00:00:00.000' AS DateTime), 12.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2, 3, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 4.1, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3, 3, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 2.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (13, 2, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 4.3, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (14, 3, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 9, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (15, 2, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 20.3, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (16, 3, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 22.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (17, 2, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 6.2, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (18, 2, CAST(N'2021-09-13T00:00:00.000' AS DateTime), 22.9, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (19, 3, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 7.6, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (20, 1007, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 10.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (21, 3, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 25.6, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (22, 1005, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 15.3, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (23, 1007, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 15.8, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (24, 1005, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 6.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (25, 1007, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 7.2, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (26, 1005, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 2.1, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (27, 1007, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 1.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (28, 1007, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 4.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (29, 1006, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 2.1, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (30, 1006, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 3, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (31, 1006, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 1, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (32, 1006, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 1, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (33, 1006, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 2, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (34, 1006, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 1.9, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (35, 1006, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 2.1, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (36, 1006, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 2.3, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (37, 1006, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 1.5, N'Momo Pay', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (38, 1005, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 1.4, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (39, 2, CAST(N'2021-09-14T00:00:00.000' AS DateTime), 5.6, N'Momo Pay', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (40, 1005, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 2.2, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (41, 1005, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 2.3, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (42, 2, CAST(N'2021-09-14T00:00:00.000' AS DateTime), 2.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (43, 1005, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 2.1, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (44, 1007, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 6.1, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (45, 1009, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 2.4, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (46, 1011, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 10, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (1040, 2005, CAST(N'2021-09-12T00:00:00.000' AS DateTime), 6.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (1041, 2, CAST(N'2021-09-14T00:00:00.000' AS DateTime), 9, N'Momo Pay', N'Delivery Success', N'1041 YellowMoonSayHi')
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2041, 2006, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 3.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2042, 2006, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 6.7, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2043, 2010, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 21, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2044, 2010, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 12, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2045, 2011, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 9, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2046, 2011, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 9.3, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2047, 2012, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 7.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2048, 2, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 3.4, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2049, 2014, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 7.1, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2050, 1005, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 4.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2051, 2015, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 6, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2052, 3, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 3, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (2053, 3, CAST(N'2021-09-17T00:00:00.000' AS DateTime), 1.6, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3041, 1005, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 5.1, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3042, 1005, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 7.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3043, 1005, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 1.5, N'Paypal', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3044, 1006, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 2, N'Paypal', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3045, 1008, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 10, N'Paypal', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3046, 1009, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 1.3, N'Paypal', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3047, 1009, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 4, N'Paypal', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3048, 1008, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 16, N'Paypal', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3049, 2, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 27.799999237060547, N'Paypal', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3050, 2010, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 8.3999996185302734, N'Paypal', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3051, 2010, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 14, N'Paypal', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3052, 2010, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 7.5, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3053, 2010, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 12, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3054, 3, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 9, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3055, 3, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 12, N'CASH', N'Delivery Success', NULL)
INSERT [dbo].[Booking] ([BookingId], [UserId], [ImportedDate], [Total], [PayWith], [PaymentStatus], [MomoCode]) VALUES (3056, 1005, CAST(N'2021-09-19T00:00:00.000' AS DateTime), 5, N'Momo Pay', N'Delivery Success', N'3056 YellowMoonSayHi')
SET IDENTITY_INSERT [dbo].[Booking] OFF
GO
SET IDENTITY_INSERT [dbo].[BookingDetail] ON 

INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (1, 1, 16, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2, 1, 18, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3, 2, 16, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (4, 1, 10, 5)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (5, 3, 13, 5)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (6, 2, 19, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (7, 2, 9, 4)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (8, 14, 18, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (9, 15, 21, 3)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (10, 15, 10, 4)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (11, 14, 19, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (12, 13, 2, 5)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (13, 16, 25, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (14, 17, 20, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (15, 17, 7, 3)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (16, 18, 8, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (17, 18, 12, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (18, 19, 8, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (19, 19, 9, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (20, 20, 15, 3)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (21, 21, 15, 10)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (22, 22, 11, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (23, 22, 15, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (24, 23, 16, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (25, 23, 15, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (26, 24, 20, 3)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (27, 25, 15, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (28, 26, 7, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (29, 27, 19, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (30, 28, 9, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (31, 29, 18, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (32, 30, 20, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (33, 31, 2, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (34, 32, 5, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (35, 33, 18, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (36, 34, 24, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (37, 35, 19, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (38, 36, 19, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (39, 37, 2, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (40, 38, 26, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (41, 39, 2, 25)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (42, 40, 26, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (43, 41, 26, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (44, 42, 2, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (45, 43, 2, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (46, 43, 20, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (47, 44, 2, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (48, 44, 14, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (49, 45, 17, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (50, 45, 7, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (51, 46, 7, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (1016, 1040, 2, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (1017, 1041, 28, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2017, 2041, 3, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2018, 2041, 8, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2019, 2042, 9, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2020, 2042, 13, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2021, 2043, 7, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2022, 2043, 9, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2023, 2044, 7, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2024, 2045, 29, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2025, 2046, 9, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2026, 2047, 7, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2027, 2048, 25, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2028, 2048, 29, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2029, 2049, 21, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2030, 2049, 11, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2031, 2050, 28, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2032, 2050, 29, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2033, 2051, 21, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2034, 2051, 23, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2035, 2052, 7, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (2036, 2053, 29, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3017, 3041, 7, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3018, 3041, 29, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3019, 3042, 7, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3020, 3043, 13, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3021, 3044, 4, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3022, 3045, 10, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3023, 3046, 10, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3024, 3047, 21, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3025, 3048, 21, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3026, 3049, 24, 2)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3027, 3049, 11, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3028, 3050, 24, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3029, 3051, 2, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3030, 3051, 8, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3031, 3052, 2, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3032, 3053, 18, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3033, 3054, 29, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3034, 3055, 7, 1)
INSERT [dbo].[BookingDetail] ([Id], [BookingId], [CakeId], [Amount]) VALUES (3035, 3056, 26, 1)
SET IDENTITY_INSERT [dbo].[BookingDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[Cake] ON 

INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (2, N'Noodle', 7.5, 13, 1, N'Banh ngon vi suc khoe', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-25T00:00:00.000' AS DateTime), N'img/banh2.jpg', 1, CAST(N'2021-09-12T23:39:00.927' AS DateTime), 1)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (3, N'Trứng muối', 8, 8, 1, N'Bánh Trung Thu đậu xanh nhân trứng muối có vỏ bánh vàng ươm thơm phức, vị mặn ngọt vừa phải, nên không làm bạn cảm thấy ngán khi ăn.', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh9.jpg', 1, CAST(N'2021-01-11T23:45:40.450' AS DateTime), 1)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (4, N'Nhân đậu xanh', 7.6, 14, 1, N'Được nhiều người ưa chuộng và tin dùng', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh2.jpg', 1, CAST(N'2021-09-06T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (5, N'Da tuyết', 8, 7, 2, N'Ngon từ những ánh nhìn đầu tiên', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh1.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (6, N'Năm nhân và thịt', 10, 8, 2, N'Nâng cao để đáp ứng nhu cầu về khẩu vị ngày càng cao của khách hàng', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-25T00:00:00.000' AS DateTime), N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (7, N'Nhân hạt sen', 12, 7, 1, N'Được chọn lọc từng hạt sen, đảm bảo về chất lượng', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-21T00:00:00.000' AS DateTime), N'img/banh3.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (8, N'Banh rau cau', 6.5, 14, 1, N'an mat voi quy trinh lam banh can than', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-22T00:00:00.000' AS DateTime), N'img/banh17.jpg', 1, CAST(N'2021-09-17T15:22:23.537' AS DateTime), 1)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (9, N'Bánh dẻo thit say', 9, 15, 3, N'Ngày nay bánh dẻo được biến tấu nhiều hình dáng, nhiều hương vị khác nhau, đa dạng hơn, cầu kì hơn, và có phần ngon hơn. ', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-23T00:00:00.000' AS DateTime), N'img/banh6.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (10, N'Bánh dẻo lạnh', 10, 8, 3, N'Bánh dẻo lạnh đúng kiểu Singapore siêu ngon', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh7.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (11, N'Bánh Lava Custard', 11, 8, 2, N'Lòng đỏ trứng muối tan chảy, thơm lừng mùi bơ đã nhanh chóng chinh phục được những vị khách khó tính từ trẻ em đến người lớn tuối', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh10.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (12, N'banh trang nuong', 6.6, 10, 1, N'very good', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (13, N'banh me cuoi', 5.5, 9, 1, N'Good For Everyone', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh2.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (14, N'Bánh nhân gà xé', 8.5, 53, 1, N'Nâng cao để đáp ứng nhu cầu về khẩu vị ngày càng cao của khách hàng', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh12.jpg', 1, CAST(N'2021-09-06T21:10:03.463' AS DateTime), 1)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (15, N'Bánh gạo lứt', 6.4, 456, 2, N'Hương vị từ gạo thơm và ngọt', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh13.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (16, N'Bánh nhân thịt chưng mắm tép', 8.6, 15, 3, N'Nân cao để đáp ứng nhu cầu về khẩu vị ngày càng cao của khách hàng', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh11.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (17, N'Nhân thập cẩm', 7.5, 8, 1, N'Thơm ngon không cần lò nướng', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh5.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (18, N'Bánh nhân sầu riêng', 12, 25, 3, N'Có mùi thơm nồng, có thể ngửi thấy ở khoảng cách từ xa', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh8.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (19, N'Bánh nướng nhân trà xanh', 9, 30, 2, N'Nhân trà xanh là một trong những loại nhân đang "hot" gần đây với mùi vị đặc trưng đến từ bột matcha của Nhật Bản.', CAST(N'2021-09-11T00:00:00.000' AS DateTime), CAST(N'2021-09-26T00:00:00.000' AS DateTime), N'img/banh14.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (20, N'Nhân trái cây và hạt', 13, 29, 2, N'Có thành phần tốt cho sức khỏe của mọi người tăng cao', CAST(N'2021-09-11T00:00:00.000' AS DateTime), CAST(N'2021-09-25T00:00:00.000' AS DateTime), N'img/banh15.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (21, N'Bánh dẻo gừng', 13, 15, 3, N'Là một trong hai loại bánh Trung thu đặc biệt của người Việt Nam', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh16.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (22, N'Rau và trái cây', 10, 10, 1, N'Sử dụng bột trà đen hoặc trà hương lài để làm nhân bánh', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh4.JPG', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (23, N'Bánh tiramisu', 11, 19, 1, N'Có ý nghĩa đề cao tình mẫu tử trong văn hóa Italia', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh18.jpg', 1, CAST(N'2021-01-11T23:45:40.450' AS DateTime), 1)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (24, N'Bánh tỏi đen', 8.4, 7, 1, N'Thành phần tỏi đen của bánh là một vị thuốc nên rất tốt cho sức khỏe, thích hợp để làm quà cho ông bà, cha mẹ', CAST(N'2021-09-10T00:00:00.000' AS DateTime), CAST(N'2021-09-28T00:00:00.000' AS DateTime), N'img/banh19.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (25, N'Banh Phia', 4, 12, 1, N'Banh ngon vi suc khoe ca gia dinh', CAST(N'2021-09-09T08:50:12.247' AS DateTime), CAST(N'2021-09-26T00:00:00.000' AS DateTime), N'img/banh3.jpg', 1, CAST(N'2021-09-12T23:39:59.720' AS DateTime), 1)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (26, N'Banh Dau Do', 5, 19, 1, N'Ngon chat luong len hang dau', CAST(N'2021-09-09T15:59:49.627' AS DateTime), CAST(N'2021-09-30T00:00:00.000' AS DateTime), N'img/banh13.jpg', 1, CAST(N'2021-09-12T23:44:34.897' AS DateTime), 1)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (27, N'Kinh Do', 10, 25, 1, N'Thiết kế đẹp mắt, mẫu mã đa dạng, hợp thời, chất lượng đảm bảo, hương vị thơm ngon và trên hết giá cả hợp túi tiền', CAST(N'2021-09-12T22:03:45.763' AS DateTime), CAST(N'2021-09-30T00:00:00.000' AS DateTime), N'img/banh16.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (28, N'Banh Dau Xanh', 3, 11, 1, N'banh sieu ngon', CAST(N'2021-09-12T23:24:59.123' AS DateTime), CAST(N'2021-09-26T00:00:00.000' AS DateTime), N'img/banh15.jpg', 1, NULL, NULL)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (29, N'Banh ngot dua', 9, 8, 1, N'Dac san thai nguyen', CAST(N'2021-09-16T23:44:35.300' AS DateTime), CAST(N'2021-09-21T00:00:00.000' AS DateTime), N'img/banh1.jpg', 1, CAST(N'2021-09-17T15:21:49.117' AS DateTime), 1)
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [quantity], [categoriId], [description], [createDate], [expirationDate], [imageLink], [statusId], [updateDate], [userId]) VALUES (30, N'Banh Sau Rieng', 11, 25, 2, N'100% tu sau rieng va bot', CAST(N'2021-09-17T15:23:57.720' AS DateTime), CAST(N'2021-09-30T00:00:00.000' AS DateTime), N'img/K3J276643P1X8CU.jpg', 1, CAST(N'2021-09-17T15:40:58.147' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[Cake] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([Id], [Name]) VALUES (1, N'Egg')
INSERT [dbo].[Category] ([Id], [Name]) VALUES (2, N'No Egg')
INSERT [dbo].[Category] ([Id], [Name]) VALUES (3, N'Clammy')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Registration] ON 

INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (1, N'long', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Đồng Hữu Long', N'0931182303', N'deptrai.jpg', N'Quan 9', 1, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (2, N'thu', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Nguyễn Minh Thư', N'0931182303', N'deptrai.jpg', N'Lô 12, Quận 6, TP HCM', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (3, N'donglong453@gmail.com', NULL, N'Đồng Long', N'0967182654', N'https://lh3.googleusercontent.com/a-/AOh14Gg7NXllU9hOO43XOlloI-8pY2TKD9dtL0GJF29f2w=s96-c', N'Q11', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (1005, N'van', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Lê Thanh Vân', N'0931182303', NULL, N'Q12', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (1006, N'ngoc', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Phùng Văn Ngọc', N'0931182303', NULL, N'32/17', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (1007, N'duc', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Lê Thái Đức', N'0987451425', NULL, N'32/17', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (1008, N'hoang', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Phùng Văn Hoàng', N'0987451425', NULL, N'32/17', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (1009, N'guess@gmail.com', NULL, N'Ngoc nguyen', N'098745566', NULL, N'Q8', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (1010, N'guess@gmail.com', NULL, N'Ngoc nguyen', N'098745566', NULL, N'Q8', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (1011, N'guess@gmail.com', NULL, N'Quang phat', N'0931182303', NULL, N'32/17', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (2005, N'guess@gmail.com', NULL, N'Ngoc', N'0684541520', NULL, N'27/5 Quan 9', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (2006, N'ngoc765@gmail.com', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Phung Van Ngoc', N'0684541520', NULL, N'27/5 Quan 9', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (2009, N'quannmse130458@fpt.edu.vn', NULL, N'Nguyen Minh Quan (K13_HCM)', NULL, NULL, NULL, 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (2010, N'nguyenpsse130582@fpt.edu.vn', NULL, N'Phan Sĩ Nguyên _K13_HCM', N'0684541520', NULL, N'27/5 Quan 9', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (2011, N'longdhse130623@fpt.edu.vn', NULL, N'Dong Huu Long K13_HCM', NULL, NULL, NULL, 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (2012, N'guess@gmail.com', NULL, N'Le thanh van', N'0874514519', NULL, N'address ok', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (2014, N'guess@gmail.com', NULL, N'Phung Van Ngoc', N'0684541520', NULL, N'27/5 Quan 9', 2, 1)
INSERT [dbo].[Registration] ([Id], [Email], [Password], [Name], [Phone], [Avatar], [Address], [RoleID], [StatusID]) VALUES (2015, N'guess@gmail.com', NULL, N'Cao Quá»³nh Trang', N'0931182303', NULL, N'32/17', 2, 1)
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
REFERENCES [dbo].[Booking] ([BookingId])
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [FK_BookingDetail_Booking]
GO
ALTER TABLE [dbo].[BookingDetail]  WITH CHECK ADD  CONSTRAINT [FK_BookingDetail_Cake] FOREIGN KEY([CakeId])
REFERENCES [dbo].[Cake] ([cakeId])
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [FK_BookingDetail_Cake]
GO
ALTER TABLE [dbo].[Cake]  WITH CHECK ADD  CONSTRAINT [FK_Cake_Category] FOREIGN KEY([categoriId])
REFERENCES [dbo].[Category] ([Id])
GO
ALTER TABLE [dbo].[Cake] CHECK CONSTRAINT [FK_Cake_Category]
GO
ALTER TABLE [dbo].[Cake]  WITH CHECK ADD  CONSTRAINT [FK_Cake_Registration] FOREIGN KEY([userId])
REFERENCES [dbo].[Registration] ([Id])
GO
ALTER TABLE [dbo].[Cake] CHECK CONSTRAINT [FK_Cake_Registration]
GO
ALTER TABLE [dbo].[Cake]  WITH CHECK ADD  CONSTRAINT [FK_Cake_Status] FOREIGN KEY([statusId])
REFERENCES [dbo].[Status] ([Id])
GO
ALTER TABLE [dbo].[Cake] CHECK CONSTRAINT [FK_Cake_Status]
GO
ALTER TABLE [dbo].[Registration]  WITH CHECK ADD  CONSTRAINT [FK_Registration_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([Id])
GO
ALTER TABLE [dbo].[Registration] CHECK CONSTRAINT [FK_Registration_Role]
GO
ALTER TABLE [dbo].[Registration]  WITH CHECK ADD  CONSTRAINT [FK_Registration_Status] FOREIGN KEY([StatusID])
REFERENCES [dbo].[Status] ([Id])
GO
ALTER TABLE [dbo].[Registration] CHECK CONSTRAINT [FK_Registration_Status]
GO
USE [master]
GO
ALTER DATABASE [lab1_yellow_moon] SET  READ_WRITE 
GO
