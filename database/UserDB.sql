-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 23, 2017 at 12:22 PM
-- Server version: 5.5.57-0+deb8u1
-- PHP Version: 5.6.30-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `UserDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `LogFile`
--

CREATE TABLE IF NOT EXISTS `LogFile` (
`id` int(11) NOT NULL,
  `LoginUser` varchar(30) NOT NULL,
  `Action` varchar(30) NOT NULL,
  `Description` text NOT NULL,
  `Date_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `LogFile`
--

INSERT INTO `LogFile` (`id`, `LoginUser`, `Action`, `Description`, `Date_Time`) VALUES
(5, 'Admin', 'Login', 'Success', '2017-09-28 18:13:49'),
(9, 'Admin', 'Login', 'Success', '2017-09-28 18:45:09'),
(10, 'Admin', 'Register', 'test1', '2017-09-28 18:46:26'),
(11, 'Admin', 'Add Product', 'PN88778890', '2017-09-28 18:47:15'),
(12, 'Admin', 'Add Order', 'PN88778890', '2017-09-28 18:52:43'),
(13, 'Admin', 'Order Confirmation', 'PN88778890', '2017-09-28 18:52:53'),
(14, 'Admin', 'Login', 'Success', '2017-09-28 18:48:35'),
(15, 'Admin', 'Order Confirmation', 'PN77888876', '2017-09-28 18:53:00'),
(16, 'Admin', 'Login', 'Success', '2017-09-28 20:20:06'),
(17, 'Admin', 'Login', 'Success', '2017-09-28 20:24:38'),
(18, 'Admin', 'Login', 'Success', '2017-09-28 20:26:01'),
(19, 'Admin', 'Login', 'Success', '2017-09-28 20:27:40'),
(20, 'Admin', 'Login', 'Success', '2017-09-28 20:37:14'),
(21, 'Admin', 'Login', 'Success', '2017-09-28 20:38:21'),
(22, 'Admin', 'Login', 'Success', '2017-09-28 20:39:30'),
(23, 'Admin', 'Login', 'Success', '2017-09-28 20:41:25'),
(24, 'Admin', 'Login', 'Success', '2017-09-28 20:45:35'),
(25, 'Admin', 'Login', 'Success', '2017-09-28 20:49:12'),
(26, 'Admin', 'Login', 'Success', '2017-09-28 21:02:17'),
(27, 'Admin', 'Login', 'Success', '2017-09-28 21:09:07'),
(28, 'Admin', 'Login', 'Success', '2017-09-28 21:10:20'),
(29, 'Admin', 'Login', 'Success', '2017-09-28 21:13:17'),
(30, 'Admin', 'Login', 'Success', '2017-09-28 21:16:16'),
(31, 'Admin', 'Login', 'Success', '2017-09-28 21:35:41'),
(32, 'Admin', 'Login', 'Success', '2017-09-28 21:38:54'),
(33, 'Admin', 'Login', 'Success', '2017-09-28 21:40:09'),
(34, 'Admin', 'Login', 'Success', '2017-10-02 09:36:28'),
(35, 'admin', 'Login', 'Success', '2017-10-07 10:23:16'),
(36, 'Admin', 'Login', 'Success', '2017-10-07 12:15:42'),
(37, 'Admin', 'Login', 'Success', '2017-10-07 13:13:54'),
(38, 'Admin', 'Update Product', 'PN45568022', '2017-10-07 13:57:18'),
(39, 'Admin', 'Login', 'Success', '2017-10-12 19:55:07'),
(40, 'Admin', 'Login', 'Success', '2017-10-16 12:28:05'),
(41, 'Admin', 'Login', 'Success', '2017-10-21 17:57:10'),
(42, 'Admin', 'Login', 'Success', '2017-10-21 18:31:01'),
(43, 'Admin', 'Login', 'Success', '2017-10-21 18:32:07'),
(44, 'Admin', 'Login', 'Success', '2017-10-21 18:41:35'),
(45, 'Admin', 'Delete User', '58', '2017-10-21 18:42:01'),
(46, 'Admin', 'Login', 'Success', '2017-10-21 19:01:22'),
(47, 'Admin', 'Login', 'Success', '2017-10-21 19:01:22'),
(48, 'Admin', 'Login', 'Success', '2017-10-21 19:01:34'),
(49, 'Admin', 'Login', 'Success', '2017-10-21 19:04:01'),
(50, 'Admin', 'Login', 'Success', '2017-10-21 19:07:46'),
(51, 'Admin', 'Login', 'Success', '2017-10-21 19:09:34'),
(52, 'Admin', 'Login', 'Success', '2017-10-21 19:10:02'),
(53, 'Admin', 'Login', 'Success', '2017-10-21 19:25:49'),
(54, 'Admin', 'Login', 'Success', '2017-10-21 21:06:36'),
(55, 'Admin', 'Login', 'Success', '2017-10-22 15:46:25'),
(56, 'Admin', 'Login', 'Success', '2017-10-22 16:53:06'),
(57, 'Admin', 'Login', 'Success', '2017-10-22 16:59:43'),
(58, 'Admin', 'Login', 'Success', '2017-10-22 16:17:21'),
(59, 'Admin', 'Login', 'Success', '2017-10-22 17:01:54'),
(60, 'Admin', 'Login', 'Success', '2017-10-22 17:40:27'),
(61, 'Admin', 'Login', 'Success', '2017-10-22 17:42:16'),
(62, 'Admin', 'Login', 'Success', '2017-10-23 08:19:18');

-- --------------------------------------------------------

--
-- Table structure for table `Orders`
--

CREATE TABLE IF NOT EXISTS `Orders` (
`id` tinyint(4) NOT NULL,
  `ProductNumber` varchar(30) DEFAULT NULL,
  `Qnt` int(11) NOT NULL,
  `Status` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Orders`
--

INSERT INTO `Orders` (`id`, `ProductNumber`, `Qnt`, `Status`) VALUES
(1, 'PN54658620', 10, 'Confirmed'),
(15, 'PN22323456', 23, 'Pending'),
(16, 'PN77888876', 1, 'Confirmed'),
(17, 'PN77888876', 10, 'Confirmed'),
(18, 'PN88778890', 30, 'Confirmed');

-- --------------------------------------------------------

--
-- Table structure for table `Products`
--

CREATE TABLE IF NOT EXISTS `Products` (
`id` tinyint(4) NOT NULL,
  `Pnumber` varchar(16) NOT NULL,
  `Pname` varchar(20) NOT NULL,
  `Pmodel` varchar(20) NOT NULL,
  `Pqnt` int(11) NOT NULL,
  `id_shelf` tinyint(4) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Products`
--

INSERT INTO `Products` (`id`, `Pnumber`, `Pname`, `Pmodel`, `Pqnt`, `id_shelf`) VALUES
(1, 'PN45568022', 'ASUS', 'STRIX', 20, 1),
(2, 'PN54658620', 'MSI', 'APACHE PRO GE72', 20, 3),
(3, 'OP23634565', 'ASER', 'xcss231', 87, 1),
(4, 'PN12888833', 'HP', 'dv5000', 22, 1),
(10, 'PN1288883093', 'HP', 'dv5000', 23, 2),
(11, 'PN77888876', 'MSI', 'PROZ67', 8, 2),
(12, 'PN88778890', 'HP', 'DV5000', 30, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Shelfs`
--

CREATE TABLE IF NOT EXISTS `Shelfs` (
`id` tinyint(4) NOT NULL,
  `Sname` varchar(5) NOT NULL,
  `Sdiscription` varchar(30) NOT NULL,
  `id_Storage` tinyint(4) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Shelfs`
--

INSERT INTO `Shelfs` (`id`, `Sname`, `Sdiscription`, `id_Storage`) VALUES
(1, 'A1', 'TVs', 1),
(2, 'A2', 'Laptops', 1),
(3, 'B52', 'Desktops', 2),
(5, 'A3', 'Laptops', 1);

-- --------------------------------------------------------

--
-- Table structure for table `Storages`
--

CREATE TABLE IF NOT EXISTS `Storages` (
`id` tinyint(4) NOT NULL,
  `Sname` varchar(5) NOT NULL,
  `Saddress` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Storages`
--

INSERT INTO `Storages` (`id`, `Sname`, `Saddress`) VALUES
(1, 'AH1', 'fourtounatou 3'),
(2, 'AA1', 'NIKIS 11 NIKAIA');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `Type` tinyint(4) NOT NULL,
`UserID` tinyint(4) NOT NULL,
  `LastName` char(36) NOT NULL,
  `FirstName` char(36) NOT NULL,
  `UserName` varchar(36) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(35) NOT NULL,
  `id_Storage` tinyint(4) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`Type`, `UserID`, `LastName`, `FirstName`, `UserName`, `Email`, `Password`, `id_Storage`) VALUES
(1, 53, 'mavroforakis', 'mixalis', 'Admin', 'mixalis365@hotmail.com', 'toor123', 1),
(0, 54, 'test', 'test', 'test', 'test@test.com', 'test', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `LogFile`
--
ALTER TABLE `LogFile`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Orders`
--
ALTER TABLE `Orders`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Products`
--
ALTER TABLE `Products`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `Pnumber` (`Pnumber`), ADD KEY `id_shelf` (`id_shelf`);

--
-- Indexes for table `Shelfs`
--
ALTER TABLE `Shelfs`
 ADD PRIMARY KEY (`id`), ADD KEY `id_Storage` (`id_Storage`);

--
-- Indexes for table `Storages`
--
ALTER TABLE `Storages`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
 ADD PRIMARY KEY (`UserID`), ADD KEY `id_Storage` (`id_Storage`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `LogFile`
--
ALTER TABLE `LogFile`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=63;
--
-- AUTO_INCREMENT for table `Orders`
--
ALTER TABLE `Orders`
MODIFY `id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `Products`
--
ALTER TABLE `Products`
MODIFY `id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `Shelfs`
--
ALTER TABLE `Shelfs`
MODIFY `id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `Storages`
--
ALTER TABLE `Storages`
MODIFY `id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
MODIFY `UserID` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=55;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Products`
--
ALTER TABLE `Products`
ADD CONSTRAINT `Products_ibfk_1` FOREIGN KEY (`id_shelf`) REFERENCES `Shelfs` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `Shelfs`
--
ALTER TABLE `Shelfs`
ADD CONSTRAINT `Shelfs_ibfk_1` FOREIGN KEY (`id_Storage`) REFERENCES `Storages` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `Users`
--
ALTER TABLE `Users`
ADD CONSTRAINT `Users_ibfk_1` FOREIGN KEY (`id_Storage`) REFERENCES `Storages` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
