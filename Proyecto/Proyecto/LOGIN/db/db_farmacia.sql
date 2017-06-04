-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-04-2016 a las 02:51:38
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `db_farmacia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `existencia`
--

CREATE TABLE IF NOT EXISTS `existencia` (
  `codigo_medicamento` int(15) NOT NULL,
  `idExistencia` int(15) NOT NULL AUTO_INCREMENT,
  `codigo_bodega` int(15) NOT NULL,
  `cantidad_medicina` double NOT NULL,
  `cantidad_faltante` double NOT NULL,
  `cantidad_vencido` double NOT NULL,
  PRIMARY KEY (`idExistencia`),
  KEY `fk_Existencia_tbl_bodega1_idx` (`codigo_bodega`),
  KEY `fk_Existencia_tbl_medicamento1_idx` (`codigo_medicamento`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `existencia`
--

INSERT INTO `existencia` (`codigo_medicamento`, `idExistencia`, `codigo_bodega`, `cantidad_medicina`, `cantidad_faltante`, `cantidad_vencido`) VALUES
(1, 1, 1, 500, 20, 10),
(2, 2, 1, 1200, 51, 10),
(3, 3, 1, 4000, 250, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_bodega`
--

CREATE TABLE IF NOT EXISTS `tbl_bodega` (
  `codigo_bodega` int(15) NOT NULL AUTO_INCREMENT,
  `nombre_bodega` varchar(200) NOT NULL,
  `ubicacion_bodega` varchar(350) NOT NULL,
  PRIMARY KEY (`codigo_bodega`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `tbl_bodega`
--

INSERT INTO `tbl_bodega` (`codigo_bodega`, `nombre_bodega`, `ubicacion_bodega`) VALUES
(1, 'Bodega 1', 'Anillo Periférico, Bodega 1'),
(2, 'Bodega 1', 'Anillo Periferico, Bodega 2'),
(3, 'Bodega 1', 'Anillo Periferico, Bodega 3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_bodeguero`
--

CREATE TABLE IF NOT EXISTS `tbl_bodeguero` (
  `id_bodeguero` int(15) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(70) NOT NULL,
  `apellidos` varchar(70) NOT NULL,
  `genero` varchar(2) NOT NULL,
  `edad` int(15) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `numero_identidad` varchar(100) NOT NULL,
  `estado_civil` varchar(20) NOT NULL,
  `direccion` varchar(300) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  PRIMARY KEY (`id_bodeguero`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `tbl_bodeguero`
--

INSERT INTO `tbl_bodeguero` (`id_bodeguero`, `nombres`, `apellidos`, `genero`, `edad`, `fecha_nacimiento`, `fecha_ingreso`, `numero_identidad`, `estado_civil`, `direccion`, `telefono`, `correo`) VALUES
(1, 'SERGIO MANUEL', 'PEREZ CANALES', 'M', 31, '1985-04-07', '2006-04-07', '0801198502314', 'CASADO', 'COL.21 DE FEBRERO CASA 2145', '98574123', 'sergiocanales@medunah.hn');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_cliente`
--

CREATE TABLE IF NOT EXISTS `tbl_cliente` (
  `codigo_cliente` int(15) NOT NULL AUTO_INCREMENT,
  `nombre_cliente` varchar(45) NOT NULL,
  `telefono_cliente` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `identidad` varchar(45) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `correo_electronico` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo_cliente`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `tbl_cliente`
--

INSERT INTO `tbl_cliente` (`codigo_cliente`, `nombre_cliente`, `telefono_cliente`, `direccion`, `identidad`, `fecha_nacimiento`, `sexo`, `correo_electronico`) VALUES
(1, 'CEILIN JOEL CARCAMO VASQUEZ', '22365898', 'Col. Villas de la Concepcion', '0801-2000-13256', '2015-04-03', 'M', 'ceilinjoel@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_descripcion`
--

CREATE TABLE IF NOT EXISTS `tbl_descripcion` (
  `codigo_descripcion` int(15) NOT NULL AUTO_INCREMENT,
  `codigo_factura` int(15) NOT NULL,
  `codigo_medicamento` int(15) NOT NULL,
  `cantidad` int(20) NOT NULL,
  `precio` decimal(10,0) NOT NULL,
  `total` decimal(10,0) NOT NULL,
  PRIMARY KEY (`codigo_descripcion`),
  KEY `fk_tbl_descripcion_tbls_Factura1_idx` (`codigo_factura`),
  KEY `fk_tbl_descripcion_tbl_medicamento1_idx` (`codigo_medicamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_distribuidora`
--

CREATE TABLE IF NOT EXISTS `tbl_distribuidora` (
  `codigo_distribuidora` int(15) NOT NULL AUTO_INCREMENT,
  `nombre_distribuidora` varchar(100) NOT NULL,
  `direccion_distribuidora` varchar(350) NOT NULL,
  `correo_distribuidora` varchar(45) NOT NULL,
  `telefono_distribuidora` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo_distribuidora`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `tbl_distribuidora`
--

INSERT INTO `tbl_distribuidora` (`codigo_distribuidora`, `nombre_distribuidora`, `direccion_distribuidora`, `correo_distribuidora`, `telefono_distribuidora`) VALUES
(1, 'Drogueria Corinfar, S.A DE C.V', 'Anillo Periférico, Desvió a los Laureles, Com', 'corinfar@gmail.hn', '22299091'),
(2, 'Droguería Farinter', 'Bo. La Granja, Edificio Farinter Tega.', 'nulo', '22252718'),
(3, 'Drogueria Humana, S.A', 'Col. Lomas del Guijarro Edificio Soles Suite 402, Tegucigalpa, M.D.C., Honduras, C.A.', 'info@drogueriahumana.com', ' (504) 2235-9659'),
(4, 'Drogueria Lancasco de Honduras', 'Col. Alameda, Ave. Tiburcio Carias Andino, 502', 'nulo', '22322094/22454515'),
(5, 'EYL Comercial, S.A. ECSA', 'Anillo Periférico Sur, desvío a Loarque, Delante de las bodegas de DHL. Entrada a mano izquierda, tercera bodega,Tegucigalpa/Honduras.', ' info@ageindustrial.com', '2245-4509/2246-9655.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_fabricante`
--

CREATE TABLE IF NOT EXISTS `tbl_fabricante` (
  `codigo_fabricante` int(15) NOT NULL AUTO_INCREMENT,
  `direccion_fabricante` varchar(350) NOT NULL,
  `correo_fabricante` varchar(45) NOT NULL,
  `telefono_fabricante` varchar(45) NOT NULL,
  `nombre_fabricante` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo_fabricante`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `tbl_fabricante`
--

INSERT INTO `tbl_fabricante` (`codigo_fabricante`, `direccion_fabricante`, `correo_fabricante`, `telefono_fabricante`, `nombre_fabricante`) VALUES
(1, 'Av. Cuauhtémoc No. 1481-2 Piso, Col. Santa Cr', 'comentarios@anafam.org.mx', 'Tels: 5601-3082 / 5601-3083', 'ANAFAM'),
(2, ' E-11, 12 & 13, Site-B, Upsidc,, Surajpur,, Greater Noida, Uttar Pradesh 201306, India', 'info@higlance.com ', '+91 120 256 1043', 'HIGLANCE LABORATORIES'),
(3, 'Brasil', 'export@eurofarma.com.br', '+55 11 5090.8487 | +55 11 5090.8538', 'EUROFARMA LABORATORIOS S.A'),
(4, '6 Avenida, 5 Calle S.O.\r\nApt. Postal 116\r\nSan Pedro Sula\r\nCórtes\r\nHonduras', 'farsimanmed@grupofarsiman.hn', '553-0321, 557-5210', 'LABORATORIOS FARSIMAN'),
(5, 'Centro Comercial Outlet Floresta \r\nCarrera 69 No 98 A -11/45 oficina 602', 'info@grupovitalis.com', '673 4340', 'VITALIS FARMACEUTICO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_factura`
--

CREATE TABLE IF NOT EXISTS `tbl_factura` (
  `tbl_Factura` int(15) NOT NULL AUTO_INCREMENT,
  `codigo_farmacia` int(15) NOT NULL,
  `codigo_bodega` int(15) NOT NULL,
  `codigo_cliente` int(15) NOT NULL,
  `fecha` date NOT NULL,
  `total` double NOT NULL,
  `isv` double NOT NULL,
  `total_pagar` double NOT NULL,
  `tipo_pago` varchar(45) NOT NULL,
  `id_vendedor` int(15) NOT NULL,
  PRIMARY KEY (`tbl_Factura`),
  KEY `fk_tbls_Factura_tbl_farmacia1_idx` (`codigo_farmacia`),
  KEY `fk_tbls_Factura_tbl_cliente1_idx` (`codigo_cliente`),
  KEY `fk_tbl_Factura_tbl_vendedor1_idx` (`id_vendedor`),
  KEY `fk_tbl_Factura_tbl_bodega1_idx` (`codigo_bodega`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_farmacia`
--

CREATE TABLE IF NOT EXISTS `tbl_farmacia` (
  `codigo_farmacia` int(15) NOT NULL AUTO_INCREMENT,
  `codigo_bodega` int(15) NOT NULL,
  `nombre_farmacia` varchar(150) NOT NULL,
  `direccion_farmacia` varchar(150) NOT NULL,
  `correo_farmacia` varchar(45) NOT NULL,
  `telefono_farmacia` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo_farmacia`),
  KEY `fk_tbl_farmacia_tbl_bodega1_idx` (`codigo_bodega`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `tbl_farmacia`
--

INSERT INTO `tbl_farmacia` (`codigo_farmacia`, `codigo_bodega`, `nombre_farmacia`, `direccion_farmacia`, `correo_farmacia`, `telefono_farmacia`) VALUES
(1, 1, 'Farmacia MedUNAH', 'Universidad Nacional Autónoma de Honduras, contiguo al edificio J1.', 'medunah@unah.hn', '22259658');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_lote`
--

CREATE TABLE IF NOT EXISTS `tbl_lote` (
  `codigo_lote` int(15) NOT NULL AUTO_INCREMENT,
  `codigo_medicamento` int(11) NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  PRIMARY KEY (`codigo_lote`),
  KEY `fk_tbl_lote_tbl_medicamento1_idx` (`codigo_medicamento`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `tbl_lote`
--

INSERT INTO `tbl_lote` (`codigo_lote`, `codigo_medicamento`, `fecha_ingreso`, `fecha_vencimiento`) VALUES
(1, 1, '2014-04-06', '2020-03-04'),
(2, 1, '2014-04-06', '2020-03-04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_medicamento`
--

CREATE TABLE IF NOT EXISTS `tbl_medicamento` (
  `codigo_medicamento` int(15) NOT NULL AUTO_INCREMENT,
  `codigo_distribuidora` int(15) NOT NULL,
  `codigo_fabricante` int(15) NOT NULL,
  `id_tipo_medicamento` int(15) NOT NULL,
  `nombre_medicamento` varchar(200) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`codigo_medicamento`),
  KEY `fk_tbl_medicamento_tbl_fabricante1_idx` (`codigo_fabricante`),
  KEY `fk_tbl_medicamento_tbl_distribuidora1_idx` (`codigo_distribuidora`),
  KEY `fk_tbl_medicamento_tbl_tipo_medicamento1_idx` (`id_tipo_medicamento`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `tbl_medicamento`
--

INSERT INTO `tbl_medicamento` (`codigo_medicamento`, `codigo_distribuidora`, `codigo_fabricante`, `id_tipo_medicamento`, `nombre_medicamento`, `fecha_vencimiento`, `precio`) VALUES
(1, 1, 1, 1, 'ketamina', '2020-03-04', 50),
(2, 1, 1, 2, 'ibuprofeno', '2023-04-07', 5),
(3, 1, 1, 3, 'metotrexato', '2023-04-07', 20),
(4, 2, 4, 4, 'carbón activado', '2022-04-02', 20),
(6, 4, 5, 5, 'carbamazepina', '2040-04-02', 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_supervisor_bodega`
--

CREATE TABLE IF NOT EXISTS `tbl_supervisor_bodega` (
  `id_supervisor_bodega` int(15) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(70) NOT NULL,
  `apellidos` varchar(70) NOT NULL,
  `genero` varchar(2) NOT NULL,
  `edad` int(11) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `numero_identidad` varchar(100) NOT NULL,
  `estado_civil` varchar(20) NOT NULL,
  `direccion` varchar(300) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  PRIMARY KEY (`id_supervisor_bodega`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_supervisor_ventas`
--

CREATE TABLE IF NOT EXISTS `tbl_supervisor_ventas` (
  `id_supervisor_ventas` int(15) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(70) NOT NULL,
  `apellidos` varchar(70) NOT NULL,
  `genero` varchar(2) NOT NULL,
  `edad` int(11) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `numero_identidad` varchar(100) NOT NULL,
  `estado_civil` varchar(20) NOT NULL,
  `direccion` varchar(300) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  PRIMARY KEY (`id_supervisor_ventas`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `tbl_supervisor_ventas`
--

INSERT INTO `tbl_supervisor_ventas` (`id_supervisor_ventas`, `nombres`, `apellidos`, `genero`, `edad`, `fecha_nacimiento`, `fecha_ingreso`, `numero_identidad`, `estado_civil`, `direccion`, `telefono`, `correo`) VALUES
(1, 'JOSUE SANTIAGO', 'MEDINA GALVEZ', 'M', 45, '1971-10-14', '2000-04-03', '6dbf52f4828e049bb80a95c121191b07d0e49f18', 'CASADO', 'Col. Cerro Grande, Zona 2', '98745698', 'josuesantiago@medunah.hn');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_super_usuario`
--

CREATE TABLE IF NOT EXISTS `tbl_super_usuario` (
  `id_super_usuario` int(15) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(70) NOT NULL,
  `apellidos` varchar(70) NOT NULL,
  `genero` varchar(2) NOT NULL,
  `edad` int(11) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `numero_identidad` varchar(100) NOT NULL,
  `estado_civil` varchar(20) NOT NULL,
  `direccion` varchar(300) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  PRIMARY KEY (`id_super_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `tbl_super_usuario`
--

INSERT INTO `tbl_super_usuario` (`id_super_usuario`, `nombres`, `apellidos`, `genero`, `edad`, `fecha_nacimiento`, `fecha_ingreso`, `numero_identidad`, `estado_civil`, `direccion`, `telefono`, `correo`) VALUES
(1, 'RONALDO ENRRIQUE', 'CARCAMO VASQUEZ', 'M', 20, '1996-10-15', '2016-04-01', 'b8134523d7287dc5ea136a6b0416c6b0eed2e7a0', 'SOLTERO', 'RES.VILLAS DE LA CONCEPCION', '96544289', 'carcamoronaldo@gmail.com'),
(2, 'OSCAR MAURICIO DE JESUS', 'GALO DUARTE', 'M', 20, '1996-01-15', '2016-01-21', '0f692656bdb54e7c70e4087dda476da7f2b40f44', 'SOLTERO', 'COL.SAN JOSE DE LA VEGA', '32154060', 'ogalo@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_tipo_medicamento`
--

CREATE TABLE IF NOT EXISTS `tbl_tipo_medicamento` (
  `id_tipo_medicamento` int(15) NOT NULL AUTO_INCREMENT,
  `nombre_tipo_medicamento` varchar(100) NOT NULL,
  PRIMARY KEY (`id_tipo_medicamento`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

--
-- Volcado de datos para la tabla `tbl_tipo_medicamento`
--

INSERT INTO `tbl_tipo_medicamento` (`id_tipo_medicamento`, `nombre_tipo_medicamento`) VALUES
(1, 'ANESTÉSICOS'),
(2, 'ANALGÉSICOS, ANTIPIRÉTICOS, ANTIINFLAMATORIOS NO ESTEROIDEOS, ANTIGOTOSOS'),
(3, 'ANTIALÉRGICOS Y MEDICAMENTOS UTILIZADOS EN LA ANAFILAXIA '),
(4, 'ANTÍDOTOS'),
(5, 'ANTICONVULSIVOS/ANTIEPILÉPTICOS'),
(6, 'ANTIINFECCIOSOS'),
(7, 'ANTIMIGRAÑOSOS'),
(8, 'ANTINEOPLÁSICOS, INMUNODEPRESORES Y MEDICAMENTOS\r\nUTILIZADOS EN LOS CUIDADOS PALIATIVOS'),
(9, 'ANTIPARKINSONIANOS'),
(10, 'MEDICAMENTOS QUE AFECTAN A LA SANGRE'),
(11, 'PRODUCTOS SANGUÍNEOS Y SUCEDÁNEOS DEL PLASMA'),
(12, 'MEDICAMENTOS CARDIOVASCULARES'),
(13, 'MEDICAMENTOS DERMATOLÓGICOS (tópicos)'),
(14, 'AGENTES DE DIAGNÓSTICO'),
(15, 'DESINFECTANTES Y ANTISÉPTICOS'),
(16, 'DIURÉTICOS'),
(17, 'MEDICAMENTOS GASTROINTESTINALES');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_vendedor`
--

CREATE TABLE IF NOT EXISTS `tbl_vendedor` (
  `id_vendedor` int(15) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(70) NOT NULL,
  `apellidos` varchar(70) NOT NULL,
  `genero` varchar(2) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `numero_identidad` varchar(100) NOT NULL,
  `estado_civil` varchar(20) NOT NULL,
  `direccion` varchar(300) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  PRIMARY KEY (`id_vendedor`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `tbl_vendedor`
--

INSERT INTO `tbl_vendedor` (`id_vendedor`, `nombres`, `apellidos`, `genero`, `fecha_nacimiento`, `fecha_ingreso`, `numero_identidad`, `estado_civil`, `direccion`, `telefono`, `correo`) VALUES
(1, 'ABDI JOSUE', 'ELVIR MONTERO', 'M', '1980-04-01', '2010-04-10', 'efaf1a36bdd665956fd3677c35ee444b0d8e6852', 'Soltero', 'Res.CentroAmerica', '98745698', 'abdijosue@medunah.hn');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `existencia`
--
ALTER TABLE `existencia`
  ADD CONSTRAINT `fk_Existencia_tbl_bodega1` FOREIGN KEY (`codigo_bodega`) REFERENCES `tbl_bodega` (`codigo_bodega`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Existencia_tbl_medicamento1` FOREIGN KEY (`codigo_medicamento`) REFERENCES `tbl_medicamento` (`codigo_medicamento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_descripcion`
--
ALTER TABLE `tbl_descripcion`
  ADD CONSTRAINT `fk_tbl_descripcion_tbls_Factura1` FOREIGN KEY (`codigo_factura`) REFERENCES `tbl_factura` (`tbl_Factura`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbl_descripcion_tbl_medicamento1` FOREIGN KEY (`codigo_medicamento`) REFERENCES `tbl_medicamento` (`codigo_medicamento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_factura`
--
ALTER TABLE `tbl_factura`
  ADD CONSTRAINT `fk_tbls_Factura_tbl_cliente1` FOREIGN KEY (`codigo_cliente`) REFERENCES `tbl_cliente` (`codigo_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbls_Factura_tbl_farmacia1` FOREIGN KEY (`codigo_farmacia`) REFERENCES `tbl_farmacia` (`codigo_farmacia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbl_Factura_tbl_bodega1` FOREIGN KEY (`codigo_bodega`) REFERENCES `tbl_bodega` (`codigo_bodega`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbl_Factura_tbl_vendedor1` FOREIGN KEY (`id_vendedor`) REFERENCES `tbl_vendedor` (`id_vendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_farmacia`
--
ALTER TABLE `tbl_farmacia`
  ADD CONSTRAINT `fk_tbl_farmacia_tbl_bodega1` FOREIGN KEY (`codigo_bodega`) REFERENCES `tbl_bodega` (`codigo_bodega`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_lote`
--
ALTER TABLE `tbl_lote`
  ADD CONSTRAINT `fk_tbl_lote_tbl_medicamento1` FOREIGN KEY (`codigo_medicamento`) REFERENCES `tbl_medicamento` (`codigo_medicamento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_medicamento`
--
ALTER TABLE `tbl_medicamento`
  ADD CONSTRAINT `fk_tbl_medicamento_tbl_distribuidora1` FOREIGN KEY (`codigo_distribuidora`) REFERENCES `tbl_distribuidora` (`codigo_distribuidora`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbl_medicamento_tbl_fabricante1` FOREIGN KEY (`codigo_fabricante`) REFERENCES `tbl_fabricante` (`codigo_fabricante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbl_medicamento_tbl_tipo_medicamento1` FOREIGN KEY (`id_tipo_medicamento`) REFERENCES `tbl_tipo_medicamento` (`id_tipo_medicamento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
