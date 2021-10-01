create table forma_pagamento (
	id bigint not null auto_increment, 
	descricao varchar(60) not null, 
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(40) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(40) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(40) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(40) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(40) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(40) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
