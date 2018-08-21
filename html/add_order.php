<?php
require "init.php";


$pnumber=$_REQUEST["pnumber"];
$qnt=$_REQUEST["qnt"];

$status="Pending";

$username=$_REQUEST["UserName"];
$action="Add Order";
$description=$pnumber;

$response=array();


if($pnumber==""||$pnumber==NULL || $qnt==""||$qnt==NULL){
		$code="fail";
		$message="Fail Inport";
		array_push($response,array("code"=>$code,"message"=>$message));
		print_r(json_encode($response));
}else{
$sql="INSERT INTO Orders(ProductNumber,Qnt,Status) VALUES('".$pnumber."','".$qnt."','".$status."')";
$result=mysqli_query($con,$sql);
if($result){
			$code="success";
			$message="Add succesfull";
			array_push($response,array("code"=>$code,"message"=>$message));

			//logfile
			$sql = "INSERT INTO LogFile (LoginUser,Action,Description) VALUES ('$username','$action','$description');";
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