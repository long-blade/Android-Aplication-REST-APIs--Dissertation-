<?php

include('init.php');
 $pending="Pending";



				$jrray = array();

				$sql = "SELECT * FROM Orders where Status like '".$pending."';";
				$result = $con->query($sql);   
				if ($result->num_rows > 0) 
				{
					while($row = $result->fetch_assoc()) 
					{
						$row_array['id'] = $row['id'];
						$row_array['pnumber'] = $row['ProductNumber'];
						$row_array['qnt'] = $row['Qnt'];
						$row_array['status'] = $row['Status'];
						
						array_push($jrray,$row_array);
						
					}
				}
echo json_encode(array('result'=>$jrray));
$con->close();

?>