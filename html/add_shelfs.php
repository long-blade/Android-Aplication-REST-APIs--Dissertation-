<?php
require "init.php";
//require "add_drop.php";

$sname=$_REQUEST["sname"];
$sdisc=$_REQUEST["sdisc"];
$storname=$_REQUEST["storname"];

$action="Add shelf";
$description=$sname;
$user_name=$_REQUEST["UserName"];


$sql1="select id from Storages where Sname like '".$storname."';";
$result1=mysqli_query($con,$sql1);
$row=mysqli_fetch_row($result1);
$id_storage=$row[0];
//print_r(json_encode($id_shelf));




$sql="select * from Shelfs where Sname like'".$sname."';";
$result=mysqli_query($con,$sql);
$response=array();
if($result->num_rows > 0)
	{

	$code="fail";
	$message="Dublicate Name Shelf";
	array_push($response,array("code"=>$code,"message"=>$message));
	print_r(json_encode($response));
	

	}else
		{

		$sql="INSERT INTO Shelfs(Sname,Sdiscription,id_Storage) VALUES('".$sname."','".$sdisc."','".$id_storage."')";
		$result=mysqli_query($con,$sql);

		if($result){
			$code="success";
			$message="Add succesfull";
			array_push($response,array("code"=>$code,"message"=>$message));
//logfile
$sql10 = "INSERT INTO LogFile (LoginUser,Action,Description) VALUES ('$user_name','$action','$description');";
$result10=mysqli_query($con,$sql10);
			print_r(json_encode($response));
			
		}else{
			$code="fail";
			$message="Fail Inport";
			array_push($response,array("code"=>$code,"message"=>$message));
			print_r(json_encode($response));
			
			}
		}



mysqli_close($con);

?>
