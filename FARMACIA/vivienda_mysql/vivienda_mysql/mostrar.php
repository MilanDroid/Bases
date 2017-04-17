<?php 
include "conexion.php";

$valor=addslashes(htmlspecialchars($_POST['nombreZona']));

$consulta="SET @p0='$valor'; CALL `sp_busar`(@p0);";
if (mysqli_multi_query($mysqli, $consulta)) {
    do {
        /* primero almacenar el conjunto de resultados */
        if ($resultado = mysqli_use_result($mysqli)) {
            while ($fila = mysqli_fetch_row($resultado)) { ?>
            <tr>
                		<td><?php  echo $fila[0] ?></td>
                        <td><?php  echo $fila[1] ?></td>
                        <td><?php  echo $fila[2] ?></td>
                        <td><?php  echo $fila[3] ?></td>
                        <td><?php  echo $fila[4] ?></td>
                        <td><?php  echo $fila[5] ?></td>
                        <td><?php  echo $fila[6] ?></td>
             </tr>           
                        <?php
            }
            mysqli_free_result($resultado);
        }
    } while (mysqli_next_result($mysqli));
}
 ?>