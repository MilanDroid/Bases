<?php 

session_start();
$user=$_POST['usuario'];
$pas=$_POST['password'];

$mysqli = new mysqli("localhost", "$user", "$pas", "catastro");
if ($mysqli->connect_errno) {
    echo '<script lenguage="javascript" > location.href="index.php" </script>';
}else{
	$_SESSION['user']=$user;
	$_SESSION['pas']=$pas;
	echo '<script lenguage="javascript" > location.href="menu.php" </script>';
}

 ?>