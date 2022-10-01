-- rollback to first_savepoint

create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products(name, producer, count, price) values('phone', 'Apple', 10, 3000);
insert into products(name, producer, count, price) values('kindle', 'Amazon', 10, 1000);
insert into products(name, producer, count, price) values('tablet', 'Samsung', 10, 3500);

begin transaction;

insert into products (name, producer, count, price) VALUES ('notebook', 'HP', 20, 5500);

savepoint first_savepoint;

delete from products where name = 'kindle';

update products set price = 4000 where name = 'phone';

select * from products;

rollback to first_savepoint;

select * from products;

commit transaction;


-- rollback to third_savepoint

create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products(name, producer, count, price) values('TV', 'LG', 10, 7000);
insert into products(name, producer, count, price) values('watch', 'Apple', 10, 2000);
insert into products(name, producer, count, price) values('camera', 'Cannon', 10, 4500);

begin transaction;

insert into products (name, producer, count, price) VALUES ('notebook', 'HP', 20, 5500);

savepoint first_savepoint;

delete from products where name = 'TV';

savepoint second_savepoint;

insert into products (name, producer, count, price) VALUES ('speakers', 'Sony', 20, 3500);

savepoint third_savepoint;

update products set price = 2500 where name = 'watch';

savepoint forth_savepoint;

delete from products where name = 'camera';

select * from products;

rollback to second_savepoint;

select * from products;

commit transaction;

