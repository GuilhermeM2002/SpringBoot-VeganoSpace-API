create table T_CLIENT(
    id bigint not null auto_increment,
    cpf varchar(11) not null unique,
    name varchar(100) not null,
    dt_birth date not null,
    email varchar(100) not null unique,
    password varchar(100) not null,

    primary key(id)
);
