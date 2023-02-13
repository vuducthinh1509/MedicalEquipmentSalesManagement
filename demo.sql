-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 13, 2023 at 08:51 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

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
  `tenKhachHang` varchar(255) CHARACTER SET utf8 NOT NULL,
  `sdtKhachHang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `diaChiKhachHang` varchar(255) CHARACTER SET utf8 NOT NULL
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
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `id` int(11) NOT NULL,
  `maNV` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hoTen` varchar(255) CHARACTER SET utf8 NOT NULL,
  `ngaySinh` date NOT NULL,
  `diaChiThuongTru` varchar(255) CHARACTER SET utf8 NOT NULL,
  `CCCD` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `soDienThoai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngayVaoLam` date NOT NULL,
  `chucVu` varchar(255) CHARACTER SET utf8 NOT NULL,
  `gioiTinh` varchar(255) CHARACTER SET utf8 NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role` int(11) NOT NULL,
  `cauHoi` varchar(255) CHARACTER SET utf8 NOT NULL,
  `cauTraLoi` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`id`, `maNV`, `hoTen`, `ngaySinh`, `diaChiThuongTru`, `CCCD`, `soDienThoai`, `email`, `ngayVaoLam`, `chucVu`, `gioiTinh`, `username`, `password`, `role`, `cauHoi`, `cauTraLoi`) VALUES
(1, 'NV0', 'admin', '2002-09-15', 'admin', 'admin', 'admin', 'admin', '2020-07-20', 'admin', 'Nam', 'admin', '123', 0, 'Nhóm KTPMUD của bạn tên gì?', 'admin'),
(2, 'NV1', 'Vũ Đức Thịnh', '2002-09-15', 'Chân Lý-Lý Nhân-Hà Nam', '03520200xxxx', '0968968xxx', 'vuducthinh1509@gmail.com', '2020-07-20', 'Quản lý', 'Nam', 'thinh', '123', 0, 'Nhóm KTPMUD của bạn tên gì?', '5BROS'),
(3, 'NV2', 'Nguyễn Long Vũ', '2002-09-15', 'Chân Lý-Lý Nhân-Hà Nam', '03520200xxxx', '0968968xxx', 'vuducthinh1509@gmail.com', '2020-07-20', 'Nhân viên bán hàng', 'Nam', 'vu', '123', 1, 'Thầy hướng dẫn bài tập lớn KTPMUD tên gì?', 'Hoang');

-- --------------------------------------------------------

--
-- Table structure for table `phieubaohanh`
--

CREATE TABLE `phieubaohanh` (
  `id` int(11) NOT NULL,
  `ngayBaoHanh` date NOT NULL,
  `noteKhachHang` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `noteNhanVien` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `chiPhiBaoHanh` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idThietBi` int(11) NOT NULL,
  `trangThai` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `idKhachHang` int(11) NOT NULL,
  `idNhanVienTaoPhieu` int(11) NOT NULL,
  `ngayBanGiao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `phieubaohanh`
--

INSERT INTO `phieubaohanh` (`id`, `ngayBaoHanh`, `noteKhachHang`, `noteNhanVien`, `chiPhiBaoHanh`, `idThietBi`, `trangThai`, `idKhachHang`, `idNhanVienTaoPhieu`, `ngayBanGiao`) VALUES
(1, '2023-01-01', NULL, NULL, '', 1, 'Đã bàn giao', 7, 2, '2023-01-15'),
(2, '2023-01-01', NULL, NULL, NULL, 5, 'Đang bảo hành', 1, 2, NULL);

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
(2, 660000, 53400, 60000, 10, 588000, '2022-12-10', 2, 4),
(5, 560000, 44400, 60000, 10, 489000, '2022-12-29', 3, 1),
(6, 660000, 53400, 60000, 10, 588000, '2022-12-29', 3, 7);

-- --------------------------------------------------------

--
-- Table structure for table `thietbi`
--

CREATE TABLE `thietbi` (
  `idThietBi` int(11) NOT NULL,
  `tenThietBi` varchar(255) CHARACTER SET utf8 NOT NULL,
  `modelThietBi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `serialThietBi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `xuatXuThietBi` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `mauThietBi` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `kichThuocThietBi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `giaThietBi` double DEFAULT NULL,
  `maNVNguoiNhap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ngayNhapThietBi` date NOT NULL,
  `thoiGianBaoHanh` varchar(255) CHARACTER SET utf8 NOT NULL,
  `idPhieuBaoHanh` int(11) DEFAULT NULL,
  `idPhieuXuat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trangThaiThietBi` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `thietbi`
--

INSERT INTO `thietbi` (`idThietBi`, `tenThietBi`, `modelThietBi`, `serialThietBi`, `xuatXuThietBi`, `mauThietBi`, `kichThuocThietBi`, `giaThietBi`, `maNVNguoiNhap`, `ngayNhapThietBi`, `thoiGianBaoHanh`, `idPhieuBaoHanh`, `idPhieuXuat`, `trangThaiThietBi`) VALUES
(1, 'Máy đo SP02 Jumper 5000D', 'Jumper500D', 'JP000000', 'Việt Nam', 'Trắng Đen', '62.2x37x33.1mm', 660000, 'NV1', '2022-11-14', '2 năm', 1, '2', 'Đã xuất'),
(2, 'Máy đo huyết áp bắp tay tự động Omron Hem-7121', 'Hem7121', 'DT000001', 'Canada', 'Trắng', '103x80x129mm', 1190000, 'NV1', '2022-11-23', '3 năm', NULL, NULL, 'Trong kho'),
(3, 'Máy đo SP02 Jumper 5000D', 'Jumper500D', 'DT000002', 'Việt Nam', 'Trắng Đen', '62.2x37x33.1mm', 660000, 'NV1', '2022-11-27', '2 năm', NULL, '6', 'Đã xuất'),
(4, 'Máy đo SP02 Jumper 5000D', 'Jumper500D', 'DT000003', 'Việt Nam', 'Trắng đen', '62.2x37x33.1mm', 660000, 'NV1', '2022-11-27', '2 năm', NULL, NULL, 'Trong kho'),
(5, 'Đai lưng cột sống CPO-6223', 'CPO-6223', 'CP000004', 'Trung Quốc', 'Đen', '2x2x2m', 560000, 'NV1', '2022-11-26', 'Không', 2, '5', 'Đã xuất'),
(6, 'Máy massage cầm tay 5 chế độ Wellmed HY-5612', 'HY-5612', 'HY000001', 'Thụy Sĩ', 'Trắng', '138 x 94.5 x 62.5 mm', 10600000, 'NV1', '2023-02-12', '24 tháng', NULL, NULL, 'Trong kho');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`idKhachHang`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

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
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
  MODIFY `idThietBi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
