<?php
    require 'dbconnect.php';

    $email = $_GET['email'];

    class USERS{
        function __construct($email,$name, $address,$phone){
            $this->email = $email;
            $this->name = $name;
            $this->address = $address;
            $this->phone = $phone;
           
            
            
            
        }
    }

    if($email==null)
        $query = "select * from khachhang";
    else
        $query = "select * from khachhang where email=$email";

    $data = mysqli_query($conn, $query);
    $arraylist = array();

    while($row=mysqli_fetch_assoc($data))
    {
        array_push($arraylist, new USERS($row["email"], $row["hoten"],$row["diachi"],$row["sodienthoai"]));
    }

    header("Content-type:application/json");
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>