-- read committed

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

-- Первая транзакция:
begin transaction;

select * from products;

insert into products (name, producer, count, price) VALUES ('notebook', 'HP', 20, 5500);
delete from products where name = 'kindle';
update products set price = 4000 where name = 'phone';

select * from products;

commit;

-- Вторая транзакция:
begin transaction;

select * from products;

select * from products;

select * from products;


-- repeatable read

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

-- Первая транзакция:
begin transaction isolation level repeatable read;

select * from products;

insert into products (name, producer, count, price) VALUES ('speakers', 'Sony', 20, 3500);
delete from products where name = 'TV';
update products set price = 2500 where name = 'watch';

rollback;

-- Вторая транзакция:
begin transaction isolation level repeatable read;

select * from products;

update products set price = 2500 where name = 'watch';

select * from products;


-- serializable

create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products(name, producer, count, price) values('phone', 'Samsung', 10, 3000);
insert into products(name, producer, count, price) values('watch', 'Apple', 10, 1000);
insert into products(name, producer, count, price) values('tablet', 'Dell', 10, 3500);

-- Первая транзакция:
begin transaction isolation level serializable;

select * from products;

select sum(count) from products;

update products set count = 20 where name = 'phone';

commit;

-- Вторая транзакция:
begin transaction isolation level serializable;

select * from products;

select sum(count) from products;

update products set count = 20 where name = 'watch';

commit;
