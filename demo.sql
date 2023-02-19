-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2023 at 05:24 PM
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
(1, 'Lại Đăng Quang', '0915339997', 'Thanh Hà, Thanh Liêm, Hà Nam'),
(2, 'Trần Hải Nam', '0968968031', 'Bào Cừu, Thanh Châu, Phủ Lý, Hà Nam'),
(3, 'Trần Anh Tuấn', '0943587555', 'Lạng Sơn');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `id` int(11) NOT NULL,
  `maNV` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `hoTen` varchar(255) CHARACTER SET utf8 NOT NULL,
  `ngaySinh` date NOT NULL,
  `diaChiThuongTru` varchar(255) CHARACTER SET utf8 NOT NULL,
  `CCCD` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `soDienThoai` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngayVaoLam` date NOT NULL,
  `chucVu` varchar(255) CHARACTER SET utf8 NOT NULL,
  `gioiTinh` varchar(255) CHARACTER SET utf8 NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role` int(11) NOT NULL,
  `cauHoi` varchar(255) CHARACTER SET utf8 NOT NULL,
  `cauTraLoi` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`id`, `maNV`, `hoTen`, `ngaySinh`, `diaChiThuongTru`, `CCCD`, `soDienThoai`, `email`, `ngayVaoLam`, `chucVu`, `gioiTinh`, `username`, `password`, `role`, `cauHoi`, `cauTraLoi`) VALUES
(1, 'NV1', 'admin', '2002-09-15', 'admin', 'admin', 'admin', 'admin', '2020-07-20', 'admin', 'Nam', 'admin', '123', 0, 'Nhóm KTPMUD của bạn tên gì?', 'admin'),
(2, 'NV2', 'Vũ Đức Thịnh', '2002-09-15', 'Chân Lý, Lý Nhân, Hà Nam', '035202001419', '0968968032', 'vuducthinh1509@gmail.com', '2023-02-01', 'Quản Lý', 'Nam', 'thinh', '124', 0, 'Nhóm KTPMUD của bạn tên gì?', '5BROS'),
(3, 'NV3', 'Nguyễn Long Vũ', '2023-02-09', 'Bắc Ninh', '035202001452', '0343376640', 'nguyenlongvu@gmail.com', '2023-01-04', 'Quản Lý', 'Nam', 'vu', '123', 0, 'Thầy hướng dẫn bài tập lớn KTPMUD là ai?', 'Huy'),
(4, 'NV4', 'Nguyễn Anh Đức', '2002-03-01', 'Hà Nội', '035202004123', '0345784561', 'nguyenanhducdeptrai@gmail.com', '2023-02-08', 'Nhân viên bán hàng', 'Nam', 'anhduc', '123', 1, 'Nhóm 5BROS có mấy thành viên?', '5'),
(5, 'NV5', 'Vũ Minh Đức', '2002-05-08', 'Hà Nội', '035202000254', '0964124785', 'minhduc@gmail.com', '2023-02-07', 'Nhân viên kho', 'Nam', 'minhduc', '123', 1, 'Chủ đề ứng dụng của nhóm 5BROS liên quan đến?', 'Y Tế'),
(6, 'NV6', 'Ngô Xuân Trường Giang', '2002-08-07', 'Hải Phong', '035202000020', '0961857412', 'truonggianghust@gmail.com', '2023-01-31', 'Nhân viên bán hàng', 'Nam', 'giang', '123', 1, 'Mã học phần KTPMUD?', 'ET3260');

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
(1, '2023-02-19', 'thiết bị bị dính nước', '', '0', 3, 'Đã bàn giao', 3, 5, '2023-02-20'),
(2, '2023-02-19', 'Áo choàng không ấm', NULL, NULL, 7, 'Đang bảo hành', 1, 4, NULL);

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
(1, 2560000, 256000, 0, 0, 2816000, '2023-02-19', 2, 1),
(2, 1370000, 116300, 0, 70000, 1279300, '2023-02-19', 4, 3),
(3, 1320000, 130000, 0, 20000, 1430000, '2023-02-19', 5, 3),
(4, 2000000, 200000, 0, 0, 2200000, '2023-02-19', 4, 1);

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
(1, 'Máy đo SP02 Jumper 5000D', 'Jumper500D', 'JP000000', 'Việt Nam', 'Trắng Đen', '62.2x37x33.1mm', 660000, 'NV3', '2022-11-14', '2 năm', NULL, '3', 'Đã xuất'),
(2, 'Máy đo huyết áp bắp tay tự động Omron Hem-7121', 'Hem7121', 'DT000001', 'Canada', 'Trắng', '103x80x129mm', 1190000, 'NV3', '2022-11-23', '3 năm', NULL, NULL, 'Trong kho'),
(3, 'Máy đo SP02 Jumper 5000D', 'Jumper500D', 'DT000002', 'Việt Nam', 'Trắng Đen', '62.2x37x33.1mm', 660000, 'NV4', '2022-11-27', '2 năm', 1, '3', 'Đã xuất'),
(4, 'Máy đo SP02 Jumper 5000D', 'Jumper500D', 'DT000003', 'Việt Nam', 'Trắng đen', '62.2x37x33.1mm', 660000, 'NV5', '2022-11-27', '2 năm', NULL, NULL, 'Trong kho'),
(5, 'Đai lưng cột sống CPO-6223', 'CPO-6223', 'CP000004', 'Trung Quốc', 'Đen', '2x2x2m', 560000, 'NV6', '2022-11-26', 'Không', NULL, NULL, 'Trong kho'),
(6, 'Thuốc Tẩy Trắng Răng Opalescence', 'CPO-6233', 'CP000005', 'Nhật Bản', ' Trắng Xanh', '62.2x37x33.1mm', 520000, 'NV2', '2022-11-26', 'Không', NULL, NULL, 'Trong kho'),
(7, 'Áo choàng Điện Ủ ấm Beurer', 'HD50', 'CP000006', 'Trung Quốc', 'Nâu', '2x2x2m', 2000000, 'NV2', '2022-11-26', 'Không', 2, '4', 'Đã xuất'),
(8, 'Máy Đo Đường Huyết Safe', 'CPO-6224', 'CP000007', 'Trung Quốc', 'Trắng', '2x2x2m', 1370000, 'NV2', '2022-11-26', '3 năm', NULL, '2', 'Đã xuất'),
(9, 'Bồn Ngâm Chân Massage', 'LA110415', 'CP000008', 'Trung Quốc', 'Trắng Xanh', '2x2x2m', 2560000, 'NV3', '2022-11-26', '2 năm', NULL, '1', 'Đã xuất'),
(10, 'Túi Truyền Enema Cà Phê Thải Độc Đại Tràng PT Medical', 'CPO-6256', 'CP000009', 'Trung Quốc', 'Trắng', '62.2x37x33.1mm', 20000, 'NV3', '2022-11-26', '1 năm', NULL, NULL, 'Trong kho');

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
  MODIFY `idKhachHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `phieubaohanh`
--
ALTER TABLE `phieubaohanh`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  MODIFY `idInvoice` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `thietbi`
--
ALTER TABLE `thietbi`
  MODIFY `idThietBi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
