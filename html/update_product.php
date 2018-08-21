<?php
require "init.php";

$pnumber=$_REQUEST["pnumber"];

$pname=$_REQUEST["pname"];
$pmodel=$_REQUEST["pmodel"];
$pqnt=$_REQUEST["pqnt"];
$pshelf=$_REQUEST["pshelf"];

$action="Update Product";
$description=$pnumber;
$user_name=$_REQUEST["UserName"];

$sql1="select id from Shelfs where Sname like '".$pshelf."';";
$result1=mysqli_query($con,$sql1);
$row=mysqli_fetch_row($result1);
$id_shelf=$row[0];
//print_r(json_encode($id_shelf));



$sql="UPDATE Products SET Pname='$pname',Pmodel='$pmodel',Pqnt='$pqnt',id_shelf='$id_shelf' WHERE Pnumber like '".$pnumber."';";


$response=array();
$result=mysqli_query($con,$sql);
//$conn->query($sql) === TRUE
if($con->query($sql) == TRUE) {
    			$code="success";
			$message="Updated";
			array_push($response,array(
			"code"=>$code,
			"message"=>$message));
//logfile
$sql10 = "INSERT INTO LogFile (LoginUser,Action,Description) VALUES ('$user_name','$action','$description');";
$result10=mysqli_query($con,$sql10);
			print_r(json_encode($response));
} else {
    		$code="fail";
		$message="Error Update";
    		array_push($response,array(
			"code"=>$code,
			"message"=>$message));
		print_r(json_encode($response));
}

mysqli_close($con);
?>