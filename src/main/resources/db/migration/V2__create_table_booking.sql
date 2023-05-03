create table T_BOOKING(
    id bigint not null auto_increment,
    client_id bigint not null,
    bk_table varchar(100) not null,
    dt_booking datetime not null,
    nr_person varchar(100) not null,

    primary key(id)
);