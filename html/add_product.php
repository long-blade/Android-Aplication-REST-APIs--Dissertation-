<?php
require "init.php";
//require "add_drop.php";

$pnumber=$_REQUEST["pnumber"];
$pname=$_REQUEST["pname"];
$pmodel=$_REQUEST["pmodel"];
$pqnt=$_REQUEST["pqnt"];
$pshelf=$_REQUEST["shelf"];

$action="Add Product";
$description=$pnumber;
$user_name=$_REQUEST["UserName"];


$sql1="select id from Shelfs where Sname like '".$pshelf."';";
$result1=mysqli_query($con,$sql1);
$row=mysqli_fetch_row($result1);
$id_shelf=$row[0];
//print_r(json_encode($id_shelf));




$sql="select * from Products where Pnumber like'".$pnumber."';";
$result=mysqli_query($con,$sql);
$response=array();
if($result->num_rows > 0)
	{

	$code="fail";
	$message="Dublicate Product Number";
	array_push($response,array("code"=>$code,"message"=>$message));
	print_r(json_encode($response));
	

	}else
		{

		$sql="INSERT INTO Products(Pnumber,Pname,Pmodel,Pqnt,id_shelf) VALUES('".$pnumber."','".$pname."','".$pmodel."','".$pqnt."','".$id_shelf."')";
		$result=mysqli_query($con,$sql);

		if($result){
			$code="success";
			$message="Add succesfull";
			array_push($response,array("code"=>$code,"message"=>$message));
$sql = "INSERT INTO LogFile (LoginUser,Action,Description) VALUES ('$user_name','$action','$description');";
$result10=mysqli_query($con,$sql);
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























