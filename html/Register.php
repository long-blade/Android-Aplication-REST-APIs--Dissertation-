
<?php


require "init.php";
$lname=$_REQUEST["LastName"];
$fname=$_REQUEST["FirstName"];
$user_name=$_REQUEST["UserName"];
$email=$_REQUEST["Email"];
$password=$_REQUEST["Password"];
$type=$_REQUEST["Type"];
$id_storage=$_REQUEST["id_Storage"];

$action="Register";
$description=$user_name;
$user_name2=$_REQUEST["UserName2"];


$sql="select * from Users where email like'".$email."';";


$result=mysqli_query($con,$sql);
$response=array();

  

if(mysqli_num_rows($result)>0)
{

$code="reg_failed";
$message="User already exist";
array_push($response,array("code"=>$code,"message"=>$message));
print_r(json_encode($response));

}
else
{

$sql="INSERT INTO Users(id_Storage,Type,LastName,FirstName,UserName,Email,Password) VALUES('".$id_storage."','".$type."','".$lname."','".$fname."','".$user_name."','".$email."','".$password."')";
$result=mysqli_query($con,$sql);
$code="reg_success";
$message="User registered succesfull";
array_push($response,array("code"=>$code,"message"=>$message));

//logfile
$sql = "INSERT INTO LogFile (LoginUser,Action,Description) VALUES ('$user_name2','$action','$description');";
$result=mysqli_query($con,$sql);


print_r(json_encode($response));

}

mysqli_close($con);

?>
