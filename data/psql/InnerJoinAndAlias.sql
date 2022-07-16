create table tour_guide(
    id serial primary key,
    name varchar(30),
    gender varchar(6),
    language varchar(10)
);

create table tours(
    id serial primary key,
    name varchar(50),
    type varchar (7),
    duration varchar(8),
    tour_guide_id int references tour_guide(id)
);

insert into tour_guide(name, gender, language) values ('Lana', 'Female', 'Russian');
insert into tour_guide(name, gender, language) values ('Robiya', 'Female', 'Arabic');
insert into tour_guide(name, gender, language) values ('Micheal', 'Male', 'English');

insert into tours(name, type, duration, tour_guide_id) values('Dubai City Tour', 'Private', 'Half Day', '1');
insert into tours(name, type, duration, tour_guide_id) values('Abu Dhabi City Tour', 'Shared', 'Full Day', '2');
insert into tours(name, type, duration, tour_guide_id) values('Safari Tour', 'Shared', 'Full Day', '3');

select * from tours
join tour_guide
on tour_guide_id = tour_guide.id;

select tours.name, tour_guide.name
from tours
join tour_guide
on tour_guide_id = tour_guide.id;

select t.name, tg.name
from tours as t
join tour_guide as tg
on t.tour_guide_id = tg.id;

select t.name, tg.name, tg.language
from tours as t
join tour_guide as tg
on t.tour_guide_id = tg.id;

select t.name "Tour Name", tg.name "Tour Guide Name", tg.language "Guided In"
from tours as t
join tour_guide as tg
on t.tour_guide_id = tg.id;