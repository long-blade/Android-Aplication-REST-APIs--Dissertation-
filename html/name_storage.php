<?php
require "init.php";

$sql1="select Sname from Storages;";
	$response1=array();
	$result1=mysqli_query($con,$sql1);
	if(mysqli_num_rows($result1)>0)
	{
		while($row1=$result1->fetch_assoc())
		{
			$sname=$row[0];
 			array_push($response1,array(
 			"Sname"=>$sname)
			);
		}
	}

 



print_r(json_encode($response1));
mysqli_close($con);
?>