create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function add_tax_as_statement()
    returns trigger as
$$
    BEGIN
	update products
	set price = price + price * 0.05
	where id = (select id from inserted);
	return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure add_tax_as_statement();

insert into products(name, producer, count, price)
values ('phone', 'Samsung', 10, 1000);

drop trigger add_tax_trigger on products;


create or replace function add_tax_as_row()
    returns trigger as
$$
    BEGIN
	new.price = new.price + new.price * 0.05;
	return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_tax_trigger
    before insert on products
    for each row
    execute procedure add_tax_as_row();

insert into products(name, producer, count, price)
values ('phone', 'Nokia', 20, 500);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function add_data_to_history_of_price()
    returns trigger as
$$
    BEGIN
    insert into history_of_price(name, price, date)
    values(NEW.name, NEW.price, now());
    return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_data_to_history_of_price_trigger
    after insert on products
    for each row
    execute procedure add_data_to_history_of_price();

insert into products(name, producer, count, price)
values ('phone', 'Apple', 10, 1500);