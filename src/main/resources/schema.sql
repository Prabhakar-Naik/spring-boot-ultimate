-- drop table if exists widgets;
--
-- drop sequence if exists public.widgets_id_seq;
--
-- create table if not exists public.widgets
-- (
--     id      bigint not null
--         constraint widgets_id_seq
--             primary key,
--     name    text,
--     purpose text
-- );
--
-- create sequence widgets_id_seq;
--
-- alter table public.widgets
--     owner to prabha;

DROP TABLE IF EXISTS "books";
DROP TABLE  IF EXISTS "authors";
CREATE TABLE "authors"(
    "id" bigint default nextval('author_id_sq') NOT NULL,
    "name" text,
    "age" integer,
    CONSTRAINT "authors_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "books"(
  "isbn" text NOT NULL,
  "title" text,
  "author_id" bigint,
    CONSTRAINT "books_pkey" PRIMARY KEY ("isbn"),
    CONSTRAINT "fk_author" FOREIGN KEY (author_id)
                    REFERENCES authors(id)
);