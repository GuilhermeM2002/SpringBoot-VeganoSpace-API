create table T_MENU(
    id bigint not null auto_increment,
    dish varchar(100) not null,
    price numeric(5,2) not null,
    details varchar(200) not null,
    category varchar(100) not null,

    primary key(id)
);