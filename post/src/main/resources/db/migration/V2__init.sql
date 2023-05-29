CREATE TABLE IF NOT EXISTS post
(
    id        INTEGER NOT NULL
        PRIMARY KEY
        CONSTRAINT post_id_check
            check (id >= 1),
    author_id INTEGER NOT NULL
        CONSTRAINT post_author_id_check
            check (author_id >= 1),
    posted_at DATE,
    "text"    VARCHAR(255)
);

INSERT INTO post(id, author_id, posted_at, "text")
VALUES (2, 10, '04-01-1987', 'Hooray!, 1 year'),
       (3, 10, '04-01-1988', 'Hooray!, 2 year'),
       (4, 10, '04-01-1989', 'Hooray!, 3 year'),
       (5, 10, '04-01-1990', 'Hooray!, 4 year'),
       (6, 10, '04-01-1991', 'Hooray!, 5 year'),
       (7, 10, '04-01-1992', 'Hooray!, 6 year'),
       (8, 10, '04-01-1993', 'Hooray!, 7 year'),
       (9, 10, '04-01-1993', 'Hooray!, 8 year'),
       (10, 10, '04-01-1993', 'Hooray!, 9 year'),
       (11, 10, '04-01-1993', 'Hooray!, 10 year'),
       (12, 20, '04-01-1987', 'Hooray!, 1 year'),
       (13, 20, '04-01-1988', 'Hooray!, 2 year'),
       (14, 20, '04-01-1989', 'Hooray!, 3 year'),
       (15, 20, '04-01-1990', 'Hooray!, 4 year'),
       (16, 20, '04-01-1991', 'Hooray!, 5 year'),
       (17, 20, '04-01-1992', 'Hooray!, 6 year'),
       (18, 20, '04-01-1993', 'Hooray!, 7 year'),
       (22, 30, '04-01-1987', 'Hooray!, 1 year'),
       (23, 30, '04-01-1988', 'Hooray!, 2 year'),
       (24, 30, '04-01-1989', 'Hooray!, 3 year'),
       (25, 30, '04-01-1990', 'Hooray!, 4 year'),
       (26, 30, '04-01-1991', 'Hooray!, 5 year'),
       (27, 30, '04-01-1992', 'Hooray!, 6 year'),
       (28, 30, '04-01-1993', 'Hooray!, 7 year');