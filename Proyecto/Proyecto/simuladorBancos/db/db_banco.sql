-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-04-2016 a las 09:29:59
-- Versión del servidor: 5.7.9
-- Versión de PHP: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_banco`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_cliente`
--

DROP TABLE IF EXISTS `tbl_cliente`;
CREATE TABLE IF NOT EXISTS `tbl_cliente` (
  `codigo_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_cliente` varchar(45) NOT NULL,
  `telefono_cliente` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `identidad` varchar(45) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `correo_electronico` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tbl_cliente`
--

INSERT INTO `tbl_cliente` (`codigo_cliente`, `nombre_cliente`, `telefono_cliente`, `direccion`, `identidad`, `fecha_nacimiento`, `sexo`, `correo_electronico`) VALUES
(1, 'Pedro Rodriguez', '226584412', 'la peña', '008011982026874', '1982-04-14', 'M', 'pro@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tlb_cuenta`
--

DROP TABLE IF EXISTS `tlb_cuenta`;
CREATE TABLE IF NOT EXISTS `tlb_cuenta` (
  `codigo_cuenta` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_cuenta` varchar(45) NOT NULL,
  `fecha_creacion` date NOT NULL,
  `saldo` double NOT NULL,
  `moneda` varchar(45) NOT NULL,
  `tbl_cliente_codigo_cliente` int(11) NOT NULL,
  PRIMARY KEY (`codigo_cuenta`),
  KEY `fk_tlb_cuenta_tbl_cliente1_idx` (`tbl_cliente_codigo_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tlb_cuenta`
--

INSERT INTO `tlb_cuenta` (`codigo_cuenta`, `tipo_cuenta`, `fecha_creacion`, `saldo`, `moneda`, `tbl_cliente_codigo_cliente`) VALUES
(1, 'Debito', '2015-11-28', 20000.96, 'HNL', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tlb_tarjeta`
--

DROP TABLE IF EXISTS `tlb_tarjeta`;
CREATE TABLE IF NOT EXISTS `tlb_tarjeta` (
  `codigo_tarjeta` int(11) NOT NULL AUTO_INCREMENT,
  `tlb_cuenta_codigo_cuenta` int(11) NOT NULL,
  `numero_tarjeta` varchar(16) NOT NULL,
  `fecha_creacion` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `emisor` varchar(45) NOT NULL,
  `validez` tinyint(1) NOT NULL,
  PRIMARY KEY (`codigo_tarjeta`),
  KEY `fk_tlb_tarjeta_tlb_cuenta1_idx` (`tlb_cuenta_codigo_cuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tlb_tarjeta`
--

INSERT INTO `tlb_tarjeta` (`codigo_tarjeta`, `tlb_cuenta_codigo_cuenta`, `numero_tarjeta`, `fecha_creacion`, `fecha_vencimiento`, `nombre`, `emisor`, `validez`) VALUES
(1, 1, '4398880112346598', '2016-02-28', '2019-04-28', 'Pedro Rodriguez', 'Visa', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tlb_cuenta`
--
ALTER TABLE `tlb_cuenta`
  ADD CONSTRAINT `fk_tlb_cuenta_tbl_cliente1` FOREIGN KEY (`tbl_cliente_codigo_cliente`) REFERENCES `tbl_cliente` (`codigo_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tlb_tarjeta`
--
ALTER TABLE `tlb_tarjeta`
  ADD CONSTRAINT `fk_tlb_tarjeta_tlb_cuenta1` FOREIGN KEY (`tlb_cuenta_codigo_cuenta`) REFERENCES `tlb_cuenta` (`codigo_cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
