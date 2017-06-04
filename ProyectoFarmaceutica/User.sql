--Crear nuevo usuario(esquema) con el password "PASSWORD" 
CREATE USER FARMACEUTICA
  IDENTIFIED BY "bases"
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP;
--asignar cuota ilimitada al tablespace por defecto  
ALTER USER FARMACEUTICA QUOTA UNLIMITED ON USERS;

--Asignar privilegios basicos
GRANT create session TO FARMACEUTICA;
GRANT create table TO FARMACEUTICA;
GRANT create view TO FARMACEUTICA;
GRANT create any trigger TO FARMACEUTICA;
GRANT create any procedure TO FARMACEUTICA;
GRANT create sequence TO FARMACEUTICA;
GRANT create synonym TO FARMACEUTICA;

COMMIT;