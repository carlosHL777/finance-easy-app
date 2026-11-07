INSERT INTO usuarios (nome, profissao, email, senha, data_nasc, role) VALUES ('Maria Silva', 'Designer', 'maria.silva@email.com', '$2b$10$N9qo8uLOickgx2ZMRZoMye0u4VSbHRGgYHda1uTCzCA9BqttI3yAO', '1998-05-20', 'USER');
INSERT INTO usuarios (nome, profissao, email, senha, data_nasc, role) VALUES ('Joao Pereira', 'Desenvolvedor', 'joao.pereira@email.com', '$2b$10$N9qo8uLOickgx2ZMRZoMye0u4VSbHRGgYHda1uTCzCA9BqttI3yAO', '1995-11-03', 'USER');
INSERT INTO usuarios (nome, profissao, email, senha, data_nasc, role) VALUES ('Ana Costa', 'Professora', 'ana.costa@email.com', '$2b$10$N9qo8uLOickgx2ZMRZoMye0u4VSbHRGgYHda1uTCzCA9BqttI3yAO', '1988-02-14', 'USER');
INSERT INTO usuarios (nome, profissao, email, senha, data_nasc, role) VALUES ('Carlos Souza', 'Contador', 'carlos.souza@email.com', '$2b$10$N9qo8uLOickgx2ZMRZoMye0u4VSbHRGgYHda1uTCzCA9BqttI3yAO', '1982-09-27', 'USER');
INSERT INTO usuarios (nome, profissao, email, senha, data_nasc, role) VALUES ('Beatriz Lima', 'Engenheira', 'beatriz.lima@email.com', '$2b$10$N9qo8uLOickgx2ZMRZoMye0u4VSbHRGgYHda1uTCzCA9BqttI3yAO', '1993-07-09', 'USER');
INSERT INTO usuarios (nome, profissao, email, senha, data_nasc, role) VALUES ('Rafael Alves', 'Motorista', 'rafael.alves@email.com', '$2b$10$N9qo8uLOickgx2ZMRZoMye0u4VSbHRGgYHda1uTCzCA9BqttI3yAO', '1990-12-30', 'USER');

INSERT INTO transacao (descricao, valor, valor_atual, data, tipo, categoria, metodo_pagamento) VALUES ('Salario de marco', 2500.00, 2500.00, '2016-03-10', 'RENDA', 'EMPREGO', 'TRANSFERENCIA');
INSERT INTO transacao (descricao, valor, valor_atual, data, tipo, categoria, metodo_pagamento) VALUES ('Aluguel', 1200.00, 1300.00, '2017-08-22', 'GASTO', 'MORADIA', 'BOLETO');
INSERT INTO transacao (descricao, valor, valor_atual, data, tipo, categoria, metodo_pagamento) VALUES ('Supermercado', 450.75, 849.25, '2018-11-05', 'GASTO', 'ALIMENTACAO', 'CREDITO');
INSERT INTO transacao (descricao, valor, valor_atual, data, tipo, categoria, metodo_pagamento) VALUES ('Projeto de site', 1800.00, 2649.25, '2020-04-18', 'RENDA', 'FREELANCE', 'PIX');
INSERT INTO transacao (descricao, valor, valor_atual, data, tipo, categoria, metodo_pagamento) VALUES ('Show de rock', 320.00, 2329.25, '2022-09-30', 'GASTO', 'LAZER', 'DEBITO');
INSERT INTO transacao (descricao, valor, valor_atual, data, tipo, categoria, metodo_pagamento) VALUES ('Consulta medica', 280.50, 2048.75, '2024-01-14', 'GASTO', 'SAUDE', 'DINHEIRO');
INSERT INTO transacao (descricao, valor, valor_atual, data, tipo, categoria, metodo_pagamento) VALUES ('Venda de notebook usado', 1500.00, 3548.75, '2026-06-08', 'RENDA', 'VENDA', 'PIX');

INSERT INTO metas (nome_meta, descricao, valor_total, valor_arrecadado, percentual, categoria, data) VALUES ('Reserva de emergencia', 'Seis meses de despesas guardados', 15000.00, 9000.00, 60.00, 'EMERGENCIA', '2016-02-15');
INSERT INTO metas (nome_meta, descricao, valor_total, valor_arrecadado, percentual, categoria, data) VALUES ('Viagem para a Europa', 'Passagens e hospedagem por 15 dias', 20000.00, 20000.00, 100.00, 'VIAGEM', '2018-06-01');
INSERT INTO metas (nome_meta, descricao, valor_total, valor_arrecadado, percentual, categoria, data) VALUES ('Notebook novo', 'Troca do computador de trabalho', 6000.00, 1500.00, 25.00, 'COMPRA', '2019-09-10');
INSERT INTO metas (nome_meta, descricao, valor_total, valor_arrecadado, percentual, categoria, data) VALUES ('Entrada do apartamento', 'Entrada do financiamento do imovel', 80000.00, 12000.00, 15.00, 'POUPANCA', '2021-01-20');
INSERT INTO metas (nome_meta, descricao, valor_total, valor_arrecadado, percentual, categoria, data) VALUES ('Curso de especializacao', 'Pos-graduacao em analise de dados', 12000.00, 4500.00, 37.50, 'EDUCACAO', '2022-08-05');
INSERT INTO metas (nome_meta, descricao, valor_total, valor_arrecadado, percentual, categoria, data) VALUES ('Carro novo', 'Troca do automovel atual', 50000.00, 47500.00, 95.00, 'AUTOMOVEL', '2024-03-12');
INSERT INTO metas (nome_meta, descricao, valor_total, valor_arrecadado, percentual, categoria, data) VALUES ('Fundo de aposentadoria', 'Complemento da previdencia', 100000.00, 0.00, 0.00, 'APOSENTADORIA', '2026-04-01');