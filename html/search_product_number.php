<?php
require "init.php";
//include('reg_drop.php');

$p_number=$_REQUEST["Product_Number"];
$password=$_REQUEST["Password"];
$sql="select Pnumber,Pname,Pmodel,Pqnt,id_shelf,Sname from Products INNER JOIN Shelfs ON Products.id_shelf=Shelfs.id WHERE Pnumber like '".$p_number."';";
$response=array();
$result=mysqli_query($con,$sql);
if(mysqli_num_rows($result)>0)
	{
		$row=mysqli_fetch_row($result);
		$pnumber=$row[0];
		$pname=$row[1];
		$pmodel=$row[2];
		$pqnt=$row[3];
		$id_shelf=$row[4];
		$sname=$row[5];
		$code="Product Found";
		array_push($response,array(
			"code"=>$code,
			"pnumber"=>$pnumber,
			"pname"=>$pname,
			"pmodel"=>$pmodel,
			"pqnt"=>$pqnt,
			"idshelf"=>$id_shelf,
			"sname"=>$sname)
			);
			print_r(json_encode($response));
	}else{
		$code="Product Not Found";
		$msg="Product Not Found";
		array_push($response,array(
		"code"=>$code,
 		"msg"=>$msg)
		);
		print_r(json_encode($response));
		}
mysqli_close($con);
?> 