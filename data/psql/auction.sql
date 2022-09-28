create table art_centers (
    id serial primary key,
    name varchar(255)
);

insert into art_centers(name) values('Musee du Louvre');
insert into art_centers(name) values('Metropolitan Museum of Art');
insert into art_centers(name) values('Uffizi Gallery');
insert into art_centers(name) values('Tate Modern');


create table artists (
    id serial primary key,
    name varchar(255)
);

insert into artists(name) values('Leonardo Da Vinci');
insert into artists(name) values('Johannes Vermeer');
insert into artists(name) values('Vincent van Gogh');
insert into artists(name) values('Pablo Picasso');

create table paintings (
    id serial primary key,
    name varchar(255),
    artist_id integer references artists(id)
);

insert into paintings(name, artist_id) values('Les Demoiselles d’Avignon', 4);
insert into paintings(name, artist_id) values('Guernica', 4);
insert into paintings(name, artist_id) values('The Old Guitarist', 4);
insert into paintings(name, artist_id) values('Figures at the Seaside', 4);
insert into paintings(name, artist_id) values('The old fisherman', 4);
insert into paintings(name, artist_id) values('Mona Lisa', 1);
insert into paintings(name, artist_id) values('Girl with a Pearl Earring', 2);
insert into paintings(name, artist_id) values('The Starry Night', 3);


create table auctions (
    id serial primary key,
    name varchar(255),
    painting_id integer references paintings(id),
    art_center_id integer references art_centers(id)
);

insert into auctions(name, painting_id, art_center_id) values('Tokyo', 8, 3);
insert into auctions(name, painting_id, art_center_id) values('London', 7, 2);
insert into auctions(name, painting_id, art_center_id) values('Paris', 6, 1);
insert into auctions(name, painting_id, art_center_id) values('Paris', 5, 4);
insert into auctions(name, painting_id, art_center_id) values('Abu Dhabi', 1, 4);
insert into auctions(name, painting_id, art_center_id) values('London', 2, 4);
insert into auctions(name, painting_id, art_center_id) values('London', 3, 4);
insert into auctions(name, painting_id, art_center_id) values('London', 4, 4);

create view show_Picasso_paintings_sold_in_London
as select auctions.name "Auctions", art_centers.name "Art Center Name",
paintings.name "Painting", artists.name "Artists Name"
from auctions
join paintings on auctions.painting_id = paintings.id
join artists on paintings.artist_id = artists.id
join art_centers on auctions.art_center_id = art_centers.id
where artists.name = 'Pablo Picasso' and auctions.name = 'London';

select * from show_Picasso_paintings_sold_in_London;
