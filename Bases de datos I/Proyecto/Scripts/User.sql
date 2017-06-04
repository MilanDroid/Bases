--Crear nuevo usuario(esquema) con el password "PASSWORD" 
CREATE USER FLIPBOARD
  IDENTIFIED BY "oracle"
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP;
--asignar cuota ilimitada al tablespace por defecto  
ALTER USER FLIPBOARD QUOTA UNLIMITED ON USERS;

--Asignar privilegios basicos
GRANT create session TO FLIPBOARD;
GRANT create table TO FLIPBOARD;
GRANT create view TO FLIPBOARD;
GRANT create any trigger TO FLIPBOARD;
GRANT create any procedure TO FLIPBOARD;
GRANT create sequence TO FLIPBOARD;
GRANT create synonym TO FLIPBOARD;