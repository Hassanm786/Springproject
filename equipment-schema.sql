drop table if exists equipment CASCADE; 
create table equipment (id integer AUTO_INCREMENT, colour varchar(255), name varchar(255) not null, price integer, primary key (id));