create table cidade (
        id BIGSERIAL primary key,
        nome_cidade varchar(80) not null,
        nome_estado varchar(80) not null
    );