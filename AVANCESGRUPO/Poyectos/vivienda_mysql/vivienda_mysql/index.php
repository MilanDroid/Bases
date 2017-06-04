<!DOCTYPE HTML>
<html lang="es">
<head>
	<title>Viviendas</title>
	<link rel="stylesheet" href="estilopagina.css">
	<link rel="stylesheet" href="materialize.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<meta charset="utf-8">
</head>
<body>
<div class="container" id="contenedor">
	<header id="encabezado">
		 <nav class="light-blue lighten-2">
      <div class="nav-wrapper">
        <ul class="right hide-on-med-and-down">
          <li><a href="consulta.php"><i class="material-icons left">search</i>Buscar</a></li>
          <li><a href="insert.php"><i class="material-icons right">view_module</i>Insertar</a></li>
        </ul>
      </div>
    </nav>
	</header>
  <section>
  	<div class="slider">
      <ul class="slides">
        <li>
          <img src="Imagenes/casa.jpg"> 
        </li>
        <li>
          <img src="Imagenes/casa2.jpg">
        </li>
        <li>
          <img src="Imagenes/casa3.jpg"> 
        </li>
      </ul>
    </div>
          <div class="row">
            <div class="col s6 m6">
              <div class="card hoverable">
                <div class="card-image">
                  <img src="Imagenes/buscar.jpg" width="300px" height="450px">
                  <span class="card-title black">Buscar</span>
                </div>
                <div class="card-content">
                  <p>Busca una vivienda de acuerdo al nombre de la zona</p>
                </div>
                <div class="card-action">
                  <a href="consulta.php">Consulta</a>
                </div>
              </div>
            </div>
            <div class="col s6 m6">
              <div class="card hoverable">
                <div class="card-image">
                  <img class="activator" src="Imagenes/agregar.png" width="300px" height="450px">
                  <span class="card-title black">Agregar</span>
                </div>
                <div class="card-content">
                <p>Agrega un registro de una vivienda</p>
                </div>
                <div class="card-action">
                  <a href="insert.php">Insertar</a>
                </div>
              </div>
            </div>
          </div>
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

<script>
    $(document).ready(function(){
      $('.slider').slider({full_width: true});
    });
 </script>

</body>
</html>