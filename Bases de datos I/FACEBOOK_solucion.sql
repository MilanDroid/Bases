--Consultar la cantidad de likes por publicaci�n.
SELECT A.CONTENIDO_PUBLICACION, COUNT(1) AS CANTIDAD
FROM TBL_PUBLICACIONES A
INNER JOIN TBL_LIKE_PUBLICACIONES B
ON (A.CODIGO_PUBLICACION = B.CODIGO_PUBLICACION)
GROUP BY A.CONTENIDO_PUBLICACION;

--2. Consultar la cantidad de likes por fotograf�a.
SELECT CODIGO_FOTO, COUNT(1) AS CANTIDAD
FROM TBL_LIKE_FOTOGRAFIAS
GROUP BY CODIGO_FOTO;

---3. Consultar los grupos en los cuales la cantidad de usuarios sea mayor que 5, mostrar el nombre del grupo y la cantidad de usuarios.
SELECT A.NOMBRE_GRUPO, COUNT(1) CANTIDAD_USUARIOS
FROM TBL_GRUPOS A
INNER JOIN TBL_GRUPOS_X_USUARIO B
ON A.CODIGO_GRUPO = B.CODIGO_GRUPO
GROUP BY A.NOMBRE_GRUPO
HAVING COUNT(1)>5;

---4. Mostrar la cantidad de amistades pendientes y rechazadas.
SELECT NOMBRE_ESTATUS, COUNT(*) AS CANTIDAD_SOLICITUDES
FROM TBL_AMIGOS A 
INNER JOIN TBL_ESTATUS_SOLICITUDES B
ON A.CODIGO_ESTATUS = B.CODIGO_ESTATUS
WHERE A.CODIGO_ESTATUS IN (2,3)
GROUP BY NOMBRE_ESTATUS;

---5. Mostrar el usuario con mayor cantidad de amigos confirmados (El m�s cool).
SELECT A.CODIGO_USUARIO, B.NOMBRE_USUARIO, CANTIDAD_CONFIRMADAS
FROM (
  SELECT CODIGO_USUARIO, COUNT(1) CANTIDAD_CONFIRMADAS
  FROM TBL_AMIGOS A
  WHERE CODIGO_ESTATUS = 1
  GROUP BY CODIGO_USUARIO 
  ORDER BY CANTIDAD_CONFIRMADAS DESC
)  A
INNER JOIN TBL_USUARIOS B
ON (A.CODIGO_USUARIO = B.CODIGO_USUARIO)
WHERE ROWNUM = 1;
---6. Mostrar el usuario con m�s solicitudes rechazadas (Forever alone).
SELECT A.CODIGO_USUARIO, B.NOMBRE_USUARIO, CANTIDAD_RECHAZADAS
FROM (
  SELECT CODIGO_USUARIO, COUNT(1) CANTIDAD_RECHAZADAS
  FROM TBL_AMIGOS A
  WHERE CODIGO_ESTATUS = 2
  GROUP BY CODIGO_USUARIO 
  ORDER BY CANTIDAD_RECHAZADAS DESC
)  A
INNER JOIN TBL_USUARIOS B
ON (A.CODIGO_USUARIO = B.CODIGO_USUARIO)
WHERE ROWNUM = 1;

---7. Mostrar la cantidad de usuarios registrados mensualmente.
SELECT TO_CHAR(FECHA_REGISTRO,'YYYY-MONTH') AS MES, COUNT(1) AS CANTIDAD
FROM TBL_USUARIOS
GROUP BY TO_CHAR(FECHA_REGISTRO,'YYYY-MONTH');

---8. Mostrar la edad promedio de los usuarios por g�nero.
SELECT GENERO_USUARIO, ROUND(AVG(EDAD)) AS EDAD_PROMEDIO
FROM TBL_USUARIOS
GROUP BY GENERO_USUARIO;

---9. Con respecto al historial de accesos se necesita saber el crecimiento de los accesos del d�a 19 de Agosto del 2015 con respecto al d�a anterior, la f�rmula para calcular dicho crecimiento se muestra a continuaci�n:
---((b-a)/a) * 100
---Donde:
---a = Cantidad de accesos del d�a anterior (18 de Agosto del 2015)
SELECT COUNT(1) CANTIDAD_ACCESOS_A
FROM TBL_HISTORIAL_ACCESOS
WHERE FECHA_HORA_ACCESO = TO_DATE('20150818','yyyymmdd');
---b = Cantidad de accesos del d�a actual (19 de Agosto del 2015)
SELECT COUNT(1) CANTIDAD_ACCESOS_B
FROM TBL_HISTORIAL_ACCESOS
WHERE FECHA_HORA_ACCESO = TO_DATE('20150819','yyyymmdd');
---Mostrar el resultado como un porcentaje (Concatenar %)
SELECT (((SELECT COUNT(1) CANTIDAD_ACCESOS_B
        FROM TBL_HISTORIAL_ACCESOS
        WHERE FECHA_HORA_ACCESO = TO_DATE('20150819','yyyymmdd'))-(SELECT COUNT(1) CANTIDAD_ACCESOS_A
        FROM TBL_HISTORIAL_ACCESOS
        WHERE FECHA_HORA_ACCESO = TO_DATE('20150818','yyyymmdd')))/(SELECT COUNT(1) CANTIDAD_ACCESOS_A
        FROM TBL_HISTORIAL_ACCESOS
        WHERE FECHA_HORA_ACCESO = TO_DATE('20150818','yyyymmdd'))) * 100
        || '%' AS CRECIMIENTO
FROM DUAL;

SELECT  ((CANTIDAD_ACCESOS_B-CANTIDAD_ACCESOS_A)/CANTIDAD_ACCESOS_A) * 100 ||'%' AS CRECIMIENTO
FROM    (
  SELECT COUNT(1) CANTIDAD_ACCESOS_A
  FROM TBL_HISTORIAL_ACCESOS
  WHERE FECHA_HORA_ACCESO = TO_DATE('20150818','yyyymmdd')
) A, 
(
  SELECT COUNT(1) CANTIDAD_ACCESOS_B
  FROM TBL_HISTORIAL_ACCESOS
  WHERE FECHA_HORA_ACCESO = TO_DATE('20150819','yyyymmdd')
) B;

---10. Crear una consulta que muestre lo siguiente:
--? Nombre del usuario.
--? Pa�s donde pertenece.
SELECT A.CODIGO_USUARIO, A.NOMBRE_USUARIO, 
      B.NOMBRE_PAIS
FROM TBL_USUARIOS A
INNER JOIN TBL_PAISES B
ON (A.CODIGO_PAIS = B.CODIGO_PAIS);

--? Cantidad de publicaciones que tiene.
SELECT CODIGO_USUARIO, COUNT(1) CANTIDAD_PUBLICACIONES
FROM TBL_PUBLICACIONES
GROUP BY CODIGO_USUARIO;
--? Cantidad de amigos confirmados.
SELECT CODIGO_USUARIO, COUNT(1) AS CANTIDAD_AMIGOS
FROM TBL_AMIGOS
WHERE CODIGO_ESTATUS = 1
GROUP BY CODIGO_USUARIO;
--? Cantidad de likes que ha dado.
--FOTOGRAFIAS
SELECT CODIGO_USUARIO, COUNT(1) CANTIDAD_LIKES_FOTOS
FROM TBL_LIKE_FOTOGRAFIAS
GROUP BY CODIGO_USUARIO;
--PUBLICACIONES
SELECT CODIGO_USUARIO, COUNT(1) CANTIDAD_LIKES_PUBLICACIONES
FROM TBL_LIKE_PUBLICACIONES
GROUP BY CODIGO_USUARIO;
--? Cantidad de fotos en las que ha sido etiquetado.
SELECT CODIGO_USUARIO, COUNT(1) CANTIDAD_ETIQUETAS_FOTOS
FROM TBL_ETIQUETA_FOTOGRAFIAS
GROUP BY CODIGO_USUARIO;
--? Cantidad de accesos en el historial.
SELECT CODIGO_USUARIO, COUNT(1) CANTIDAD_ACCESOS
FROM TBL_HISTORIAL_ACCESOS
GROUP BY CODIGO_USUARIO;
--Tip: utilice subconsultas.*/

DROP MATERIALIZED VIEW MVW_USUARIOS_FACEBOOK;
CREATE MATERIALIZED VIEW MVW_USUARIOS_FACEBOOK AS
SELECT A.CODIGO_USUARIO, A.NOMBRE_USUARIO, 
      B.NOMBRE_PAIS, 
      NVL(C.CANTIDAD_PUBLICACIONES,0) AS CANTIDAD_PUBLICACIONES,
      NVL(D.CANTIDAD_AMIGOS,0) AS CANTIDAD_AMIGOS,
      NVL(CANTIDAD_ETIQUETAS_FOTOS, 0)  AS CANTIDAD_ETIQUETAS_FOTOS,
      NVL(F.CANTIDAD_LIKES_FOTOS,0) AS CANTIDAD_LIKES_FOTOS, 
      NVL(G.CANTIDAD_LIKES_PUBLICACIONES,0) AS CANTIDAD_LIKES_PUBLICACIONES,
      NVL(F.CANTIDAD_LIKES_FOTOS,0) + 
      NVL(G.CANTIDAD_LIKES_PUBLICACIONES,0) AS CANTIDAD_LIKES_TOTAL,
      NVL(H.CANTIDAD_ACCESOS,0) CANTIDAD_ACCESOS
FROM TBL_USUARIOS A
INNER JOIN TBL_PAISES B
ON (A.CODIGO_PAIS = B.CODIGO_PAIS)
LEFT JOIN (
  SELECT CODIGO_USUARIO, COUNT(1) CANTIDAD_PUBLICACIONES
  FROM TBL_PUBLICACIONES
  GROUP BY CODIGO_USUARIO
) C
ON (A.CODIGO_USUARIO = C.CODIGO_USUARIO)
LEFT JOIN (
  SELECT CODIGO_USUARIO, COUNT(1) AS CANTIDAD_AMIGOS
  FROM TBL_AMIGOS
  WHERE CODIGO_ESTATUS = 1
  GROUP BY CODIGO_USUARIO
) D
ON (A.CODIGO_USUARIO = D.CODIGO_USUARIO)
LEFT JOIN (
  SELECT CODIGO_USUARIO, COUNT(1) CANTIDAD_ETIQUETAS_FOTOS
  FROM TBL_ETIQUETA_FOTOGRAFIAS
  GROUP BY CODIGO_USUARIO
) E
ON (A.CODIGO_USUARIO = E.CODIGO_USUARIO)
LEFT JOIN (
  SELECT CODIGO_USUARIO, COUNT(1) CANTIDAD_LIKES_FOTOS
  FROM TBL_LIKE_FOTOGRAFIAS
  GROUP BY CODIGO_USUARIO
) F
ON (A.CODIGO_USUARIO = F.CODIGO_USUARIO)
LEFT JOIN (
  SELECT CODIGO_USUARIO, COUNT(1) CANTIDAD_LIKES_PUBLICACIONES
  FROM TBL_LIKE_PUBLICACIONES
  GROUP BY CODIGO_USUARIO
) G
ON (A.CODIGO_USUARIO = G.CODIGO_USUARIO)
LEFT JOIN (
  SELECT CODIGO_USUARIO, COUNT(1) CANTIDAD_ACCESOS
  FROM TBL_HISTORIAL_ACCESOS
  GROUP BY CODIGO_USUARIO
) H
ON (A.CODIGO_USUARIO = H.CODIGO_USUARIO);


SELECT *
FROM MVW_USUARIOS_FACEBOOK;

BEGIN
  DBMS_MVIEW.REFRESH ('MVW_USUARIOS_FACEBOOK');
END;


SELECT *
FROM VW_USUARIOS_FACEBOOK;
--11. De la consulta anterior cree una vista materializada y util�cela desde una tabla din�mica en Excel para mostrar una gr�fica de l�nea que muestre la cantidad de amigos por cada usuario.