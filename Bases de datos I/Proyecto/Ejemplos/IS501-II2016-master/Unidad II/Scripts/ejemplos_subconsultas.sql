


--- MOSTRAR "TODOS" LOS VUELOS CON LA CANTIDAD DE PASAJEROS.
SELECT CODIGO_VUELO, 
      SUM(CANTIDAD_PASAJEROS)
FROM (
  SELECT A.CODIGO_VUELO,
      B.CODIGO_PASAJERO,
      CASE 
        WHEN CODIGO_PASAJERO IS NOT NULL THEN 1
      ELSE
        0
      END AS CANTIDAD_PASAJEROS
  FROM TBL_VUELOS  A
  LEFT JOIN TBL_FACTURA_DETALLE B
  ON (A.CODIGO_VUELO = B.CODIGO_VUELO)
)
GROUP BY CODIGO_VUELO;



  SELECT A.CODIGO_VUELO,
      SUM(
        CASE 
          WHEN B.CODIGO_PASAJERO IS NOT NULL THEN 1
        ELSE
          0
        END
      ) AS CANTIDAD_PASAJEROS
  FROM TBL_VUELOS  A
  LEFT JOIN TBL_FACTURA_DETALLE B
  ON (A.CODIGO_VUELO = B.CODIGO_VUELO)
GROUP BY A.CODIGO_VUELO;