create table car_bodies(
    id serial primary key,
    name varchar(30)
);

create table car_engines(
    id serial primary key,
    name varchar(30)
);

create table car_transmissions(
    id serial primary key,
    name varchar(30)
);

create table cars(
    id serial primary key,
    name varchar(30),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('sedan');
insert into car_bodies(name) values ('hatchback');
insert into car_bodies(name) values ('coupe');
insert into car_bodies(name) values ('suv');
insert into car_bodies(name) values ('van');
insert into car_bodies(name) values ('supercar');

insert into car_engines(name) values ('twin-cylinder');
insert into car_engines(name) values ('three-cylinder');
insert into car_engines(name) values ('four-cylinder');
insert into car_engines(name) values ('five-cylinder');
insert into car_engines(name) values ('six-cylinder');

insert into car_transmissions(name) values ('automatic');
insert into car_transmissions(name) values ('manual');
insert into car_transmissions(name) values ('semi automatic');

insert into cars(name, body_id, engine_id, transmission_id)
values ('nissan sunny', '1', '1', '1');
insert into cars(name, engine_id, transmission_id)
values ('toyota camry', '2', '1');
insert into cars(name, body_id, engine_id, transmission_id)
values ('kia picanto', '2', '1', '2');
insert into cars(name, body_id, transmission_id)
values ('mazda mx-5', '3', '1');
insert into cars(name, body_id, engine_id, transmission_id)
values ('jaguar f-type', '3', '2', '1');
insert into cars(name, body_id, engine_id)
values ('nissan patrol', '4', '4');
insert into cars(name, body_id, transmission_id)
values ('land rover', '4', '1');
insert into cars(name, body_id)
values ('toyota raw-4', '4');

select cb.name
from car_bodies cb
left join cars c
on c.body_id = cb.id
where c.name is null;

select ce.name
from car_engines ce
left join cars c
on c.engine_id = ce.id
where c.name is null;

select ct.name
from car_transmissions ct
left join cars c
on c.transmission_id = ct.id
where c.name is null;