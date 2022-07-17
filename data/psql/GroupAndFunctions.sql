create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('HP Pavilion', '1700.99');
insert into devices(name, price) values ('Lenovo Legion', '1900.99');
insert into devices(name, price) values ('Dell Inspiron', '800.99');
insert into devices(name, price) values ('MSI Sword', '900.99');
insert into devices(name, price) values ('Aser Aspire', '600.99');

insert into people(name) values ('Rose');
insert into people(name) values ('Alex');
insert into people(name) values ('Mark');
insert into people(name) values ('Kamala');

insert into devices_people(device_id, people_id)
values ('1','1');
insert into devices_people(device_id, people_id)
values ('1','3');
insert into devices_people(device_id, people_id)
values ('2','1');
insert into devices_people(device_id, people_id)
values ('2','4');
insert into devices_people(device_id, people_id)
values ('3','2');
insert into devices_people(device_id, people_id)
values ('3','3');
insert into devices_people(device_id, people_id)
values ('4','2');
insert into devices_people(device_id, people_id)
values ('4','4');
insert into devices_people(device_id, people_id)
values ('4','3');
insert into devices_people(device_id, people_id)
values ('5','4');

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
join devices as d
on dp.device_id = d.id
join people as p
on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price)
from devices_people as dp
join devices as d
on dp.device_id = d.id
join people as p
on dp.people_id = p.id
group by p.name
having avg(d.price) > 1500;

select d.name, p.name
from devices d
join devices_people dp
on d.id = dp.device_id
join people p
on p.id = dp.people_id
where p.name = 'Rose';