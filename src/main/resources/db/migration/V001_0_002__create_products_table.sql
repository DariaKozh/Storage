drop table if exists products cascade;

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