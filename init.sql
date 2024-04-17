create database if not exists expense_manager;

create or replace table expense_manager.category
(
    category_id bigint auto_increment
        primary key,
    name        varchar(100) not null
);

create or replace table expense_manager.payment_method
(
    payment_method_id bigint auto_increment
        primary key,
    name              varchar(100) not null
);

create or replace table expense_manager.user
(
    user_id  bigint auto_increment
        primary key,
    username varchar(100) not null,
    password varchar(100) not null,
    balance  decimal      null
);

create or replace table expense_manager.transaction
(
    transaction_id    bigint auto_increment
        primary key,
    amount            decimal                    not null,
    date              date                       not null,
    description       varchar(255)               null,
    user_id           bigint                     not null,
    category_id       bigint                     not null,
    payment_method_id bigint                     not null,
    type              enum ('income', 'outcome') null,
    constraint expense_category_FK
        foreign key (category_id) references expense_manager.category (category_id),
    constraint expense_payment_method_FK
        foreign key (payment_method_id) references expense_manager.payment_method (payment_method_id),
    constraint expense_user_FK
        foreign key (user_id) references expense_manager.user (user_id)
);

INSERT INTO expense_manager.category (category_id, name) VALUES (1, 'food');
INSERT INTO expense_manager.category (category_id, name) VALUES (2, 'transport');
INSERT INTO expense_manager.category (category_id, name) VALUES (3, 'entertainment');
INSERT INTO expense_manager.category (category_id, name) VALUES (4, 'clothes');
INSERT INTO expense_manager.category (category_id, name) VALUES (5, 'health');
INSERT INTO expense_manager.category (category_id, name) VALUES (6, 'education');
INSERT INTO expense_manager.category (category_id, name) VALUES (7, 'technology');
INSERT INTO expense_manager.category (category_id, name) VALUES (8, 'sport');
INSERT INTO expense_manager.category (category_id, name) VALUES (9, 'travel');
INSERT INTO expense_manager.category (category_id, name) VALUES (10, 'insurance');
INSERT INTO expense_manager.category (category_id, name) VALUES (11, 'other');

INSERT INTO expense_manager.payment_method (payment_method_id, name) VALUES (1, 'cash');
INSERT INTO expense_manager.payment_method (payment_method_id, name) VALUES (2, 'card');
INSERT INTO expense_manager.payment_method (payment_method_id, name) VALUES (3, 'blik');
INSERT INTO expense_manager.payment_method (payment_method_id, name) VALUES (4, 'online payment');