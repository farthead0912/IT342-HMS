-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 24, 2025 at 01:48 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hms-db`
--

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `dept_id` int(11) NOT NULL,
  `dept_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`dept_id`, `dept_name`) VALUES
(1, 'Cardiology'),
(2, 'Neurology'),
(3, 'Pediatrics'),
(4, 'Surgery'),
(5, 'Radiology'),
(6, 'Orthopedics'),
(7, 'Intensive Care Unit'),
(8, 'Obstetrics & Gynecology'),
(9, 'Emergency'),
(10, 'Ophthalmology'),
(11, 'Not In Use');

-- --------------------------------------------------------

--
-- Table structure for table `department_equipment`
--

CREATE TABLE `department_equipment` (
  `id` int(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  `equipment_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `department_equipment`
--

INSERT INTO `department_equipment` (`id`, `dept_id`, `equipment_id`) VALUES
(1, 1, 11),
(2, 1, 20),
(3, 2, 12),
(4, 3, 16),
(5, 4, 17),
(6, 5, 15),
(7, 5, 16),
(8, 6, 18),
(9, 7, 11),
(10, 7, 12),
(11, 7, 13),
(12, 7, 14),
(13, 8, 16),
(14, 9, 14),
(15, 9, 19),
(16, 10, 20);

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE `doctors` (
  `doctor_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `specialization` varchar(50) NOT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctors`
--

INSERT INTO `doctors` (`doctor_id`, `first_name`, `last_name`, `specialization`, `dept_id`, `user_id`) VALUES
(1, 'Ophthal', 'Mologist', 'Ophthalmologist', 10, 1),
(2, 'Neuro', 'Logy', 'Neurologist', 2, 5),
(3, 'Ob', 'Gyne', 'Obstetrics & Gynecologist', 8, 4),
(4, 'Pedia', 'Trician', 'Pediatrician', 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE `equipment` (
  `equipment_id` int(11) NOT NULL,
  `equipment_name` varchar(50) NOT NULL,
  `equipment_type` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `status` varchar(50) NOT NULL,
  `stock` int(11) NOT NULL,
  `room_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`equipment_id`, `equipment_name`, `equipment_type`, `price`, `status`, `stock`, `room_id`) VALUES
(11, 'Heart Monitor', 'Monitoring', 15000, 'Available', 2, 6),
(12, 'Ventilator', 'Respiratory', 25000, 'In Use', 3, 6),
(13, 'Infusion Pump', 'Medical Device', 8000, 'Available', 5, 2),
(14, 'Defibrillator', 'Emergency', 20000, 'Available', 1, 7),
(15, 'X-Ray Machine', 'Imaging', 50000, 'In Use', 1, 3),
(16, 'Ultrasound Machine', 'Imaging', 30000, 'Available', 2, 3),
(17, 'Hospital Bed', 'Furniture', 12000, 'Available', 10, NULL),
(18, 'Wheelchair', 'Mobility Aid', 5000, 'In Use', 4, 4),
(19, 'Syringe Pump', 'Medical Device', 7000, 'Available', 6, 5),
(20, 'Portable ECG', 'Diagnostic', 18000, 'Available', 3, 7);

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `patient_id` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `blood_type` varchar(3) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `room_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`patient_id`, `age`, `blood_type`, `first_name`, `gender`, `last_name`, `room_id`, `user_id`) VALUES
(1, 21, 'AB+', 'Patient', 'Male', 'McPatient', NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `patient_records`
--

CREATE TABLE `patient_records` (
  `record_id` int(11) NOT NULL,
  `diagnosis_date` datetime(6) DEFAULT NULL,
  `severity` varchar(50) NOT NULL,
  `sickness` varchar(255) DEFAULT NULL,
  `treatment_plan` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `patient_records`
--

INSERT INTO `patient_records` (`record_id`, `diagnosis_date`, `severity`, `sickness`, `treatment_plan`, `patient_id`) VALUES
(1, '2025-03-21 10:50:00.000000', 'Mild', 'Common cold, sniffles, a little booboo to the shoulder', 'Antibiotics, flu medicine, and a kiss to the booboo should be enough. No need to monitor.', 1);

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `room_id` int(11) NOT NULL,
  `floor_number` int(11) NOT NULL,
  `is_occupied` bit(1) NOT NULL,
  `room_number` int(11) NOT NULL,
  `room_price` double NOT NULL,
  `room_type` varchar(50) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_id`, `floor_number`, `is_occupied`, `room_number`, `room_price`, `room_type`, `patient_id`, `staff_id`) VALUES
(1, 1, b'0', 101, 3500, 'Standard', NULL, NULL),
(2, 2, b'0', 202, 5000, 'Private', NULL, NULL),
(3, 3, b'0', 305, 7500, 'VIP', NULL, NULL),
(4, 1, b'0', 110, 4000, 'Semi-Private', NULL, NULL),
(5, 2, b'0', 215, 6000, 'Deluxe Suite', NULL, NULL),
(6, 4, b'0', 401, 12000, 'ICU', NULL, NULL),
(7, 1, b'0', 0, 0, 'Emergency', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `schedules`
--

CREATE TABLE `schedules` (
  `schedule_id` int(11) NOT NULL,
  `appointment_date` datetime(6) DEFAULT NULL,
  `appointment_time` time(6) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staff_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `position` varchar(50) NOT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(18) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `email`, `password`, `role`, `username`) VALUES
(1, 'ophthal@testing.com', 'ophthal123', 'Ophthalmologist', 'ophthal'),
(2, 'test@testing.com', 'testing123', 'Patient', 'tester'),
(3, 'doctor@testing.com', 'doctor123', 'Doctor', 'doctor'),
(4, 'obgyn@testing.com', 'obgyn123', 'OB/GYN', 'obgyn'),
(5, 'neuro@testing.com', 'neuro123', 'Neurologist', 'neuro');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`dept_id`);

--
-- Indexes for table `department_equipment`
--
ALTER TABLE `department_equipment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKouqbq1i3kw72so057gk13oh2p` (`dept_id`),
  ADD KEY `FKcp8aoywydnh15rr6uy88y4838` (`equipment_id`);

--
-- Indexes for table `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`doctor_id`),
  ADD UNIQUE KEY `UKt1f6cueqyjwx5ghew9ar1exe3` (`user_id`),
  ADD KEY `FKr453b3sve2xpdwqcvib49ob8r` (`dept_id`);

--
-- Indexes for table `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`equipment_id`),
  ADD KEY `FKha4qvopj5gipabhegokka0qo7` (`room_id`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`patient_id`),
  ADD UNIQUE KEY `UK9tbsl3fmey0eofbm2xj69v4qs` (`user_id`),
  ADD KEY `FKehw4x5ovd8uekmlurrw8h9x7s` (`room_id`);

--
-- Indexes for table `patient_records`
--
ALTER TABLE `patient_records`
  ADD PRIMARY KEY (`record_id`),
  ADD KEY `FKp3i7exgh035i4miakj703j1si` (`patient_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`room_id`),
  ADD KEY `FK6gmk2kpb5k8v6sbqf09u8rcj8` (`patient_id`),
  ADD KEY `FKhi7yn3kvugo0jqn3ko1e4f3rd` (`staff_id`);

--
-- Indexes for table `schedules`
--
ALTER TABLE `schedules`
  ADD PRIMARY KEY (`schedule_id`),
  ADD KEY `FKfpyatautb52nts46e1y1y4nvg` (`doctor_id`),
  ADD KEY `FKlkbhd3qson2igvcgunljlwdym` (`patient_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staff_id`),
  ADD KEY `FKit442l781hp6idqx7hsffdmmk` (`dept_id`),
  ADD KEY `FKdlvw23ak3u9v9bomm8g12rtc0` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
  MODIFY `dept_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `department_equipment`
--
ALTER TABLE `department_equipment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `doctors`
--
ALTER TABLE `doctors`
  MODIFY `doctor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `equipment`
--
ALTER TABLE `equipment`
  MODIFY `equipment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `patients`
--
ALTER TABLE `patients`
  MODIFY `patient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `patient_records`
--
ALTER TABLE `patient_records`
  MODIFY `record_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `schedules`
--
ALTER TABLE `schedules`
  MODIFY `schedule_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `department_equipment`
--
ALTER TABLE `department_equipment`
  ADD CONSTRAINT `FKcp8aoywydnh15rr6uy88y4838` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`equipment_id`),
  ADD CONSTRAINT `FKouqbq1i3kw72so057gk13oh2p` FOREIGN KEY (`dept_id`) REFERENCES `departments` (`dept_id`);

--
-- Constraints for table `doctors`
--
ALTER TABLE `doctors`
  ADD CONSTRAINT `FKe9pf5qtxxkdyrwibaevo9frtk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKr453b3sve2xpdwqcvib49ob8r` FOREIGN KEY (`dept_id`) REFERENCES `departments` (`dept_id`);

--
-- Constraints for table `equipment`
--
ALTER TABLE `equipment`
  ADD CONSTRAINT `FKha4qvopj5gipabhegokka0qo7` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`);

--
-- Constraints for table `patients`
--
ALTER TABLE `patients`
  ADD CONSTRAINT `FKehw4x5ovd8uekmlurrw8h9x7s` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`),
  ADD CONSTRAINT `FKuwca24wcd1tg6pjex8lmc0y7` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `patient_records`
--
ALTER TABLE `patient_records`
  ADD CONSTRAINT `FKp3i7exgh035i4miakj703j1si` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`);

--
-- Constraints for table `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `FK6gmk2kpb5k8v6sbqf09u8rcj8` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  ADD CONSTRAINT `FKhi7yn3kvugo0jqn3ko1e4f3rd` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`);

--
-- Constraints for table `schedules`
--
ALTER TABLE `schedules`
  ADD CONSTRAINT `FKfpyatautb52nts46e1y1y4nvg` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`),
  ADD CONSTRAINT `FKlkbhd3qson2igvcgunljlwdym` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`);

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `FKdlvw23ak3u9v9bomm8g12rtc0` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKit442l781hp6idqx7hsffdmmk` FOREIGN KEY (`dept_id`) REFERENCES `departments` (`dept_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
