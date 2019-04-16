DROP TABLE IF exists public.contacts;

CREATE TABLE public.contacts (
  id              int not null IDENTITY,
  first_name      varchar(100),
  last_name       varchar(100),
  email           varchar(100),
  street          varchar(100),
  postal          varchar(10),
  city            varchar(100),
  country         varchar(100),
  primary key (id)
);
INSERT INTO public.contacts (first_name, last_name, email, street, postal, city, country) VALUES('Mike', 'Norman', 'normanm@algonquincollge.com', '123 Somewhere', 'K2G 1V8', 'Nepean ON', 'Canada');
INSERT INTO public.contacts (first_name, last_name, email, street, postal, city, country) VALUES('Helen', 'Norman', 'helen@example.com', '123 Somewhere', 'K2G 1V8', 'Nepean ON', 'Canada');
