<?php

include('init.php');
 



				$jrray = array();

				$sql = "SELECT * FROM Storages";
				$result = $con->query($sql);   
				if ($result->num_rows > 0) 
				{
					while($row = $result->fetch_assoc()) 
					{
						$row_array['id'] = $row['id'];
						$row_array['sname'] = $row['Sname'];
						array_push($jrray,$row_array);
						
					}
				}
echo json_encode(array('result'=>$jrray));
$con->close();
?>