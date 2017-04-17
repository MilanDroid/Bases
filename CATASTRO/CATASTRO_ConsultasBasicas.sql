-- 1. Obtener el DNI de todos los propietarios de una casa en la zona
-- Centro.
-- Se obtiene de la tabla Vivienda el número y la calle de todas las
-- viviendas ubicadas en la zona 'Centro', mediante esta se puede
-- obtener la consulta sobre la tabla CasaParticular el dni de
-- todos los propietarios de una casa en la zona.

SELECT A.DNI_CP
FROM CASAPARTICULAR A
LEFT JOIN VIVIENDA B
ON (A.CALLE = B.CALLE)
WHERE B.NOMBRE_ZONA = 'CENTRO';

-- 2. Obtener todos los propietarios de un piso en el bloque de casas 
-- de la calle Damasco, número 20.

SELECT B.DNI, B.NOMBRE_PERSONA AS NOMBRE, B.APELLIDOS_PERSONA AS APPELLIDOS
FROM PISO A
LEFT JOIN PERSONA B
ON (A.DNI_P = B.DNI)
WHERE A.CALLE = 'Damasco' AND A.NUMERO = 20;

-- 3. Obtener todos los pisos de más de 50 metros cuadrados cuyo
-- propietario tiene el D.N.I 44351312.

SELECT CALLE, NUMERO, ESCALERA, PLANTA, PUERTA, METROS_P
FROM PISO
WHERE METROS_P > 50 AND DNI_P = 44351312;

-- 4. obtener todas las zonas de la ciudad en las que sólo existen 
-- casas particulares.

SELECT DISTINCT A.NOMBRE_ZONA
FROM
(
SELECT CALLE, CODIGO_POSTAL, METROS, NOMBRE_ZONA, NUMERO, OD_VIVIENDA, TIPO_VIVIENDA  
FROM VIVIENDA
WHERE TIPO_VIVIENDA = 'C'
) A
LEFT JOIN 
(
SELECT CALLE, CODIGO_POSTAL, METROS, NOMBRE_ZONA, NUMERO, OD_VIVIENDA, TIPO_VIVIENDA  
FROM VIVIENDA 
WHERE TIPO_VIVIENDA != 'C'
) B
ON (A.NOMBRE_ZONA = B.NOMBRE_ZONA)
WHERE B.NOMBRE_ZONA IS NULL;

-- 5. Obtener el tamaño medio de las casas particulares existentes en
-- la zona urbana 'SECTOR SUR'.

SELECT AVG (METROS) 
FROM VIVIENDA
WHERE NOMBRE_ZONA = 'SECTOR SUR' AND TIPO_VIVIENDA = 'C';

-- 6. Obtener información de los metros cuadrados de solar, agrupados
-- por zonas urbanas y ordenados por el número total de metros de
-- cada zona urbana.

SELECT SUM(METROS)AS METROS, NOMBRE_ZONA
FROM VIVIENDA
GROUP BY NOMBRE_ZONA
ORDER BY METROS;

-- 7. Obtener el número de domicilios particulares que existen en el
-- bloque Felipe II, número 14.

SELECT COUNT(*) AS TOTAL_DOMICILIOS_PARTICULARES
FROM PISO A
LEFT JOIN 
(
  SELECT *
  FROM CASAPARTICULAR
) B
ON (A.CALLE = B.CALLE)
WHERE A.CALLE = 'Felipe II' AND A.NUMERO = 14;