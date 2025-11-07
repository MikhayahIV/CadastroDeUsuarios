-- V2: Migration para adicionar a Coluna Cargo na tabela de cadastro de Usuarios

ALTER TABLE tb_cadastro_de_usuarios
ADD COLUMN Cargo VARCHAR(255);