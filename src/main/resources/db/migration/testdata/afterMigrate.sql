insert into cozinha (id, nome) values (51, 'Taillandesa') ON CONFLICT DO NOTHING;
insert into cozinha (id, nome) values (52, 'Indiana') ON CONFLICT DO NOTHING;

insert into estado (id, nome) values (51, 'Minas Gerais') ON CONFLICT DO NOTHING;
insert into estado (id, nome) values (52, 'São Paulo') ON CONFLICT DO NOTHING;
insert into estado (id, nome) values (53, 'Ceará') ON CONFLICT DO NOTHING;

insert into cidade (id, nome, estado_id) values (51, 'Uberlândia', 51) ON CONFLICT DO NOTHING;
insert into cidade (id, nome, estado_id) values (52, 'Belo Horizonte', 51) ON CONFLICT DO NOTHING;
insert into cidade (id, nome, estado_id) values (53, 'São Paulo', 52) ON CONFLICT DO NOTHING;
insert into cidade (id, nome, estado_id) values (54, 'Campinas', 52) ON CONFLICT DO NOTHING;
insert into cidade (id, nome, estado_id) values (55, 'Fortaleza', 53) ON CONFLICT DO NOTHING;

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (51, 'Thai Gourmet', 10, 51, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 51, '38400-999', 'Rua João Pinheiro', '1000', 'Centro') ON CONFLICT DO NOTHING;
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (52, 'Thai Delivery', 9.50, 51, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) ON CONFLICT DO NOTHING;
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (53, 'Tuk Tuk Comida Indiana', 15, 52, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) ON CONFLICT DO NOTHING;


insert into forma_pagamento (id, descricao) values (51, 'Cartão de crédito') ON CONFLICT DO NOTHING;
insert into forma_pagamento (id, descricao) values (52, 'Cartão de débito') ON CONFLICT DO NOTHING;
insert into forma_pagamento (id, descricao) values (53, 'Dinheiro') ON CONFLICT DO NOTHING;

insert into permissao (id, nome, descricao) values (51, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas') ON CONFLICT DO NOTHING;
insert into permissao (id, nome, descricao) values (52, 'EDITAR_COZINHAS', 'Permite editar cozinhas') ON CONFLICT DO NOTHING;

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (51, 51), (51, 52), (51, 53), (52, 53), (53, 52), (53, 53) ON CONFLICT DO NOTHING;
