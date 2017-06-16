-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2017 at 06:15 AM
-- Server version: 5.6.17
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `docket-sbcon`
--

-- --------------------------------------------------------

--
-- Table structure for table `bb_details`
--

CREATE TABLE IF NOT EXISTS `bb_details` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `A+` int(11) NOT NULL DEFAULT '0',
  `B+` int(11) NOT NULL DEFAULT '0',
  `AB+` int(11) NOT NULL DEFAULT '0',
  `O+` int(11) NOT NULL DEFAULT '0',
  `A-` int(11) NOT NULL DEFAULT '0',
  `B-` int(11) NOT NULL DEFAULT '0',
  `AB-` int(11) NOT NULL DEFAULT '0',
  `O-` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `bb_details`
--

INSERT INTO `bb_details` (`id`, `A+`, `B+`, `AB+`, `O+`, `A-`, `B-`, `AB-`, `O-`, `user_id`, `created_at`, `updated_at`) VALUES
(1, 1, 0, 1, 0, 12, 0, 0, 0, 4, '2017-06-15 19:29:45', '2017-06-15 20:21:48'),
(2, 0, 0, 0, 0, 0, 0, 0, 0, 5, '2017-06-16 02:47:36', '2017-06-16 02:47:36'),
(3, 0, 0, 0, 0, 0, 0, 0, 0, 6, '2017-06-16 02:53:04', '2017-06-16 02:53:04'),
(4, 0, 0, 0, 0, 0, 0, 0, 0, 7, '2017-06-16 02:57:53', '2017-06-16 02:57:53'),
(5, 0, 0, 0, 0, 0, 0, 0, 0, 11, '2017-06-16 03:07:06', '2017-06-16 03:07:06'),
(6, 0, 0, 0, 0, 0, 0, 0, 0, 12, '2017-06-16 03:29:35', '2017-06-16 03:29:35');

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `bloodgroup` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`id`, `name`, `email`, `phone`, `password`, `city`, `state`, `bloodgroup`, `created_at`, `updated_at`) VALUES
(1, 'Piyush', 'piyushfzr96@gmail.com', '09878543235', 'abc', 'Ferozepur', 'Punjab', 'A+', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `donar_requests`
--

CREATE TABLE IF NOT EXISTS `donar_requests` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `client_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `donations`
--

CREATE TABLE IF NOT EXISTS `donations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mobile` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `bloodgroup` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `donations`
--

INSERT INTO `donations` (`id`, `name`, `address`, `mobile`, `bloodgroup`, `date`, `status`, `created_at`, `updated_at`, `user_id`) VALUES
(1, 'Piyush Gupta', '156 Street 4, Ferozepur, Ferozepur', '9878543235', 'a+', '2017-06-16', 'Approved', '2017-06-16 04:04:27', '2017-06-16 04:04:27', 0);

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE IF NOT EXISTS `migrations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=10 ;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2017_06_15_123459_create_clients_table', 2),
(4, '2017_06_15_205716_create_receive_requests_table', 3),
(7, '2017_06_15_223036_create_donar_requests_table', 4),
(8, '2017_06_16_002412_create_bb_details', 5),
(9, '2017_06_16_015541_create_donations_table', 6);

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE IF NOT EXISTS `password_resets` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  KEY `password_resets_email_index` (`email`),
  KEY `password_resets_token_index` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `password_resets`
--

INSERT INTO `password_resets` (`email`, `token`, `created_at`) VALUES
('admin@docket.com', '6cfb086c43341235dd59237a5091662693f5b6e1fd182cc94d958178fd5e14fd', '2017-06-15 06:40:14');

-- --------------------------------------------------------

--
-- Table structure for table `receive_requests`
--

CREATE TABLE IF NOT EXISTS `receive_requests` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bloodgroup` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `city1` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `city2` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `city3` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `client_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `receive_requests`
--

INSERT INTO `receive_requests` (`id`, `bloodgroup`, `state`, `city1`, `city2`, `city3`, `phone`, `client_id`, `created_at`, `updated_at`) VALUES
(1, 'a+', 'Punjab', 'Ferozepur', 'chandigarh', 'jalandhar', '9878543235', 1, '2017-06-15 16:04:51', '2017-06-15 16:04:51'),
(2, '', 'Punjab', 'Ferozepur', 'Ferozepur', 'Ferozepur', '9878543235', 1, '2017-06-15 17:22:37', '2017-06-15 17:22:37');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `address` text COLLATE utf8_unicode_ci,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_unique` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=13 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `remember_token`, `created_at`, `updated_at`, `address`, `phone`, `city`, `state`) VALUES
(1, 'Admin', 'admin@docket.com', '$2y$10$tVsoetOFX0vpXw7ZMVQGguiX2QiBMk07772NfZ8zKLCoTx7D40H5m', '2VPTng5FvQG2lRmlt8h3Hf6T988hMhZxLMSTVWYg8ASba6fOFIj8hBaIUXw2', '2017-06-15 01:02:09', '2017-06-15 19:39:15', NULL, NULL, NULL, NULL),
(4, 'abc1', 'abc@3.com', '$2y$10$vnohPqJ7WhDamyqGFwGV7eNg1Vd7uMY5GRvKQzsICTZP3iiwuQceC', 'zBOPBeYWx55F3j3P5mrwGxQlDvmLPQOI4mb5pxDFpcASuV41Q4VwPAHg25On', '2017-06-15 19:29:45', '2017-06-16 01:39:46', NULL, NULL, NULL, NULL),
(11, 'Piyush Gupta', 'piyushfzr96@gmail.com', '$2y$10$LpRbmjTFcUdnJzjYYpTDjeW6Lw8g1d2flQeummqhZB917IRHprIWW', 'b5OTDgG4k5wgELhmUIStMQTCGw3r3JsBjk5KBn3V58eUblPSklFJo3FBiYWO', '2017-06-16 03:07:05', '2017-06-16 03:29:08', '156 Street 4, Ferozepur, Ferozepur', '9878543235', 'Port Blair*', 'Punjab'),
(12, 'Piyush Gupta', 'c1@c.com', '$2y$10$T8Nl4ktczE31LhVuUfm..u0gd9lXe.7VC3RRcFrsBg7OoMMFFeNuK', NULL, '2017-06-16 03:29:34', '2017-06-16 03:29:34', '156 Street 4, Ferozepur, Ferozepur', '9878543235', 'Port Blair*', 'Punjab');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
