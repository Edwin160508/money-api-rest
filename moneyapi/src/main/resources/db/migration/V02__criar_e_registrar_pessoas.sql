CREATE TABLE pessoa (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(250),
    numero VARCHAR(16),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    cep VARCHAR(20),
    cidade VARCHAR(50),
    estado VARCHAR(50),
    ativo BOOLEAN

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO pessoa (nome, logradouro,numero,complemento,bairro,cep,cidade,estado, ativo) 
VALUES ('Edwin Lima','Rua Compositor Rosil Cavalcante','855','Bloco K Apto 201','Novo Bodocongó','58431070','Campina Grande','PB',true);

INSERT INTO pessoa (nome, logradouro,numero,complemento,bairro,cep,cidade,estado, ativo) 
VALUES ('Beta Lima','Rua Compositor Rosil Cavalcante','855','Bloco K Apto 201','Novo Bodocongó','58431070','Campina Grande','PB',true);

INSERT INTO pessoa (nome, logradouro,numero,complemento,bairro,cep,cidade,estado, ativo) 
VALUES ('Everton Lima','Rua Manuel Paulino Júnior','191','Apto 201','Itararé','58042000','Campina Grande','PB',true);

INSERT INTO pessoa (nome, logradouro,numero,complemento,bairro,cep,cidade,estado, ativo) 
VALUES ('Andrea Jóice','Rua Manuel Paulino Júnior','191','Apto 201','Itararé','58042000','Campina Grande','PB',true);

INSERT INTO pessoa (nome, logradouro,numero,complemento,bairro,cep,cidade,estado, ativo) 
VALUES ('João Vieira Neto','Rua Compositor Rosil Cavalcante','855','Bloco K Apto 101','Novo Bodocongó','58431070','Campina Grande','PB',true);

INSERT INTO pessoa (nome, logradouro,numero,complemento,bairro,cep,cidade,estado, ativo) 
VALUES ('Maurício Júnior','Rua Compositor Rosil Cavalcante','855','Bloco K Apto 301','Novo Bodocongó','58431070','Campina Grande','PB',true);

INSERT INTO pessoa (nome, logradouro,numero,complemento,bairro,cep,cidade,estado, ativo) 
VALUES ('Andrício Júnior','Rua Compositor Rosil Cavalcante','855','Bloco K Apto 302','Novo Bodocongó','58431070','Campina Grande','PB',true);

INSERT INTO pessoa (nome, logradouro,numero,complemento,bairro,cep,cidade,estado, ativo) 
VALUES ('Ítalo Figueiredo','Rua Compositor Rosil Cavalcante','855','Bloco K Apto 401','Novo Bodocongó','58431070','Campina Grande','PB',true);

INSERT INTO pessoa (nome, logradouro,numero,complemento,bairro,cep,cidade,estado, ativo) 
VALUES ('André Luis','Rua Compositor Rosil Cavalcante','855','Bloco K Apto 402','Novo Bodocongó','58431070','Campina Grande','PB',true);

INSERT INTO pessoa (nome, logradouro,numero,complemento,bairro,cep,cidade,estado, ativo) 
VALUES ('José Victor Medeiros','Rua Compositor Rosil Cavalcante','855','Bloco K Apto 501','Novo Bodocongó','58431070','Campina Grande','PB',true);