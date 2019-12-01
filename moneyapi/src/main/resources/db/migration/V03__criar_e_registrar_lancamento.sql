CREATE TABLE lancamento (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor DECIMAL(40,2) NOT NULL,
    observacao VARCHAR(100),
    tipo VARCHAR(20) NOT NULL,
    codigo_categoria BIGINT(20) NOT NULL,
    codigo_pessoa BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
    FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Salário mensal','2019-06-10',null, 6500.00, 'Distribuição de lucros','RECEITA',1,1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Bahamas','2019-02-10','2019-02-10', 100.32, null,'DESPESA',2,2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Top Club','2019-06-10',null, 120, null,'RECEITA',3,3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('CEMIG','2019-02-10','2019-02-10', 110.44, 'Geração','RECEITA',3,4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('DMAE','2019-06-10',null, 200.30, null,'DESPESA',3,5);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Extra','2019-03-10','2019-03-10', 1010.32, null,'RECEITA',4,6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Bahamas','2019-06-10',null, 500, null,'RECEITA',1,7);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Top Club','2019-03-10','2019-03-10', 400.32, null,'DESPESA',4,8);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Despachante','2019-06-10',null, 123.64, 'Multas','DESPESA',3,9);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Pneus','2019-04-10','2019-04-10', 665.33, null,'RECEITA',5,10);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Café','2019-04-10',null, 8.32, null,'DESPESA',1,5);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Eletrônicos','2019-04-10','2019-04-10', 2100.32, null,'DESPESA',5,4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Instrumentos','2019-06-10',null, 1040.03, null,'DESPESA',4,3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Café','2019-04-10','2019-04-10', 4.23, null,'DESPESA',4,2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)VALUES('Lanche','2019-06-10',null, 10.23, null,'DESPESA',4,1);