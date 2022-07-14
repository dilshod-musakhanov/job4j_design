create table gym_clubs(
    id serial primary key,
    name varchar(30),
    location varchar(20)
);

create table members(
    id serial primary key,
    name varchar(30),
    age int
);

create table gym_clubs_members(
    id serial primary key,
    gym_clubs_id int references gym_clubs(id),
    members_id int references members(id)
);

insert into gym_clubs(name, location) values ('The Titan', 'Dubai');
insert into gym_clubs(name, location) values ('The Desert', 'Dubai');
insert into gym_clubs(name, location) values ('The Rock', 'Dubai');

insert into members(name, age) values ('Joe', '30');
insert into members(name, age) values ('Ann', '25');
insert into members(name, age) values ('Bob', '40');

insert into gym_clubs_members(gym_clubs_id, members_id) values ('1', '3');
insert into gym_clubs_members(gym_clubs_id, members_id) values ('2', '1');
insert into gym_clubs_members(gym_clubs_id, members_id) values ('2', '2');
insert into gym_clubs_members(gym_clubs_id, members_id) values ('3', '1');
insert into gym_clubs_members(gym_clubs_id, members_id) values ('3', '2');

select * from gym_clubs_members where gym_clubs_id = '3';
