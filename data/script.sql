create table tours (
    id serial primary key,
    name varchar(30),
    on_sale boolean,
    description text,
    price int
);

insert into tours(
    name, on_sale, description, price)
    values ('Dubai', 'True', 'Spend your summer in Dubai', 2000
);

update tours set name = 'Abu Dhabi';

delete from tours;