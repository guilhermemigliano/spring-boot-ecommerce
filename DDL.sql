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
  car_prod_id varchar(50) not null,
  car_pord_qtd float not null,
  ped_id bigint unsigned not null,
  primary key (car_id, ped_id),
  foreign key ped_pedido_fk (ped_id) references pedido (ped_id) on delete restrict on update cascade
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







