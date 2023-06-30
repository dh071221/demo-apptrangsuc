<?php
if (isset($_POST['email'])) {

    require_once "dbconnect.php";
    $email = $_POST['email'];
    $hoten = $_POST['hoten'];
    $diachi = $_POST['diachi'];
    $sodienthoai = $_POST['sodienthoai'];
   
    $sql = "UPDATE `khachhang` SET `hoten` = '$hoten',`diachi` = '$diachi',
                    `sodienthoai` = '$sodienthoai' WHERE `khachhang`.`email` = '$email';";
    if (!$conn->query($sql)) {
        echo "Failure";
    }else{
        echo "Success";
    }
}
?>