create table logins(

    id bigint not null auto_increment,
    login varchar(200) not null UNIQUE,
    password varchar(300) not null,
    primary key(id)

);