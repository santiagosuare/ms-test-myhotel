DROP TABLE IF EXISTS countries;
CREATE TABLE countries (
  COUNTRY_ID VARCHAR(2) NOT NULL,
  COUNTRY_NAME VARCHAR(40) DEFAULT NULL,
  REGION_ID DECIMAL(10,0) DEFAULT NULL,
  PRIMARY KEY (COUNTRY_ID)
);

CREATE INDEX COUNTR_REG_FK ON countries(REGION_ID);

DROP TABLE IF EXISTS departments;
CREATE TABLE departments (
  DEPARTMENT_ID DECIMAL(4,0) NOT NULL DEFAULT '0',
  DEPARTMENT_NAME VARCHAR(30) NOT NULL,
  MANAGER_ID DECIMAL(6,0) DEFAULT NULL,
  LOCATION_ID DECIMAL(4,0) DEFAULT NULL,
  PRIMARY KEY (DEPARTMENT_ID)
);

CREATE INDEX DEPT_MGR_FK ON departments(MANAGER_ID);
CREATE INDEX DEPT_LOCATION_IX ON departments(LOCATION_ID);


DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
  EMPLOYEE_ID DECIMAL(6,0) NOT NULL DEFAULT '0',
  FIRST_NAME VARCHAR(20) DEFAULT NULL,
  LAST_NAME VARCHAR(25) NOT NULL,
  EMAIL VARCHAR(25) NOT NULL,
  PHONE_NUMBER VARCHAR(20) DEFAULT NULL,
  HIRE_DATE DATE NOT NULL,
  JOB_ID VARCHAR(10) NOT NULL,
  SALARY DECIMAL(8,2) DEFAULT NULL,
  COMMISSION_PCT DECIMAL(2,2) DEFAULT NULL,
  MANAGER_ID DECIMAL(6,0) DEFAULT NULL,
  DEPARTMENT_ID DECIMAL(4,0) DEFAULT NULL,
  PRIMARY KEY (EMPLOYEE_ID)
);

CREATE UNIQUE INDEX EMP_EMAIL_UK ON employees(EMAIL);
CREATE INDEX EMP_DEPARTMENT_IX ON employees(DEPARTMENT_ID);
CREATE INDEX EMP_JOB_IX ON employees(JOB_ID);
CREATE INDEX EMP_MANAGER_IX ON employees(MANAGER_ID);
CREATE INDEX EMP_NAME_IX ON employees(LAST_NAME, FIRST_NAME);

DROP TABLE IF EXISTS job_history;
CREATE TABLE job_history (
  EMPLOYEE_ID DECIMAL(6,0) NOT NULL,
  START_DATE DATE NOT NULL,
  END_DATE DATE NOT NULL,
  JOB_ID VARCHAR(10) NOT NULL,
  DEPARTMENT_ID DECIMAL(4,0) DEFAULT NULL,
  PRIMARY KEY (EMPLOYEE_ID, START_DATE)
);

CREATE INDEX JHIST_DEPARTMENT_IX ON job_history(DEPARTMENT_ID);
CREATE INDEX JHIST_EMPLOYEE_IX ON job_history(EMPLOYEE_ID);
CREATE INDEX JHIST_JOB_IX ON job_history(JOB_ID);

DROP TABLE IF EXISTS jobs;
CREATE TABLE jobs (
  JOB_ID VARCHAR(10) NOT NULL DEFAULT '',
  JOB_TITLE VARCHAR(35) NOT NULL,
  MIN_SALARY DECIMAL(6,0) DEFAULT NULL,
  MAX_SALARY DECIMAL(6,0) DEFAULT NULL,
  PRIMARY KEY (JOB_ID)
);

DROP TABLE IF EXISTS locations;
CREATE TABLE locations (
  LOCATION_ID DECIMAL(4,0) NOT NULL DEFAULT '0',
  STREET_ADDRESS VARCHAR(40) DEFAULT NULL,
  POSTAL_CODE VARCHAR(12) DEFAULT NULL,
  CITY VARCHAR(30) NOT NULL,
  STATE_PROVINCE VARCHAR(25) DEFAULT NULL,
  COUNTRY_ID VARCHAR(2) DEFAULT NULL,
  PRIMARY KEY (LOCATION_ID)
);

CREATE INDEX LOC_CITY_IX ON locations(CITY);
CREATE INDEX LOC_COUNTRY_IX ON locations(COUNTRY_ID);
CREATE INDEX LOC_STATE_PROVINCE_IX ON locations(STATE_PROVINCE);

DROP TABLE IF EXISTS regions;
CREATE TABLE regions (
  REGION_ID DECIMAL(5,0) NOT NULL,
  REGION_NAME VARCHAR(25) DEFAULT NULL,
  PRIMARY KEY (REGION_ID)
);

CREATE UNIQUE INDEX sss ON regions(REGION_NAME);

DROP TABLE IF EXISTS vehiculo;
CREATE TABLE vehiculo(
    id SERIAL PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    patente VARCHAR(50) NOT NULL,
    anio_fabricacion DATE NOT NULL,
    kilometraje INT NOT NULL,
    cilindrada VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS automovil;
CREATE TABLE automovil(
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    cant_puertas INT NOT NULL,
    cant_pasajeros INT NOT NULL,
    cant_maletero INT NOT NULL,
    vehiculo_id INT NOT NULL,
    FOREIGN KEY (vehiculo_id) REFERENCES vehiculo(id)
);

DROP TABLE IF EXISTS camion;
CREATE TABLE camion(
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    capacidad_toneladas INT NOT NULL,
    cant_ejes INT NOT NULL,
    vehiculo_id INT NOT NULL,
    FOREIGN KEY (vehiculo_id) REFERENCES vehiculo(id)
);

DROP TABLE IF EXISTS mantencion;
CREATE TABLE mantencion(
    id SERIAL PRIMARY KEY,
    fecha_mantencion DATE NOT NULL,
    descripcion VARCHAR(50) NOT NULL,
    costo DECIMAL(10,2) NOT NULL,
    vehiculo_id INT NOT NULL,
    FOREIGN KEY (vehiculo_id) REFERENCES vehiculo(id)
);

