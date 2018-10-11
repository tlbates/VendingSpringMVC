drop database if exists vending_items_test;
create database if not exists vending_items_test;
use vending_items_test;

create table items (
	ID int not null auto_increment,
    name varchar(20) not null,
    price double not null,
    stock int,
    primary key (ID)
);