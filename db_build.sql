create table if not exists puzzle_round
(
    id        bigserial
        constraint puzzle_round_pk
            primary key,
    moves     integer not null,
    timestamp timestamp default now()
);

create index if not exists puzzle_round_moves_index
    on puzzle_round (moves);
