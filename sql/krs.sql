-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 07, 2024 at 06:02 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `krs`
--

-- --------------------------------------------------------

--
-- Table structure for table `krs`
--

CREATE TABLE `krs` (
  `kode` varchar(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `sks` int(2) NOT NULL,
  `dosen` varchar(50) NOT NULL,
  `kelas` varchar(4) NOT NULL,
  `ruang` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `krs`
--

INSERT INTO `krs` (`kode`, `nama`, `sks`, `dosen`, `kelas`, `ruang`) VALUES
('as', '2', 2, 'Mayang Sari', '1B', 'FTI 8'),
('TIF101', 'Pemrograman Dasar', 2, 'Muhammad Edya Rosadi', '1A', 'FTI 1'),
('TIF102', 'Struktur Data', 4, 'Prof. Dr. Ni Putu Desi Wulandari', 'TI-B', 'Lab 102'),
('TIF103', 'Sistem Operasi', 3, 'Dr. I Made Kerta Yasa, S.T., M.T.', 'TI-C', 'Lab 103'),
('TIF104', 'Basis Data', 3, 'Prof. Dr. I Nyoman Dewa Made Ardana', 'TI-A', 'Lab 104'),
('TIF105', 'Pemrograman Web', 3, 'Dr. Ni Wayan Sri Suprapti, S.T., M.T.', 'TI-B', 'Lab 105'),
('TIF106', 'Jaringan Komputer', 4, 'Dr. I Gede Putu Wirawan, S.T., M.T.', 'TI-C', 'Lab 106'),
('TIF107', 'Kecerdasan Buatan', 3, 'Prof. Dr. Dewa Gde Dharma Nugraha', 'TI-A', 'Lab 107'),
('TIF108', 'Manajemen Proyek TI', 2, 'Dr. Ni Kadek Ayu Kurniawati, S.T., M.T.', 'TI-B', 'Lab 108'),
('TIF109', 'Pemrograman Mobile', 3, 'Prof. Dr. I Made Wiryana, S.T., M.T.', 'TI-C', 'Lab 109'),
('TIF110', 'Keamanan Informasi', 4, 'Dr. Ni Luh Putu Ika Pratiwi, S.T., M.T.', 'TI-A', 'Lab 110');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `npm` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jenis_kelamin` varchar(2) NOT NULL,
  `prodi` varchar(50) NOT NULL,
  `semester` int(4) NOT NULL,
  `program` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`npm`, `nama`, `jenis_kelamin`, `prodi`, `semester`, `program`) VALUES
('0011223344', 'Lia Indah', 'P', 'Teknik Informatika', 3, 'Reguler'),
('0011223355', 'Mira Permata', 'P', 'Teknik Informatika', 1, 'Reguler'),
('0011223356', 'Sari Utami', 'P', 'Teknik Informatika', 2, 'Reguler'),
('123', 'assssssssss', 'P', 'Sistem Informasi', 7, 'Non-Reg'),
('12333', '12', 'P', 'Sistem Informasi', 1, 'Reg Pagi'),
('1234', '12', 'L', 'Sistem Informasi', 3, 'Reg Pagi'),
('1234555', '12', 'L', 'Sistem Informasi', 3, 'Reg Malam'),
('444', 'as', 'L', 'Teknik Informatika', 5, 'Reg Pagi');

-- --------------------------------------------------------

--
-- Table structure for table `nilai`
--

CREATE TABLE `nilai` (
  `id_nilai` int(11) NOT NULL,
  `npm` varchar(10) DEFAULT NULL,
  `kode_krs` varchar(10) DEFAULT NULL,
  `nilai` varchar(10) DEFAULT NULL,
  `tanggal_input` varchar(50) DEFAULT NULL,
  `dosen` varchar(255) DEFAULT NULL,
  `keterangan` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran_krs`
--

CREATE TABLE `pembayaran_krs` (
  `id_pembayaran` int(11) NOT NULL,
  `npm` varchar(10) NOT NULL,
  `semester` int(11) NOT NULL,
  `total_biaya` varchar(50) NOT NULL,
  `total_krs` varchar(50) NOT NULL,
  `tanggal_bayar` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `krs`
--
ALTER TABLE `krs`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`npm`);

--
-- Indexes for table `nilai`
--
ALTER TABLE `nilai`
  ADD PRIMARY KEY (`id_nilai`),
  ADD KEY `npm` (`npm`),
  ADD KEY `kode_krs` (`kode_krs`);

--
-- Indexes for table `pembayaran_krs`
--
ALTER TABLE `pembayaran_krs`
  ADD PRIMARY KEY (`id_pembayaran`),
  ADD KEY `pembayaran_krs_npm_fk` (`npm`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `nilai`
--
ALTER TABLE `nilai`
  MODIFY `id_nilai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `pembayaran_krs`
--
ALTER TABLE `pembayaran_krs`
  MODIFY `id_pembayaran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `nilai`
--
ALTER TABLE `nilai`
  ADD CONSTRAINT `nilai_ibfk_1` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`),
  ADD CONSTRAINT `nilai_ibfk_2` FOREIGN KEY (`kode_krs`) REFERENCES `krs` (`kode`);

--
-- Constraints for table `pembayaran_krs`
--
ALTER TABLE `pembayaran_krs`
  ADD CONSTRAINT `pembayaran_krs_npm_fk` FOREIGN KEY (`npm`) REFERENCES `mahasiswa` (`npm`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
