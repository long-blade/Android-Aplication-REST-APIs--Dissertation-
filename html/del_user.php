<?php
require "init.php";

$user_id=$_REQUEST["userid"];

$action="Delete User";
$description=$user_id;
$user_name=$_REQUEST["UserName"];


$sql="select UserID,Type from Users where UserID like '".$user_id."';";
//$sql="select * FROM Users WHERE UserID =$user_id";
$response=array();
$result=mysqli_query($con,$sql);
$row=mysqli_fetch_row($result);

$type=$row[1];
$x="1";
//print_r(json_encode($x));					

if (mysqli_num_rows($result)>0 ) {
	
	
	
	if($type!=$x){

		$sql2="DELETE FROM Users WHERE UserID =$user_id";
		$result2=mysqli_query($con,$sql2);
		
			$code="success";
			$message="User Deleted succesfull";
			array_push($response,array(
			"code"=>$code,
			"message"=>$message));
//logfile
$sql10 = "INSERT INTO LogFile (LoginUser,Action,Description) VALUES ('$user_name','$action','$description');";
$result10=mysqli_query($con,$sql10);
			print_r(json_encode($response));
			//echo json_encode($response);
	}else{
		$row=mysqli_fetch_row($result);
		$code="fail";
		$message="THIS IS ADMIN";
    		array_push($response,array(
			"code"=>$code,
			"message"=>$message));
		print_r(json_encode($response));
		
		}
	
    	
	} else {	
		
		$row=mysqli_fetch_row($result);
		$code="fail";
		$message="User Delete Fail(NoSuchEntry)";
    		array_push($response,array(
			"code"=>$code,
			"message"=>$message));
		print_r(json_encode($response));
		//echo json_encode($response);
		}

mysqli_close($con);
?>