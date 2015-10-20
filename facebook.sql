-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2+deb7u1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 14, 2015 at 05:17 PM
-- Server version: 5.5.44
-- PHP Version: 5.4.45-0+deb7u1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `facebook`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`user_id`, `email`, `password`) VALUES
(1, 'rafsan@gmail.com', 'teto'),
(2, 'rafsanteto@gmail.com', 'teto'),
(3, 'reto@gmail.com', 'reto'),
(4, 'shovon@gmail.com', 'shovon'),
(5, 'rahim@gmail.com', 'rahim'),
(6, 'teto@gmail.com', 'teto'),
(7, 'babar@gmail.colm', 'babar');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` longtext NOT NULL,
  `comment_time` date NOT NULL,
  PRIMARY KEY (`comment_id`),
  UNIQUE KEY `comment_id` (`comment_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `post_id`, `user_id`, `content`, `comment_time`) VALUES
(1, 1, 6, 'tor abar ki holo....', '2015-10-14'),
(2, 2, 7, 'hi hi hi..', '2015-10-14'),
(3, 3, 2, 'ok ok ok', '2015-10-14');

-- --------------------------------------------------------

--
-- Table structure for table `friend_list`
--

CREATE TABLE IF NOT EXISTS `friend_list` (
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `friendship` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friend_list`
--

INSERT INTO `friend_list` (`user_id`, `friend_id`, `friendship`) VALUES
(1, 2, NULL),
(1, 3, NULL),
(1, 5, NULL),
(1, 7, NULL),
(2, 1, NULL),
(2, 3, NULL),
(2, 6, NULL),
(3, 1, NULL),
(3, 2, NULL),
(3, 4, NULL),
(4, 3, NULL),
(4, 6, NULL),
(5, 1, NULL),
(6, 2, NULL),
(6, 4, NULL),
(7, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `likes`
--

CREATE TABLE IF NOT EXISTS `likes` (
  `post_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`post_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `likes`
--

INSERT INTO `likes` (`post_id`, `user_id`) VALUES
(1, 6),
(2, 2),
(2, 7),
(3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `content` longtext,
  PRIMARY KEY (`message_id`),
  UNIQUE KEY `message_id` (`message_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`message_id`, `sender_id`, `receiver_id`, `content`) VALUES
(1, 1, 5, 'kmn asis dost...'),
(2, 5, 1, 'ami valo asi tui kmn asis?'),
(3, 6, 4, 'hi shovon..'),
(4, 7, 1, 'hello...'),
(5, 2, 6, 'ki koros?');

-- --------------------------------------------------------

--
-- Table structure for table `personal_info`
--

CREATE TABLE IF NOT EXISTS `personal_info` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `sex` varchar(100) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personal_info`
--

INSERT INTO `personal_info` (`user_id`, `first_name`, `last_name`, `sex`, `date_of_birth`, `age`) VALUES
(1, 'Md. Rafsan', 'Jani', 'male', '1990-06-30', 25),
(2, 'Rafsan', 'Jani', 'male', '1990-06-30', 25),
(3, 'sabbir', 'janee', 'male', '1993-12-07', 21),
(4, 'ashraf', 'shovon', 'male', '1990-06-27', 25),
(5, 'abdur ', 'rahim', 'male', '1990-05-01', 25),
(6, 'teto', 'teto', 'male', '1989-06-30', 26),
(7, 'Babar', 'Akbar', 'male', '1990-06-08', 25);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `post_content` longtext,
  `post_time` date DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `post_id` (`post_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`post_id`, `user_id`, `post_content`, `post_time`) VALUES
(1, 2, 'ami valo asi....', '2015-10-13'),
(2, 1, 'Amake amar moto thakte daw ami nijeke nijer moto gusia niachi..', '2015-10-14'),
(3, 1, 'ai jahaz mastul sarkhar tobu shopno deksi bachbar....', '2015-10-14'),
(4, 7, 'good morning..', '2015-10-14');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
