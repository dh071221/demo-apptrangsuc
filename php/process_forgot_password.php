<?php
// Allow requests from any origin
header('Access-Control-Allow-Origin: *');

// Allow specified HTTP methods
header('Access-Control-Allow-Methods: POST');

// Allow specified headers
header('Access-Control-Allow-Headers: Content-Type');

use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;

require_once "PHPMailer.php";
require_once "SMTP.php";
require_once "Exception.php";

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $postData = file_get_contents('php://input');
    $data = json_decode($postData, true);

    if(isset($data['email'])){
        $email = $data['email'];

        $phpmailer = new PHPMailer();
        $phpmailer->isSMTP();
        $phpmailer->Host = 'sandbox.smtp.mailtrap.io';
        $phpmailer->SMTPAuth = true;
        $phpmailer->Port = 2525;
        $phpmailer->Username = '8f75194bc29456';
        $phpmailer->Password = 'f66a9bb4a923f8';
        $phpmailer->setFrom("test@gmail.com", "test");

        require_once "dbconnect.php";

        // Kiểm tra xem địa chỉ email tồn tại trong cơ sở dữ liệu hay không
        $sql = "SELECT * FROM khachhang WHERE email = '$email'";
        $result = $conn->query($sql);
        error_log("Địa chỉ email: " . $email);

        if($result->num_rows > 0){
            // Tạo mật khẩu tạm thời ngẫu nhiên
            $temporaryPassword = generateRandomPassword();
            //$hashedPassword = password_hash($temporaryPassword, PASSWORD_DEFAULT); // Loại bỏ mã hóa mật khẩu

            // Cập nhật mật khẩu mới trong cơ sở dữ liệu
            $updateSql = "UPDATE khachhang SET password = '$temporaryPassword' WHERE email = '$email'"; // Lưu mật khẩu tạm thời chưa được mã hóa
            if($conn->query($updateSql)){
                // Gửi mật khẩu tạm thời đến địa chỉ email
                $subject = "Mật khẩu tạm thời";
                $message = "Bạn đã yêu cầu đặt lại mật khẩu. Mật khẩu tạm thời của bạn là: $temporaryPassword";

                $phpmailer->CharSet = 'UTF-8'; // Cài đặt mã hóa UTF-8
                $phpmailer->Encoding = 'base64'; // Cài đặt phương thức mã hóa

                $phpmailer->addAddress($email);
                $phpmailer->Subject = $subject;
                $phpmailer->Body = $message;
                $phpmailer->SMTPDebug = SMTP::DEBUG_SERVER;
                if($phpmailer->send()){
                    echo "Mật khẩu tạm thời đã được gửi đến địa chỉ email của bạn.";
                }else{
                    error_log("Gửi email không thành công.");
                    echo "Gửi email không thành công.";
                }
            }else{
                echo "Cập nhật mật khẩu không thành công.";
            }
        }else{
            echo "Địa chỉ email không tồn tại trong hệ thống.";
        }
    }
}

// Hàm để tạo mật khẩu ngẫu nhiên dạng văn bản
function generateRandomPassword($length = 10){
    $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    $password = '';
    for ($i = 0; $i < $length; $i++) {
        $password .= $characters[rand(0, strlen($characters) - 1)];
    }
    return $password;
}
?>