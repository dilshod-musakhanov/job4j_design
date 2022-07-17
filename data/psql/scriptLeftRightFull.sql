create table departments(
    id serial primary key,
    name varchar(30)
);

create table employees(
    id serial primary key,
    name varchar(30),
    department_id int references departments(id)
);

insert into departments(name) values('IT');
insert into departments(name) values('HR');
insert into departments(name) values('Customer Service');
insert into departments(name) values('Accounts');
insert into departments(name) values('Marketing');

insert into employees(name, department_id) values('Lana', '2');
insert into employees(name, department_id) values('Anna', '3');
insert into employees(name, department_id) values('Alexey', '1');
insert into employees(name, department_id) values('Peter', '2');
insert into employees(name) values('Kate');
insert into employees(name, department_id) values('Sveta', '4');
insert into employees(name) values('Bob');

select * from employees e
left join departments d
on e.department_id  = d.id;

select * from employees e
right join departments d
on e.department_id  = d.id;

select * from employees e
full join departments d
on e.department_id  = d.id;

select * from employees e
cross join departments d;

select e.*, d.*
from departments d
right join employees e
on d.id = e.department_id;

select e.*, d.*
from employees e
left join departments d
on e.department_id = d.id;

select d.name
from departments d
left join employees e
on e.department_id = d.id
where e.name is null;

create table teens(
    id serial primary key,
    name varchar(30),
    gender varchar(6)
);

insert into teens(name, gender) values ('Joe', 'Male');
insert into teens(name, gender) values ('Lana', 'Female');
insert into teens(name, gender) values ('Bob', 'Male');
insert into teens(name, gender) values ('Alla', 'Female');

select t1.name, t2.name
from teens t1
cross join teens t2
where t1.gender = 'Male' AND t2.gender = 'Female';