<?php

include('init.php');
 



				$jrray = array();

				$sql = "SELECT * FROM Products";
				$result = $con->query($sql);   
				if ($result->num_rows > 0) 
				{
					while($row = $result->fetch_assoc()) 
					{
						$row_array['id'] = $row['id'];
						$row_array['pnumber'] = $row['Pnumber'];
						$row_array['pname'] = $row['Pname'];
						$row_array['pmodel'] = $row['Pmodel'];
						$row_array['pqnt'] = $row['Pqnt'];
						$row_array['idshelf'] = $row['id_shelf'];
						array_push($jrray,$row_array);
						
					}
				}
echo json_encode(array('result'=>$jrray));
$con->close();
?>