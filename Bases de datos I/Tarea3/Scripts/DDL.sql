-- Generated by Oracle SQL Developer Data Modeler 4.1.0.866
--   at:        2015-08-05 23:21:45 CST
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g




CREATE TABLE FACEBOOK.TBL_ALBUMES
  (
    CODIGO_ALBUM      INTEGER NOT NULL ,
    CODIGO_USUARIO    INTEGER NOT NULL ,
    DESCRIPCION_ALBUM VARCHAR2 (500) NOT NULL ,
    UBICACION_ALBUM   VARCHAR2 (100) NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_ALBUMES ADD CONSTRAINT TBL_ALBUM_FOTOS_PK PRIMARY KEY ( CODIGO_ALBUM ) ;

CREATE TABLE FACEBOOK.TBL_AMIGOS
  (
    CODIGO_USUARIO       INTEGER NOT NULL ,
    CODIGO_AMIGO         INTEGER NOT NULL ,
    CODIGO_ESTATUS       INTEGER NOT NULL ,
    CODIGO_TIPO_RELACION INTEGER NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_AMIGOS ADD CONSTRAINT TBL_AMIGO_PK PRIMARY KEY ( CODIGO_USUARIO, CODIGO_AMIGO ) ;

CREATE TABLE FACEBOOK.TBL_COMENTARIOS
  (
    CODIGO_COMENTARIO          INTEGER NOT NULL ,
    CODIGO_PUBLICACION         INTEGER NOT NULL ,
    CODIGO_USUARIO             INTEGER NOT NULL ,
    CODIGO_COMENTARIO_SUPERIOR INTEGER ,
    CONTENIDO_COMENTARIO       VARCHAR2 (500) NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_COMENTARIOS ADD CONSTRAINT TBL_COMENTARIO_PK PRIMARY KEY ( CODIGO_COMENTARIO ) ;

CREATE TABLE FACEBOOK.TBL_ESTATUS_SOLICITUDES
  (
    CODIGO_ESTATUS INTEGER NOT NULL ,
    NOMBRE_ESTATUS VARCHAR2 (200) NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_ESTATUS_SOLICITUDES ADD CONSTRAINT TBL_ESTATUS_SOLICITUDES_PK PRIMARY KEY ( CODIGO_ESTATUS ) ;

CREATE TABLE FACEBOOK.TBL_ETIQUETA_ALBUMES
  (
    CODIGO_ALBUM   INTEGER NOT NULL ,
    CODIGO_USUARIO INTEGER NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_ETIQUETA_ALBUMES ADD CONSTRAINT TBL_ETIQUETA_ALBUM_PK PRIMARY KEY ( CODIGO_ALBUM, CODIGO_USUARIO ) ;

CREATE TABLE FACEBOOK.TBL_ETIQUETA_FOTOGRAFIAS
  (
    CODIGO_FOTO            INTEGER NOT NULL ,
    CODIGO_USUARIO         INTEGER NOT NULL ,
    USUARIO_QUIEN_ETIQUETO INTEGER NOT NULL ,
    COORDENADA_X_FOTO      INTEGER NOT NULL ,
    COORDENADA_Y_FOTO      INTEGER NOT NULL ,
    DIMENSION_CUADRADO     INTEGER NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_ETIQUETA_FOTOGRAFIAS ADD CONSTRAINT TBL_ETIQUETA_PK PRIMARY KEY ( CODIGO_FOTO, CODIGO_USUARIO ) ;

CREATE TABLE FACEBOOK.TBL_FOTOS
  (
    CODIGO_FOTO      INTEGER NOT NULL ,
    CODIGO_ALBUM     INTEGER NOT NULL ,
    FECHA_SUBIDA     DATE ,
    DESCRIPCION_FOTO VARCHAR2 (500) NOT NULL ,
    UBICACION_FOTO   VARCHAR2 (100) NOT NULL ,
    FOTO BLOB
  ) ;
ALTER TABLE FACEBOOK.TBL_FOTOS ADD CONSTRAINT TBL_FOTOS_PK PRIMARY KEY ( CODIGO_FOTO ) ;

CREATE TABLE FACEBOOK.TBL_GRUPOS
  (
    CODIGO_GRUPO      INTEGER NOT NULL ,
    NOMBRE_GRUPO      VARCHAR2 (250) NOT NULL ,
    DESCRIPCION_GRUPO VARCHAR2 (500) NOT NULL ,
    FECHA_CREACION    DATE NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_GRUPOS ADD CONSTRAINT TBL_GRUPO_PK PRIMARY KEY ( CODIGO_GRUPO ) ;

CREATE TABLE FACEBOOK.TBL_GRUPOS_X_USUARIO
  (
    CODIGO_USUARIO INTEGER NOT NULL ,
    CODIGO_GRUPO   INTEGER NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_GRUPOS_X_USUARIO ADD CONSTRAINT TBL_GRUPO_X_USUARIO_PK PRIMARY KEY ( CODIGO_GRUPO, CODIGO_USUARIO ) ;

CREATE TABLE TBL_HISTORIAL_ACCESOS
  (
    CODIGO_USUARIO    INTEGER NOT NULL ,
    FECHA_HORA_ACCESO DATE NOT NULL
  ) ;

CREATE TABLE FACEBOOK.TBL_LIKE_FOTOGRAFIAS
  (
    CODIGO_USUARIO  INTEGER NOT NULL ,
    CODIGO_FOTO     INTEGER NOT NULL ,
    FECHA_LIKE_FOTO DATE NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_LIKE_FOTOGRAFIAS ADD CONSTRAINT TBL_LIKE_FOTO_PK PRIMARY KEY ( CODIGO_USUARIO, CODIGO_FOTO ) ;

CREATE TABLE FACEBOOK.TBL_LIKE_PUBLICACIONES
  (
    CODIGO_USUARIO         INTEGER NOT NULL ,
    CODIGO_PUBLICACION     INTEGER NOT NULL ,
    FECHA_LIKE_PUBLICACION DATE NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_LIKE_PUBLICACIONES ADD CONSTRAINT TBL_LIKE_PK PRIMARY KEY ( CODIGO_USUARIO, CODIGO_PUBLICACION ) ;

CREATE TABLE FACEBOOK.TBL_MENSAJES
  (
    CODIGO_USUARIO   INTEGER NOT NULL ,
    CODIGO_REMITENTE INTEGER NOT NULL ,
    FECHA_HORA       DATE NOT NULL ,
    MENSAJE_LEIDO    VARCHAR2 (2) NOT NULL ,
    MENSAJE          VARCHAR2 (4000) NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_MENSAJES ADD CONSTRAINT TBL_INBOX_PK PRIMARY KEY ( CODIGO_USUARIO, CODIGO_REMITENTE ) ;

CREATE TABLE FACEBOOK.TBL_PAISES
  (
    CODIGO_PAIS INTEGER NOT NULL ,
    NOMBRE_PAIS VARCHAR2 (200) NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_PAISES ADD CONSTRAINT TBL_PAISES_PK PRIMARY KEY ( CODIGO_PAIS ) ;

CREATE TABLE FACEBOOK.TBL_PUBLICACIONES
  (
    CODIGO_PUBLICACION    INTEGER NOT NULL ,
    CODIGO_USUARIO        INTEGER NOT NULL ,
    CODIGO_GRUPO          INTEGER ,
    CONTENIDO_PUBLICACION VARCHAR2 (4000) NOT NULL ,
    FECHA_PUBLICACION     DATE NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_PUBLICACIONES ADD CONSTRAINT TBL_PUBLICACION_PK PRIMARY KEY ( CODIGO_PUBLICACION ) ;

CREATE TABLE FACEBOOK.TBL_TIPOS_RELACIONES
  (
    CODIGO_TIPO_RELACION INTEGER NOT NULL ,
    TIPO_RELACION        VARCHAR2 (200) NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_TIPOS_RELACIONES ADD CONSTRAINT TBL_TIPOS_RELACIONES_PK PRIMARY KEY ( CODIGO_TIPO_RELACION ) ;

CREATE TABLE FACEBOOK.TBL_TRABAJOS
  (
    CODIGO_TRABAJO      INTEGER NOT NULL ,
    NOMBRE_TRABAJO      VARCHAR2 (100) NOT NULL ,
    DESCRIPCION_TRABAJO VARCHAR2 (500) NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_TRABAJOS ADD CONSTRAINT TBL_TRABAJO_PK PRIMARY KEY ( CODIGO_TRABAJO ) ;

CREATE TABLE FACEBOOK.TBL_TRABAJOS_X_USUARIO
  (
    CODIGO_TRABAJO INTEGER NOT NULL ,
    CODIGO_USUARIO INTEGER NOT NULL ,
    FECHA_INICIO   DATE NOT NULL ,
    FECHA_FINAL    INTEGER NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_TRABAJOS_X_USUARIO ADD CONSTRAINT TBL_TRABAJO_X_USUARIO_PK PRIMARY KEY ( CODIGO_TRABAJO, CODIGO_USUARIO ) ;

CREATE TABLE FACEBOOK.TBL_USUARIOS
  (
    CODIGO_USUARIO           INTEGER NOT NULL ,
    CODIGO_TRABAJO           INTEGER NOT NULL ,
    CODIGO_PAIS              INTEGER NOT NULL ,
    NOMBRE_USUARIO           VARCHAR2 (100) NOT NULL ,
    FECHA_NACIMIENTO_USUARIO DATE NOT NULL ,
    FECHA_REGISTRO           DATE NOT NULL ,
    GENERO_USUARIO           VARCHAR2 (1) NOT NULL ,
    FOTO_PERFIL BLOB ,
    EDAD INTEGER NOT NULL
  ) ;
ALTER TABLE FACEBOOK.TBL_USUARIOS ADD CONSTRAINT TBL_USUARIO_PK PRIMARY KEY ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_ALBUMES ADD CONSTRAINT TBL_ALBUM_FOTOS_TBL_USUARIO_FK FOREIGN KEY ( CODIGO_USUARIO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_AMIGOS ADD CONSTRAINT TBL_AMG_SOLICITUDES_FK FOREIGN KEY ( CODIGO_ESTATUS ) REFERENCES FACEBOOK.TBL_ESTATUS_SOLICITUDES ( CODIGO_ESTATUS ) ;

ALTER TABLE FACEBOOK.TBL_AMIGOS ADD CONSTRAINT TBL_AMIGOS_TBL_USR_FK FOREIGN KEY ( CODIGO_AMIGO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_AMIGOS ADD CONSTRAINT TBL_AMIGO_TBL_TIP_REL_FK FOREIGN KEY ( CODIGO_TIPO_RELACION ) REFERENCES FACEBOOK.TBL_TIPOS_RELACIONES ( CODIGO_TIPO_RELACION ) ;

ALTER TABLE FACEBOOK.TBL_AMIGOS ADD CONSTRAINT TBL_AMIGO_TBL_USUARIO_FK FOREIGN KEY ( CODIGO_USUARIO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_COMENTARIOS ADD CONSTRAINT TBL_COMENTARIO_TBL_COMT_FK FOREIGN KEY ( CODIGO_COMENTARIO_SUPERIOR ) REFERENCES FACEBOOK.TBL_COMENTARIOS ( CODIGO_COMENTARIO ) ;

ALTER TABLE FACEBOOK.TBL_COMENTARIOS ADD CONSTRAINT TBL_COMT_TBL_PUBLICACION_FK FOREIGN KEY ( CODIGO_PUBLICACION ) REFERENCES FACEBOOK.TBL_PUBLICACIONES ( CODIGO_PUBLICACION ) ;

ALTER TABLE FACEBOOK.TBL_COMENTARIOS ADD CONSTRAINT TBL_COMT_TBL_USUARIO_FK FOREIGN KEY ( CODIGO_USUARIO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_ETIQUETA_FOTOGRAFIAS ADD CONSTRAINT TBL_ETIQUETA_TBL_FOTOS_FK FOREIGN KEY ( CODIGO_FOTO ) REFERENCES FACEBOOK.TBL_FOTOS ( CODIGO_FOTO ) ;

ALTER TABLE FACEBOOK.TBL_ETIQUETA_FOTOGRAFIAS ADD CONSTRAINT TBL_ETIQUETA_TBL_USUARIO_FK FOREIGN KEY ( CODIGO_USUARIO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_ETIQUETA_ALBUMES ADD CONSTRAINT TBL_ETIQ_ALB_TBL_USUARIO_FK FOREIGN KEY ( CODIGO_USUARIO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_ETIQUETA_ALBUMES ADD CONSTRAINT TBL_ETQ_ALB_TBL_ALB_FOTOS_FK FOREIGN KEY ( CODIGO_ALBUM ) REFERENCES FACEBOOK.TBL_ALBUMES ( CODIGO_ALBUM ) ;

ALTER TABLE FACEBOOK.TBL_FOTOS ADD CONSTRAINT TBL_FOTOS_TBL_ALBUM_FOTOS_FK FOREIGN KEY ( CODIGO_ALBUM ) REFERENCES FACEBOOK.TBL_ALBUMES ( CODIGO_ALBUM ) ;

ALTER TABLE FACEBOOK.TBL_GRUPOS_X_USUARIO ADD CONSTRAINT TBL_GRUPO_X_USR_TBL_GRUPO_FK FOREIGN KEY ( CODIGO_GRUPO ) REFERENCES FACEBOOK.TBL_GRUPOS ( CODIGO_GRUPO ) ;

ALTER TABLE FACEBOOK.TBL_GRUPOS_X_USUARIO ADD CONSTRAINT TBL_GRUPO_X_USUARIO_TBL_USR_FK FOREIGN KEY ( CODIGO_USUARIO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE TBL_HISTORIAL_ACCESOS ADD CONSTRAINT TBL_HIST_ACC_USR_FK FOREIGN KEY ( CODIGO_USUARIO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_MENSAJES ADD CONSTRAINT TBL_INBOX_TBL_USUARIO_FK FOREIGN KEY ( CODIGO_USUARIO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_LIKE_FOTOGRAFIAS ADD CONSTRAINT TBL_LIKE_FOTO_TBL_FOTOS_FK FOREIGN KEY ( CODIGO_FOTO ) REFERENCES FACEBOOK.TBL_FOTOS ( CODIGO_FOTO ) ;

ALTER TABLE FACEBOOK.TBL_LIKE_FOTOGRAFIAS ADD CONSTRAINT TBL_LIKE_FOTO_TBL_USUARIO_FK FOREIGN KEY ( CODIGO_USUARIO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_LIKE_PUBLICACIONES ADD CONSTRAINT TBL_LIKE_TBL_PUBLICACION_FK FOREIGN KEY ( CODIGO_PUBLICACION ) REFERENCES FACEBOOK.TBL_PUBLICACIONES ( CODIGO_PUBLICACION ) ;

ALTER TABLE FACEBOOK.TBL_LIKE_PUBLICACIONES ADD CONSTRAINT TBL_LIKE_TBL_USUARIO_FK FOREIGN KEY ( CODIGO_USUARIO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_PUBLICACIONES ADD CONSTRAINT TBL_PUBLICACION_TBL_GRUPO_FK FOREIGN KEY ( CODIGO_GRUPO ) REFERENCES FACEBOOK.TBL_GRUPOS ( CODIGO_GRUPO ) ;

ALTER TABLE FACEBOOK.TBL_PUBLICACIONES ADD CONSTRAINT TBL_PUBLICACION_TBL_USUARIO_FK FOREIGN KEY ( CODIGO_USUARIO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_TRABAJOS_X_USUARIO ADD CONSTRAINT TBL_TRABAJO_X_USR_FK FOREIGN KEY ( CODIGO_TRABAJO ) REFERENCES FACEBOOK.TBL_TRABAJOS ( CODIGO_TRABAJO ) ;

ALTER TABLE FACEBOOK.TBL_TRABAJOS_X_USUARIO ADD CONSTRAINT TBL_TRABAJO_X_USR_TBL_USR_FK FOREIGN KEY ( CODIGO_USUARIO ) REFERENCES FACEBOOK.TBL_USUARIOS ( CODIGO_USUARIO ) ;

ALTER TABLE FACEBOOK.TBL_USUARIOS ADD CONSTRAINT TBL_USUARIOS_TBL_PAISES_FK FOREIGN KEY ( CODIGO_PAIS ) REFERENCES FACEBOOK.TBL_PAISES ( CODIGO_PAIS ) ;

ALTER TABLE FACEBOOK.TBL_USUARIOS ADD CONSTRAINT TBL_USUARIO_TBL_TRABAJO_FK FOREIGN KEY ( CODIGO_TRABAJO ) REFERENCES FACEBOOK.TBL_TRABAJOS ( CODIGO_TRABAJO ) ;


-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            19
-- CREATE INDEX                             0
-- ALTER TABLE                             45
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
