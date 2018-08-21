<?php
session_start();
include('init.php');






				$jrray = array();

				$sql = "SELECT * FROM Products INNER JOIN Shelfs ON Products.id_shelf=Shelfs.id";
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
						$row_array['sname'] = $row['Sname'];
						array_push($jrray,$row_array);
						
					}
				}
		echo json_encode(array('result'=>$jrray));



$con->close();
?>