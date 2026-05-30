CREATE TABLE membro (
    id    BIGSERIAL    PRIMARY KEY,
    nome  VARCHAR(150) NOT NULL,
    cargo VARCHAR(20)  NOT NULL
);

INSERT INTO membro (nome, cargo) VALUES
    ('Maria Silva',     'GERENTE'),
    ('Bruno Souza',   'FUNCIONARIO'),
    ('Carla Mendes',  'FUNCIONARIO'),
    ('Diego Silva', 'FUNCIONARIO'),
    ('Elena Costa',   'FUNCIONARIO'),
    ('Joao Luiz',   'FUNCIONARIO'),
    ('Jose Silva',   'FUNCIONARIO'),
    ('Luiz Carlos',   'FUNCIONARIO'),
    ('Paulo Oliveira',   'FUNCIONARIO'),
    ('Pietro Costa',   'GERENTE');
