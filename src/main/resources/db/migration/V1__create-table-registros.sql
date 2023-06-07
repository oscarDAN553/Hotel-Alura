create table registros(

    id bigint not null auto_increment,
    id_registro varchar(36) not null UNIQUE,
    fecha_entrada varchar(10) not null,
    fecha_salida varchar(10) not null,
    valor varchar(100) not null,
    forma_pago varchar(11) not null,
    activo varchar(1) not null,
    primary key(id)

);