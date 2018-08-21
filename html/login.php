<?php
session_start();

require "init.php";

date_default_timezone_set('Europe/Athens');
$action="Login";
$description="Success";
$user_name=$_REQUEST["UserName"];
$password=$_REQUEST["Password"];

$sql="select UserName,Email,Type from Users where UserName like '".$user_name."' and Password like '".$password."';";
$response=array();
$result=mysqli_query($con,$sql);
if(mysqli_num_rows($result)>0)
{
 $row=mysqli_fetch_row($result);
 $name=$row[0];
 $email=$row[1];
 $type=$row[2];
 $code="login_success";
 array_push($response,array(
 "code"=>$code,
 "name"=>$name,
 "type"=>$type,
 "email"=>$email)
 );
//logfile
$sql10 = "INSERT INTO LogFile (LoginUser,Action,Description) VALUES ('$user_name','$action','$description');";
$result10=mysqli_query($con,$sql10);

print_r(json_encode($response));

}
else{
 		$code="login failed";
 		$msg="user not found";
 		array_push($response,array(
 		"code"=>$code,
 		"msg"=>$msg)
 		);
		
 	print_r(json_encode($response));
}
mysqli_close($con);
?>