---BLOQUE PLSQL APEX---
--Ubicar este codigo en el proceso PLSQL de apex llamado LOGIN

/*apex_authentication.login(
    p_username => :P101_USERNAME,
    p_password => :P101_PASSWORD );*/

DECLARE
    V_RESULT BOOLEAN:=FALSE;
BEGIN
	V_RESULT := HR.F_LOGIN(:P101_USER_NAME, :P101_PASSWORD);
	IF (V_RESULT = TRUE) THEN
		wwv_flow_custom_auth_std.post_login(
			P_UNAME=> :P101_USERNAME,
			P_PASSWORD=> :P101_PASSWORD,
			P_SESSION_ID=> v('APP_SESSION'),
			P_FLOW_PAGE=> :APP_ID||':1'
		);
	ELSE
		owa_util.redirect_url('f?p=?&APP_ID.:101:&SESSION.');
	END IF;
END;

---FIN BLOQUE PLSQL APEX---



---Crear una funcion que verifique si el usuario existe:

create or replace function f_login(p_usuario varchar2, p_contrasena varchar2)
return boolean as 
	--Variable para capturar la cantidad de registros de usuarios existentes
  V_CANTIDAD_REGISTROS INTEGER;
begin
	--Insertar en una tabla el historico de accesos
  INSERT INTO TBL_LOG_LOGIN(
      CODIGO_USUARIO,NOMBRE_USUARIO,
      CONTRASENA,FECHA_INTENTO
  ) VALUES (NULL ,p_usuario, p_contrasena, SYSDATE);
  COMMIT;
  
  --Verificar si en la tabla de usuarios existe un usuario con la informacion indicada.
  SELECT COUNT(*)
  INTO V_CANTIDAD_REGISTROS
  FROM TBL_USUARIOS
  WHERE NOMBRE_USUARIO = p_usuario
  AND CONTRASENA = p_contrasena;
  
  --En caso de haber registros significa que el usuario existe, por lo tanto se deja pasar
  IF (V_CANTIDAD_REGISTROS=1) THEN
    return true;
  ELSE
    return false;
  END IF;
end;

--Fin de la funcion

---Misma funcion mejorada:
create or replace function f_login(p_usuario varchar2, p_contrasena varchar2)
return boolean as 
  V_CANTIDAD_REGISTROS INTEGER;
  V_CODIGO_USUARIO INTEGER;
begin
  SELECT COUNT(*)
  INTO V_CANTIDAD_REGISTROS
  FROM TBL_USUARIOS
  WHERE NOMBRE_USUARIO = p_usuario
  AND CONTRASENA = p_contrasena;
  
  IF (V_CANTIDAD_REGISTROS=1) THEN
    SELECT CODIGO_USUARIO
    INTO V_CODIGO_USUARIO
    FROM TBL_USUARIOS
    WHERE NOMBRE_USUARIO = p_usuario
    AND CONTRASENA = p_contrasena;
    
    INSERT INTO TBL_LOG_LOGIN(
        CODIGO_USUARIO,NOMBRE_USUARIO,
        CONTRASENA,FECHA_INTENTO,
        INTENTO_EXITOSO
    ) VALUES (
        V_CODIGO_USUARIO ,p_usuario, 
        p_contrasena, SYSDATE,'S'
    );
    COMMIT;
    return true;
  ELSE
    INSERT INTO TBL_LOG_LOGIN(
        CODIGO_USUARIO,NOMBRE_USUARIO,
        CONTRASENA,FECHA_INTENTO,
        INTENTO_EXITOSO
    ) VALUES (
        NULL ,p_usuario, 
        p_contrasena, SYSDATE,'N'
    );
    return false;
  END IF;
end;