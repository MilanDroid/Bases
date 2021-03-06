-- 1.Propietarios por zona
SELECT A.DNI_CP, B.NOMBRE_ZONA, B.NUMERO, B.CALLE
FROM CASAPARTICULAR A
LEFT JOIN VIVIENDA B
ON (A.CALLE = B.CALLE);

-- 2. Propietarios de bloques de piso por calle y numero
SELECT B.DNI, B.NOMBRE_PERSONA AS NOMBRE, B.APELLIDOS_PERSONA AS APPELLIDOS, A.CALLE, A.NUMERO
FROM PISO A
LEFT JOIN PERSONA B
ON (A.DNI_P = B.DNI);

-- 3. Pisos por tama�o y DNI del propietario
SELECT A.CALLE, A.NUMERO, A.ESCALERA, A.PLANTA, A.PUERTA, A.METROS_P AS METROS,
A.DNI_P AS DNI_PROPIETARIO, B.NOMBRE_PERSONA AS NOMBRE,
B.APELLIDOS_PERSONA AS APELLIDOS
FROM PISO A
LEFT JOIN PERSONA B
ON (A.DNI_P = B.DNI);

--4. Zonas con existencia �nica de vivienda tipo C
SELECT DISTINCT A.NOMBRE_ZONA AS ZONAS_SOLO_CASA_PARTICULARES
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

--5. Tama�o medio de casas por zona
SELECT AVG (METROS) AS TAMA�O_PROMEDIO, NOMBRE_ZONA
FROM VIVIENDA
GROUP BY NOMBRE_ZONA;

--6. Metros cuadrados de solar por zona urbana
SELECT SUM(METROS)AS METROS, NOMBRE_ZONA
FROM VIVIENDA
GROUP BY NOMBRE_ZONA
ORDER BY METROS;

--7. Domicilio por bloque y numero
SELECT COUNT(*) AS TOTAL_DOMICILIOS_PARTICULARES, A.NUMERO, A.CALLE
FROM PISO A
LEFT JOIN 
(
  SELECT *
  FROM CASAPARTICULAR
) B
ON (A.CALLE = B.CALLE)
GROUP BY A.NUMERO, A.CALLE;
