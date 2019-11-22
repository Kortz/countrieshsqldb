drop table if exists country_language cascade;
drop table if exists country_currency cascade;
drop table if exists language cascade;
drop table if exists border cascade;
drop table if exists currency cascade;
drop table if exists country cascade;

create table if not exists 
    country (id INT IDENTITY PRIMARY KEY, name varchar(150),capital varchar(150),population BIGINT);
create table if not exists 
    currency (id INT IDENTITY PRIMARY KEY, code varchar(45), name varchar(150), symbol varchar(5));
create table if not exists
    country_currency(id INT IDENTITY PRIMARY KEY, country_id INT FOREIGN KEY REFERENCES country(id) ON DELETE CASCADE, currency_id INT FOREIGN KEY REFERENCES currency(id) ON DELETE CASCADE);
create table if not exists 
    border (id INT IDENTITY PRIMARY KEY, country_id INT FOREIGN KEY REFERENCES country(id) ON DELETE CASCADE, country_border_id INT);
create table if not exists 
    language (id INT IDENTITY PRIMARY KEY, iso639_1 varchar(5), iso639_2 varchar(5), name varchar(150), nativeName varchar(150));
create table if not exists
    country_language(id INT IDENTITY PRIMARY KEY, country_id INT FOREIGN KEY REFERENCES country(id) ON DELETE CASCADE, language_id INT FOREIGN KEY REFERENCES language(id) ON DELETE CASCADE);
