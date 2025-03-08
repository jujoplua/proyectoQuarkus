 create table tbl_mascota(
    id int not null auto_increment  primary key,
    nombre varchar(60) not null,
    edad int not null
 );

 create table tbl_mascota_SEQ (next_val bigint) engine=InnoDB;
 insert into tbl_mascota_SEQ values ( 1 );