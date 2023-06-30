-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 27, 2023 lúc 04:35 AM
-- Phiên bản máy phục vụ: 10.6.7-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `datatrangsuc`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `idhoadon` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `idsanpham` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongia` int(11) NOT NULL,
  `thanhtien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chudesanpham`
--

CREATE TABLE `chudesanpham` (
  `idchude` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `tenchude` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `hinhchude` text COLLATE utf8mb3_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chudesanpham`
--

INSERT INTO `chudesanpham` (`idchude`, `tenchude`, `hinhchude`) VALUES
('BT', 'Bông tai', 'bongtai_hcd.png'),
('DC', 'Dây cổ', 'dayco_hcd.png'),
('DH', 'Đồng hồ', 'dongho_hcd.jpg'),
('K', 'Kiềng', 'kieng_hcd.png'),
('KC', 'Kim cương', 'kimcuong_hcd.png'),
('LT', 'Lắc tay', 'lactay_hcd.png'),
('NC', 'Nhẫn cưới', 'nhancuoi_hcd.png'),
('V', 'Vòng', 'vong_hcd.png'),
('VT', 'Vòng tay', 'vongtay_hcd.png'),
('VTL', 'Vàng tài lộc', 'vangtailoc_hcd.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhgia`
--

CREATE TABLE `danhgia` (
  `id_danhgia` int(11) NOT NULL,
  `idsanpham` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `noidung` text DEFAULT NULL,
  `diemdanhgia` int(11) DEFAULT NULL,
  `thoigian` datetime DEFAULT NULL,
  `username` varchar(10000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `danhgia`
--

INSERT INTO `danhgia` (`id_danhgia`, `idsanpham`, `noidung`, `diemdanhgia`, `thoigian`, `username`) VALUES
(1, 'BT01', 'a', 1, '2023-06-22 15:50:41', NULL),
(2, 'BT01', 'a', 1, '2023-06-22 15:52:33', NULL),
(3, 'BT01', 'a', 1, '2023-06-22 15:53:56', NULL),
(4, 'BT01', 'b', 2, '2023-06-22 15:54:13', NULL),
(5, 'BT01', 'v', 2, '2023-06-22 15:54:38', NULL),
(6, 'BT02', 'San pham tot', 5, '2023-06-22 15:56:26', NULL),
(7, 'BT01', 'a', 5, '2023-06-27 08:19:12', ''),
(8, 'BT01', 'b', 4, '2023-06-27 08:20:42', '\"a\"'),
(9, 'BT01', 'danh gia tot', 5, '2023-06-27 08:22:41', 'a'),
(10, 'BT01', 'b', 5, '2023-06-27 09:01:00', 'a'),
(11, 'BT01', 'danh gai tot', 5, '2023-06-27 09:02:30', 'a'),
(12, 'BT01', 'hoan thanh roi', 6, '2023-06-27 09:02:51', 'a'),
(13, 'BT01', 'ok', 7, '2023-06-27 09:03:46', 'a'),
(14, 'BT01', 'ok 2', 5, '2023-06-27 09:04:52', 'a'),
(15, 'DC01', 'ok', 5, '2023-06-27 09:06:00', 'a'),
(16, 'DC01', 'ok2', 6, '2023-06-27 09:06:10', 'a'),
(17, 'BT01', 'b', 5, '2023-06-27 09:07:27', 'abc');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `idhoadon` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `thanhtien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `email` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  `hoten` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `diachi` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `sodienthoai` varchar(11) COLLATE utf8mb3_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`email`, `password`, `hoten`, `diachi`, `sodienthoai`) VALUES
('a', 'a', 'a', 'a', '123456'),
('abc', 'abc', 'abc', '094248', 'abc'),
('vohung@gmail.com', '123456', 'abc', '094248', 'abc');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `idsanpham` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `idchude` varchar(10) COLLATE utf8mb3_unicode_ci NOT NULL,
  `tensanpham` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `giasanpham` int(11) NOT NULL,
  `hinhsanpham` text COLLATE utf8mb3_unicode_ci NOT NULL,
  `motasanpham` varchar(10000) COLLATE utf8mb3_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`idsanpham`, `idchude`, `tensanpham`, `giasanpham`, `hinhsanpham`, `motasanpham`) VALUES
('BT01', 'BT', 'Bông tai cưới Vàng', 5746000, 'bongtai1.png', 'Bông tai là một trong những món phụ kiện không thể thiếu đối với phái nữ.\r\nLà con gái ai cũng muốn mình luôn xinh đẹp và có sức hút trong mắt người đối diện vì thế chọn đôi bông tai vừa nhẹ nhàng lại vừa tinh tế là lựa chọn hàng đầu cho chị em phụ nữ\r\nCó thể kết hợp với nhiều loại trang phục, phụ kiện đi kèm tôn nét đẹp tinh tế của phái đẹp, với giá cả phải chăng bạn dễ dàng thay đổi phong cách của chính mình làm cho mối quan hệ thêm tươi mới.\r\nBông tai nữ Sở hữu thiết kế với vẻ đẹp tinh tế.\r\nSáng bóng, bền đẹp theo thơi gian.\r\nThiết kế đính đá một cách tinh xảo hoàn hảo.\r\nBông tai nữ Có thể mix với những bộ trang phục yêu thích.\r\n\r\nGiá sản phẩm trên Tiki đã bao gồm thuế theo luật hiện hành. Bên cạnh đó, tuỳ vào loại sản phẩm, hình thức và địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như phí vận chuyển, phụ phí hàng cồng kềnh, thuế nhập khẩu (đối với đơn hàng giao từ nước ngoài có giá trị trên 1 triệu đồng).....'),
('BT02', 'BT', 'Bông tai cưới Vàng ', 6160000, 'bongtai2.png', 'Trong văn hóa Việt Nam truyền thống tốt đẹp luôn được giữ gìn và truyền lại cho thế hệ sau. Những nghi thức, tục lệ xưa vẫn luôn được bảo tồn và duy trì. Ví như việc trăm năm của đời người, có rất nhiều tục lệ mà nhiều người chưa biết, đặc biệt có những thứ chúng ta tưởng như đơn giản nhưng thực sự ý nghĩa đằng sau nó nhiều người không ngờ đến. Ý nghĩa của đôi bông tai cưới là một ví dụ cụ thể.\r\n\r\nNgày nay, chúng ta thường thấy chú rể đeo bông tai cưới cho cô dâu mà không hề biết rằng theo phong tục Việt Nam từ ngàn xưa, theo sự thỏa thuận giữa hai bên gia đình, mẹ chồng sẽ tự tay đeo bông tai cưới cho con dâu trong ngày dạm hỏi (được tổ chức bên đàng gái ) hoặc trong ngày cưới ( trước lễ rước dâu ).'),
('DC01', 'DC', 'Dây cổ cưới Vàng ', 30276889, 'dayco1.png', 'Có thể nhiều người không biết, ý nghĩa sâu sắc của chiếc dây chuyền nằm chính ở thiết kế hai đầu nối nhau của nó. Một chiếc dây chuyền lúc nào cũng có điểm đầu và điểm cuối, nhưng để đeo được lên cổ thì tất nhiên chúng ta phải nối hai đầu lại với nhau. Đây chính là ý nghĩa của nó.\r\n\r\nSự móc nối của hai đầu sợi dây chuyền được xem như một vòng tuần hoàn, biểu tượng sự gắn kết, bền chặt. Vậy nên, tặng dây chuyền cho bạn gái mang ý nghĩa mong muốn tình yêu của hai bạn luôn gắn kết lâu bền, qua đó cũng thể hiện được tình cảm chân thành mà người con trai dành cho người yêu của mình.'),
('DC02', 'DC', 'Dây cổ cưới Vàng \r\n', 31374163, 'dayco2.png', 'Trang sức vàng là một trong những biểu hiện rõ nét nhất cho sự sang trọng, đẳng cấp của một người. Vì vậy, nếu bạn đang phân vân tặng dây chuyền cho bạn gái có ý nghĩa gì không thì đây chính là một ý nghĩa rất đáng chú ý.\r\n\r\nMột sợi dây chuyền vàng sẽ góp phần không nhỏ giúp ngoại hình của cô gái tỏa sáng hơn, sang trọng, thu hút hơn. Không chỉ làm đẹp cho người được tặng mà chính người tặng cũng thêm phần tự hào, “ghi điểm” tuyệt đối với đối phương cũng như nhận được cái nhìn thiện cảm, ngưỡng mộ từ nhiều người.'),
('DH01', 'DH', 'Đồng hồ Calvin Klein Nam ', 5378000, 'dongho1.jpg', ' Bạn mong muốn người ấy sẽ luôn nhớ đến những khoảnh khắc hai bạn ở bên nhau, qua đó thể hiện sự tin tưởng, tình cảm chân thành, sâu sắc và luôn sẵn sàng cùng người ấy vượt qua mọi khó khăn trong cuộc sống. Bên cạnh đó, việc tặng đồng hồ đúng dịp sinh nhật sẽ giúp món quà trở nên ý nghĩa hơn bao giờ hết. \r\n\r\nKhi nam giới sở hữu một chiếc đồng hồ phù hợp với bản thân còn giúp người đó trông phong cách và có uy tín hơn. Không chỉ là món phụ kiện thời gian quan trọng mà nó còn mang tính biểu tượng cho sự thành công và danh tiếng. Một chiếc đồng hồ đẹp sẽ giúp chàng tạo nên sự khác biệt, mang tới những cơ hội mới, những thành công mới, tự tin hơn khi giao tiếp và gặp đối tác.'),
('DH02', 'DH', 'Đồng hồ Calvin Klein Nam ', 5979000, 'dongho2.jpg', 'Ý nghĩa của việc tặng đồng hồ với người được nhận rất quan trọng. Khi bạn muốn thể hiện hay dành sự quan tâm của mình cho họ bằng những món quà mang giá trị cao về vật chất cũng như là tinh thần. Đồng hồ mang ý nghĩa về thời gian, tính chất công việc và đồng thời phản ánh phong cách của người sở hữu. \r\n\r\nViệc tặng đồng hồ còn thể hiện tình yêu, tình cảm sâu sắc của bạn dành cho đối phương.'),
('K01', 'K', 'Kiềng cưới Vàng \r\n', 18401000, 'kieng1.png', 'Bao đời nay, tặng kiềng cưới cho cô dâu luôn là nét văn hoá đặc sắc trong các lễ cưới của người Việt Nam. Tuy nhiên, không phải ai cũng hiểu rõ ý nghĩa của chiếc kiềng vàng quen thuộc này. \r\n\r\nKiềng cưới mang hình dáng tròn, không có điểm bắt đầu hay kết thúc, tượng trưng cho sự vĩnh cữu, bền lâu, là lời chúc cho cô dâu chú rể luôn hạnh phúc viên mãn, bên nhau đến răng long đầu bạc.'),
('K02', 'K', 'Kiềng cưới vàng \r\n', 11758000, 'kieng2.png', 'Ngoài kiềng cưới trơn truyền thống, để tăng tính thẩm mỹ, các nhà kim hoàn còn cho ra đời nhiều mẫu kiềng cưới với hoa văn chìm, hoa văn cuốn rất phong phú và đẹp mắt.\r\n\r\n- Hoa văn long phụng: Nằm trong bộ tứ linh, Long tượng trưng cho sự mạnh mẽ, tài lực, phú quý và Phụng là biểu trưng của vẻ đẹp, sự thanh cao, năng lực tái sinh mạnh mẽ. Sự kết hợp của hai linh vật này đã tạo nên thành ngữ “Long phụng sum vầy”, ý nói đến sự cộng hưởng sức mạnh, thịnh vượng, con đàn cháu đống. Vì lẽ đó, người Việt tin rằng, hoa văn long phụng là lời chúc tốt đẹp nhất gửi đến các cặp đôi trong ngày hôn lễ.'),
('KC01', 'KC', 'Nhẫn cưới Kim cương Vàng trắng ', 26341000, 'kimcuong1.png', 'Bạch kim hay còn gọi là Platinum là một kim loại quý. Bạch kim ít bị hao mòn theo thời gian, biểu tượng cho sự thuần khiết. Vì vậy, ý nghĩa của chiếc nhẫn cưới bạch kim là ý niệm muốn bảo tồn tình yêu theo năm tháng.'),
('KC02', 'KC', 'Nhẫn cưới nam Kim cương Vàng \r\n', 17961000, 'kimcuong2.png', 'Nhẫn cưới kim cương luôn là một trong những lựa chọn hàng đầu của các cặp vợ chồng vì vẻ đẹp và chính ý nghĩa của chúng. Kim cương là vật liệu cứng, đại diện cho sự vĩnh cửu trong tình yêu. Bản chất bền bỉ và sức mạnh phi thường của kim cương như một mối liên hệ thiết thực giữa tình yêu bền chặt, không dễ dàng chia cắt của các cặp vợ chồng.'),
('LT01', 'LT', 'Lắc tay cưới Vàng \r\n', 14744000, 'lactay1.png', 'Lắc tay là một món quà khá ý nghĩa dành tặng cho những người thân yêu bên cạnh mình chẳng hạn như mẹ, chị/em gái, bạn thân là phái nữ. Việc tặng lắc tay này mang ý nghĩa gắn kết tình yêu thương trong gia đình, thể hiện sự trân quý tình bạn và mong muốn những điều may mắn, tốt đẹp nhất đến với họ. Bên cạnh đó lắc tay còn như một lời chúc sức khỏe mà bạn muốn dành tặng cho người nhận.'),
('LT02', 'LT', 'Lắc tay cưới Vàng \r\n', 21817333, 'lactay2.png', 'Tặng chiếc lắc tay cho người ấy chứa đựng những ý nghĩa to lớn, giúp chúng ta truyền đạt những tình cảm, tâm tư yêu thương muốn gửi gắm đến người ấy. Chiếc lắc tay sẽ là món quà ý nghĩa giúp thể hiện sự chân thành với mong muốn những điều may mắn, hạnh phúc sẽ đến. Đồng thời, lắc tay tượng trưng cho một sợi dây kết nối cho tình yêu đôi lứa vững bền. Tặng lắc tay cho người bạn yêu có ý nghĩa vô cùng đặc biệt, nó chất chứa tình cảm sâu nặng của bạn dành cho người ấy, đồng thời mong muốn được bên nhau trọn đời.'),
('NC01', 'NC', 'Cặp nhẫn cưới Kim cương Vàng trắng \r\n', 8900000, 'nhancuoi1.png', 'Trong bộ trang sức cưới thì nhẫn cưới là món đồ không thể thiếu trong ngày vui của cô dâu và chú rể. Nhẫn cưới mang ý nghĩa đặc biệt xuất phát từ người Ai Cập cổ đại, biểu hiện cho tình yêu lứa đôi.\r\n\r\nNgày nay, nhẫn cưới còn là món đồ quý giá thể hiện họ thuộc về nhau. Khi nhìn thấy người đối diện đeo nhẫn cưới ở ngón áp út thì sẽ hiểu rằng người đó đã lập gia đình. Vì vậy nhẫn cưới còn được xem là vật bảo vệ hạnh phúc gia đình.'),
('NC02', 'NC', 'Cặp nhẫn cưới Kim cương Vàng \r\n', 20944000, 'nhancuoi2.png', 'Giá trị của chiếc nhẫn không nằm ở kiểu dáng hay những viên đá đính kết lấp lánh xung quanh, đôi khi chỉ cần một viên kim cương cũng đủ thể hiện được ý nghĩa. Viên kim cương 99 giác cắt thay cho viên kim cương thông thường cũng mang đến vẻ đẹp rực rỡ cho người sử dụng. \r\n\r\nĐể trở thành kiệt tác kim cương 99 giác cắt quý hiếm, mỗi viên kim cương phải trải qua hành trình chọn lựa kỹ lưỡng, sau đó là quá trình mài cắt, đánh bóng tỉ mỉ của những nghệ nhân lành nghề của DOJI.\r\n\r\nTheo quan niệm phương Đông, con số 99 đại diện cho hạnh phúc và vĩnh cửu. Mỗi chiếc nhẫn đính hôn Shape of Love như lời chúc phúc các cặp đôi luôn có được hành trình yêu thương trọn vẹn'),
('V01', 'V', 'Vòng Vàng \r\n', 9590000, 'vong1.png', 'Vòng đeo tay được dùng để gọi chung cho các loại trang sức được đeo ở cổ tay như lắc tay hay lập lắc nhưng mọi người thường hay tách riêng ra để chỉ cụ thể từng loại trang sức đeo ở cổ tay.\r\n\r\nTừ xa xưa con người đã sử dụng vòng đeo tay vàng cùng những đồ trang sức vàng khác như để khẳng định địa vị của mình trong xã hội. Các chiến binh cũng thường sử dụng vòng đeo tay kim loại trong các trận đấu nhằm bảo vệ cổ tay của mình'),
('V02', 'V', 'Vòng Vàng \r\n', 6290000, 'vong2.png', 'Vòng đeo tay vàng không chỉ được các bạn nữ ưa thích mà vòng đeo tay nam bằng vàng cũng được những chàng trai ưa chuộng không kém vì sự độc đáo cũng như thể hiện được sự mạnh mẻ, cá tính không kém phần hấp dẫn lôi cuốn.'),
('VT01', 'VT', 'Vòng tay Vàng trắng \r\n', 16861000, 'vongtay1.png', 'Sau khi đeo một thời gian thì trang sức làm bằng vàng trắng sẽ bị đen và tối lại. Có 2 nguyên nhân chính sau đây:\r\n\r\n– Vàng trắng bị đen do cọ sát và do môi trường tác động: Trong quá trình vận động, lớp phủ của vàng bị trầy và bị bào mòn, môi trường sẽ làm cho kim loại bị oxi hóa nên vàng trắng sẽ bị đen đi.\r\n– Vàng bị đen do mồ hôi: Tuyến mồ hôi của sẽ tiết ra muối đặc biệt là lưu huỳnh, khi đó tác dụng với vàng không nguyên chất sẽ sinh ra hiện tượng ố vàng trên bề mặt dẫn đến vàng trắng bị đen đi.'),
('VT02', 'VT', 'Vòng tay cưới Vàng \r\n', 27036000, 'vongtay2.png', 'Từ trước đến nay rất nhiều người hay nhầm lẫn vàng trắng với bạch kim vì bề ngoài nó khá giống nhau. Tuy nhiên vàng trắng và bạch kim là hai loại hoàn toàn khác biệt nhau, không phải là một như nhiều người vẫn nhầm tưởng. Vậy điểm khác biệt để so sánh giữa bạch kim và vàng trắng là gì, tìm hiểu ngay sau đây.\r\n\r\nTrước tiên hãy xem bạch kim là gì? Đây chính là một loại kim loại và có thành phần hóa học là platin. Tên gọi bạch kim cũng dựa trên bản chất màu sắc vốn có của bản thân nó mà ra. Bạch kim có màu xám trắng, tính dẻo và rất dễ uốn nắn, do vậy được sử dụng nhiều trong việc chế tác trang sức.'),
('VTL01', 'VTL', 'Vàng Tài Lộc \r\n', 5650000, 'vangtailoc1.png', 'Theo dân gian truyền lại rằng, Thần Tài là vị Thần cai quản tiền tài ở trên trời. Là một vị thần có danh tiếng từ lâu. Trong một lần đi chơi uống rượu, vì say quá nên thần đã rơi xuống trần gian. Khi thần vào nhà nào xin đồ ăn thì gia đình ấy lại trở nên giàu có, kinh doanh buôn may bán đắt,… Và đến ngày mùng 10 tháng Giêng thì Thần trở về trời.\r\n\r\nĐể tưởng nhớ ngài, người dân đã chọn ngày ngài trở về trời là ngày vía Thần Tài để thờ cúng. Và chọn vàng là vật phẩm cúng để cầu tài lộc, sung túc cho một năm tiếp theo.\r\n\r\nVàng Thần Tài chính là một biểu tượng của sự giàu có, phú quý, tài lộc cũng như thể hiện sự may mắn cho gia chủ. Việc chọn mua vàng Thần Tài cũng được xem như là một cách “đổi vía” kinh doanh cho người dân vào dịp đầu xuân năm mới.'),
('VTL02', 'VTL', 'Vàng Tài Lộc \r\n', 6350000, 'vangtailoc2.png', 'Theo trung tâm nghiên cứu tôn giáo, người dân Việt Nam thờ Thần Tài với hy vọng mang đến tài lộc, sự sung túc, giàu có và thịnh vượng cho gia đình. Hầu như tất cả các cửa hàng kinh doanh buôn bán, các công ty doanh nghiệp đều lập bàn thờ Thần Tài. Vào ngày này, mọi người đều làm lễ cúng bái xin một năm làm ăn gặp nhiều may mắn, tấn tài tấn lộc, công việc hanh thông.\r\n\r\nVậy có nên mua vàng ngày vía thần tài không? Điều này còn tùy thuộc vào từng khu vực sinh sống, quan điểm của mỗi người. Bởi việc “đổ xô” đi mua vàng ngày vía Thần Tài, thường chỉ xuất hiện tại những khu vực thành phố lớn. Tại các khu vực nông thôn, việc mua vàng ngày Thần Tài chưa phổ biến nhiều. Với những người làm kinh doanh buôn bán thì thường rất coi trọng ngày vía Thần Tài. \r\n\r\nMua vàng Thần Tài đầu năm không chỉ mang ý nghĩa tâm linh, mà còn là một hình thức tiết kiệm, phòng thân của nhiều người dân Việt Nam. Bởi vàng vẫn được xem là kênh “giữ tiền” an toàn nhất trong các loại đầu tư từ trước đến nay. Mua vàng đầu năm, như một cách để dành, tạo thói quen tiết kiệm và tránh được việc chi tiêu quá tay vào những thứ không cần thiết. Khi có việc phát sinh cũng dễ dàng tìm được cách giải quyết.');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`idhoadon`,`idsanpham`),
  ADD KEY `idsanpham` (`idsanpham`);

--
-- Chỉ mục cho bảng `chudesanpham`
--
ALTER TABLE `chudesanpham`
  ADD PRIMARY KEY (`idchude`);

--
-- Chỉ mục cho bảng `danhgia`
--
ALTER TABLE `danhgia`
  ADD PRIMARY KEY (`id_danhgia`),
  ADD KEY `idsanpham` (`idsanpham`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`idhoadon`) USING BTREE,
  ADD KEY `hoadon_ibfk_1` (`email`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`email`) USING BTREE;

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`idsanpham`,`idchude`),
  ADD KEY `idchude` (`idchude`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `danhgia`
--
ALTER TABLE `danhgia`
  MODIFY `id_danhgia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`idsanpham`) REFERENCES `sanpham` (`idsanpham`),
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`idhoadon`) REFERENCES `hoadon` (`idhoadon`);

--
-- Các ràng buộc cho bảng `danhgia`
--
ALTER TABLE `danhgia`
  ADD CONSTRAINT `danhgia_ibfk_1` FOREIGN KEY (`idsanpham`) REFERENCES `sanpham` (`idsanpham`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`email`) REFERENCES `khachhang` (`email`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`idchude`) REFERENCES `chudesanpham` (`idchude`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
