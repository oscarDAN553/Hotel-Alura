alter table usuarios add activo varchar(1);
update usuarios set activo = "1";