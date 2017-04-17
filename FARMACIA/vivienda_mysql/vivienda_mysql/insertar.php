<?php 

include "conexion.php";


$nombrezona=addslashes(htmlspecialchars($_POST['nombreZona']));
$calle=addslashes(htmlspecialchars($_POST['calle']));
$numero=addslashes(htmlspecialchars($_POST['numero']));
$tipovivienda=addslashes(htmlspecialchars($_POST['tipoVivienda']));
$codigopostal=addslashes(htmlspecialchars($_POST['codigoPostal']));
$metros=addslashes(htmlspecialchars($_POST['metros']));
$otrodato=addslashes(htmlspecialchars($_POST['otroDato']));
$dni=addslashes(htmlspecialchars($_POST['DNI']));

if (!$mysqli->query("CALL `sp_insertar`('$calle',$numero,'$tipovivienda',$codigopostal,$metros,'$otrodato','$nombrezona',$dni, @p8);")) {
    if(strpos($mysqli->error, "fk_viv_zon") ){
    	echo "La zona no existe </br>";
    	echo "no se inserto ninguna fila";
    }
    if(strpos($mysqli->error, "fk_cas_per") ){
    	echo " La persona que se introduce como dueño no existe  </br>";
    	echo "no se inserto ninguna fila";
    }
    if($mysqli->errno == 1062 ){
    	echo "Esta vivienda ya ha sido introducida en la Base de Datos </br>";
    	echo "no se inserto ninguna fila";
    }     
}

if (!($resultado = $mysqli->query("SELECT @p8 AS 'MENSAJE';"))) {
    echo "Falló la obtención: (" . $mysqli->errno . ") " . $mysqli->error;
}

$fila = $resultado->fetch_assoc();
echo $fila['MENSAJE'];


 ?>

