create table if not exists users
(
    id              integer           not null
        primary key
        constraint users_id_check
            check (id >= 1),
    amount_of_posts integer default 0 not null,
    username        varchar(255)      not null
);

insert into users(id, amount_of_posts, username)
values
    (10, 10, 'Sergei'),
    (20, 20, 'Evgenii'),
    (30, 30, 'Slava');