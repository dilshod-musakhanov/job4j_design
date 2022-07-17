create table type(
    id serial primary key,
    name varchar(30)
);

create table product(
    id serial primary key,
    name varchar(30),
    expired_date date,
    price decimal,
    type_id int references type(id)
);

insert into type(name) values ('cheese');
insert into type(name) values ('milk');
insert into type(name) values ('seafood');
insert into type(name) values ('icecream');

insert into product(name, expired_date, price, type_id)
values ('cheddar', date '2022-08-01', '3.50', '1');
insert into product(name, expired_date, price, type_id)
values ('cream cheese', date '2022-07-15', '3.50', '1');
insert into product(name, expired_date, price, type_id)
values ('mozzarella', date '2022-08-07', '5.50', '1');

insert into product(name, expired_date, price, type_id)
values ('fresh cow milk', date '2022-07-27', '2.50', '2');
insert into product(name, expired_date, price, type_id)
values ('soy milk', date '2022-07-16', '3.50', '2');
insert into product(name, expired_date, price, type_id)
values ('almond milk', date '2022-07-17', '4.50', '2');
insert into product(name, expired_date, price, type_id)
values ('coconut milk', date '2022-09-07', '4.00', '2');

insert into product(name, expired_date, price, type_id)
values ('fish', date '2022-07-20', '4.50', '3');
insert into product(name, expired_date, price, type_id)
values ('crab', date '2022-07-27', '6.50', '3');
insert into product(name, expired_date, price, type_id)
values ('oyster', date '2022-07-21', '8.50', '3');
insert into product(name, expired_date, price, type_id)
values ('shrimp', date '2022-07-20', '5.00', '3');
insert into product(name, expired_date, price, type_id)
values ('lobster', date '2022-07-22', '9.00', '3');

insert into product(name, expired_date, price, type_id)
values ('chocolate icecream', date '2022-07-19', '3.00', '4');
insert into product(name, expired_date, price, type_id)
values ('vanila icecream', date '2022-07-19', '2.50', '4');

select * from product where type_id = '1';

select * from product where name like '%icecream';

select * from product where expired_date < '2022-07-17';

select name from product where price = (select max(price) from product);

select t.name, count(p.type_id)
from type t
join product p
on p.type_id = t.id
group by t.name;

select * from product
where type_id = '1'
or type_id = '2';

select t.name, count(p.type_id)
from type t
join product p
on p.type_id = t.id
group by t.name
having count(p.type_id) < 3;

select p.name "Product Name", t.name "Type"
from product p
join type t
on p.type_id = t.id
order by p.name asc;
