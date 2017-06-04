

DROP TABLE Trabajo;
DROP TABLE Proyeccion;
DROP TABLE Sala;
DROP TABLE Cine;
DROP TABLE Otorgo;
DROP TABLE Reconocimiento;
DROP TABLE Festival_Premio;
DROP TABLE Pelicula;
DROP TABLE Premio;
DROP TABLE Tarea;
DROP TABLE Personaje;
DROP TABLE Certamen;
DROP TABLE Festival;

CREATE TABLE Pelicula  (
  cip VARCHAR2(10) NOT NULL,
  titulo_p VARCHAR2(45) NOT NULL,
  ano_produccion NUMBER(4) NOT NULL,
  titulo_s VARCHAR2(45),
  nacionalidad VARCHAR2(15),
  presupuesto NUMBER(11),
  duracion NUMBER(3),
    CONSTRAINT pk_pelicula 
      PRIMARY KEY (cip),
    CONSTRAINT ck_cip 
      UNIQUE (titulo_p,ano_produccion),
    CONSTRAINT ck_pre 
      CHECK (presupuesto > 0), 
    CONSTRAINT ck_dur 
      CHECK (duracion > 0) );
CREATE TABLE Personaje  (
  nombre_persona VARCHAR2(25) NOT NULL,
  nacionalidad_persona VARCHAR2(15),
  sexo_persona CHAR(1),
    CONSTRAINT pk_persona 
      PRIMARY KEY(nombre_persona),
    CONSTRAINT ck_npe 
      CHECK (nombre_persona=INITCAP(nombre_persona)),
    CONSTRAINT ck_sep 
      CHECK (sexo_persona IN ('H','M')) );
CREATE TABLE Tarea  (
  tarea VARCHAR2(30) NOT NULL,
  sexo_tarea CHAR(1),
    CONSTRAINT pk_tarea 
      PRIMARY KEY (tarea),
    CONSTRAINT ck_set 
      CHECK (sexo_tarea IN ('H','M','N')) );
CREATE TABLE Trabajo  (
  cip VARCHAR2(10) NOT NULL,
  nombre_persona VARCHAR2(25) NOT NULL,
  tarea VARCHAR2(35) NOT NULL,
    CONSTRAINT pk_trabajo 
      PRIMARY KEY (cip,nombre_persona, tarea),
    CONSTRAINT fk_Tra_Pel
      FOREIGN KEY (cip)
      REFERENCES Pelicula(cip) 
      ON DELETE CASCADE,
    CONSTRAINT fk_tra_per
      FOREIGN KEY (nombre_persona)
      REFERENCES Personaje (nombre_persona)
      ON DELETE CASCADE,
    CONSTRAINT fk_tra_tar
      FOREIGN KEY (tarea)
      REFERENCES Tarea(tarea)
      ON DELETE CASCADE );
CREATE TABLE Festival  (
  festival VARCHAR2(40) NOT NULL,
  fundacion DATE,
    CONSTRAINT pk_festival 
      PRIMARY KEY (festival),
    CONSTRAINT ck_fes
      CHECK (festival=INITCAP(festival)) );
CREATE TABLE Certamen  (
  festival VARCHAR2(40) NOT NULL,
  certamen NUMBER(4) NOT NULL,
  organizador VARCHAR2(60),
    CONSTRAINT pk_certamen
      PRIMARY KEY (festival,certamen),
    CONSTRAINT fk_cer_fes
      FOREIGN KEY (festival)
      REFERENCES Festival (festival)
      ON DELETE CASCADE );
CREATE TABLE Premio  (
  premio VARCHAR2(50),
  tarea VARCHAR2(50),
    CONSTRAINT pk_premio
      PRIMARY KEY (premio),
    CONSTRAINT fk_pre_tar
      FOREIGN KEY (tarea)
      REFERENCES Tarea(tarea) );
CREATE TABLE Festival_Premio  (
  festival VARCHAR2(45) NOT NULL,
  premio VARCHAR2(50) NOT NULL,
  galardon VARCHAR2(50),
    CONSTRAINT pk_fespre
      PRIMARY KEY(festival, premio),
    CONSTRAINT fk_fepr_fes
      FOREIGN KEY (festival)
      REFERENCES Festival(festival)
      ON DELETE CASCADE,
    CONSTRAINT fk_fepr_pre
      FOREIGN KEY (premio)
      REFERENCES Premio(premio)
      ON DELETE CASCADE );
CREATE TABLE  Otorgo  (
  festival VARCHAR2(50) NOT NULL,
  certamen NUMBER(4) NOT NULL,
  premio VARCHAR2(40) NOT NULL,
  cip VARCHAR2(10) NOT NULL,
    CONSTRAINT pk_otorgo
      PRIMARY KEY (festival, certamen, premio),
    CONSTRAINT  fk_otor_fepr
      FOREIGN KEY (festival,premio)
      REFERENCES Festival_Premio (festival, premio)
      ON DELETE CASCADE,
    CONSTRAINT  fk_otor_cer
      FOREIGN KEY (festival, certamen)
      REFERENCES Certamen (festival, certamen)
      ON DELETE CASCADE );
CREATE TABLE Reconocimiento  (
  festival VARCHAR2(45) NOT NULL,
  certamen NUMBER(4) NOT NULL,
  premio VARCHAR2(40) NOT NULL,
  nombre_persona VARCHAR2(35) NOT NULL,
    CONSTRAINT pk_recono 
      PRIMARY KEY (festival, certamen, premio),
    CONSTRAINT fk_reco_fepr
      FOREIGN KEY (festival, premio)
      REFERENCES Festival_Premio (festival,premio)
      ON DELETE CASCADE,
    CONSTRAINT fk_reco_cer
      FOREIGN KEY (festival, certamen)
      REFERENCES Certamen (festival, certamen)
      ON DELETE CASCADE,
    CONSTRAINT fk_reco_per
      FOREIGN KEY (nombre_persona)
      REFERENCES Personaje (nombre_persona)
      ON DELETE CASCADE );
CREATE TABLE Cine  (
  cine VARCHAR2(25) NOT NULL,
  ciudad_cine VARCHAR2(25),
  direccion_cine VARCHAR2(65),
    CONSTRAINT pk_cine 
      PRIMARY KEY (cine),
    CONSTRAINT ck_cin
      CHECK (cine=INITCAP(cine)));
CREATE TABLE Sala  (
  cine VARCHAR2(25) NOT NULL,
  sala NUMBER(2) NOT NULL,
  aforo NUMBER(4),
    CONSTRAINT pk_sala
      PRIMARY KEY (cine,sala),
    CONSTRAINT fk_sal_cin
      FOREIGN KEY (cine)
      REFERENCES Cine(cine)
      ON DELETE CASCADE,
    CONSTRAINT ck_afr
      CHECK (aforo > 0) );
CREATE TABLE Proyeccion  (
  cine VARCHAR2(25) NOT NULL,
  sala NUMBER(2) NOT NULL,
  cip VARCHAR2(10) NOT NULL,
  fecha_estreno DATE NOT NULL,
  dias_estreno NUMBER(3),
  espectadores NUMBER(6),
  recaudacion NUMBER(8),
    CONSTRAINT pk_proyección
      PRIMARY KEY (cine,sala, cip, fecha_estreno),
    CONSTRAINT fk_pro_sal
      FOREIGN KEY (cine,sala)
      REFERENCES Sala(cine,sala)
      ON DELETE CASCADE,
    CONSTRAINT fk_pro_pel
      FOREIGN KEY (cip)
      REFERENCES Pelicula (cip)
      ON DELETE CASCADE,
    CONSTRAINT ck_dia
      CHECK (dias_estreno>0) );
