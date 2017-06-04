SELECT A.NOMBRE, A.CORREO, 
NVL(B.CANTIDAD_TABLEROS_CREADOS, 0) AS CANTIDAD_TABLEROS_CREADOS, 
NVL(C.CANTIDAD_ORGANIZACIONES, 0) AS CANTIDAD_ORGANIZACIONES,
NVL(D.CANTIDAD_TARJETAS_CREADAS, 0) AS CANTIDAD_TARJETAS_CREADAS, 
NVL(E.CANTIDAD_ARCHIVOS, 0) AS CANTIDAD_ARCHIVOS_SUBIDOS
FROM TBL_USUARIOS A
LEFT JOIN (
    SELECT CODIGO_USUARIO_CREA, COUNT(*) AS CANTIDAD_TABLEROS_CREADOS
    FROM TBL_TABLERO
    GROUP BY CODIGO_USUARIO_CREA
) B
ON(A.CODIGO_USUARIO = B.CODIGO_USUARIO_CREA)
LEFT JOIN(
    SELECT CODIGO_USUARIO, COUNT(*) AS CANTIDAD_ORGANIZACIONES
    FROM TBL_USUARIOS_X_ORGANIZACION
    GROUP BY CODIGO_USUARIO
) C
ON(A.CODIGO_USUARIO = C.CODIGO_USUARIO)
LEFT JOIN (
    SELECT CODIGO_USUARIO, COUNT(*) AS CANTIDAD_TARJETAS_CREADAS
    FROM TBL_TARJETAS
    GROUP BY CODIGO_USUARIO
) D
ON(A.CODIGO_USUARIO = D.CODIGO_USUARIO)
LEFT JOIN (
    SELECT CODIGO_USUARIO, COUNT(*) AS CANTIDAD_ARCHIVOS
    FROM TBL_ARCHIVOS_ADJUNTOS
    GROUP BY CODIGO_USUARIO
) E
ON(A.CODIGO_USUARIO = E.CODIGO_USUARIO);

SELECT B.NOMBRE_PLAN, COUNT(A.CODIGO_PLAN) AS CANTIDAD_USUARIOS
FROM TBL_USUARIOS A
LEFT JOIN TBL_PLANES B
ON(A.CODIGO_PLAN = B.CODIGO_PLAN)
GROUP BY B.NOMBRE_PLAN, A.CODIGO_PLAN;

SELECT A.NOMBRE || ' ' || A.APELLIDO AS NOMBRE_COMPLETO, A.CORREO,
COUNT(1) AS CANTIDAD_COMENTARIOS,
C.NOMBRE_RED_SOCIAL,
MAX(B.FECHA_PUBLICACION) AS FECHA_ULTIMO_COMENTARIO,
MIN(B.FECHA_PUBLICACION) AS FECHA_PRIMER_COMENTARIO
FROM TBL_USUARIOS A
INNER JOIN TBL_COMENTARIOS B
ON(A.CODIGO_USUARIO = B.CODIGO_USUARIO)
LEFT JOIN TBL_REDES_SOCIALES C
ON(A.CODIGO_RED_SOCIAL = C.CODIGO_RED_SOCIAL)
GROUP BY A.NOMBRE, ' ', A.APELLIDO, A.CORREO, C.NOMBRE_RED_SOCIAL
HAVING COUNT(1) > 2;

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

