<?php
if (isset($_POST['email'])) {

    require_once "dbconnect.php";
    $email = $_POST['email'];
    $newPassword = $_POST['newPassword'];

    $sql = "UPDATE khachhang SET password ='$newPassword' WHERE email ='$email';";
    if (!$conn->query($sql)) {
        echo "Failure";
    }else{
        echo "Success";
    }
}
?>