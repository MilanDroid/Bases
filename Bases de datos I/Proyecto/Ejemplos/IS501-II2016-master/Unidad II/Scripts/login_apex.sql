---BLOQUE PLSQL APEX---
--Ubicar este codigo en el proceso PLSQL de apex llamado LOGIN

/*apex_authentication.login(
    p_username => :P101_USERNAME,
    p_password => :P101_PASSWORD );*/

DECLARE
    V_RESULT BOOLEAN:=FALSE;
    V_CODIGO_USUARIO INTEGER;
BEGIN
  V_RESULT := HR.F_LOGIN(:P101_USERNAME, :P101_PASSWORD, V_CODIGO_USUARIO);
  --ESTABLECER UNA VARIABLE DE SESION CON EL CODIGO DEL USUARIO:

  :USER_ID := V_CODIGO_USUARIO;
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




---Funcion de login
create or replace function f_login(p_usuario varchar2, p_contrasena varchar2,
 p_codigo_usuario out integer )
return boolean as 
  V_CANTIDAD_REGISTROS INTEGER;
begin
  SELECT COUNT(*)
  INTO V_CANTIDAD_REGISTROS
  FROM TBL_USUARIOS
  WHERE NOMBRE_USUARIO = p_usuario
  AND CONTRASENA = p_contrasena;
  
  IF (V_CANTIDAD_REGISTROS=1) THEN
    SELECT CODIGO_USUARIO
    INTO p_codigo_usuario
    FROM TBL_USUARIOS
    WHERE NOMBRE_USUARIO = p_usuario
    AND CONTRASENA = p_contrasena;
    
    INSERT INTO TBL_LOG_LOGIN(
        CODIGO_USUARIO,NOMBRE_USUARIO,
        CONTRASENA,FECHA_INTENTO,
        INTENTO_EXITOSO
    ) VALUES (
        p_codigo_usuario ,p_usuario, 
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