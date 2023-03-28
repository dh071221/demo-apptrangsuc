<?php
if(isset($_POST['hoten']) && isset($_POST['diachi']) && isset($_POST['email']) && 
isset($_POST['sodienthoai']) &&  isset($_POST['password'])){
    
    require_once "dbconnect.php";
    require_once "validate.php";
    
    $hoten = validate($_POST['hoten']);
    $diachi = validate($_POST['diachi']);
    $email = validate($_POST['email']);
    $sodienthoai = validate($_POST['sodienthoai']);
    $password = validate($_POST['password']);

    
    $sql = "insert into khachhang value('$hoten','$diachi','$email','$sodienthoai','$password')";
    
    if(!$conn->query($sql)){
        echo "failure";
    }else{
        echo "success";   
    }
}
?>