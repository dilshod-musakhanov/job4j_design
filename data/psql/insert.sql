insert into role(name) values ('admin');
insert into role(name) values ('visitor');
insert into role(name) values ('tester');

insert into users(name, role_id) values ('Kate', '1');
insert into users(name, role_id) values ('Lee', '2');
insert into users(name, role_id) values ('Ali', '3');

insert into rules(name) values ('Limited access');
insert into rules(name) values ('Unlimited access');
insert into rules(name) values ('Access to test files');

insert into role_rules(role_id, rules_id) values ('1','2');
insert into role_rules(role_id, rules_id) values ('2','1');
insert into role_rules(role_id, rules_id) values ('3','3');

insert into category(name) values ('sell');
insert into category(name) values ('buy');
insert into category(name) values ('refund');

insert into state(name) values ('processed');
insert into state(name) values ('failed');
insert into state(name) values ('pending');

insert into item(name, users_id, category_id, state_id) values ('PS-5', '2', '2', '1');
insert into item(name, users_id, category_id, state_id) values ('Kindle-10', '1', '3', '3');
insert into item(name, users_id, category_id, state_id) values ('Kindle-10', '3', '3', '2');

insert into comments(name, item_id) values ('Perfect inside & outside', '1');
insert into comments(name, item_id) values ('Broken. Shitty one', '2');

insert into attachs(name, item_id) values ('picture1.png', '1');
insert into attachs(name, item_id) values ('picture2.png', '2');
