REM INSERTING into FARMACEUTICA.TBL_TIPO_LOCALIDADES
SET DEFINE OFF;
Insert into FARMACEUTICA.TBL_TIPO_LOCALIDADES(COD_TIPO_LOCALIDAD, NOMBRE_TIPO_LOCALIDAD) values (1,'Tipo_Localidad I');
Insert into FARMACEUTICA.TBL_TIPO_LOCALIDADES(COD_TIPO_LOCALIDAD, NOMBRE_TIPO_LOCALIDAD) values (2,'Tipo_Localidad II');

REM INSERTING into FARMACEUTICA.TBL_LOCALIDADES
SET DEFINE OFF;
Insert into FARMACEUTICA.TBL_LOCALIDADES(COD_LOCALIDAD, NOMBRE_LOCALIDAD, COD_LOCALIDAD_PADRE, COD_TIPO_LOCALIDAD) values (1,'Localidad I', NULL, 1);
Insert into FARMACEUTICA.TBL_LOCALIDADES(COD_LOCALIDAD, NOMBRE_LOCALIDAD, COD_LOCALIDAD_PADRE, COD_TIPO_LOCALIDAD) values (2,'Localidad I-II', 1, 2);
Insert into FARMACEUTICA.TBL_LOCALIDADES(COD_LOCALIDAD, NOMBRE_LOCALIDAD, COD_LOCALIDAD_PADRE, COD_TIPO_LOCALIDAD) values (3,'Localidad III', NULL, 1);
Insert into FARMACEUTICA.TBL_LOCALIDADES(COD_LOCALIDAD, NOMBRE_LOCALIDAD, COD_LOCALIDAD_PADRE, COD_TIPO_LOCALIDAD) values (4,'Localidad III-IV', 3, 2);
Insert into FARMACEUTICA.TBL_LOCALIDADES(COD_LOCALIDAD, NOMBRE_LOCALIDAD, COD_LOCALIDAD_PADRE, COD_TIPO_LOCALIDAD) values (5,'Localidad V', NULL, 1);
Insert into FARMACEUTICA.TBL_LOCALIDADES(COD_LOCALIDAD, NOMBRE_LOCALIDAD, COD_LOCALIDAD_PADRE, COD_TIPO_LOCALIDAD) values (6,'Localidad V-VI', 5, 2);
Insert into FARMACEUTICA.TBL_LOCALIDADES(COD_LOCALIDAD, NOMBRE_LOCALIDAD, COD_LOCALIDAD_PADRE, COD_TIPO_LOCALIDAD) values (7,'Localidad VII', NULL, 1);
Insert into FARMACEUTICA.TBL_LOCALIDADES(COD_LOCALIDAD, NOMBRE_LOCALIDAD, COD_LOCALIDAD_PADRE, COD_TIPO_LOCALIDAD) values (8,'Localidad V-VIII', 5, 2);
Insert into FARMACEUTICA.TBL_LOCALIDADES(COD_LOCALIDAD, NOMBRE_LOCALIDAD, COD_LOCALIDAD_PADRE, COD_TIPO_LOCALIDAD) values (9,'Localidad VII-IX', 7, 2);

REM INSERTING into FARMACEUTICA.TBL_TIPO_PRODUCTO
SET DEFINE OFF;
Insert into FARMACEUTICA.TBL_TIPO_PRODUCTO (COD_TIPO_PRODUCTO, NOMBRE_TIPO_PRODUCTO) values (1,'Oral');
Insert into FARMACEUTICA.TBL_TIPO_PRODUCTO (COD_TIPO_PRODUCTO, NOMBRE_TIPO_PRODUCTO) values (2,'Sublingual');
Insert into FARMACEUTICA.TBL_TIPO_PRODUCTO (COD_TIPO_PRODUCTO, NOMBRE_TIPO_PRODUCTO) values (3,'Topica');
Insert into FARMACEUTICA.TBL_TIPO_PRODUCTO (COD_TIPO_PRODUCTO, NOMBRE_TIPO_PRODUCTO) values (4,'Transdermica');
Insert into FARMACEUTICA.TBL_TIPO_PRODUCTO (COD_TIPO_PRODUCTO, NOMBRE_TIPO_PRODUCTO) values (5,'Oftalmica');
Insert into FARMACEUTICA.TBL_TIPO_PRODUCTO (COD_TIPO_PRODUCTO, NOMBRE_TIPO_PRODUCTO) values (6,'Otica');
Insert into FARMACEUTICA.TBL_TIPO_PRODUCTO (COD_TIPO_PRODUCTO, NOMBRE_TIPO_PRODUCTO) values (7,'Intranasal');
Insert into FARMACEUTICA.TBL_TIPO_PRODUCTO (COD_TIPO_PRODUCTO, NOMBRE_TIPO_PRODUCTO) values (8,'Inhalatoria');
Insert into FARMACEUTICA.TBL_TIPO_PRODUCTO (COD_TIPO_PRODUCTO, NOMBRE_TIPO_PRODUCTO) values (9,'Rectal');
Insert into FARMACEUTICA.TBL_TIPO_PRODUCTO (COD_TIPO_PRODUCTO, NOMBRE_TIPO_PRODUCTO) values (10,'Vaginal');
Insert into FARMACEUTICA.TBL_TIPO_PRODUCTO (COD_TIPO_PRODUCTO, NOMBRE_TIPO_PRODUCTO) values (11,'Parenteral');

REM INSERTING into FARMACEUTICA.Tbl_TipoUsuario
SET DEFINE OFF;
Insert into FARMACEUTICA.Tbl_TipoUsuario (COD_USUARIO, NOMBRE_USUARIO) values (1,'USUARIO I');
Insert into FARMACEUTICA.Tbl_TipoUsuario (COD_USUARIO, NOMBRE_USUARIO) values (2,'USUARIO II');
Insert into FARMACEUTICA.Tbl_TipoUsuario (COD_USUARIO, NOMBRE_USUARIO) values (3,'USUARIO III');
Insert into FARMACEUTICA.Tbl_TipoUsuario (COD_USUARIO, NOMBRE_USUARIO) values (4,'USUARIO IV');

REM INSERTING into FARMACEUTICA.Tbl_Quimico_Farmaceutico
SET DEFINE OFF;
Insert into FARMACEUTICA.Tbl_Quimico_Farmaceutico (Cod_Quimico_Farmaceutico, Descripcion) values (1, 'Descripcion_Quimico I');
Insert into FARMACEUTICA.Tbl_Quimico_Farmaceutico (Cod_Quimico_Farmaceutico, Descripcion) values (2, 'Descripcion_Quimico II');
Insert into FARMACEUTICA.Tbl_Quimico_Farmaceutico (Cod_Quimico_Farmaceutico, Descripcion) values (3, 'Descripcion_Quimico III');
Insert into FARMACEUTICA.Tbl_Quimico_Farmaceutico (Cod_Quimico_Farmaceutico, Descripcion) values (4, 'Descripcion_Quimico IV');

REM INSERTING into FARMACEUTICA.TBL_EMPLEADO
SET DEFINE OFF;
Insert into FARMACEUTICA.TBL_EMPLEADO (Cod_Empleado, Nombre_Empleado, Sexo_Empleado, Direccion_Empleado, Numero_Telef_Empleado, Correo_Empleado, Cod_Usuario, Cod_Quimico_Farmaceutico) values (1, 'Empleado I', 'M', 'Direccion_Empleado I', 88888888, 'Correo_Empleado I', 1,1);
Insert into FARMACEUTICA.TBL_EMPLEADO (Cod_Empleado, Nombre_Empleado, Sexo_Empleado, Direccion_Empleado, Numero_Telef_Empleado, Correo_Empleado, Cod_Usuario, Cod_Quimico_Farmaceutico) values (2, 'Empleado II', 'F', 'Direccion_Empleado II', 85432108, 'Correo_Empleado II', 2, 2);
Insert into FARMACEUTICA.TBL_EMPLEADO (Cod_Empleado, Nombre_Empleado, Sexo_Empleado, Direccion_Empleado, Numero_Telef_Empleado, Correo_Empleado, Cod_Usuario, Cod_Quimico_Farmaceutico) values (3, 'Empleado III', 'M', 'Direccion_Empleado III', 87676788, 'Correo_Empleado III', 3, 3);
Insert into FARMACEUTICA.TBL_EMPLEADO (Cod_Empleado, Nombre_Empleado, Sexo_Empleado, Direccion_Empleado, Numero_Telef_Empleado, Correo_Empleado, Cod_Usuario, Cod_Quimico_Farmaceutico) values (4, 'Empleado IV', 'F', 'Direccion_Empleado IV', 88999988, 'Correo_Empleado IV', 4, 4);


REM INSERTING into FARMACEUTICA.TBL_FARMACIA
SET DEFINE OFF;
Insert into FARMACEUTICA.TBL_FARMACIA (Cod_Farmacia, Nombre_Farmacia, DirrecioN, Correo_Farmacia, COD_LOCALIDAD) values (1, 'Nombre_Farmacia I', 'Direccion_Farmacia I', 'Correo_Farmacia I', 2);
Insert into FARMACEUTICA.TBL_FARMACIA (Cod_Farmacia, Nombre_Farmacia, DirrecioN, Correo_Farmacia, COD_LOCALIDAD) values (2, 'Nombre_Farmacia II', 'Direccion_Farmacia II', 'Correo_Farmacia II', 4);
Insert into FARMACEUTICA.TBL_FARMACIA (Cod_Farmacia, Nombre_Farmacia, DirrecioN, Correo_Farmacia, COD_LOCALIDAD) values (3, 'Nombre_Farmacia III', 'Direccion_Farmacia III', 'Correo_Farmacia III', 6);
Insert into FARMACEUTICA.TBL_FARMACIA (Cod_Farmacia, Nombre_Farmacia, DirrecioN, Correo_Farmacia, COD_LOCALIDAD) values (4, 'Nombre_Farmacia IV', 'Direccion_Farmacia IV', 'Correo_Farmacia IV', 8);
Insert into FARMACEUTICA.TBL_FARMACIA (Cod_Farmacia, Nombre_Farmacia, DirrecioN, Correo_Farmacia, COD_LOCALIDAD) values (5, 'Nombre_Farmacia V', 'Direccion_Farmacia V', 'Correo_Farmacia V', 9);

REM INSERTING into FARMACEUTICA.TBL_PRODUCTO
SET DEFINE OFF;
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (1,'Producto I', 'Descripcion_Producto I', 85.00, to_date('26-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 1);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (2,'Producto II', 'Descripcion_Producto II', 65.76, to_date('26-MAY-16','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 2);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (3,'Producto III', 'Descripcion_Producto III', 135.80, to_date('26-MAY-17','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 2);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (4,'Producto IV', 'Descripcion_Producto IV', 48.50, to_date('26-MAY-14','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 4);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (5,'Producto V', 'Descripcion_Producto V', 53.60, to_date('29-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 5);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (6,'Producto VI', 'Descripcion_Producto VI', 95.00, to_date('28-MAY-14','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 6);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (7,'Producto I', 'Descripcion_Producto I', 85.00, to_date('26-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 1);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (8,'Producto II', 'Descripcion_Producto II', 65.76, to_date('26-MAY-16','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 2);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (9,'Producto III', 'Descripcion_Producto III', 135.80, to_date('26-MAY-17','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 2);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (10,'Producto IV', 'Descripcion_Producto IV', 48.50, to_date('26-MAY-14','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 4);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (11,'Producto V', 'Descripcion_Producto V', 53.60, to_date('29-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 5);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (12,'Producto VI', 'Descripcion_Producto VI', 95.00, to_date('28-MAY-14','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 6);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (13,'Producto I', 'Descripcion_Producto I', 85.00, to_date('26-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 1);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (14,'Producto I', 'Descripcion_Producto I', 85.00, to_date('26-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 1);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (15,'Producto I', 'Descripcion_Producto I', 85.00, to_date('26-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 1);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (16,'Producto I', 'Descripcion_Producto I', 85.00, to_date('26-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 1);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (17,'Producto II', 'Descripcion_Producto II', 65.76, to_date('26-MAY-16','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 2);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (18,'Producto II', 'Descripcion_Producto II', 65.76, to_date('26-MAY-16','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 2);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (19,'Producto III', 'Descripcion_Producto III', 135.80, to_date('26-MAY-17','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 2);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (20,'Producto V', 'Descripcion_Producto V', 53.60, to_date('29-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 5);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (21,'Producto V', 'Descripcion_Producto V', 53.60, to_date('29-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 5);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (22,'Producto V', 'Descripcion_Producto V', 53.60, to_date('29-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 5);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (23,'Producto V', 'Descripcion_Producto V', 53.60, to_date('29-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 5);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (24,'Producto V', 'Descripcion_Producto V', 53.60, to_date('29-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 5);
Insert into FARMACEUTICA.TBL_PRODUCTO (Cod_Producto, Nombre_Producto, Descripcion_Producto, Precio_Producto, Fecha_Ingreso_Producto, Fecha_Exp_Producto, Cod_Tipo_Producto) values (25,'Producto V', 'Descripcion_Producto V', 53.60, to_date('29-MAY-15','DD-MON-RR'), to_date('26-MAY-16','DD-MON-RR'), 5);

COMMIT;

REM INSERTING into FARMACEUTICA.TBL_TECNICO_FARMACEUTICO
SET DEFINE OFF;
Insert into FARMACEUTICA.TBL_TECNICO_FARMACEUTICO (Cod_Tec_Farmaceutico, Descripcion) values (1,'Descripcion_Tecnico I');
Insert into FARMACEUTICA.TBL_TECNICO_FARMACEUTICO (Cod_Tec_Farmaceutico, Descripcion) values (2,'Descripcion_Tecnico II');
Insert into FARMACEUTICA.TBL_TECNICO_FARMACEUTICO (Cod_Tec_Farmaceutico, Descripcion) values (3,'Descripcion_Tecnico III');
Insert into FARMACEUTICA.TBL_TECNICO_FARMACEUTICO (Cod_Tec_Farmaceutico, Descripcion) values (4,'Descripcion_Tecnico IV');

REM INSERTING into FARMACEUTICA.Tbl_Proveedor
SET DEFINE OFF;
Insert into FARMACEUTICA.Tbl_Proveedor (Cod_Proveedor, Nombre_Proveedor, Direccion_Proveedor, Numero_TelefonicoProveedor) values (1, 'Proveedor I', 'Direccion_Proveedor I', 88998899);
Insert into FARMACEUTICA.Tbl_Proveedor (Cod_Proveedor, Nombre_Proveedor, Direccion_Proveedor, Numero_TelefonicoProveedor) values (2, 'Proveedor II', 'Direccion_Proveedor II', 87798899);
Insert into FARMACEUTICA.Tbl_Proveedor (Cod_Proveedor, Nombre_Proveedor, Direccion_Proveedor, Numero_TelefonicoProveedor) values (3, 'Proveedor III', 'Direccion_Proveedor III', 85558899);
Insert into FARMACEUTICA.Tbl_Proveedor (Cod_Proveedor, Nombre_Proveedor, Direccion_Proveedor, Numero_TelefonicoProveedor) values (4, 'Proveedor IV', 'Direccion_Proveedor IV', 88000009);

REM INSERTING into FARMACEUTICA.TBL_CONVENIO
SET DEFINE OFF;
Insert into FARMACEUTICA.TBL_CONVENIO (Cod_Convenio, Nombre_Convenio, Descripcion_Convenio, Cod_Proveedor) values (1, 'Convenio I', 'Descripcion_Convenio I', 1);
Insert into FARMACEUTICA.TBL_CONVENIO (Cod_Convenio, Nombre_Convenio, Descripcion_Convenio, Cod_Proveedor) values (2, 'Convenio II', 'Descripcion_Convenio II', 2);
Insert into FARMACEUTICA.TBL_CONVENIO (Cod_Convenio, Nombre_Convenio, Descripcion_Convenio, Cod_Proveedor) values (3, 'Convenio III', 'Descripcion_Convenio III', 3);
Insert into FARMACEUTICA.TBL_CONVENIO (Cod_Convenio, Nombre_Convenio, Descripcion_Convenio, Cod_Proveedor) values (4, 'Convenio IV', 'Descripcion_Convenio IV', 4);

REM INSERTING into FARMACEUTICA.TBL_CLIENTE
SET DEFINE OFF;
Insert into FARMACEUTICA.TBL_CLIENTE (Cod_Cliente, Nombre_Cliente, Descripcion_Cliente , Cod_Convenio) values (1, 'Nombre_Cliente I', 'Descripcion_Cliente I', 1);
Insert into FARMACEUTICA.TBL_CLIENTE (Cod_Cliente, Nombre_Cliente, Descripcion_Cliente , Cod_Convenio) values (2, 'Nombre_Cliente II', 'Descripcion_Cliente II', 2);
Insert into FARMACEUTICA.TBL_CLIENTE (Cod_Cliente, Nombre_Cliente, Descripcion_Cliente , Cod_Convenio) values (3, 'Nombre_Cliente III', 'Descripcion_Cliente III', 3);
Insert into FARMACEUTICA.TBL_CLIENTE (Cod_Cliente, Nombre_Cliente, Descripcion_Cliente , Cod_Convenio) values (4, 'Nombre_Cliente IV', 'Descripcion_Cliente IV', 4);

REM INSERTING into FARMACEUTICA.Tbl_Almacen
SET DEFINE OFF;
Insert into FARMACEUTICA.Tbl_Almacen (Cod_Almacen, Nombre_Almacen, Existencia, Ingreso, Salida) values (1, 'Nombre_Almacen I', 123, 21, 31);
Insert into FARMACEUTICA.Tbl_Almacen (Cod_Almacen, Nombre_Almacen, Existencia, Ingreso, Salida) values (2, 'Nombre_Almacen II', 333, 61, 41);
Insert into FARMACEUTICA.Tbl_Almacen (Cod_Almacen, Nombre_Almacen, Existencia, Ingreso, Salida) values (3, 'Nombre_Almacen III', 223, 41, 51);
Insert into FARMACEUTICA.Tbl_Almacen (Cod_Almacen, Nombre_Almacen, Existencia, Ingreso, Salida) values (4, 'Nombre_Almacen IV', 523, 41, 61);

REM INSERTING into FARMACEUTICA.TBL_Productos_x_Almacen
SET DEFINE OFF;
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (1,4);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (2,3);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (3,2);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (4,1);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (5,4);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (6,3);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (7,2);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (8,1);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (9,4);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (10,3);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (11,2);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (12,1);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (13,4);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (14,3);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (15,2);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (16,1);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (17,4);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (18,3);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (19,2);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (20,1);
Insert into FARMACEUTICA.TBL_Productos_x_Almacen (COD_PRODUCTO, COD_ALMACEN) values (21,1);

COMMIT;

REM INSERTING into FARMACEUTICA.Tbl_Deducciones
SET DEFINE OFF;
Insert into FARMACEUTICA.Tbl_Deducciones (COD_DEDUCCIONES, NOMBRE_DEDUCCION) values (1, 'DEDUCCION I');
Insert into FARMACEUTICA.Tbl_Deducciones (COD_DEDUCCIONES, NOMBRE_DEDUCCION) values (2, 'DEDUCCION II');
Insert into FARMACEUTICA.Tbl_Deducciones (COD_DEDUCCIONES, NOMBRE_DEDUCCION) values (3, 'DEDUCCION III');
Insert into FARMACEUTICA.Tbl_Deducciones (COD_DEDUCCIONES, NOMBRE_DEDUCCION) values (4, 'DEDUCCION IV');

REM INSERTING into FARMACEUTICA.TBL_FACTURA
SET DEFINE OFF;
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, Cod_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (1, to_date('26-MAY-15','DD-MON-RR'), 'Descripcion_Factura I', 1, 1, 1, 1, 1);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (2, to_date('12-MAY-16','DD-MON-RR'), 'Descripcion_Factura II', 2, 2, 2, 2, 1);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (3, to_date('04-MAY-13','DD-MON-RR'), 'Descripcion_Factura III', 3, 3, 3, 3, 1);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (4, to_date('06-MAY-16','DD-MON-RR'), 'Descripcion_Factura IV', 4, 4, 4, 4, 2);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, Cod_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (5, to_date('26-MAY-15','DD-MON-RR'), 'Descripcion_Factura I', 1, 1, 2, 1, 2);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (6, to_date('22-MAY-16','DD-MON-RR'), 'Descripcion_Factura II', 2, 2, 2, 2, 3);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (7, to_date('14-MAY-13','DD-MON-RR'), 'Descripcion_Factura III', 3, 3, 2, 3, 3);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (8, to_date('26-MAY-16','DD-MON-RR'), 'Descripcion_Factura IV', 4, 4, 3, 4, 3);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, Cod_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (9, to_date('06-MAY-15','DD-MON-RR'), 'Descripcion_Factura I', 1, 1, 3, 1, 4);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (10, to_date('22-MAY-16','DD-MON-RR'), 'Descripcion_Factura II', 2, 2, 1, 2, 4);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (11, to_date('24-MAY-13','DD-MON-RR'), 'Descripcion_Factura III', 3, 3, 3, 3, 4);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (12, to_date('16-MAY-16','DD-MON-RR'), 'Descripcion_Factura IV', 4, 4, 4, 4, 4);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, Cod_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (13, to_date('26-MAY-15','DD-MON-RR'), 'Descripcion_Factura I', 1, 1, 1, 1, 4);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (14, to_date('02-MAY-16','DD-MON-RR'), 'Descripcion_Factura II', 2, 2, 1, 2, 4);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (15, to_date('04-MAY-13','DD-MON-RR'), 'Descripcion_Factura III', 3, 3, 1, 3, 1);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (16, to_date('06-MAY-16','DD-MON-RR'), 'Descripcion_Factura IV', 4, 4, 4, 4, 3);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, Cod_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (17, to_date('26-MAY-15','DD-MON-RR'), 'Descripcion_Factura XI', 1, 1, 1, 1, 5);
Insert into FARMACEUTICA.TBL_FACTURA (Cod_Factura, Fecha_Factura, Descripcion_Factura, Cod_Tec_Farmaceutico, Cod_Deducciones, COD_EMPLEADO, Cod_Cliente, COD_FARMACIA) values (18, to_date('12-MAY-16','DD-MON-RR'), 'Descripcion_Factura XII', 2, 2, 2, 2, 5);

REM INSERTING into FARMACEUTICA.Tbl_Producto_x_Factura
SET DEFINE OFF;
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (1, 1);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (2, 2);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (3, 3);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (4, 4);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (1, 1);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (1, 2);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (1, 3);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (2, 4);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (3, 1);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (2, 2);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (3, 3);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (2, 4);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (2, 1);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (1, 2);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (1, 3);
Insert into FARMACEUTICA.Tbl_Producto_x_Factura (COD_PRODUCTO, COD_FACTURA) values (4, 4);

REM INSERTING into FARMACEUTICA.Tbl_Producto_x_Farmacia
SET DEFINE OFF;
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (1, 1);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (2, 2);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (3, 3);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (4, 4);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (5, 1);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (6, 2);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (7, 3);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (8, 4);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (9, 1);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (10, 2);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (11, 3);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (12, 4);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (13, 1);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (14, 2);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (15, 3);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (16, 4);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (17, 1);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (18, 2);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (19, 3);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (20, 4);
Insert into FARMACEUTICA.Tbl_Producto_x_Farmacia (COD_PRODUCTO, COD_FARMACIA) values (21, 1);

COMMIT;

REM INSERTING into FARMACEUTICA.Tbl_Producto_x_Proveedor
SET DEFINE OFF;
Insert into FARMACEUTICA.Tbl_Producto_x_Proveedor (COD_PRODUCTO, COD_PROVEEDOR) values (1, 1);
Insert into FARMACEUTICA.Tbl_Producto_x_Proveedor (COD_PRODUCTO, COD_PROVEEDOR) values (2, 2);
Insert into FARMACEUTICA.Tbl_Producto_x_Proveedor (COD_PRODUCTO, COD_PROVEEDOR) values (3, 3);
Insert into FARMACEUTICA.Tbl_Producto_x_Proveedor (COD_PRODUCTO, COD_PROVEEDOR) values (4, 4);

COMMIT;