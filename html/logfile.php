<?php
session_start();
include('init.php');






				$jrray = array();

				$sql = "SELECT * FROM LogFile";
				$result = $con->query($sql);   
				if ($result->num_rows > 0) 
				{
					while($row = $result->fetch_assoc()) 
					{
						
						$row_array['loguser'] = $row['LoginUser'];
						$row_array['action'] = $row['Action'];
						$row_array['desc'] = $row['Description'];
						$row_array['date_time'] = $row['Date_Time'];
						
						array_push($jrray,$row_array);
						
					}
				}
		echo json_encode(array('result'=>$jrray));



$con->close();
?>