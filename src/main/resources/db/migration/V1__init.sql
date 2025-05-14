create table books(
  id bigserial primary key,
  title varchar(120) not null,
  price numeric not null,
  published_at date,
  deleted boolean default false
);
