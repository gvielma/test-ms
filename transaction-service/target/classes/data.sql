create table movimientos(
     
id INTEGER auto_increment NOT NULL PRIMARY KEY, 
numero_cuenta VARCHAR (255) NOT NULL,
cliente_id INTEGER NOT NULL,
fecha TIMESTAMP NOT NULL,
saldo_inicial double NOT NULL,
saldo_disponible double NOT NULL,
movimiento double NOT NULL);
