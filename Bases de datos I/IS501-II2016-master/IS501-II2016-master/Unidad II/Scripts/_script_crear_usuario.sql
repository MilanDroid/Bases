--Crear nuevo usuario(esquema) con el password "PASSWORD" 
CREATE USER YOUTUBE
  IDENTIFIED BY "oracle"
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP;
--asignar cuota ilimitada al tablespace por defecto  
ALTER USER YOUTUBE QUOTA UNLIMITED ON USERS;

--Asignar privilegios basicos
GRANT create session TO YOUTUBE;
GRANT create table TO YOUTUBE;
GRANT create view TO YOUTUBE;
GRANT create any trigger TO YOUTUBE;
GRANT create any procedure TO YOUTUBE;
GRANT create sequence TO YOUTUBE;
GRANT create materialized view TO YOUTUBE;
GRANT select any table TO YOUTUBE;
GRANT create synonym TO YOUTUBE;





--En caso de que el usuario system se bloquee ejecutar lo siguiente:
--Desde la consola:


sqlplus sys as sysdba 
--ingresar el password

alter user system account unlock;
alter user system identified by "nuevo_password";


