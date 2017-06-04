--1. Mostrar todos los usuarios que no han creado ningún tablero, para dichos usuarios mostrar el nombre completo y correo, utilizar producto cartesiano con el operador (+).
SELECT A.NOMBRE, A.CORREO
FROM TBL_USUARIOS A,
      TBL_TABLERO B
WHERE A.CODIGO_USUARIO = B.CODIGO_USUARIO_CREA(+)
AND  B.CODIGO_TABLERO IS NULL;


--2. Mostrar la cantidad de usuarios que se han registrado por cada red social, mostrar inclusive la cantidad de usuarios que no están registrados con redes sociales.
SELECT NVL(B.NOMBRE_RED_SOCIAL,'NINGUNA') AS RED_SOCIAL, 
      COUNT(CODIGO_USUARIO) CANTIDAD_USUARIOS
FROM TBL_USUARIOS A
FULL OUTER JOIN TBL_REDES_SOCIALES B
ON A.CODIGO_RED_SOCIAL = B.CODIGO_RED_SOCIAL
GROUP BY NVL(B.NOMBRE_RED_SOCIAL,'NINGUNA');

--3. Consultar el usuario que ha hecho más comentarios sobre una tarjeta (El más prepotente), para este usuario mostrar el nombre completo, correo, cantidad de comentarios y cantidad de tarjetas a las que ha comentado (pista: una posible solución para este último campo es utilizar count(distinct campo))

--nombre completo, correo, cantidad de comentarios y cantidad de tarjetas
--192
--253
SELECT CODIGO_USUARIO
FROM (
  SELECT CODIGO_USUARIO,CODIGO_TARJETA, COUNT(1) CANTIDAD_COMENTARIOS
  FROM TBL_COMENTARIOS
  GROUP BY CODIGO_USUARIO, CODIGO_TARJETA
  ORDER BY CANTIDAD_COMENTARIOS DESC
)
WHERE ROWNUM = 1;


SELECT A.NOMBRE||' '||A.APELLIDO AS NOMBRE_COMPLETO,
      A.CORREO, 
      COUNT(1) AS CANTIDAD_COMENTARIOS,
      COUNT(DISTINCT CODIGO_TARJETA) AS CANTIDAD_TARJETAS_COMENTADAS
FROM TBL_USUARIOS A
INNER JOIN TBL_COMENTARIOS B
ON (A.CODIGO_USUARIO = B.CODIGO_USUARIO)
WHERE A.CODIGO_USUARIO IN (
        SELECT CODIGO_USUARIO
        FROM (
          SELECT CODIGO_USUARIO,CODIGO_TARJETA, COUNT(1) CANTIDAD_COMENTARIOS
          FROM TBL_COMENTARIOS
          GROUP BY CODIGO_USUARIO, CODIGO_TARJETA
          ORDER BY CANTIDAD_COMENTARIOS DESC
        )
        WHERE ROWNUM = 1
) 
GROUP BY A.NOMBRE||' '||A.APELLIDO,
      A.CORREO;

/*4. Mostrar TODOS los usuarios con plan FREE, de dichos usuarios mostrar la siguiente información:
? Nombre completo
? Correo
? Red social (En caso de estar registrado con una)
? Cantidad de organizaciones que ha creado, mostrar 0 si no ha creado ninguna.
*/

SELECT *
FROM TBL_PLANES;

SELECT A.NOMBRE,A.APELLIDO, 
        nvl(B.NOMBRE_RED_SOCIAL,'Ninguna') as NOMBRE_RED_SOCIAL,
        nvl(C.CANTIDAD_ORGANIZACIONES,0) as CANTIDAD_ORGANIZACIONES
FROM TBL_USUARIOS A
LEFT JOIN TBL_REDES_SOCIALES B
ON (A.CODIGO_RED_SOCIAL = B.CODIGO_RED_SOCIAL)
LEFT JOIN (
    SELECT CODIGO_ADMINISTRADOR, COUNT(1) CANTIDAD_ORGANIZACIONES
    FROM TBL_ORGANIZACIONES
    GROUP BY CODIGO_ADMINISTRADOR
) C
ON (A.CODIGO_USUARIO= C.CODIGO_ADMINISTRADOR)
WHERE CODIGO_PLAN = 1;

SELECT CODIGO_ADMINISTRADOR, COUNT(1) CANTIDAD_ORGANIZACIONES
FROM TBL_ORGANIZACIONES
GROUP BY CODIGO_ADMINISTRADOR;

/*5. Mostrar los usuarios que han creado más de 5 tarjetas, para estos usuarios mostrar:
Nombre completo, correo, cantidad de tarjetas creadas*/
SELECT  A.NOMBRE||' '||A.APELLIDO AS NOMBRE_COMPLETO,
        A.CORREO,
        COUNT(1) CANTIDAD_TARJETAS
FROM TBL_USUARIOS A
INNER JOIN TBL_TARJETAS B
ON (A.CODIGO_USUARIO = B.CODIGO_USUARIO)
GROUP BY A.NOMBRE||' '||A.APELLIDO,
        A.CORREO
HAVING COUNT(1)>5;
/*
6. Un usuario puede estar suscrito a tableros, listas y tarjetas, de tal forma que si hay algún cambio se le notifica en su teléfono o por teléfono, sabiendo esto, se necesita mostrar los nombres de todos los usuarios con la cantidad de suscripciones de cada tipo, en la consulta se debe mostrar:
? Nombre completo del usuario
? Cantidad de tableros a los cuales está suscrito
? Cantidad de listas a las cuales está suscrito
? Cantidad de tarjetas a las cuales está suscrito
*/

SELECT A.NOMBRE|| ' '||A.APELLIDO AS NOMBRE_COMPLETO,
      COUNT(CODIGO_LISTA) AS CANTIDAD_LISTAS,
      COUNT(CODIGO_TABLERO) AS CANTIDAD_TABLEROS,
      COUNT(CODIGO_TARJETA) AS CANTIDAD_TARJETAS
FROM TBL_USUARIOS A
INNER JOIN TBL_SUSCRIPCIONES B
ON (A.CODIGO_USUARIO = B.CODIGO_USUARIO)
GROUP BY A.NOMBRE|| ' '||A.APELLIDO;


SELECT *
FROM TBL_SUSCRIPCIONES;

/*
7. Consultar todas las organizaciones con los siguientes datos:
? Nombre de la organización
? Cantidad de usuarios registrados en cada organización*/

SELECT CODIGO_ORGANIZACION, COUNT(*) CANTIDAD_USUARIOS
FROM TBL_USUARIOS_X_ORGANIZACION
GROUP BY CODIGO_ORGANIZACION;

/*
? Cantidad de Tableros por cada organización
*/
SELECT CODIGO_ORGANIZACION, COUNT( CODIGO_TABLERO) CANTIDAD_TABLEROS
FROM TBL_TABLERO
GROUP BY CODIGO_ORGANIZACION;
/*
? Cantidad de Listas asociadas a cada organización
*/
SELECT A.CODIGO_ORGANIZACION, COUNT(B.CODIGO_LISTA) AS CANTIDAD_LISTAS
FROM TBL_TABLERO A 
INNER JOIN TBL_LISTAS B
ON (A.CODIGO_TABLERO = B.CODIGO_TABLERO)
GROUP BY A.CODIGO_ORGANIZACION;

/*
? Cantidad de Tarjetas asociadas a cada organización
*/
SELECT A.CODIGO_ORGANIZACION, COUNT(C.CODIGO_TARJETA) AS CANTIDAD_TARJETAS
FROM TBL_TABLERO A 
LEFT JOIN TBL_LISTAS B
ON (A.CODIGO_TABLERO = B.CODIGO_TABLERO)
LEFT JOIN TBL_TARJETAS C
ON (B.CODIGO_LISTA = C.CODIGO_LISTA)
GROUP BY A.CODIGO_ORGANIZACION;



SELECT A.NOMBRE_ORGANIZACION,
      NVL(B.CANTIDAD_USUARIOS,0) AS CANTIDAD_USUARIOS,
      NVL(C.CANTIDAD_TABLEROS,0) AS CANTIDAD_TABLEROS,
      NVL(D.CANTIDAD_LISTAS,0) AS CANTIDAD_LISTAS,
      NVL(E.CANTIDAD_TARJETAS,0) AS CANTIDAD_TARJETAS
FROM TBL_ORGANIZACIONES A 
LEFT JOIN (
    SELECT CODIGO_ORGANIZACION, COUNT(*) CANTIDAD_USUARIOS
    FROM TBL_USUARIOS_X_ORGANIZACION
    GROUP BY CODIGO_ORGANIZACION
) B
ON (A.CODIGO_ORGANIZACION = B.CODIGO_ORGANIZACION)
LEFT JOIN (
    SELECT CODIGO_ORGANIZACION, COUNT( CODIGO_TABLERO) CANTIDAD_TABLEROS
    FROM TBL_TABLERO
    GROUP BY CODIGO_ORGANIZACION
) C
ON (A.CODIGO_ORGANIZACION = C.CODIGO_ORGANIZACION)
LEFT JOIN (
    SELECT A.CODIGO_ORGANIZACION, COUNT(B.CODIGO_LISTA) AS CANTIDAD_LISTAS
    FROM TBL_TABLERO A 
    INNER JOIN TBL_LISTAS B
    ON (A.CODIGO_TABLERO = B.CODIGO_TABLERO)
    GROUP BY A.CODIGO_ORGANIZACION
) D
ON (A.CODIGO_ORGANIZACION = D.CODIGO_ORGANIZACION)
LEFT JOIN (
    SELECT A.CODIGO_ORGANIZACION, COUNT(C.CODIGO_TARJETA) AS CANTIDAD_TARJETAS
    FROM TBL_TABLERO A 
    LEFT JOIN TBL_LISTAS B
    ON (A.CODIGO_TABLERO = B.CODIGO_TABLERO)
    LEFT JOIN TBL_TARJETAS C
    ON (B.CODIGO_LISTA = C.CODIGO_LISTA)
    GROUP BY A.CODIGO_ORGANIZACION
) E
ON (A.CODIGO_ORGANIZACION = E.CODIGO_ORGANIZACION)
ORDER BY CANTIDAD_TARJETAS DESC;


/*
8. Crear una vista materializada con la información de facturación, los campos a incluir son los siguientes:
? Código factura
? Nombre del plan a facturar
? Nombre completo del usuario
? Fecha de pago (Utilizar fecha inicio, mostrarla en formato Día-Mes-Año)
? Año y Mes de pago (basado en la fecha inicio)
? Monto de la factura
? Descuento
? Total neto
*/
CREATE MATERIALIZED VIEW MVW_FACTURAS_USUARIOS AS
SELECT A.NOMBRE||' '||A.APELLIDO AS NOMBRE_COMPLETO,
      B.CODIGO_FACTURA,
      TO_CHAR(B.FECHA_INICIO,'dd-mm-yyyy') FECHA_PAGO,
      TO_CHAR(B.FECHA_INICIO,'yyyy-MM') ANIO_MES_PAGO,
      C.NOMBRE_PLAN,
      ROUND(B.MONTO,2) AS MONTO,
      ROUND(B.DESCUENTO,2) AS DESCUENTO,
      ROUND(B.MONTO - B.DESCUENTO,2) AS TOTAL_NETO
FROM TBL_USUARIOS A
INNER JOIN TBL_FACTURACION_PAGOS B
ON (A.CODIGO_USUARIO = B.CODIGO_USUARIO)
INNER JOIN TBL_PLANES C 
ON (B.CODIGO_PLAN = C.CODIGO_PLAN);

SELECT * FROM MVW_FACTURAS_USUARIOS;
/*
9. Crear una tabla dinámica en excel que consulte la información de la vista materializada del inciso anterior, de dicha tabla dinámica crear un gráfico de línea que muestre en el eje X el campo Año/mes de pago y en el eje Y los nombres de los planes, el valor numérico a mostrar en la grafica deberá ser el Total neto.*/




/*Mostrar la cantidad de usuarios que han hecho más de 2 comentarios, mostrar la siguiente información:
? Nombre completo del usuario
? Correo
? Cantidad de comentario
? Fecha del último comentario
? Fecha del primer comentario
? Red social en caso de estar registrado con una*/

SELECT A.NOMBRE||' '||A.APELLIDO AS NOMBRE_COMPLETO,
      A.CORREO,
      COUNT(1) CANTIDAD_COMENTARIOS,
      MAX(B.FECHA_PUBLICACION) AS FECHA_ULTIMO_COMENTARIO,
      MIN(B.FECHA_PUBLICACION) AS FECHA_PRIMER_COMENTARIO
FROM TBL_USUARIOS A
INNER JOIN TBL_COMENTARIOS B
ON (A.CODIGO_USUARIO = B.CODIGO_USUARIO)
GROUP BY A.NOMBRE||' '||A.APELLIDO,
      A.CORREO
HAVING COUNT(1)>2;