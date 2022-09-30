create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('phone', 'HTC', 20, 1000);

create or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
        end if;
    END;
$$;

call insert_data(10, 0, 1);

call insert_data('phone', 'Apple', 20, 2000);

call update_data(0, 0.2, 0);

create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END;
$$;

select f_insert_data('phone', 'Samsung', 30, 1500);

create or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
            select into result count from products where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
            select into result sum(price) from products;
        end if;
        return result;
    end;
$$;

select f_update(5, 0, 3);

select f_update_data(0, 0.2, 0);

create or replace procedure delete_data_if_count_zero(d_count integer, tax float, d_id integer)
language 'plpgsql'
as
$$
    BEGIN
        if d_count = 0 THEN
            delete from products where count = d_count;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
        end if;
    END;
$$;

select f_insert_data('phone', 'Nokia', 0, 500);

call delete_data_if_count_zero(0, 0, 0);

create or replace function f_delete_data_if_count_zero(d_count integer, tax float, d_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if d_count = 0 THEN
            delete from products where count = d_count;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
        end if;
        return result;
    end;
$$;

select f_insert_data('phone', 'Nokia', 0, 500);

select f_delete_data_if_count_zero(0, 0, 0);