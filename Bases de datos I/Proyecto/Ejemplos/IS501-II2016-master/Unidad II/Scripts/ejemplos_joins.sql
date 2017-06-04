SELECT A.FIRST_NAME ||' '|| A.LAST_NAME AS NAME,
       A.EMAIL,
       A.JOB_ID,
       B.JOB_TITLE
FROM EMPLOYEES A
INNER JOIN JOBS B
ON (A.JOB_ID = B.JOB_ID);




SELECT * --113
FROM EMPLOYEES;
SELECT * --27
FROM DEPARTMENTS;


SELECT FIRST_NAME||' '||LAST_NAME AS NAME,
      DEPARTMENT_NAME
FROM EMPLOYEES A
INNER JOIN DEPARTMENTS B
ON (A.DEPARTMENT_ID = B.DEPARTMENT_ID);


SELECT FIRST_NAME||' '||LAST_NAME AS NAME,
      DEPARTMENT_NAME, B.LOCATION_ID, B.MANAGER_ID
FROM EMPLOYEES A
LEFT JOIN DEPARTMENTS B
ON (A.DEPARTMENT_ID = B.DEPARTMENT_ID)
WHERE B.DEPARTMENT_ID IS NULL;


SELECT FIRST_NAME||' '||LAST_NAME AS NAME,
      DEPARTMENT_NAME, B.LOCATION_ID, B.MANAGER_ID
FROM EMPLOYEES A
RIGHT JOIN DEPARTMENTS B
ON (A.DEPARTMENT_ID = B.DEPARTMENT_ID);


SELECT FIRST_NAME||' '||LAST_NAME AS NAME,
      DEPARTMENT_NAME, B.LOCATION_ID, B.MANAGER_ID
FROM EMPLOYEES A
RIGHT JOIN DEPARTMENTS B
ON (A.DEPARTMENT_ID = B.DEPARTMENT_ID)
WHERE A.DEPARTMENT_ID IS NULL;


SELECT FIRST_NAME||' '||LAST_NAME AS NAME,
      DEPARTMENT_NAME, B.LOCATION_ID, B.MANAGER_ID
FROM EMPLOYEES A
FULL OUTER JOIN DEPARTMENTS B
ON (A.DEPARTMENT_ID = B.DEPARTMENT_ID);


SELECT FIRST_NAME||' '||LAST_NAME AS NAME,
      DEPARTMENT_NAME, B.LOCATION_ID, B.MANAGER_ID
FROM EMPLOYEES A
FULL OUTER JOIN DEPARTMENTS B
ON (A.DEPARTMENT_ID = B.DEPARTMENT_ID)
WHERE A.DEPARTMENT_ID IS NULL OR B.DEPARTMENT_ID IS NULL;


SELECT A.FIRST_NAME  AS BOSS, B.FIRST_NAME
FROM EMPLOYEES A
LEFT JOIN EMPLOYEES B
ON (A.EMPLOYEE_ID = B.MANAGER_ID)
ORDER BY BOSS;

SELECT*
FROM EMPLOYEES
ORDER BY MANAGER_ID;
