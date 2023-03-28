<?php
    require 'dbconnect.php';

    class chudesanpham{
        function __construct($idchude, $tenchude,$hinhchude){
            $this->idchude= $idchude;
            $this->tenchude = $tenchude;
            $this->hinhchude =$hinhchude;
        }
    }	

    $query = "SELECT * FROM `chudesanpham`";
    $data = mysqli_query($conn, $query);
    $arraylist = array();

    while($row=mysqli_fetch_assoc($data))
    {
        array_push($arraylist, new chudesanpham($row["idchude"], $row["tenchude"],$row["hinhchude"]));
    }

    header("Content-type:application/json");
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>