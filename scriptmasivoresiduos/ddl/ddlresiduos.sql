

DROP TABLE Traslado_EmpresaTransportista;
DROP TABLE Traslado;
DROP TABLE Destino;
DROP TABLE EmpresaTransportista;
DROP TABLE Residuo_Constituyente;
DROP TABLE Constituyente;
DROP TABLE Residuo;
DROP TABLE EmpresaProductora;

CREATE TABLE EmpresaProductora ( 
  nif_empresa VARCHAR2(12) NOT NULL, 
  nombre_empresa VARCHAR2(40) NOT NULL,  
  ciudad_empresa VARCHAR2(20),  
  actividad VARCHAR2(80),  
  od_empresa LONG,  
    CONSTRAINT  pk_emp  
      PRIMARY KEY (nif_empresa),  
    CONSTRAINT  ck_emp  
      CHECK (nif_empresa = UPPER(nif_empresa)) ); 
CREATE TABLE Residuo ( 
  nif_empresa VARCHAR2(12) NOT NULL, 
  cod_residuo NUMBER(6,3) NOT NULL, 
  toxicidad NUMBER(3),  
  cantidad_residuo NUMBER(6),  
  od_residuo LONG,  
    CONSTRAINT  pk_res  
      PRIMARY KEY (nif_empresa, cod_residuo),  
    CONSTRAINT  fk_res_emp  
      FOREIGN KEY (nif_empresa)  
      REFERENCES EmpresaProductora(nif_empresa) 
      ON DELETE CASCADE,  
    CONSTRAINT  ck_res  
      CHECK (cod_residuo > = 0),  
    CONSTRAINT  ck_ctd  
      CHECK (cantidad_residuo > 0) ); 
CREATE TABLE Constituyente ( 
  cod_constituyente NUMBER(3) NOT NULL, 
  nombre_constituyente VARCHAR2(20) NOT NULL,  
  od_constituyente LONG,  
    CONSTRAINT  pk_con  
      PRIMARY KEY (cod_constituyente),  
    CONSTRAINT  ck_cod  
      CHECK (cod_constituyente > = 0) ); 
CREATE TABLE Residuo_Constituyente ( 
  nif_empresa VARCHAR2(12) NOT NULL, 
  cod_residuo NUMBER(6,3) NOT NULL, 
  cod_constituyente NUMBER(3) NOT NULL, 
  cantidad NUMBER(3),  
    CONSTRAINT  pk_rec  
      PRIMARY KEY (nif_empresa, cod_residuo, cod_constituyente),  
    CONSTRAINT  fk_rec_res  
      FOREIGN KEY (nif_empresa,cod_residuo)  
      REFERENCES Residuo(nif_empresa, cod_residuo) 
      ON DELETE CASCADE,  
    CONSTRAINT  fk_rec_con  
      FOREIGN KEY (cod_constituyente)  
      REFERENCES Constituyente(cod_constituyente) 
      ON DELETE CASCADE,  
    CONSTRAINT  ck_cant  
      CHECK (cantidad > 0) ); 
CREATE TABLE EmpresaTransportista ( 
  nif_emptransporte VARCHAR2(12) NOT NULL, 
  nombre_emptransporte VARCHAR2(40) NOT NULL,  
  ciudad_emptransporte VARCHAR2(30),  
  od_emptransporte LONG,  
    CONSTRAINT  pk_tra  
      PRIMARY KEY (nif_emptransporte),  
    CONSTRAINT  ck_tra  
      CHECK (nif_emptransporte = UPPER(nif_emptransporte)) ); 
CREATE TABLE Destino ( 
  cod_destino VARCHAR2(12) NOT NULL, 
  nombre_destino VARCHAR2(20) NOT NULL,  
  ciudad_destino VARCHAR2(15),  
  od_destino LONG,  
    CONSTRAINT  pk_des  
      PRIMARY KEY (cod_destino),  
    CONSTRAINT  ck_des  
      CHECK (cod_destino = UPPER(cod_destino)) ); 
CREATE TABLE Traslado ( 
  nif_empresa VARCHAR2(12) NOT NULL, 
  cod_residuo NUMBER(6,3) NOT NULL, 
  fecha_envio DATE NOT NULL, 
  cod_destino VARCHAR2(12) NOT NULL,  
  envase VARCHAR2(10),  
  fecha_llegada DATE,  
  tratamiento VARCHAR2(120),  
  cantidad_traslado NUMBER(6),  
  od_traslado LONG,  
    CONSTRAINT  pk_trl  
      PRIMARY KEY (nif_empresa, cod_residuo, fecha_envio, 
                  cod_destino),  
    CONSTRAINT  fk_trl_res  
      FOREIGN KEY (nif_empresa, cod_residuo)  
      REFERENCES Residuo(nif_empresa, cod_residuo), 
    CONSTRAINT  fk_trl_des  
      FOREIGN KEY (cod_destino)  
      REFERENCES Destino(cod_destino), 
    CONSTRAINT  ck_fecha_llegada  
      CHECK (fecha_llegada > = fecha_envio),  
    CONSTRAINT  ck_can  
      CHECK (cantidad_traslado > 0) ); 
CREATE TABLE Traslado_EmpresaTransportista ( 
  nif_empresa VARCHAR2(12) NOT NULL, 
  cod_residuo NUMBER(6,3) NOT NULL, 
  fecha_envio DATE NOT NULL, 
  cod_destino VARCHAR2(12) NOT NULL,  
  nif_emptransporte VARCHAR2(12) NOT NULL,  
  tipo_transporte VARCHAR2(15),  
  kms NUMBER(4),  
  coste NUMBER(5),  
    CONSTRAINT  pk_tet  
      PRIMARY KEY (nif_empresa, cod_residuo, fecha_envio, 
                  cod_destino, nif_emptransporte),  
    CONSTRAINT  fk_tet_trl 
      FOREIGN KEY (nif_empresa, cod_residuo, fecha_envio, 
                  cod_destino)  
      REFERENCES Traslado(nif_empresa, cod_residuo, fecha_envio, 
                 cod_destino) 
      ON DELETE CASCADE,  
    CONSTRAINT  fk_tet_tra  
      FOREIGN KEY (nif_emptransporte)  
      REFERENCES EmpresaTransportista(nif_emptransporte), 
    CONSTRAINT  ck_cos  
      CHECK (coste > = 0),  
    CONSTRAINT  ck_kms  
      CHECK (kms > 0) ); 
