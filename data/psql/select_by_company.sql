CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_key PRIMARY KEY (id)
);

INSERT INTO company VALUES
(1, 'Adidas'),
(2, 'Nike'),
(3, 'Puma'),
(4, 'Reebok'),
(5, 'Vans');

INSERT INTO person VALUES
(1, 'Alla', 5),
(2, 'Ben', 4),
(3, 'Tom', 2),
(4, 'Bred', 3),
(5, 'Ali', 5),
(6, 'Paul', 5),
(7, 'Anna', 5),
(8, 'Lee', 5),
(9, 'Kim', 1),
(10, 'Joe', 1),
(11, 'John', 1),
(12, 'Luke', 1),
(13, 'Mia', 1),
(14, 'Kate', 2),
(15, 'Ira', 2),
(16, 'Bob', 3),
(17, 'Sonya', 4),
(18, 'Lucy', 4),
(19, 'Leo', 2),

SELECT p.name staff, c.name company
FROM person p
LEFT JOIN company c
ON p.company_id = c.id
WHERE c.id <> 5;

SELECT c.name company, COUNT(c.name) staff
FROM company c
LEFT JOIN person p
ON c.id = p.company_id
GROUP BY c.name
HAVING count(c.name) = (SELECT COUNT(p.company_id)
                       FROM person p
                       GROUP BY p.company_id
                       ORDER BY COUNT DESC
                       LIMIT 1);


