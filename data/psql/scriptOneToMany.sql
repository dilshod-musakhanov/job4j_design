create table department(
    id serial primary key,
    name varchar(50)
);

insert into department(name) values ('IT');
insert into department(name) values ('HR');

create table employees(
    id serial primary key,
    name varchar(30),
    date_of_joining DATE,
    department_id int references department(id)
);

insert into employees(name, date_of_joining, department_id) values('Joe', '2000-02-22', 1);
insert into employees(name, date_of_joining, department_id) values('Ann', '2010-10-12', 2);
insert into employees(name, date_of_joining, department_id) values('Ali', '2015-11-30', 1);

select * from employees where department_id = '1';