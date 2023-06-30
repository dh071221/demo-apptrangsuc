<?php
	require "dbconnect.php";

	$customer_totalprice = $_POST['tongtien'];
	$email = $_POST['email'];
		$query = "INSERT INTO `hoadon` (`idhoadon`, `email`, `tongtien`) 
				  VALUES (NULL, '$email', '$customer_totalprice')";
		
		$stmt = mysqli_prepare($conn, $query);	
        
        if(mysqli_stmt_execute($stmt)){
			$billid = mysqli_insert_id($conn);
			echo $billid;
		}else {
			echo 'Failed to insert data into the database.';
		}
		mysqli_stmt_close($stmt);
?>