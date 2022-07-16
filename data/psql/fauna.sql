create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('hypo', '40', date '1750-10-05');
insert into fauna(name) values ('jellyfish');
insert into fauna(name, avg_age, discovery_date) values ('white tiger', '30', date '1710-03-20');
insert into fauna(name, avg_age) values ('golden fish', '5');
insert into fauna(name, avg_age, discovery_date) values ('leopard', '20', date '1890-12-25');
insert into fauna(name, avg_age, discovery_date) values ('white whale', '120', date '1900-01-10');
insert into fauna(name, avg_age, discovery_date) values ('rhino', '60', date '1870-05-11');
insert into fauna(name, avg_age) values ('pigeon', '30');
insert into fauna(name, avg_age, discovery_date) values ('mockingbird', '10', date '1880-09-04');
insert into fauna(name, avg_age, discovery_date) values ('komodo dragon', '30', date '1910-11-02');
insert into fauna(name, avg_age, discovery_date) values ('nemo fish', '40', date '2013-05-30');

select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age > 20 and avg_age < 40;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < date '1950-01-01';
select * from fauna where avg_age is null and discovery_date is null;
select * from fauna where avg_age < 60 and discovery_date > date '1900-01-01';
select * from fauna order by discovery_date asc;
select * from fauna order by avg_age asc limit 3;
select * from fauna where discovery_date is not null order by avg_age asc limit 3;



