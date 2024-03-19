drop table if exists categories cascade;

create table categories
(
    id          uuid    not null,
    title       varchar not null unique,
    description varchar not null,
    constraint categories_pk primary key (id)
);