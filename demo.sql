-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 30, 2023 at 04:37 AM
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
-- Database: `demo`
--

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `idKhachHang` int(11) NOT NULL,
  `tenKhachHang` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sdtKhachHang` varchar(255) NOT NULL,
  `diaChiKhachHang` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`idKhachHang`, `tenKhachHang`, `sdtKhachHang`, `diaChiKhachHang`) VALUES
(1, 'Vũ Đức Thịnh', '0968968032', 'Hà Nam'),
(4, 'Nguyễn Long Vũ', '0342356541', 'Lào Cai'),
(7, 'Lại Đăng Quang', '0968968030', 'Hà Nam'),
(8, 'Ngô Xuân Trường Giang', '0324567612', 'Bắc Ninh');

-- --------------------------------------------------------

--
-- Table structure for table `nguoidung`
--

CREATE TABLE `nguoidung` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nguoidung`
--

INSERT INTO `nguoidung` (`id`, `username`, `password`, `role`) VALUES
(1, 'admin', '1', 0),
(2, 'thinh', '1', 1),
(8, 'quang', '1', 2);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `id` int(11) NOT NULL,
  `maNV` varchar(255) NOT NULL,
  `hoTen` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ngaySinh` date NOT NULL,
  `diaChiThuongTru` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CCCD` varchar(255) NOT NULL,
  `soDienThoai` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ngayVaoLam` date NOT NULL,
  `chucVu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gioiTinh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`id`, `maNV`, `hoTen`, `ngaySinh`, `diaChiThuongTru`, `CCCD`, `soDienThoai`, `email`, `ngayVaoLam`, `chucVu`, `gioiTinh`) VALUES
(2, 'NV01', 'Vũ Đức Thịnh', '2002-09-15', 'Hà Nội', '035202001419', '0968968032', 'vuducthinh1509@gmail.com', '2020-01-01', 'Nhân viên bán hàng', 'Nam'),
(8, 'NV02', 'Lại Đăng Quang', '2002-01-18', 'Hà Nam', '03520200364', '0123456789', 'quangvippro@gmail.com', '2021-01-15', 'Nhân viên bảo trì', 'Nam');

-- --------------------------------------------------------

--
-- Table structure for table `phieubaohanh`
--

CREATE TABLE `phieubaohanh` (
  `id` int(11) NOT NULL,
  `ngayBaoHanh` date NOT NULL,
  `noteKhachHang` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `noteNhanVien` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `chiPhiBaoHanh` varchar(255) DEFAULT NULL,
  `idThietBi` int(11) NOT NULL,
  `trangThai` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `idKhachHang` int(11) NOT NULL,
  `idNhanVienTaoPhieu` int(11) NOT NULL,
  `ngayBanGiao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `phieubaohanh`
--

INSERT INTO `phieubaohanh` (`id`, `ngayBaoHanh`, `noteKhachHang`, `noteNhanVien`, `chiPhiBaoHanh`, `idThietBi`, `trangThai`, `idKhachHang`, `idNhanVienTaoPhieu`, `ngayBanGiao`) VALUES
(1, '2023-01-01', 'a', 'ab', '', 1, 'Đã bàn giao', 7, 2, '2023-01-15'),
(2, '2023-01-01', 'cd', 'cde', NULL, 5, 'Đang bảo hành', 1, 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `idInvoice` int(11) NOT NULL,
  `subTotalInvoice` float NOT NULL,
  `vatInvoice` int(11) NOT NULL,
  `discountInvoice` float NOT NULL,
  `discount1Invoice` float NOT NULL,
  `totalInvoice` float NOT NULL,
  `exportDateInvoice` date NOT NULL,
  `idEmployeeInvoice` int(11) NOT NULL,
  `idCustomerInvoice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `phieuxuat`
--

INSERT INTO `phieuxuat` (`idInvoice`, `subTotalInvoice`, `vatInvoice`, `discountInvoice`, `discount1Invoice`, `totalInvoice`, `exportDateInvoice`, `idEmployeeInvoice`, `idCustomerInvoice`) VALUES
(2, 660000, 53400, 60000, 10, 588000, '2022-12-10', 8, 4),
(5, 560000, 44400, 60000, 10, 489000, '2022-12-29', 2, 1),
(6, 660000, 53400, 60000, 10, 588000, '2022-12-29', 2, 7);

-- --------------------------------------------------------

--
-- Table structure for table `thietbi`
--

CREATE TABLE `thietbi` (
  `idThietBi` int(11) NOT NULL,
  `tenThietBi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `modelThietBi` varchar(255) NOT NULL,
  `serialThietBi` varchar(255) NOT NULL,
  `xuatXuThietBi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `mauThietBi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `kichThuocThietBi` varchar(255) DEFAULT NULL,
  `giaThietBi` double DEFAULT NULL,
  `maNVNguoiNhap` varchar(255) NOT NULL,
  `ngayNhapThietBi` date NOT NULL,
  `thoiGianBaoHanh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idPhieuBaoHanh` int(11) DEFAULT NULL,
  `idPhieuXuat` varchar(255) DEFAULT NULL,
  `trangThaiThietBi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `thietbi`
--

INSERT INTO `thietbi` (`idThietBi`, `tenThietBi`, `modelThietBi`, `serialThietBi`, `xuatXuThietBi`, `mauThietBi`, `kichThuocThietBi`, `giaThietBi`, `maNVNguoiNhap`, `ngayNhapThietBi`, `thoiGianBaoHanh`, `idPhieuBaoHanh`, `idPhieuXuat`, `trangThaiThietBi`) VALUES
(1, 'Máy đo SP02 Jumper 5000D', 'Jumper500D', 'JP000000', 'Việt Nam', 'Trắng Đen', '62.2x37x33.1mm', 660000, 'NV02', '2022-11-14', '2 năm', 1, '2', 'Đã xuất'),
(2, 'Máy đo huyết áp bắp tay tự động Omron Hem-7121', 'Hem7121', 'DT000001', 'Canada', 'Trắng', '103x80x129mm', 1190000, 'NV01', '2022-11-23', '3 năm', NULL, NULL, 'Trong kho'),
(3, 'Máy đo SP02 Jumper 5000D', 'Jumper500D', 'DT000002', 'Việt Nam', 'Trắng Đen', '62.2x37x33.1mm', 660000, 'NV01', '2022-11-27', '2 năm', NULL, '6', 'Đã xuất'),
(4, 'Máy đo SP02 Jumper 5000D', 'Jumper500D', 'DT000003', 'Việt Nam', 'Trắng đen', '62.2x37x33.1mm', 660000, 'NV02', '2022-11-27', '2 năm', NULL, NULL, 'Trong kho'),
(5, 'Đai lưng cột sống CPO-6223', 'CPO-6223', 'CP000004', 'Trung Quốc', 'Đen', '2x2x2m', 560000, 'NV01', '2022-11-26', 'Không', 2, '5', 'Đã xuất');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`idKhachHang`);

--
-- Indexes for table `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `username_2` (`username`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`maNV`),
  ADD UNIQUE KEY `maNV` (`maNV`),
  ADD KEY `FK_nhan_vien` (`id`);

--
-- Indexes for table `phieubaohanh`
--
ALTER TABLE `phieubaohanh`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`idInvoice`);

--
-- Indexes for table `thietbi`
--
ALTER TABLE `thietbi`
  ADD PRIMARY KEY (`idThietBi`),
  ADD UNIQUE KEY `serialThietBi` (`serialThietBi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `idKhachHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `nguoidung`
--
ALTER TABLE `nguoidung`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `phieubaohanh`
--
ALTER TABLE `phieubaohanh`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  MODIFY `idInvoice` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `thietbi`
--
ALTER TABLE `thietbi`
  MODIFY `idThietBi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `FK_nhan_vien` FOREIGN KEY (`id`) REFERENCES `nguoidung` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
