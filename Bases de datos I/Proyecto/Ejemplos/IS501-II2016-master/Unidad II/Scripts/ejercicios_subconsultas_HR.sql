select *
from employees
where lower(TRIM(first_name)) like lower('%'||TRIM(V_VARIABLE)||'%');


SELECT *
FROM (
  SELECT *
  FROM EMPLOYEES
  WHERE UPPER(FIRST_NAME) LIKE UPPER('%EN%')
)
WHERE LAST_NAME = 'Kings';

--No se puede utilizar un alias en un where, solo en un order by
--No se pueden incluir funciones de agrupacion dentro de un where
--En el caso de que se necesite evaluar un resultado de una funcion de agrupacion
-- no se debe utilizar where, se debe utilizar having, esta instruccion debera
-- ser incluida despues de la instraccion group by
-- having funcion_de_agrupacion operador valor
SELECT B.DEPARTMENT_NAME,  SUM(A.SALARY) TOTAL
FROM EMPLOYEES  A
LEFT JOIN DEPARTMENTS B
ON (A.DEPARTMENT_ID = B.DEPARTMENT_ID)
--WHERE DEPARTMENT_NAME = 'IT Support' -- Si se puede aplicar where pero a campos normales
GROUP BY B.DEPARTMENT_NAME
HAVING SUM(A.SALARY) < 20000
ORDER BY B.DEPARTMENT_NAME;

--Mismo resultado sin utilizar having pero utilizando subconsultas
SELECT *
FROM (
    SELECT B.DEPARTMENT_NAME,  SUM(A.SALARY) TOTAL
    FROM EMPLOYEES  A
    LEFT JOIN DEPARTMENTS B
    ON (A.DEPARTMENT_ID = B.DEPARTMENT_ID)
    GROUP BY B.DEPARTMENT_NAME
    ORDER BY B.DEPARTMENT_NAME
) 
WHERE TOTAL <20000;


--Mostrar para cada departamento el porcentaje correspondiente a salarios con respecto
--al salario total.
SELECT DEPARTMENT_NAME, ROUND((TOTAL_DEPARTAMENTO / 
      (
        SELECT SUM(SALARY)
        FROM EMPLOYEES
      )) * 100,2)||'%' TOTAL
FROM (
  SELECT B.DEPARTMENT_NAME,  
        SUM(A.SALARY) TOTAL_DEPARTAMENTO
  FROM EMPLOYEES  A
  LEFT JOIN DEPARTMENTS B
  ON (A.DEPARTMENT_ID = B.DEPARTMENT_ID)
  GROUP BY B.DEPARTMENT_NAME
  ORDER BY B.DEPARTMENT_NAME
);

--Identificar el departamento que gana mas y el que gana menos

---DEPARTAMENTO QUE GANA MAS:
SELECT ROWNUM, DEPARTMENT_NAME, TOTAL_DEPARTAMENTO
FROM (
    SELECT B.DEPARTMENT_NAME,  
          SUM(A.SALARY) TOTAL_DEPARTAMENTO
    FROM EMPLOYEES  A
    LEFT JOIN DEPARTMENTS B
    ON (A.DEPARTMENT_ID = B.DEPARTMENT_ID)
    GROUP BY B.DEPARTMENT_NAME
    ORDER BY TOTAL_DEPARTAMENTO DESC
)
WHERE ROWNUM = 1;


---DEPARTAMENTO QUE GANA MENOS:
SELECT ROWNUM, DEPARTMENT_NAME, TOTAL_DEPARTAMENTO
FROM (
    SELECT B.DEPARTMENT_NAME,  
          SUM(A.SALARY) TOTAL_DEPARTAMENTO
    FROM EMPLOYEES  A
    LEFT JOIN DEPARTMENTS B
    ON (A.DEPARTMENT_ID = B.DEPARTMENT_ID)
    GROUP BY B.DEPARTMENT_NAME
    ORDER BY TOTAL_DEPARTAMENTO ASC
)
WHERE ROWNUM = 1;


select rownum, first_name 
from (
  select first_name
  from employees
  order by first_name desc
)
where rownum = 1;




SELECT SUM(SALARY)
FROM EMPLOYEES;

SELECT *
FROM DEPARTMENTS;

SELECT 8300 +12008 --20308
FROM DUAL;

