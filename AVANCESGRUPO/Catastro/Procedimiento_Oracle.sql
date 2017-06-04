CREATE OR REPLACE PROCEDURE CATASTRO.ins_vivienda

(p_nombre_zona VARCHAR2,
p_calle VARCHAR2,
p_numero NUMBER,
p_tipo_vivienda CHAR,
p_codigo_postal NUMBER,
p_metros NUMBER,
p_od_vivienda VARCHAR2,
p_dni NUMBER DEFAULT NULL
)
IS
BEGIN
/*Se inserta en la tabla Vivienda*/
	INSERT 
		INTO Vivienda (calle,numero,tipo_vivienda,codigo_postal,metros,od_vivienda,nombre_zona)
	VALUES (UPPER(p_calle),p_numero,p_tipo_vivienda,p_codigo_postal,p_metros,p_od_vivienda,UPPER(p_nombre_zona));
/*Se comprueba el tipo de vivienda*/
IF p_tipo_vivienda='B' THEN
/*Se inserta en Bloque de viviendas*/
	INSERT
		INTO BloqueCasas(calle, numero, metros_b, od_bloque)
		VALUES(UPPER(p_calle), p_numero, p_metros, p_od_vivienda);
ELSE
/*Se inserta en Casa Particular*/
	INSERT
		INTO CasaParticular (calle, numero, metros_c, od_casa, dni_cp)
	VALUES (UPPER(p_calle),p_numero,p_metros, p_od_vivienda, p_dni);
END IF;
COMMIT;
/*Se comprueban los posibles errores en el proceso*/
EXCEPTION

	WHEN OTHERS THEN
		IF INSTR(SQLERRM.'CHENCODD.FK_VIV_ZON')<> 0 THEN
			DBMS_OUTPUT.PUT_LINE('La zona de la vivienda introducida no existe');
		END IF;
		IF INSTR(SQLERRM.'CHENCODD.FK_VIV')<> 0 THEN
			DBMS_OUTPUT.PUT_LINE('Esta vivienda ya ha sido introducida en la Base de Datos');
		END IF;
		IF INSTR(SQLERRM.'CHENCODD.CK_TV')<> 0 THEN
			DBMS_OUTPUT.PUT_LINE('El tipo de vivienda' ||p_tipo_vivienda||' no existe');
		END IF;
		IF INSTR(SQLERRM.'CHENCODD.FK_CAS_PER')<> 0 THEN
			DBMS_OUTPUT.PUTLINE('La persona que se introduce como dueño no existe');
		END IF;
		ROLLBACK;
END;