create table gender(
    id serial primary key,
    name varchar(20)
);

create table users(
    id serial primary key,
    fullname varchar(30),
    address varchar(255),
    gender_id int references gender(id)
);

create table movies(
    id serial primary key,
    name varchar(30)
);

create table movie_rentals(
    id serial primary key,
    movie_id int references movies(id),
    user_id int references users(id)
);

insert into gender(name) values ('male');
insert into gender(name) values ('female');

insert into users(fullname, address, gender_id)
values ('Barak Obama', 'Fifth Ave, 7, ap 15, New York', 1);
insert into users(fullname, address, gender_id)
values ('Sheikh Rashed', 'Burj Khalifa, ap 101, Dubai', 1);
insert into users(fullname, address, gender_id)
values ('Monica Belucci', 'Cinque Terre, 3, villa 3, Liguria', 2);

insert into movies(name) values ('I am legend');
insert into movies(name) values ('La Dolce Vita');
insert into movies(name) values ('Fast And Furious');
insert into movies(name) values ('Django Unchained');
insert into movies(name) values ('Mission Impossible');
insert into movies(name) values ('Some Like It Hot');

insert into movie_rentals(movie_id, user_id) values (1, 1);
insert into movie_rentals(movie_id, user_id) values (4, 1);
insert into movie_rentals(movie_id, user_id) values (3, 2);
insert into movie_rentals(movie_id, user_id) values (5, 2);
insert into movie_rentals(movie_id, user_id) values (2, 3);
insert into movie_rentals(movie_id, user_id) values (6, 3);

select m.name as "Movie", u.fullname as "Ordered By", u.address as "Delivered To"
from movie_rentals
join movies m on m.id = movie_rentals.movie_id
join users u on u.id = movie_rentals.user_id;