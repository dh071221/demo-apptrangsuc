<?php

if(isset($_POST['email']) && isset($_POST['diachi']) && isset($_POST['hoten']) && 
isset($_POST['sodienthoai']) &&  isset($_POST['password'])){
    
    require_once "dbconnect.php";
    require_once "validate.php";
    
    $email = validate($_POST['email']);
    $diachi = validate($_POST['diachi']);
    $hoten = validate($_POST['hoten']);
    $sodienthoai = validate($_POST['sodienthoai']);
    $password = validate($_POST['password']);

    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo "invalid_email"; // Gửi thông báo email không hợp lệ về máy khách
        exit();
    }

    
    $sql = "insert into khachhang value('$email','$diachi','$hoten','$sodienthoai','$password')";
    
    if(!$conn->query($sql)){
        echo "failure";
    }else{
        echo "success";   
    }
}
?>