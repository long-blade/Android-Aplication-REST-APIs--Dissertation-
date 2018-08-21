<?php
require "init.php";
$pnumber=$_REQUEST["pnumber"];
$action="Order Confirmation";
$description=$pnumber;
$username=$_REQUEST["UserName"];
$response=array();

if($pnumber=="" || $pnumber==NULL){
		$code="fail";
		$message="Fail Verify";
		array_push($response,array("code"=>$code,"message"=>$message));
		print_r(json_encode($response));
}else{
	$sql1="select Pnumber,Pqnt from Products where Pnumber like '".$pnumber."';";
	$result1=mysqli_query($con,$sql1);
	$row=mysqli_fetch_row($result1);
	$pqnt=$row[1];
	if($row){
		$sql2="select Qnt from Orders where ProductNumber like '".$pnumber."';";
		$result2=mysqli_query($con,$sql2);
		$row2=mysqli_fetch_row($result2);
		$oqnt=$row2[0];
		if($row2){
			$conf="Confirmed";
			$qntt=$pqnt+$oqnt;
			$sql3="UPDATE Products SET Pqnt='$qntt' WHERE Pnumber like '".$pnumber."';";
			$result3=mysqli_query($con,$sql3);
			$sql4="UPDATE Orders SET Status='$conf' WHERE ProductNumber like '".$pnumber."';";
			$result4=mysqli_query($con,$sql4);
			
				$code="success";
				$message="Confirmed";
				array_push($response,array(
					"code"=>$code,
					"message"=>$message));
//logfile
$sql10 = "INSERT INTO LogFile (LoginUser,Action,Description) VALUES ('$username','$action','$description');";
$result10=mysqli_query($con,$sql10);
				print_r(json_encode($response));
			
		}else {
			$code="fail";
			$message="Error Update";
    			array_push($response,array(
				"code"=>$code,
				"message"=>$message));
			print_r(json_encode($response));

		}
	}else{	
		$code="fail";
		$message="Error Update product must be as a category";
    		array_push($response,array(
			"code"=>$code,
			"message"=>$message));
		print_r(json_encode($response));

	}
}
mysqli_close($con);
?>	
