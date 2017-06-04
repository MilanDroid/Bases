--- CODIGO PRUEBAS
--- MOSTRANDO VALORES EN LA TABLAS
SELECT * FROM TBL_LIKE_PUBLICACIONES

SELECT * FROM TBL_USUARIOS

SELECT * FROM TBL_LIKE_FOTOGRAFIAS

SELECT * FROM TBL_GRUPOS_X_USUARIO
ORDER BY CODIGO_USUARIO

SELECT * FROM TBL_GRUPOS

SELECT * FROM TBL_ESTATUS_SOLICITUDES

SELECT * FROM TBL_AMIGOS
ORDER BY CODIGO_USUARIO

SELECT * FROM TBL_USUARIOS
ORDER BY FECHA_REGISTRO

--- Insertando un valor en la tabla TBL_LIKE_PUBLICACIONES para verificar la consulta anterior
Insert into FACEBOOK.TBL_LIKE_PUBLICACIONES (CODIGO_USUARIO,CODIGO_PUBLICACION,FECHA_LIKE_PUBLICACION) values (1,5,to_date('13-JUL-15','DD-MON-RR'));

--- Mostrando FOTOGRAFIAS POR USUARIO
SELECT * FROM TBL_FOTOS A 
LEFT JOIN TBL_LIKE_FOTOGRAFIAS B 
ON A.CODIGO_FOTO = B.CODIGO_FOTO 
WHERE B.CODIGO_USUARIO=2

SELECT * FROM TBL_LIKE_FOTOGRAFIAS A 
LEFT JOIN TBL_FOTOS B 
ON A.CODIGO_FOTO = B.CODIGO_FOTO 
WHERE A.CODIGO_USUARIO=2


--- CODIGO TAREA
--- Mostrando Likes por publicacion
SELECT A.CODIGO_PUBLICACION,
      SUM(
        CASE 
          WHEN B.CODIGO_USUARIO IS NOT NULL THEN 1
        ELSE
          0
        END
      ) AS CANTIDAD_LIKES
  FROM TBL_PUBLICACIONES  A
  INNER JOIN TBL_LIKE_PUBLICACIONES B
  ON (A.CODIGO_PUBLICACION = B.CODIGO_PUBLICACION)
GROUP BY A.CODIGO_PUBLICACION;

SELECT CODIGO_PUBLICACION, COUNT(1) AS CANTIDAD_LIKES
FROM TBL_LIKE_PUBLICACIONES
GROUP BY CODIGO_PUBLICACION;

--- Mostrando Likes por fotografia
SELECT A.CODIGO_FOTO,
      SUM(
        CASE 
          WHEN B.CODIGO_USUARIO IS NOT NULL THEN 1
        ELSE
          0
        END
      ) AS CANTIDAD_LIKES
  FROM TBL_FOTOS  A
  INNER JOIN TBL_LIKE_FOTOGRAFIAS B
  ON (A.CODIGO_FOTO = B.CODIGO_FOTO)
GROUP BY A.CODIGO_FOTO;

SELECT CODIGO_FOTO, COUNT(1) AS CANTIDAD_LIKES
FROM TBL_LIKE_FOTOGRAFIAS
GROUP BY CODIGO_FOTO;

--- Mostrando GRUPOS donde la cantidad de USUARIOS sea mayor que 5
SELECT CODIGO_GRUPO, COUNT(*) AS CANTIDAD_USUARIOS
  FROM TBL_GRUPOS_X_USUARIO
  GROUP BY CODIGO_GRUPO
HAVING COUNT(*)>5;

--- Mostrando la cantidad de peticiones de amistad PENDIENTES y RECHAZADAS
SELECT CODIGO_ESTATUS, COUNT(*) AS PETICIONES_ACEPT_Y_RECH
  FROM TBL_AMIGOS
  GROUP BY CODIGO_ESTATUS
  HAVING CODIGO_ESTATUS > 1;

SELECT NOMBRE_ESTATUS, COUNT (*) CANTIDAD SOLICITUDES

---Mostrando el USUARIO con mayor cantidad de AMIGOS
select codigo_usuario
  from (select a.codigo_usuario
          from tbl_amigos a
          join tbl_estatus_solicitudes e
            on e.codigo_estatus = a.codigo_estatus
           and e.nombre_estatus = 'CONFIRMADA'
         group by a.codigo_usuario
         order by count(*) desc)
 where rownum < 2;

SELECT CODIGO_USUARIO,CANTIDAD FROM
  (SELECT CODIGO_USUARIO, COUNT(*) CANTIDAD
    FROM TBL_AMIGOS 
    WHERE CODIGO_ESTATUS=1
    GROUP BY CODIGO_USUARIO
    ORDER BY COUNT(*) DESC)
    WHERE ROWNUM=1;

SELECT A.CODIGO_USUARIO, B.NOMBRE_USUARIO, CANTIDAD_CONFIRMADAS
FROM (
  SELECT CODIGO_USUARIO, COUNT(*) CANTIDAD_CONFIRMADAS
  FROM TBL_AMIGOS A 
  WHERE CODIGO_ESTATUS = 1
  GROUP BY CODIGO_USUARIO
  ORDER BY CANTIDAD_CONFIRMADAS DESC
  ) A
INNER JOIN  TBL_USUARIOS B
ON (A.CODIGO_USUARIO = B.CODIGO_USUARIO)
WHERE ROWNUM = 1;

---Mostrando el USUARIO con menor cantidad de AMIGOS

SELECT CODIGO_USUARIO
  FROM (SELECT A.CODIGO_USUARIO
          FROM TBL_AMIGOS A
          JOIN TBL_ESTATUS_SOLICITUDES E
            ON E.CODIGO_ESTATUS = A.CODIGO_ESTATUS
           AND E.NOMBRE_ESTATUS = 'CONFIRMADA'
         GROUP BY A.CODIGO_USUARIO
         ORDER BY COUNT(*) ASC)
 WHERE ROWNUM = 1;
 
SELECT A.CODIGO_USUARIO, B.NOMBRE_USUARIO, CANTIDAD_CONFIRMADAS
FROM (
  SELECT CODIGO_USUARIO, COUNT(*) CANTIDAD_CONFIRMADAS
  FROM TBL_AMIGOS A 
  WHERE CODIGO_ESTATUS = 2
  GROUP BY CODIGO_USUARIO
  ORDER BY CANTIDAD_CONFIRMADAS DESC
  ) A
INNER JOIN  TBL_USUARIOS B
ON (A.CODIGO_USUARIO = B.CODIGO_USUARIO)
WHERE ROWNUM = 1;

SELECT CODIGO_USUARIO,CANTIDAD FROM
  (SELECT CODIGO_USUARIO, COUNT(*) CANTIDAD
    FROM TBL_AMIGOS 
    WHERE CODIGO_ESTATUS=1
    GROUP BY CODIGO_USUARIO
    ORDER BY COUNT(*) ASC)
    WHERE ROWNUM=1;

--- Mostrando cantidad de REGISTROS por MES
SELECT TO_CHAR(FECHA_REGISTRO,'YYYY-MONTH'), COUNT(*) AS CANTIDAD_REGISTROS_MES
  FROM TBL_USUARIOS
GROUP BY (FECHA_REGISTRO,'YYYY-MONTH');

--- Mostrando edad promedio de los usuarios 
SELECT GENERO_USUARIO, ROUND(AVG(EDAD)) AS EDAD_PROMEDIO
  FROM TBL_USUARIOS
GROUP BY GENERO_USUARIO;

--- Mostrando crecimiento de registros con respecto al dia anterior del 19-agosto-2015
SELECT COUNT(*) AS CANTIDAD_ACCESOS_A 
FROM TBL_HISTORIAL_ACCESOS
WHERE FECHA_HORA_ACCESO = TO_DATE('20150818', 'YYYYMMDD');
