<?php
    require 'dbconnect.php';

    if ($_SERVER['REQUEST_METHOD'] === 'GET') {
        // Lấy ID sản phẩm từ yêu cầu GET
        $idsanpham = $_GET['idsanpham'];

        // Truy vấn danh sách đánh giá từ cơ sở dữ liệu dựa vào ID sản phẩm
        $query = "SELECT * FROM danhgia WHERE idsanpham = '$idsanpham'";
        $result = mysqli_query($conn, $query);

        if ($result) {
            // Tạo một mảng để lưu trữ danh sách đánh giá
            $reviews = array();

            // Lặp qua từng dòng kết quả và thêm vào mảng đánh giá
            while ($row = mysqli_fetch_assoc($result)) {
                $review = array(
                    'id_danhgia' => $row['id_danhgia'],
                    'idsanpham' => $row['idsanpham'],
                    'noidung' => $row['noidung'],
                    'diemdanhgia' => $row['diemdanhgia'],
                    'email' => $row['email'],
                    'thoigian' => $row['thoigian']
                );
                $reviews[] = $review;
            }

            // Trả về danh sách đánh giá dưới dạng JSON
            header("Content-Type: application/json");
            echo json_encode(array(
                'success' => true,
                'data' => $reviews
            ));
        } else {
            // Lỗi khi truy vấn danh sách đánh giá
            echo "Lỗi khi truy vấn danh sách đánh giá.";
        }
    }
?>
