<!DOCTYPE HTML>
<html lang="es">
<head>
	<title>Viviendas</title>
	<link rel="stylesheet" href="estilopagina.css">
	<link rel="stylesheet" href="materialize.min.css">
  <link rel="stylesheet" href="jquery.dataTables.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<meta charset="utf-8">
</head>
<body>
<div class="container" id="contenedor">
	<header id="encabezado">
		 <nav class="light-blue lighten-2">
      <div class="nav-wrapper">
        <ul class="right hide-on-med-and-down">
          <li class="active"><a href="consulta.php"><i class="material-icons left">search</i>Buscar</a></li>
          <li><a href="insert.php"><i class="material-icons right">view_module</i>Insertar</a></li>
          <li><a href="index.php">Menu</a></li>
        </ul>
      </div>
    </nav>
	</header>
  <section>
    <h4> Consultar viviendas </h4><br>
    <div class="row">
    <form name="formulario"  id="formulario" method="post" action="">
      <div class="input-field col s12">
        <input id="nombreZona" name="nombreZona" type="text" class="validate">
        <label>Nombre de la zona </label>
      <button class="btn light-blue lighten-2" type="submit" id="Buscar" name="Buscar">Buscar
        <i class="material-icons right"></i>
      </button>
      </div>
    </form> 
    </div>   
        <table id="tabla" class="display" cellspacing="0" width="90%">
         <thead>
              <tr>
                  <th>Calle</th>
                  <th>Número</th>
                  <th>Tipo de vivienda</th>
                  <th>Código postal</th>
                  <th>Metros</th>
                  <th>Otro dato</th>
                  <th>Nombre zona</th>
              </tr>
          </thead>
            <tbody>
<?PHP

if(isset($_POST['nombreZona'])){
$valor=$_POST['nombreZona'];  
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
}

?>
          </tbody>
        </table>

  </section>    
	<footer class="page-footer light-blue lighten-2">
          <div class="footer-copyright">
            <div class="container">
            © 2016 Honduras - Todos los derechos reservados
            </div>
          </div>
    </footer>
</div>

<script type="text/javascript" src="jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="materialize.min.js"></script>
<script type="text/javascript" src="jquery.dataTables.js"></script>
      <script>
        $(document).ready(function() {
          $('#tabla').DataTable( {
            "language": {
                "lengthMenu": "",
                "zeroRecords": "No hay datos - Lo sentimos",
                "info": "Mostrando página _PAGE_ de _PAGES_",
                "infoEmpty": "No hay datos disponibles",
                "infoFiltered": "(Filtrando de _MAX_ total datos)",
                "paginate": {
                  "first":      "Primero",
                  "last":       "Ultimo",
                  "next":       "Siguiente",
                  "previous":   "Anterior"
                },
                "search":         "Buscar"
              }
          });
      });
      </script>

</body>
</html>