<?php
    require 'dbconnect.php';

    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $idsanpham = $_POST['idsanpham'];
        $noidung = $_POST['noidung'];
        $diemdanhgia = $_POST['diemdanhgia'];
        $email = $_POST['email']; 


        // Tiếp tục xử lý lưu đánh giá vào cơ sở dữ liệu
        $query = "INSERT INTO danhgia (idsanpham, noidung, diemdanhgia, email, thoigian) VALUES ('$idsanpham', '$noidung', '$diemdanhgia', '$email', NOW())";
        $result = mysqli_query($conn, $query);

        if ($result) {
            
            echo "Đánh giá đã được lưu thành công.";
        } else {
            
            echo "Lỗi khi lưu đánh giá.";
        }
    }
?>
