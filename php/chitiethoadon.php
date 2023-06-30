<?php

error_reporting(E_ALL);
ini_set('display_errors', 1);


	require "dbconnect.php";
	$json = $_POST['json'];
	$data = json_decode($json,true);
	foreach ($data as $value) {
		$idhoadon = $value['idhoadon'];
		$idsanpham = $value['idsanpham'];
		$soluong = $value['soluong'];
		$dongia = $value['dongia'];
		$thanhtien = $value['thanhtien'];
	
		$query = "INSERT INTO chitiethoadon (idhoadon, idsanpham, soluong, dongia, thanhtien)
					VALUES ('$idhoadon', '$idsanpham', '$soluong', '$dongia','$thanhtien')";
		$Dta = mysqli_query($conn,$query);
	}
	if ($Dta) {
		echo "1";
	}else {
		echo "0";
	}
?>