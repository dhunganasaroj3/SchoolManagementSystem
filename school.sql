-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2017 at 12:51 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `school`
--

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `s/n` int(20) NOT NULL,
  `Class` int(20) NOT NULL,
  `Subject` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`s/n`, `Class`, `Subject`) VALUES
(1, 1, 'Nepali'),
(2, 1, 'English'),
(3, 1, 'Math'),
(4, 1, 'Social Studies'),
(5, 1, 'Science'),
(6, 2, 'Nepali'),
(7, 2, 'English'),
(8, 2, 'Math'),
(9, 2, 'Social Studies'),
(10, 2, 'Science'),
(11, 3, 'Nepali'),
(12, 3, 'English'),
(13, 3, 'Math'),
(14, 3, 'Social Studies'),
(15, 3, 'Science'),
(16, 4, 'Nepali'),
(17, 4, 'English'),
(18, 4, 'Math'),
(19, 4, 'Social Studies'),
(20, 4, 'Science'),
(21, 5, 'Nepali'),
(22, 5, 'English'),
(23, 5, 'Math'),
(24, 5, 'Social Studies'),
(25, 5, 'Science'),
(26, 6, 'Nepal'),
(27, 6, 'English'),
(28, 6, 'Math'),
(29, 6, 'Social Stuies'),
(30, 6, 'Science'),
(31, 6, 'Health and Environme'),
(32, 7, 'Nepal'),
(33, 7, 'English'),
(34, 7, 'Math'),
(35, 7, 'Social Stuies'),
(36, 7, 'Science'),
(37, 7, 'Health and Environme'),
(38, 8, 'Nepal'),
(39, 8, 'English'),
(40, 8, 'Math'),
(41, 8, 'Social Stuies'),
(42, 8, 'Science'),
(43, 8, 'Health and Environme'),
(44, 9, 'Nepal'),
(45, 9, 'English'),
(46, 9, 'Math'),
(47, 9, 'Social Stuies'),
(48, 9, 'Science'),
(49, 9, 'Health and Environme'),
(50, 10, 'Nepal'),
(51, 10, 'English'),
(52, 10, 'Math'),
(53, 10, 'Social Stuies'),
(54, 10, 'Science'),
(55, 10, 'Health and Environme');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `userid` varchar(20) NOT NULL,
  `fullname` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `division` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`userid`, `fullname`, `username`, `password`, `division`) VALUES
('10001', 'binodnagarkoti', 'admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `resultr`
--

CREATE TABLE `resultr` (
  `s/n` int(11) NOT NULL,
  `symbol_number` varchar(30) NOT NULL,
  `student_id` varchar(50) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `rool_no` int(20) NOT NULL,
  `terminal` varchar(20) NOT NULL,
  `class` int(10) NOT NULL,
  `subject` varchar(20) NOT NULL,
  `marks` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `student_id` varchar(20) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `roll_no` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `email` varchar(40) NOT NULL,
  `DOB` date NOT NULL,
  `Class` int(20) NOT NULL,
  `DOB_place` varchar(20) NOT NULL,
  `father` varchar(20) NOT NULL,
  `mother` varchar(20) NOT NULL,
  `parentnumber` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `fname`, `lname`, `roll_no`, `username`, `password`, `gender`, `email`, `DOB`, `Class`, `DOB_place`, `father`, `mother`, `parentnumber`) VALUES
('14ONE01', 'abc', 'dfg', 1, 'abcd13', '123', 'male', 'abc_dfg@gmail.com', '2010-01-14', 1, 'kathmandu', 'bcd', 'fgd', '980311221'),
('14ONE02', 'ads', 'fsd', 2, 'adsfsd32', '123', 'male', 'ads_mal@gmail.com', '2011-04-12', 1, 'bhaktapur', 'bfsd', 'msd', '9823192833'),
('14TWO01', 'gfS', 'kals', 1, 'kalsgfS@12', '123', 'female', 'kalsgfs@icloud.com', '2009-05-09', 2, 'kathmandu', 'hahsla', 'masbsj', '981231234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`s/n`),
  ADD KEY `Class` (`Class`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `userid` (`userid`);

--
-- Indexes for table `resultr`
--
ALTER TABLE `resultr`
  ADD PRIMARY KEY (`s/n`),
  ADD KEY `s/n` (`s/n`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD UNIQUE KEY `student_id` (`student_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `s/n` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;
--
-- AUTO_INCREMENT for table `resultr`
--
ALTER TABLE `resultr`
  MODIFY `s/n` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
