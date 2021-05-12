-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 12, 2021 at 07:12 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_nagano`
--

-- --------------------------------------------------------

--
-- Table structure for table `carts`
--

CREATE TABLE `carts` (
  `date_start` date NOT NULL,
  `date_end` date NOT NULL,
  `user_email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `cart_rooms`
--

CREATE TABLE `cart_rooms` (
  `cart_email` varchar(255) NOT NULL,
  `room_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `days`
--

CREATE TABLE `days` (
  `id` int(11) NOT NULL,
  `day_type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `days`
--

INSERT INTO `days` (`id`, `day_type`) VALUES
(1, 'weekday'),
(2, 'weekend');

-- --------------------------------------------------------

--
-- Table structure for table `day_prices`
--

CREATE TABLE `day_prices` (
  `id` int(11) NOT NULL,
  `day_type_id` int(11) NOT NULL,
  `start_at` date NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `day_prices`
--

INSERT INTO `day_prices` (`id`, `day_type_id`, `start_at`, `price`) VALUES
(1, 1, '2020-09-01', 25),
(2, 2, '2020-09-01', 50);

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `id` int(11) NOT NULL,
  `customer_count` int(11) NOT NULL,
  `date_start` date NOT NULL,
  `date_end` date NOT NULL,
  `special_instructions` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`id`, `customer_count`, `date_start`, `date_end`, `special_instructions`, `email`, `name`, `phone`) VALUES
(1, 1, '2021-04-12', '2021-04-15', NULL, 'test@test.com', 'testeur', 55555),
(2, 1, '2021-05-02', '2021-05-05', NULL, 'fgbvfrgvbdfb', 'fgbfbfbfg', 123123),
(3, 1, '2021-04-03', '2021-04-16', NULL, 'rfvdfvdfevge', 'erverfvegfvefg', 23423),
(6, 2, '2021-05-12', '2021-05-13', 'rfergergrt', 'rtgrtgtrtgh', 'rfgdrcgrg', 3242);

-- --------------------------------------------------------

--
-- Table structure for table `reservation_rooms`
--

CREATE TABLE `reservation_rooms` (
  `room_id` int(11) NOT NULL,
  `reservation_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reservation_rooms`
--

INSERT INTO `reservation_rooms` (`room_id`, `reservation_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2),
(12, 2),
(10, 3),
(11, 3),
(12, 3),
(1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'CUSTOMER'),
(3, 'EMPLOYEE');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL,
  `view_id` int(11) NOT NULL,
  `suite_id` int(11) NOT NULL,
  `floor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `view_id`, `suite_id`, `floor`) VALUES
(1, 1, 1, 3),
(2, 1, 2, 3),
(3, 2, 2, 3),
(4, 2, 3, 3),
(5, 1, 2, 2),
(6, 1, 3, 2),
(7, 2, 4, 2),
(8, 2, 4, 2),
(9, 1, 3, 1),
(10, 1, 4, 1),
(11, 2, 4, 1),
(12, 2, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `seasons`
--

CREATE TABLE `seasons` (
  `id` int(11) NOT NULL,
  `price` double NOT NULL,
  `date_start` date NOT NULL,
  `date_end` date NOT NULL,
  `event` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `seasons`
--

INSERT INTO `seasons` (`id`, `price`, `date_start`, `date_end`, `event`) VALUES
(1, 50, '2020-07-30', '2020-09-05', NULL),
(2, 1000, '2020-09-14', '2020-09-15', NULL),
(3, 25, '2020-10-01', '2020-10-02', NULL),
(4, 30, '2020-09-17', '2020-09-18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `suite`
--

CREATE TABLE `suite` (
  `id` int(11) NOT NULL,
  `suite_type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `suite`
--

INSERT INTO `suite` (`id`, `suite_type`) VALUES
(1, 'PRESIDENTIAL'),
(2, 'EXECUTIVE'),
(3, 'DOUBLE'),
(4, 'SINGLE');

-- --------------------------------------------------------

--
-- Table structure for table `suite_prices`
--

CREATE TABLE `suite_prices` (
  `id` int(11) NOT NULL,
  `suite_id` int(11) NOT NULL,
  `start_at` date NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `suite_prices`
--

INSERT INTO `suite_prices` (`id`, `suite_id`, `start_at`, `price`) VALUES
(1, 1, '2019-08-31', 3500),
(2, 2, '2019-08-31', 500),
(3, 3, '2019-08-31', 250),
(4, 4, '2019-08-31', 125),
(5, 4, '2020-10-01', 1000),
(6, 2, '2020-10-02', 550),
(7, 2, '2020-09-21', 599);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `views`
--

CREATE TABLE `views` (
  `id` int(11) NOT NULL,
  `view_type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `views`
--

INSERT INTO `views` (`id`, `view_type`) VALUES
(1, 'OCEAN'),
(2, 'PARKING');

-- --------------------------------------------------------

--
-- Table structure for table `view_prices`
--

CREATE TABLE `view_prices` (
  `id` int(11) NOT NULL,
  `view_id` int(11) NOT NULL,
  `start_at` date NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `view_prices`
--

INSERT INTO `view_prices` (`id`, `view_id`, `start_at`, `price`) VALUES
(1, 1, '2019-08-31', 150),
(2, 2, '2019-08-31', 50),
(3, 1, '2020-08-30', 175),
(4, 1, '2020-09-05', 200),
(5, 1, '2020-09-30', 20),
(6, 2, '2020-10-01', 25),
(7, 2, '2020-10-03', 26),
(8, 2, '2020-09-17', 25);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carts`
--
ALTER TABLE `carts`
  ADD KEY `FK_CartUser` (`user_email`);

--
-- Indexes for table `cart_rooms`
--
ALTER TABLE `cart_rooms`
  ADD KEY `FK_CRCart` (`cart_email`),
  ADD KEY `FK_CRRoom` (`room_id`);

--
-- Indexes for table `days`
--
ALTER TABLE `days`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `day_prices`
--
ALTER TABLE `day_prices`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_days_daysprice` (`day_type_id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservation_rooms`
--
ALTER TABLE `reservation_rooms`
  ADD KEY `FK_RRRooms` (`room_id`),
  ADD KEY `FK_RRREservation` (`reservation_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_RoomsViews` (`view_id`),
  ADD KEY `FK_RoomsSuites` (`suite_id`);

--
-- Indexes for table `seasons`
--
ALTER TABLE `seasons`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `suite`
--
ALTER TABLE `suite`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `suite_prices`
--
ALTER TABLE `suite_prices`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_SuiteSuitePrices` (`suite_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`email`),
  ADD KEY `role_id` (`role_id`);

--
-- Indexes for table `views`
--
ALTER TABLE `views`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `view_prices`
--
ALTER TABLE `view_prices`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ViewsViewPrices` (`view_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `days`
--
ALTER TABLE `days`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `day_prices`
--
ALTER TABLE `day_prices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `seasons`
--
ALTER TABLE `seasons`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `suite`
--
ALTER TABLE `suite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `suite_prices`
--
ALTER TABLE `suite_prices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `views`
--
ALTER TABLE `views`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `view_prices`
--
ALTER TABLE `view_prices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `FK_CartUser` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`);

--
-- Constraints for table `cart_rooms`
--
ALTER TABLE `cart_rooms`
  ADD CONSTRAINT `FK_CRCart` FOREIGN KEY (`cart_email`) REFERENCES `carts` (`user_email`),
  ADD CONSTRAINT `FK_CRRoom` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`);

--
-- Constraints for table `day_prices`
--
ALTER TABLE `day_prices`
  ADD CONSTRAINT `FK_days_daysprice` FOREIGN KEY (`day_type_id`) REFERENCES `days` (`id`);

--
-- Constraints for table `reservation_rooms`
--
ALTER TABLE `reservation_rooms`
  ADD CONSTRAINT `FK_RRREservation` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_RRReservations` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`),
  ADD CONSTRAINT `FK_RRRooms` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`);

--
-- Constraints for table `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `FK_RoomsSuites` FOREIGN KEY (`suite_id`) REFERENCES `suite` (`id`),
  ADD CONSTRAINT `FK_RoomsViews` FOREIGN KEY (`view_id`) REFERENCES `views` (`id`);

--
-- Constraints for table `suite_prices`
--
ALTER TABLE `suite_prices`
  ADD CONSTRAINT `FK_SuiteSuitePrices` FOREIGN KEY (`suite_id`) REFERENCES `suite` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Constraints for table `view_prices`
--
ALTER TABLE `view_prices`
  ADD CONSTRAINT `FK_ViewsViewPrices` FOREIGN KEY (`view_id`) REFERENCES `views` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
