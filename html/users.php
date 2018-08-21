<?php

include('init.php');
 



				$jrray = array();

				$sql = "SELECT * FROM Users INNER JOIN Storages ON Users.id_Storage=Storages.id";
				$result = $con->query($sql);   
				if ($result->num_rows > 0) 
				{	
					while($row = $result->fetch_assoc()) 
					{
						$row_array['user_id'] = $row['UserID'];
						$row_array['lastname'] = $row['LastName'];
						$row_array['email'] = $row['Email'];
						$row_array['id_storage'] = $row['id_Storage'];
						$row_array['sname'] = $row['Sname'];
						array_push($jrray,$row_array);
						
					}
				}
echo json_encode(array('result'=>$jrray));
$con->close();
?>