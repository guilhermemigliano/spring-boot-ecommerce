drop user 'user'@'localhost';
drop schema ecommerce;

create schema ecommerce;

use ecommerce;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on ecommerce.* to user@'localhost';

-- Cria tabela cliente
create table cliente (
cli_id bigint unsigned not null auto_increment,
cli_nome varchar(20) not null,
cli_email varchar(100) not null,
cli_senha varchar(100) not null,
primary key (cli_id),
unique key uni_cli_nome (cli_nome),
unique key uni_cli_email (cli_email)
);

-- Cria tabela Pedido
create table pedido (
  ped_id bigint unsigned not null auto_increment,
  cli_id bigint unsigned not null,
  ped_nome varchar(50) not null,
  ped_valor float not null,  
  primary key (ped_id, cli_id),
  unique key uni_ped_nome (ped_nome),
  foreign key pedcli_cli_fk (cli_id) references cliente (cli_id) on delete restrict on update cascade  
);

-- Cria tabela Autorização
create table aut_autorizacao (
    aut_id bigint unsigned not null auto_increment,
    aut_nome varchar(20) not null,
    primary key (aut_id),
    unique key uni_aut_nome (aut_nome)
);

-- Cria tabela usuarios autorizacao
create table uau_usuario_autorizacao (
    cli_id bigint unsigned not null,
    aut_id bigint unsigned not null,
    primary key (cli_id, aut_id),
    foreign key uau_usr_fk (cli_id) references cliente (cli_id) on delete restrict on update cascade,
    foreign key uau_aut_fk (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

-- Cliente
insert into cliente (cli_nome, cli_email, cli_senha) values ('Guilherme', 'guilherme@fatec.com.br', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
insert into cliente (cli_nome, cli_email, cli_senha) values ('Maria', 'maria@fatec.com.br', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
insert into cliente (cli_nome, cli_email, cli_senha) values ('Joana', 'joana@fatec.com.br', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
insert into cliente (cli_nome, cli_email, cli_senha) values ('admin', 'admin@fatec.com.br', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');

-- Pedido
insert into pedido (ped_nome, ped_valor, cli_id) values ("Pedido-01", 155.99, 2);
insert into pedido (ped_nome, ped_valor, cli_id) values ("Pedido-02", 160.10, 2);
insert into pedido (ped_nome, ped_valor, cli_id) values ("Pedido-03", 277.00, 3);
insert into pedido (ped_nome, ped_valor, cli_id) values ("Pedido-04", 299.99, 3);


-- Autorização
insert into aut_autorizacao(aut_nome) values ('ROLE_ADMIN');
insert into aut_autorizacao(aut_nome) values ('ROLE_USER');

-- Cliente autorização
insert into uau_usuario_autorizacao values (1,1);
insert into uau_usuario_autorizacao values (2,2);
insert into uau_usuario_autorizacao values (3,2);
insert into uau_usuario_autorizacao values (4,1);







-- delete from uau_usuario_autorizacao where cli_id > 3;
-- delete from aut_autorizacao where aut_id > 2;
-- delete from cliente where cli_id > 3;
-- delete from pedido_cliente where cli_id = 1;
-- delete from pedido where ped_id > 4;


-- select * from uau_usuario_autorizacao;
-- select * from aut_autorizacao;
-- select * from cliente;