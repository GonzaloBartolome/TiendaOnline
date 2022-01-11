-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 10-01-2022 a las 18:13:19
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.0.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tiendaonline`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `descripcion`, `nombre`) VALUES
(1, 'Denominacion de Origen Toro', 'Toro'),
(2, 'Denominacion de Origen Rioja', 'Rioja'),
(3, 'Denominacion de Origen Ribera del Duero', 'Ribera del Duero'),
(4, 'Packs y Cestas Regalo - Especial Navidad', 'Packs');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL,
  `clave` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `tipo` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `valor` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `clave`, `tipo`, `valor`) VALUES
(1, 'numFactura', 'int', '52');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles_pedido`
--

CREATE TABLE `detalles_pedido` (
  `id` int(11) NOT NULL,
  `impuesto` float DEFAULT NULL,
  `precio_unidad` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `unidades` int(11) DEFAULT NULL,
  `pedido_id` int(11) DEFAULT NULL,
  `producto_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `detalles_pedido`
--

INSERT INTO `detalles_pedido` (`id`, `impuesto`, `precio_unidad`, `total`, `unidades`, `pedido_id`, `producto_id`) VALUES
(140, 21, 72, 72, 1, 74, 8),
(141, 21, 41, 41, 1, 74, 4),
(142, 21, 16, 16, 1, 74, 19),
(143, 21, 124, 124, 1, 74, 16),
(144, 21, 41, 41, 1, 75, 4),
(145, 21, 124, 124, 1, 75, 16),
(148, 21, 67, 67, 1, 76, 17),
(149, 21, 16, 16, 1, 76, 19),
(150, 21, 8.5, 8.5, 1, 77, 2),
(151, 21, 28, 28, 1, 77, 15),
(152, 21, 16, 16, 1, 77, 19),
(154, 21, 37, 37, 1, 78, 3),
(155, 21, 41, 41, 1, 78, 4),
(156, 21, 105, 105, 1, 79, 12),
(158, 21, 72, 72, 1, 79, 8),
(159, 21, 41, 82, 2, 79, 4),
(160, 21, 124, 124, 1, 80, 16),
(161, 21, 16, 32, 2, 80, 19),
(162, 21, 72, 72, 1, 80, 8),
(163, 21, 37, 37, 1, 81, 3),
(164, 21, 41, 41, 1, 81, 4),
(165, 21, 41, 82, 2, 82, 4),
(166, 21, 41, 41, 1, 83, 4),
(167, 21, 16, 16, 1, 83, 19),
(168, 21, 8.5, 8.5, 1, 83, 2),
(170, 21, 67, 67, 1, 83, 17),
(171, 21, 41, 82, 2, 84, 4),
(172, 21, 37, 37, 1, 85, 3),
(173, 21, 9, 9, 1, 85, 7),
(174, 21, 105, 105, 1, 85, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `impuestos`
--

CREATE TABLE `impuestos` (
  `id` int(11) NOT NULL,
  `impuesto` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `impuestos`
--

INSERT INTO `impuestos` (`id`, `impuesto`) VALUES
(1, 21);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pago`
--

CREATE TABLE `metodos_pago` (
  `id` int(11) NOT NULL,
  `metodo_pago` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `metodos_pago`
--

INSERT INTO `metodos_pago` (`id`, `metodo_pago`) VALUES
(1, 'Debito Mastercard'),
(2, 'Debito Visa'),
(3, 'Paypal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opciones_menu`
--

CREATE TABLE `opciones_menu` (
  `id` int(11) NOT NULL,
  `nombre_opcion` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `url_opcion` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `roles_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL,
  `estado` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `num_factura` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `total` double DEFAULT NULL,
  `metodo_pago_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `estado`, `fecha`, `num_factura`, `total`, `metodo_pago_id`, `usuario_id`) VALUES
(74, 'enviado', NULL, '45', 253, 1, 2),
(75, 'enviado', NULL, '50', 165, 2, 2),
(76, 'cancelado', NULL, NULL, 83, 3, 1),
(77, 'enviado', NULL, '46', 52.5, 3, 1),
(78, 'enviado', NULL, '47', 78, 3, 1),
(79, 'pendiente de cancelacion', NULL, NULL, 259, 2, 1),
(80, 'enviado', NULL, '49', 228, 2, 1),
(81, 'enviado', NULL, '48', 78, 3, 1),
(82, 'pendiente', NULL, NULL, 82, 3, 2),
(83, 'pendiente', NULL, NULL, 132.5, 2, 1),
(84, 'enviado', NULL, '51', 82, 2, 1),
(85, 'pendiente', NULL, NULL, 151, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `fecha_alta` datetime DEFAULT CURRENT_TIMESTAMP,
  `fecha_baja` date DEFAULT '0000-00-00',
  `imagen` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `impuesto_id` int(11) DEFAULT NULL,
  `baja` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `descripcion`, `fecha_alta`, `fecha_baja`, `imagen`, `nombre`, `precio`, `stock`, `categoria_id`, `impuesto_id`, `baja`) VALUES
(1, 'Tiene un toque aromatico', '2021-11-23 02:59:08', '2021-12-27', '25.png', 'Matsu El Pícaro 2020', 8, 0, 1, 1, 0),
(2, ' vino tinto concentrado con aromas de fruta roja y negra, notas especias y toques de cacao. En boca es goloso, carnoso y envolvente', '2021-12-27 03:19:23', '0002-11-30', '28.png', 'Madre Mia 2020', 8.5, 32, 1, 1, 0),
(3, 'Un tinto de lujo de la RIbera del Duero hecho con los mejores racimos de cada cepa para asegurar su excelencia.', '2022-01-10 00:15:44', '0002-11-30', '1.png', 'Valduero Crianza 2016', 37, 8, 3, 1, 0),
(4, 'Equilibrio: Entre la madurez de la fruta y su crianza en barrica. Descansa en barricas seleccionadas de roble francés y americano', '2022-01-04 15:43:37', '2022-01-09', '3.png', 'Matarromera 2016', 41, 20, 3, 1, 1),
(7, 'Tipo de Uva: 100% Tempranillo. Capacidad: 75 CL. Criado 14 meses en barricas de roble americano nuevo y 8 meses en botella. Maridaje con embutidos, quesos suaves y gran variedad de carnes.', '2022-01-10 00:15:44', '0002-11-30', 'ramon-bilbao.png', 'Ramon Bilbao 2017', 9, 50, 2, 1, 0),
(8, 'Tiene un agradable toque a toffee. Es un vino muy entero, con cuerpo, con volumen y muy equilibrado.Es un vino para celebraciones y marida muy bien con chuletón, cochinillo y carnes de caza.', '2021-12-07 09:49:00', '0002-11-30', '8.png', 'Rioja Vega 2015', 72, 43, 2, 1, 0),
(12, '     Elaborador: Pingus; Añada: 2016; Color: Tinto; Variedad: Tinto Fino; Zona: Ribera del Duero; Grado alcohólico: 14,5; Puntuación Parker: 95.', '2022-01-10 00:15:44', '0002-11-30', 'pingus.png', 'Flor de Pingus 2016 ', 105, 30, 3, 1, 0),
(15, 'Pack de 3 vinos de la Bodega Divina Proporción, podrás diferenciar claramente las propiedades que le da a los vinos su paso por barrica. ', '2021-11-23 08:12:29', '0002-11-30', 'pack-divina.png', 'Pack Divina Proporción', 28, 42, 4, 1, 0),
(16, 'Vino de calidad excepcional por la procedencia de la uva: viñedo de marco al cuadro y suelo de piedra, plantado en 1950.', '2021-12-07 09:49:00', '0002-11-30', 'pack-eguren-ugarte.png', 'Pack Eguren Ugarte ', 124, 19, 4, 1, 0),
(17, 'Seleción de 2 una botella Garnacha vella de Chaira do Ramiriño y otra de godello en Lágrimas de los bancales de Olivedo.', '2021-12-27 03:19:23', '0002-11-30', 'pack-sampayolo.png', 'Vinos de Autor Sampayolo', 67, 43, 4, 1, 0),
(18, 'Gran combinación para acompañar carnes y pescados con esta mezcla de vinos tinto y blanco de la gran Bodega Campoameno.', '2022-01-09 05:17:46', '0002-11-30', 'pack-campoameno.png', 'Pack Campoameno', 20, 19, 4, 1, 0),
(19, 'Protos Reserva es un vino elegante, complejo y con mucho carácter que no dejará a nadie indiferente.', '2021-12-27 03:19:23', '2022-01-09', 'protos-reserva.png', 'Protos Reserva', 16, 37, 3, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id` int(11) NOT NULL,
  `cif` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `direccion` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `localidad` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `provincia` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `telefono` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `rol` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`) VALUES
(1, 'Cliente'),
(2, 'Empleado'),
(3, 'Admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `apellido1` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `apellido2` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `clave` varchar(16) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `direccion` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `dni` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `localidad` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
  `provincia` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `telefono` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `rol_id` int(11) DEFAULT NULL,
  `baja` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `apellido1`, `apellido2`, `clave`, `direccion`, `dni`, `email`, `localidad`, `nombre`, `provincia`, `telefono`, `rol_id`, `baja`) VALUES
(1, 'Sanchez', 'Mato', 'MTIzNA==', 'Calle la Resurreccion 212', '119825341Y', 'cliente@tienda.com', 'Valladolid', 'Teresa', 'Valladolid', '654983738', 1, 0),
(2, 'Tenorio', 'Castroviejo', 'MTIzNA==', 'Calle Pasion 95', '459825341Y', 'cliente1@tienda.com', 'Valladolid', 'Maria', 'Valladolid', '654233738', 1, 0),
(3, 'Martinez', 'Carote', 'MTIzNA==', 'Avd Cardenal', '71927971I', 'empleado@tienda.com', 'León', 'Eulogio', 'León', '698324623', 2, 0),
(4, 'Martueros', 'Leal', 'MTIzNA==', 'Calle Convento', '23287364T', 'admin@tienda.com', 'Leon', 'Luca', 'Salamanca', '97392744', 3, 0),
(8, 'Ortiz', 'Valderrama', 'MTIzNA==', 'Calle Lugones', '11982633P', 'cliente2@tienda.com', 'Zamora', 'Luis', 'Zamora', '678241627', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoraciones`
--

CREATE TABLE `valoraciones` (
  `id` int(11) NOT NULL,
  `comentario` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `valoracion` int(11) DEFAULT NULL,
  `producto_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `baja` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `valoraciones`
--

INSERT INTO `valoraciones` (`id`, `comentario`, `valoracion`, `producto_id`, `usuario_id`, `baja`) VALUES
(1, 'Un vino increible por el precio. Calidad precio insuperable', 5, 2, 1, b'0');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4qmqlxyy78kjl4ec4wjnfmggu` (`pedido_id`),
  ADD KEY `FK8144uqs26ce7usdnqb1aml16` (`producto_id`);

--
-- Indices de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqy2fua0y2fjxyf7mg7o6pmnrp` (`roles_id`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1406bqgphpafvi9n6bkd1twa7` (`metodo_pago_id`),
  ADD KEY `FK5g0es69v35nmkmpi8uewbphs2` (`usuario_id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2fwq10nwymfv7fumctxt9vpgb` (`categoria_id`),
  ADD KEY `FKgbt77pxusq6dhpcf8cvadyr3g` (`impuesto_id`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqf5elo4jcq7qrt83oi0qmenjo` (`rol_id`);

--
-- Indices de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn14xyxjg57ghtqjjnc20innfh` (`producto_id`),
  ADD KEY `FKmtbedrv2q0wjdsrvnb57g8whw` (`usuario_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=175;

--
-- AUTO_INCREMENT de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  ADD CONSTRAINT `FK4qmqlxyy78kjl4ec4wjnfmggu` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`),
  ADD CONSTRAINT `FK8144uqs26ce7usdnqb1aml16` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`);

--
-- Filtros para la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  ADD CONSTRAINT `FKqy2fua0y2fjxyf7mg7o6pmnrp` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`);

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `FK1406bqgphpafvi9n6bkd1twa7` FOREIGN KEY (`metodo_pago_id`) REFERENCES `metodos_pago` (`id`),
  ADD CONSTRAINT `FK5g0es69v35nmkmpi8uewbphs2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `FK2fwq10nwymfv7fumctxt9vpgb` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`),
  ADD CONSTRAINT `FKgbt77pxusq6dhpcf8cvadyr3g` FOREIGN KEY (`impuesto_id`) REFERENCES `impuestos` (`id`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `FKqf5elo4jcq7qrt83oi0qmenjo` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`);

--
-- Filtros para la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD CONSTRAINT `FKmtbedrv2q0wjdsrvnb57g8whw` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `FKn14xyxjg57ghtqjjnc20innfh` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
