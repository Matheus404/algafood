    insert into cozinha (id, nome) values (51, 'Taillandesa');
	insert into cozinha (id, nome) values (52, 'Indiana');
	
	insert into restaurante (id, nome, taxa_frete, cozinha_id) values (51, 'Thai Gourmet', 10, 51);
    insert into restaurante (id, nome, taxa_frete, cozinha_id) values (52, 'Thai Delivery', 9.50, 51);
    insert into restaurante (id, nome, taxa_frete, cozinha_id) values (53, 'Tuk Tuk Comida Indiana', 15, 52); 
	
	insert into estado (id, nome) values (51, 'Minas Gerais');
    insert into estado (id, nome) values (52, 'São Paulo');
    insert into estado (id, nome) values (53, 'Ceará');

    insert into cidade (id, nome, estado_id) values (51, 'Uberlândia', 51);
    insert into cidade (id, nome, estado_id) values (52, 'Belo Horizonte', 51);
    insert into cidade (id, nome, estado_id) values (53, 'São Paulo', 52);
    insert into cidade (id, nome, estado_id) values (54, 'Campinas', 52);
    insert into cidade (id, nome, estado_id) values (55, 'Fortaleza', 53);

    insert into forma_pagamento (id, descricao) values (51, 'Cartão de crédito');
    insert into forma_pagamento (id, descricao) values (52, 'Cartão de débito');
    insert into forma_pagamento (id, descricao) values (53, 'Dinheiro');

    insert into permissao (id, nome, descricao) values (51, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
    insert into permissao (id, nome, descricao) values (52, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
    