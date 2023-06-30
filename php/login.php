<?php

if (isset($_POST['email']) && isset($_POST['password'])) {

    require_once "dbconnect.php";
    require_once "validate.php";

    $email = validate($_POST['email']);
    $password = validate($_POST['password']);


    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo "invalid_email"; // Gửi thông báo email không hợp lệ về máy khách
        exit();
    }

    $sql = "select * from khachhang where email='$email' and password='$password'";

    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        echo "success";
    } else {

        echo "failure";
    }
}
?>