--Script de criacao das tabelas
CREATE TABLE tb_beneficiarios (id numeric not null, nome varchar(50), telefone varchar(14), data_nascimento date, data_inclusao date, data_atualizacao date, CONSTRAINT tb_beneficiarios_pk PRIMARY KEY (id));
CREATE TABLE tb_documentos (id numeric not null, tipo_documento varchar(50), descricao varchar(50), data_inclusao date, data_atualizacao date, beneficiario_id numeric not null, CONSTRAINT fk_beneficiario FOREIGN KEY (beneficiario_id) REFERENCES tb_beneficiarios(id));
--Massa de testes beneficiarios
INSERT INTO tb_beneficiarios (id, nome, telefone, data_nascimento, data_inclusao, data_atualizacao) VALUES (1, 'maria', '(99)99999-9999', DATE '2011-04-22', DATE '2024-02-13', DATE '2024-02-13');
INSERT INTO tb_beneficiarios (id, nome, telefone, data_nascimento, data_inclusao, data_atualizacao) VALUES (2, 'joao', '(88)98888-8888', DATE '2014-05-31', DATE '2024-02-13', DATE '2024-02-13');
--Massa de testes documentos
INSERT INTO tb_documentos (id, tipo_documento, descricao, data_inclusao, data_atualizacao, beneficiario_id) VALUES (1, 'RG', 'Documento RG da Maria', DATE '2024-02-13', DATE '2024-02-13', 1);
commit;