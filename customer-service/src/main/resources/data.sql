create table persons(
	id INTEGER auto_increment NOT NULL PRIMARY KEY, 
	nombre varchar(255) NOT NULL,
	genero varchar(25) NOT NULL,
	edad INTEGER NOT NULL,
	identificacion varchar(50) NOT NULL,
	direccion varchar(255) NOT NULL,
	telefono varchar(25) NOT NULL
); 

create table customers(
	id INTEGER auto_increment NOT NULL PRIMARY KEY, 
	codigo_cliente INTEGER NOT NULL UNIQUE,
	clave varchar(255) NOT NULL,
	estado varchar(25) NOT NULL,
	person_id INTEGER,
	
	foreign key (person_id) references persons(id)
); 


/******************** INSERTAR DATOS DE PERSONA *********************/
INSERT INTO PERSONS (NOMBRE, GENERO, EDAD, IDENTIFICACION, DIRECCION, TELEFONO) VALUES ('Jose Lema','Masculino','35','18426432','Otavalo sn y principal','098254785');
INSERT INTO PERSONS (NOMBRE, GENERO, EDAD, IDENTIFICACION, DIRECCION, TELEFONO) VALUES ('Marianela Montalvo','Femenino','40','16669258','Amazonas y NNUU','097548965');
INSERT INTO PERSONS (NOMBRE, GENERO, EDAD, IDENTIFICACION, DIRECCION, TELEFONO) VALUES ('Juan Osorio','Masculino','35','00011234562','13 junio y Equinoccial','098874587');

/******************** INSERTAR DATOS DE CLIENTE *********************/
INSERT INTO CUSTOMERS (CODIGO_CLIENTE, CLAVE, ESTADO, PERSON_ID) VALUES ('111111','1234','True',1);
INSERT INTO CUSTOMERS (CODIGO_CLIENTE, CLAVE, ESTADO, PERSON_ID) VALUES ('111222','5678','True',2);
INSERT INTO CUSTOMERS (CODIGO_CLIENTE, CLAVE, ESTADO, PERSON_ID) VALUES ('111333','1245','True',3);

