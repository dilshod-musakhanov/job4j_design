create table visa(
    id serial primary key,
    number int,
    type varchar(10)
);

create table person(
    id serial primary key,
    name varchar(30),
    profession varchar(20),
    visa_id int references visa(id) unique
);

insert into person(name, profession, visa_id) values ('Ben', 'Manager', '1');
insert into person(name, profession, visa_id) values ('Sara', 'Dancer', '2');

select * from person where visa_id = '2';