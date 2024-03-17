drop table if exists categories cascade;
drop table if exists products cascade;

CREATE TABLE categories
(
    id          uuid    not null,
    title       varchar not null unique,
    description varchar not null,
    constraint categories_pk primary key (id)
);

create table products
(
    id               uuid           not null,
    item             varchar        not null unique,
    title            varchar        not null,
    description      varchar        not null,
    category_id      uuid           not null
        constraint products_categories_id_fk
            references categories,
    price            float not null,
    quantity         integer        not null,
    last_modified_date timestamp      not null,
    creation_date     date      not null,
    constraint products_pk
        primary key (id)
);
