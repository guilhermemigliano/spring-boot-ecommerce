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
  ped_valor float not null,
  cli_id bigint unsigned not null,
  primary key (ped_id, cli_id),
  foreign key cli_id_fk (cli_id) references cliente (cli_id) on delete restrict on update cascade
);

-- Cria tabela Produto
create table produto(
    prod_id bigint unsigned not null auto_increment,
    prod_nome varchar(20) not null,
    prod_preco float not null,
    primary key (prod_id)
);


-- Cria tabela Carrinho

create table carrinho (
  car_id bigint unsigned not null auto_increment,
  prod_id bigint unsigned not null,
  car_prod_qtd float not null,
  ped_id bigint unsigned not null,
  primary key (car_id, ped_id),
  foreign key ped_pedido_fk (ped_id) references pedido (ped_id) on delete restrict on update cascade,
  foreign key car_prodid_fk (prod_id) references produto (prod_id) on delete restrict on update cascade
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
insert into cliente (cli_nome, cli_email, cli_senha) values ('Guilherme', 'guilherme@fatec.com.br', 'senha12345');
insert into cliente (cli_nome, cli_email, cli_senha) values ('Maria', 'maria@fatec.com.br', 'senha12345');
insert into cliente (cli_nome, cli_email, cli_senha) values ('Joana', 'joana@fatec.com.br', 'senha12345');

-- Pedido
insert into pedido (ped_valor, cli_id) values (40, 2);
insert into pedido (ped_valor, cli_id) values (25, 2);
insert into pedido (ped_valor, cli_id) values (100, 3);
insert into pedido (ped_valor, cli_id) values (50, 3);

-- Produto
insert into produto (prod_nome, prod_preco) values ('Relógio', 10);
insert into produto (prod_nome, prod_preco) values ('Televisão', 30);
insert into produto (prod_nome, prod_preco) values ('Computador', 15);
insert into produto (prod_nome, prod_preco) values ('Smartphone', 50);
insert into produto (prod_nome, prod_preco) values ('Teclado', 25);

-- Carrinho
insert into carrinho (prod_id, car_prod_qtd, ped_id) values (1, 1, 1);
insert into carrinho (prod_id, car_prod_qtd, ped_id) values (2, 1, 1);

insert into carrinho (prod_id, car_prod_qtd, ped_id) values (1, 1, 2);
insert into carrinho (prod_id, car_prod_qtd, ped_id) values (3, 1, 2);

insert into carrinho (prod_id, car_prod_qtd, ped_id) values (4, 2, 3);

insert into carrinho (prod_id, car_prod_qtd, ped_id) values (1, 1, 4);
insert into carrinho (prod_id, car_prod_qtd, ped_id) values (3, 1, 4);
insert into carrinho (prod_id, car_prod_qtd, ped_id) values (5, 1, 4);


-- Autorização
insert into aut_autorizacao(aut_nome) values ('ROLE_ADMIN');
insert into aut_autorizacao(aut_nome) values ('ROLE_USER');

-- Cliente autorização
insert into uau_usuario_autorizacao values (1,1);
insert into uau_usuario_autorizacao values (2,2);
insert into uau_usuario_autorizacao values (3,2);






-- delete from uau_usuario_autorizacao where cli_id > 3;
-- delete from aut_autorizacao where aut_id > 2;
-- delete from cliente where cli_id > 3;

-- select * from uau_usuario_autorizacao;
-- select * from aut_autorizacao;
-- select * from cliente;