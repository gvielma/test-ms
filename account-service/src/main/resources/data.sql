create table accounts(
     
id INTEGER auto_increment NOT NULL PRIMARY KEY, 
numero_cuenta VARCHAR (255) NOT NULL UNIQUE,
cliente_id INTEGER NOT NULL,
estado varchar(25) NOT NULL,
tipo_cuenta varchar(25) NOT NULL,
saldo_inicial double NOT NULL);

INSERT INTO ACCOUNTS (NUMERO_CUENTA, CLIENTE_ID, ESTADO, TIPO_CUENTA, SALDO_INICIAL) VALUES ('478758',111111,'True','Ahorro',2000);
INSERT INTO ACCOUNTS (NUMERO_CUENTA, CLIENTE_ID, ESTADO, TIPO_CUENTA, SALDO_INICIAL) VALUES ('225487',111222,'True','Corriente',100);
INSERT INTO ACCOUNTS (NUMERO_CUENTA, CLIENTE_ID, ESTADO, TIPO_CUENTA, SALDO_INICIAL) VALUES ('495878',111333,'True','Ahorro',0);
INSERT INTO ACCOUNTS (NUMERO_CUENTA, CLIENTE_ID, ESTADO, TIPO_CUENTA, SALDO_INICIAL) VALUES ('496825',111222,'True','Ahorro',540);
INSERT INTO ACCOUNTS (NUMERO_CUENTA, CLIENTE_ID, ESTADO, TIPO_CUENTA, SALDO_INICIAL) VALUES ('585545',111111,'True','Corriente',1000);
