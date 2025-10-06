-- V3: remove TB_ATIVIDADES_COPY_3_2  que foi criada pelo hibernate para uma alteração

DROP TABLE TB_ATIVIDADES_COPY_3_2;

ALTER TABLE tb_atividades ALTER COLUMN dificuldade VARCHAR(255);