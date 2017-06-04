-- 1.	El ministerio de cultura desea realizar un estudio sobre la distribución
-- del cine escocés en España. Obtener el nombre y cantidad de salas que han emitido 
-- películas escocesas en el periodo comprendido entre dos meses antes del 07 de 
-- diciembre del año 1996. ( puede usar la función Months_between)

select cine, sala 
from proyeccion 
where CIP in (select CIP from pelicula where nacionalidad = 'Escocia') and 
      FECHA_ESTRENO >= TO_DATE('07-10-1996','DD/MM/YYYY') and 
      FECHA_ESTRENO < TO_DATE('07-12-1996','DD/MM/YYYY');
      
CREATE OR REPLACE PROCEDURE SALAS
IS 
  cursor c_salas is (select cine, sala 
  from proyeccion 
  where CIP in (select CIP from pelicula where nacionalidad = 'Escocia') and 
        FECHA_ESTRENO >= TO_DATE('07-10-1996','DD/MM/YYYY') and 
        FECHA_ESTRENO < TO_DATE('07-12-1996','DD/MM/YYYY'));
        o_cine proyeccion.cine%type;
        o_sala proyeccion.sala%type;
BEGIN
  open c_salas;
  sys.dbms_output.put_line(CHR(13));
  sys.dbms_output.put_line(RPAD('CINE',15) || '|' || RPAD('SALA',10));
  sys.dbms_output.put_line(RPAD('-',30,'-'));
  loop
  fetch c_salas into o_cine, o_sala;
  exit when c_salas%notfound;
  sys.dbms_output.put_line(RPAD(o_cine,15) || '|' || RPAD(o_sala,10));
  end loop;
  close c_salas;
END;

SET SERVEROUTPUT ON;
execute SALAS;
-- 2.	Obtener todas las películas en las que el director ha trabajado también 
-- como actor (o sea que dirigió y actuó en la misma película).

SELECT C.TITULO_P, C.ANO_PRODUCCION, D.NOMBRE_PERSONA
FROM PELICULA C
RIGHT JOIN (SELECT A.CIP, A.NOMBRE_PERSONA, A.TAREA, B.TAREA AS TAREA_SECUNDARIA
FROM (
SELECT CIP, NOMBRE_PERSONA, TAREA 
FROM TRABAJO
WHERE TAREA = 'Director'
) A
LEFT JOIN (
SELECT CIP, NOMBRE_PERSONA, TAREA 
FROM TRABAJO
WHERE TAREA != 'Director' AND TAREA != 'Productor'
) B
ON(A.CIP = B.CIP)
WHERE A.NOMBRE_PERSONA = B.NOMBRE_PERSONA)D
ON(C.CIP = D.CIP);

CREATE OR REPLACE PROCEDURE DIRECTORES
IS 
  CURSOR C_DIRECTORES IS (
                      SELECT C.TITULO_P, C.ANO_PRODUCCION, D.NOMBRE_PERSONA
                      FROM PELICULA C
                      RIGHT JOIN (SELECT A.CIP, A.NOMBRE_PERSONA, A.TAREA, B.TAREA AS TAREA_SECUNDARIA
                      FROM (
                      SELECT CIP, NOMBRE_PERSONA, TAREA 
                      FROM TRABAJO
                      WHERE TAREA = 'Director'
                      ) A
                      LEFT JOIN (
                      SELECT CIP, NOMBRE_PERSONA, TAREA 
                      FROM TRABAJO
                      WHERE TAREA != 'Director' AND TAREA != 'Productor'
                      ) B
                      ON(A.CIP = B.CIP)
                      WHERE A.NOMBRE_PERSONA = B.NOMBRE_PERSONA)D
                      ON(C.CIP = D.CIP));
       O_TITULO_P PELICULA.TITULO_P%TYPE;
       O_ANO_PRODUCCION PELICULA.ANO_PRODUCCION%TYPE;
       O_NOMBRE_PERSONA TRABAJO.NOMBRE_PERSONA%TYPE;
BEGIN
  OPEN C_DIRECTORES;
  sys.dbms_output.put_line(CHR(13));
  sys.dbms_output.put_line(RPAD('TITULO PELICULA',35) || '|' || RPAD('ANIO PRODUCCION',4) || '|' || RPAD('NOMBRE PERSONA',25));
  sys.dbms_output.put_line(RPAD('-',100,'-'));
  loop
  fetch C_DIRECTORES into O_TITULO_P, O_ANO_PRODUCCION, O_NOMBRE_PERSONA;
  exit when C_DIRECTORES%notfound;
  sys.dbms_output.put_line(RPAD(O_TITULO_P,35) || '|' || RPAD(O_ANO_PRODUCCION,4) || '|' || RPAD(O_NOMBRE_PERSONA,25));
  end loop;
  close C_DIRECTORES;
END;

SET SERVEROUTPUT ON;
execute DIRECTORES;
-- 3.	Obtener todos los premios de obtenidos por películas dirigidas por Isaac Hayes en los oscars.

SELECT C.TITULO_P, B.FESTIVAL, B.CERTAMEN, B.PREMIO
FROM (
SELECT CIP, NOMBRE_PERSONA
FROM TRABAJO
WHERE TAREA = 'Director' AND NOMBRE_PERSONA = 'Isaac Hayes'
)A
LEFT JOIN (
SELECT FESTIVAL, CERTAMEN, PREMIO, CIP
FROM OTORGO
WHERE FESTIVAL = 'Oscars'
)B
ON (A.CIP = B.CIP)
LEFT JOIN (
SELECT CIP, TITULO_P
FROM PELICULA
)C
ON(A.CIP = C.CIP);

CREATE OR REPLACE PROCEDURE PREMIOS
IS 
  CURSOR C_PREMIOS IS (                       
                          SELECT C.TITULO_P, B.FESTIVAL, B.CERTAMEN, B.PREMIO
                          FROM (
                          SELECT CIP, NOMBRE_PERSONA
                          FROM TRABAJO
                          WHERE TAREA = 'Director' AND NOMBRE_PERSONA = 'Isaac Hayes'
                          )A
                          LEFT JOIN (
                          SELECT FESTIVAL, CERTAMEN, PREMIO, CIP
                          FROM OTORGO
                          WHERE FESTIVAL = 'Oscars'
                          )B
                          ON (A.CIP = B.CIP)
                          LEFT JOIN (
                          SELECT CIP, TITULO_P
                          FROM PELICULA
                          )C
                          ON(A.CIP = C.CIP));
       O_TITULO_P PELICULA.TITULO_P%TYPE;
       O_FESTIVAL OTORGO.FESTIVAL%TYPE;
       O_CERTAMEN OTORGO.CERTAMEN%TYPE;
       O_PREMIO OTORGO.PREMIO%TYPE;
BEGIN
  OPEN C_PREMIOS;
  sys.dbms_output.put_line(CHR(13));
  sys.dbms_output.put_line(RPAD('TITULO PELICULA',35) || '|' || RPAD('FESTIVAL',20) || '|' || RPAD('CERTAMEN',20) || '|' || RPAD('PREMIO',20));
  sys.dbms_output.put_line(RPAD('-',120,'-'));
  loop
  fetch C_PREMIOS into O_TITULO_P, O_FESTIVAL, O_CERTAMEN, O_PREMIO;
  exit when C_PREMIOS%notfound;
  sys.dbms_output.put_line(RPAD(O_TITULO_P,35) || '|' || RPAD(O_FESTIVAL,20) || '|' || RPAD(O_CERTAMEN,20) || '|' || RPAD(O_PREMIO,20) );
  end loop;
  close C_PREMIOS;
END;

SET SERVEROUTPUT ON;
execute PREMIOS;

-- 4.	Obtener los actores principales que han obtenido ´Oscars´ y cuantos cada uno.

SELECT NOMBRE_PERSONA, COUNT(NOMBRE_PERSONA)
FROM RECONOCIMIENTO
WHERE FESTIVAL = 'Oscars' AND PREMIO = 'Mejor actor'
GROUP BY NOMBRE_PERSONA;

CREATE OR REPLACE PROCEDURE ACTORES
IS 
  CURSOR C_ACTORES IS (
                      SELECT NOMBRE_PERSONA, COUNT(NOMBRE_PERSONA) AS NUMERO
                      FROM RECONOCIMIENTO
                      WHERE FESTIVAL = 'Oscars' AND PREMIO = 'Mejor actor'
                      GROUP BY NOMBRE_PERSONA
                      );
       O_NOMBRE_PERSONA RECONOCIMIENTO.NOMBRE_PERSONA%TYPE;
       O_NUMERO INT;
BEGIN
  OPEN C_ACTORES;
  sys.dbms_output.put_line(CHR(13));
  sys.dbms_output.put_line(RPAD('NOMBRE PERSONA',25) || '|' || RPAD('NUMERO DE OSCARS GANADOS',3));
  sys.dbms_output.put_line(RPAD('-',35,'-'));
  loop
  fetch C_ACTORES into O_NOMBRE_PERSONA, O_NUMERO;
  exit when C_ACTORES%notfound;
  sys.dbms_output.put_line(RPAD(O_NOMBRE_PERSONA,25) || '|' || RPAD(O_NUMERO,3));
  end loop;
  close C_ACTORES;
END;

SET SERVEROUTPUT ON;
execute ACTORES;

-- 5.	Existe una película en la base de datos con un presupuesto superior al 
-- de ´Viaje al centro de la tierra´ y producida en 1995, cual es.

SELECT TITULO_P, ANO_PRODUCCION
FROM PELICULA
WHERE PRESUPUESTO > (
SELECT PRESUPUESTO
FROM PELICULA
WHERE TITULO_P = 'Viaje al centro de la tierra');

CREATE OR REPLACE PROCEDURE PELICULAS
IS 
  CURSOR C_PELICULAS IS (
                      SELECT TITULO_P, ANO_PRODUCCION
                      FROM PELICULA
                      WHERE PRESUPUESTO > (
                      SELECT PRESUPUESTO
                      FROM PELICULA
                      WHERE TITULO_P = 'Viaje al centro de la tierra')
                      );
       O_TITULO_P PELICULA.TITULO_P%TYPE;
       O_ANO_PRODUCCION PELICULA.ANO_PRODUCCION%TYPE;
BEGIN
  OPEN C_PELICULAS;
  sys.dbms_output.put_line(CHR(13));
  sys.dbms_output.put_line(RPAD('TITULO DE LA PELICULA',35) || '|' || RPAD('ANIO DE PRODUCCION',4));
  sys.dbms_output.put_line(RPAD('-',40,'-'));
  loop
  fetch C_PELICULAS into O_TITULO_P, O_ANO_PRODUCCION;
  exit when C_PELICULAS%notfound;
  sys.dbms_output.put_line(RPAD(O_TITULO_P,35) || '|' || RPAD(O_ANO_PRODUCCION,4));
  end loop;
  close C_PELICULAS;
END;

SET SERVEROUTPUT ON;
execute PELICULAS;

-- Procedimiento Informe_Personas con manejo de excepciones 5%
-- Realizar un procedimiento PL/SQL que muestre en pantalla (DBMS) el 
-- historial de una persona, cuyo nombre será introducido como argumento 
-- del procedimiento. En la salida por pantalla se deberá indicar en todo lo 
-- que ha participado dicha persona, así como los premios que ha obtenido.
-- Este procedimiento se hace con 2 métodos del paquete DBMS_OUTPUT que permiten 
-- realizar operaciones de Entrada/Salida por pantalla desde un procedimiento. Los 
-- dos procedimientos de este paquete usados son:
-- •	enable (numero), el cual permite establecer el tamaño del buffer.
-- •	PUT_LINE (number\date\string)el cual permite mostrar el argumento por pantalla.
-- La información a visualizar será obtenida mediante dos cursores basados en consultas 
-- las cuales serán recorridos con dos ciclos FOR

/* 
Elaborar un procedimiento que muestre en el SGBD el 
historial de una persona, cuyo nombre ser? introducido como 
argumento del procedimiento. En la salida del SGBD y se deber? 
indicar en todo lo que ha participado dicha persona, as? como los 
premios que ha obtenido.

Este procedimiento se fundamenta en dos m?todos del paquete 
DBMS_OUTPUT Los dos procedimientos de este paquete 
usados son:
  1. ENABLE(numero), el cual permite establecer el tama?o 
     del buffer.
  2 PUT_LINE(number|date|string) el cual permite mostrar el 
    argumento en pantalla.
La informaci?n a visualizar ser? obtenida mediante dos cursores 
basados en consultas los cuales ser?n recorridos con dos ciclos
FOR.
*/

CREATE OR REPLACE PROCEDURE Informe_Persona
-- Se definen las variables de la interfaz del procedimiento 
  (p_nombre_persona VARCHAR2)
AS
/*  Cursor que permite obtener en qu? pel?culas ha trabajo una 
persona */
  CURSOR participaciones_peliculas IS 
    SELECT P.titulo_p,T.tarea,P.ano_produccion
      FROM Pelicula P, Trabajo T
      WHERE P.CIP = T.CIP
      ORDER BY P.TITULO_P;

/* Cursor que permite obtener los premios alcanzados en cada 
festival */
  CURSOR premios_obtenidos  IS 
    SELECT P_P.nombre_persona, premio, Festival, Certamen,
           P_P.titulo_p
      FROM Reconocimiento, 
           (SELECT T.nombre_persona,P.titulo_p, 
                   P.ano_produccion fecha
             FROM Trabajo T, Pelicula P
             WHERE T.cip=P.cip) P_P
      WHERE P_P.nombre_persona=RECONOCIMIENTO.nombre_persona
            AND Reconocimiento.nombre_persona=P_P.nombre_persona
            AND Certamen=P_P.fecha;

BEGIN
-- Procedimiento que me modifica el tama?o del buffer 
  DBMS_OUTPUT.ENABLE(100000);
-- Se imprime la cabecera de la salida 
  DBMS_OUTPUT.PUT_LINE('----------------------------------');
  DBMS_OUTPUT.PUT_LINE('----------------------------------');
  DBMS_OUTPUT.PUT_LINE('Trabajos:   '||p_nombre_persona);
  DBMS_OUTPUT.PUT_LINE('----------------------------------');
  DBMS_OUTPUT.PUT_LINE('----------------------------------');
-- Recorrido del primer cursor
  FOR v1 IN participaciones_peliculas LOOP
    DBMS_OUTPUT.PUT_LINE(' ');
    dbms_output.put_line(RPAD('TITULO DE LA PELICULA',35) || '|' || RPAD('TAREA',15) || '|' || RPAD('ANIO DE PRODUCCION',4));
    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('completar:     ' || completar);
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('----------------------------------');
  DBMS_OUTPUT.PUT_LINE('----------------------------------');
  DBMS_OUTPUT.PUT_LINE('Premios:    ' || p_nombre_persona);
  DBMS_OUTPUT.PUT_LINE('----------------------------------');
  DBMS_OUTPUT.PUT_LINE('----------------------------------');
-- Recorrido del segundo cursor
  FOR v2 IN premios_obtenidos LOOP
    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('completar: ' || v2.festival);
    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('Completar: ' || v2.certamen);
    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('completar: ' || v2.titulo_p);
    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('completar:  ' || v2.premio);
    DBMS_OUTPUT.PUT_LINE(' ');
  END LOOP;
-- Se comprueban los posibles errores del proceso
EXCEPTION 
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;
/

SET serveroutput ON
  EXEC Informe_Persona ('Maribel Verd?');
SET serveroutput OFF;

BEGIN
  OPEN C_PELICULAS;
  sys.dbms_output.put_line(CHR(13));
  sys.dbms_output.put_line(RPAD('TITULO DE LA PELICULA',35) || '|' || RPAD('ANIO DE PRODUCCION',4));
  sys.dbms_output.put_line(RPAD('-',40,'-'));
  loop
  fetch C_PELICULAS into O_TITULO_P, O_ANO_PRODUCCION;
  exit when C_PELICULAS%notfound;
  sys.dbms_output.put_line(RPAD(O_TITULO_P,35) || '|' || RPAD(O_ANO_PRODUCCION,4));
  end loop;
  close C_PELICULAS;
END;