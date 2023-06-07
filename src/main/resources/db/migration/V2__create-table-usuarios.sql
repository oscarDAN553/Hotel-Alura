CREATE TABLE usuarios (

    id bigint not null auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    fecha_nacimiento varchar(10) not null,
    nacionalidad varchar(50)not null,
    telefono varchar(13)not null,
    id_registro varchar(36) not null,
    primary key(id),
    foreign key (id_registro) references registros(id_registro)

);