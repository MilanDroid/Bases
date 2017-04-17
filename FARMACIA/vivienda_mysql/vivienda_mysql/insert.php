<!DOCTYPE HTML>
<html lang="es">
<head>
	<title>Viviendas</title>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
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
          <li class="active"><a href="insert.php"><i class="material-icons right">view_module</i>Insertar</a>
            <li><a href="index.php">Menu</a></li>
          </li>
        </ul>
      </div>
    </nav>
	</header>
  <section  id="seccion">
    <form  name="formulario" id="formulario" method="post" class="col s12">
      <h4> Agregar registro </h4>
          <div class="input-field col s6">
            <input name="nombreZona" id="nombreZona" type="text" class="validate">
            <label>Nombre de zona</label>
          </div>
          <div class="input-field col s6">
            <input name="calle" id="calle" type="text" class="validate">
            <label>Calle</label>
          </div>
          <div class="input-field col s6">
            <input name="numero" id="numero" type="text" class="validate">
            <label>Número</label>
          </div>
          <div class="input-field col s6">
            <input name="tipoVivienda" id="tipoVivienda" type="text" class="validate">
            <label>Tipo de vivienda</label>
          </div>
          <div class="input-field col s6">
            <input name="codigoPostal" id="codigoPostal" type="text" class="validate">
            <label>Código postal</label>
          </div>
          <div class="input-field col s6">
            <input name="metros" id="metros" type="text" class="validate">
            <label>Metros</label>
          </div>
          <div class="input-field col s6">
            <input name="otroDato" id="otroDato" type="text" class="validate">
            <label>Otro dato</label>
          </div>
          <div class="input-field col s6">
            <input name="DNI" id="DNI" type="text" class="validate">
            <label>DNI de persona</label>
          </div>
          <button class="btn light-blue lighten-2" type="submit" id="Ingresar" name="Ingresar">Agregar
                  <i class="material-icons right"></i>
          </button>
          <button class="btn light-blue lighten-2" type="reset" name="Cancelar">Cancelar
                  <i class="material-icons right"></i>
          </button>
       </form>
       </br>
       <div class="col s12"> 
       <div id="mostrar" class="col s6"></div>
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


<script type="text/javascript" src="materialize.min.js"></script>

<script>  
$(function(){
 $("#Ingresar").click(function(){
 var url = "insertar.php";
    $.ajax({
           type: "POST",
           url: url,
           data: $("#formulario").serialize(),
           success: function(data)
           {
               $("#mostrar").html(data);
           }
         });
    return false;
 });
});
</script>


</body>
</html>