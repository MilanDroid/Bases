create or replace PROCEDURE P_AGREGAR_ACTUALIZAR_DIETA(VAR_ID_ANIMAL IN INT, VAR_NOMBRE_DIETA IN VARCHAR2, 
VAR_OD_DIETA IN VARCHAR2,VAR_OD_HISTORIAL_DIETA IN VARCHAR2, 
VAR_ALIMENTOS IN VARCHAR2, VAR_NUTRIENTES IN VARCHAR2) AS
BEGIN 

UPDATE TBL_ANIMALES
  SET  ID_DIETA = SEQ_DIETAS.NEXTVAL
  WHERE ID_ANIMAL = VAR_ID_ANIMAL;

INSERT INTO TBL_DIETAS(ID_DIETA, NOMBRE_DIETA, OD_DIETA, ALIMENTOS, NUTRIENTES)
VALUES(SEQ_DIETAS.CURRVAL, VAR_NOMBRE_DIETA, VAR_OD_DIETA, VAR_ALIMENTOS, VAR_NUTRIENTES);

INSERT INTO TBL_HISTORIAL_DIETA (ID_DIETA, ID_ANIMAL, FECHA_INICIO, FECHA_FINAL, OD_HISTORIAL_DIETA)
VALUES (SEQ_DIETAS.CURRVAL, VAR_ID_ANIMAL, SYSDATE, NULL, VAR_OD_HISTORIAL_DIETA);
END;

---------------------------------------------------------

create or replace PROCEDURE P_AGREGAR_ANIMAL(VAR_TIPO_ANIMAL IN VARCHAR2, 
VAR_OD_ANIMAL IN VARCHAR2, VAR_ID_DIETA IN INT) AS
BEGIN 
INSERT INTO TBL_ANIMALES (ID_ANIMAL, OD_ANIMAL, ID_DIETA, TIPO_ANIMAL) 
VALUES(SEQ_ANIMALES.NEXTVAL, VAR_OD_ANIMAL, VAR_ID_DIETA, VAR_TIPO_ANIMAL);
END;

---------------------------------------------------------

create or replace PROCEDURE P_AGREGAR_DIETAS(VAR_NOMBRE_DIETA IN VARCHAR2, 
VAR_OD_DIETA IN VARCHAR2, VAR_ALIMENTOS IN VARCHAR2, VAR_NUTRIENTES IN VARCHAR2) AS

BEGIN

INSERT INTO TBL_DIETAS(ID_DIETA, NOMBRE_DIETA, OD_DIETA, ALIMENTOS, NUTRIENTES)
VALUES(SEQ_DIETAS.NEXTVAL, VAR_NOMBRE_DIETA, VAR_OD_DIETA, VAR_ALIMENTOS, VAR_NUTRIENTES);

END;

--------------------------------------------------------

create or replace PROCEDURE P_AGREGAR_HIST_NEC(VAR_ID_ANIMAL IN INT, VAR_FECHA_INICIO IN DATE, 
VAR_FECHA_FINAL IN DATE,VAR_NUTRIENTES IN VARCHAR2) AS

BEGIN

INSERT INTO TBL_HISTORIAL_NECESIDADES(FECHA_INICIO, FECHA_FINAL, NUTRIENTES, ID_ANIMAL)
VALUES (SYSDATE, NULL, VAR_NUTRIENTES, VAR_ID_ANIMAL);

END;

-------------------------------------------------------

create or replace PROCEDURE P_AGREGAR_HISTORIAL_DIETAS(VAR_OD_HISTORIAL_DIETA IN VARCHAR2) AS

BEGIN

INSERT INTO TBL_HISTORIAL_DIETA (ID_DIETA, ID_ANIMAL, FECHA_INICIO, FECHA_FINAL, OD_HISTORIAL_DIETA)
VALUES (SEQ_DIETAS.CURRVAL, SEQ_ANIMALES.CURRVAL, SYSDATE, NULL, VAR_OD_HISTORIAL_DIETA);

END;

------------------------------------------------------

create or replace PROCEDURE P_AGREGAR_INFO(VAR_NOMBRE_DIETA IN VARCHAR2, 
VAR_OD_DIETA IN VARCHAR2, VAR_TIPO_ANIMAL IN VARCHAR2, 
VAR_OD_ANIMAL IN VARCHAR2, VAR_OD_HISTORIAL_DIETA IN VARCHAR2, 
VAR_ALIMENTOS IN VARCHAR2, VAR_NUTRIENTES IN VARCHAR2) 

IS

VAR_ID_DIETA INT := SEQ_DIETAS.NEXTVAL;
VAR_ID_ANIMAL INT := SEQ_ANIMALES.NEXTVAL;
BEGIN 

INSERT INTO TBL_ANIMALES (ID_ANIMAL, OD_ANIMAL, ID_DIETA, TIPO_ANIMAL) 
VALUES(VAR_ID_ANIMAL, VAR_OD_ANIMAL, VAR_ID_DIETA, VAR_TIPO_ANIMAL);

INSERT INTO TBL_DIETAS(ID_DIETA, NOMBRE_DIETA, OD_DIETA, ALIMENTOS, NUTRIENTES)
VALUES(VAR_ID_DIETA, VAR_NOMBRE_DIETA, VAR_OD_DIETA, VAR_ALIMENTOS, VAR_NUTRIENTES);

INSERT INTO TBL_HISTORIAL_DIETA (ID_DIETA, ID_ANIMAL, FECHA_INICIO, FECHA_FINAL, OD_HISTORIAL_DIETA)
VALUES (VAR_ID_DIETA, VAR_ID_ANIMAL, SYSDATE, NULL, VAR_OD_HISTORIAL_DIETA);
END;

------------------------------------------------------------------

--                               VISTAS

-----------------------------------------------------------------

CREATE OR REPLACE FORCE VIEW "GRANJASOL"."V_DIETAS" ("ID_ANIMAL", "NOMBRE_DIETA", "OD_DIETA", "ALIMENTOS", "NUTRIENTES") AS 
  SELECT A.ID_ANIMAL, B.NOMBRE_DIETA, B.OD_DIETA, B.ALIMENTOS, B.NUTRIENTES
FROM TBL_ANIMALES A
LEFT JOIN TBL_DIETAS B
ON (A.ID_DIETA = B.ID_DIETA);

-----------------------------------------------------------------

CREATE OR REPLACE FORCE VIEW "GRANJASOL"."V_INF_ANIMALES" ("ID_ANIMAL", "TIPO_ANIMAL", "OD_TIPO_ANIMAL", "OD_ANIMAL", "NOMBRE_DIETA", "OD_DIETA") AS 
  SELECT A.ID_ANIMAL, C.TIPO_ANIMAL, C.OD_TIPO_ANIMAL, A.OD_ANIMAL, B.NOMBRE_DIETA, B.OD_DIETA
FROM TBL_ANIMALES A
LEFT JOIN TBL_DIETAS B
ON (A.ID_DIETA = B.ID_DIETA)
LEFT JOIN TBL_TIPO_ANIMAL C
ON(A.TIPO_ANIMAL = C.TIPO_ANIMAL);

----------------------------------------------------------------

CREATE OR REPLACE FORCE VIEW "GRANJASOL"."V_NECESIDADES" ("ID_ANIMAL", "FECHA_INICIO", "FECHA_FINAL", "NUTRIENTES") AS 
  SELECT A.ID_ANIMAL, B.FECHA_INICIO, B.FECHA_FINAL, B.NUTRIENTES
FROM TBL_ANIMALES A
LEFT JOIN TBL_HISTORIAL_NECESIDADES B
ON (A.ID_ANIMAL = B.ID_ANIMAL);

---------------------------------------------------------------
----                       SECUENCIAS
--------------------------------------------------------------

CREATE SEQUENCE  SEQ_ANIMALES
INCREMENT BY 1 START WITH 100;

---------------------------------------------------------------

CREATE SEQUENCE  SEQ_DIETAS  
INCREMENT BY 1 START WITH 100;

---------------------------------------------------------------

CREATE SEQUENCE  SEQ_TIPO_ANIMAL  
INCREMENT BY 1 START WITH 100;
