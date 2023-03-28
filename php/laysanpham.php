<?php
    require 'dbconnect.php';

    class trangsuc{
        function __construct($idsanpham, $idchude, $tensanpham,$giasanpham,$hinhsanpham,$motasanpham){
            $this->idsanpham = $idsanpham;
            $this->idchude= $idchude;
            $this->tensanpham = $tensanpham;
            $this->giasanpham = $giasanpham;
            $this->hinhsanpham = $hinhsanpham;
            $this->motasanpham =$motasanpham;
        }
    }	

    $query = "SELECT * FROM `sanpham`";
    $data = mysqli_query($conn, $query);
    $arraylist = array();

    while($row=mysqli_fetch_assoc($data))
    {
        array_push($arraylist, new trangsuc($row["idsanpham"], $row["idchude"], $row["tensanpham"], $row["giasanpham"], $row["hinhsanpham"],$row["motasanpham"]));
    }

    header("Content-type:application/json");
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>