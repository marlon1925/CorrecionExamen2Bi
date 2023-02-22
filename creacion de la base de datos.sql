CREATE DATABASE COLEGIO

DROP DATABASE COLEGIO
DROP TABLE NIVEL 
DROP TABLE GENERO
DROP TABLE Persona


USE COLEGIO

CREATE TABLE PERSONA( Id_est varchar(10) not null, Nom_est varchar(20)not null, Cel_est numeric(10)not null, Email_est varchar(20)not null, Nivel varchar(20) not null, Genero varchar(10) not null)

SELECT * FROM PERSONA

Insert Into PERSONA VALUES('1718688284','Wilson', 0993925118,'wilo@e.com','PRIMARIA','HOMBRE')

CREATE TABLE NIVEL(Nivel varchar(20) not null)

Insert Into NIVEL VALUES ('PRIMARIA')
Insert Into NIVEL VALUES ('SECUNDARIA')
Insert Into NIVEL VALUES ('UNIVERSIDAD')


CREATE TABLE GENERO(GENERO varchar(10) not null)

Insert Into GENERO VALUES ('HOMBRE')
Insert Into GENERO VALUES ('MUJER')
