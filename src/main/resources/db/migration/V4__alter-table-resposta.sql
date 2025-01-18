ALTER TABLE resposta DROP FOREIGN KEY fk_resposta_topico;
ALTER TABLE resposta DROP FOREIGN KEY fk_resposta_autor;

ALTER TABLE resposta ADD CONSTRAINT fk_resposta_topico
FOREIGN KEY (topico_id) REFERENCES topicos(id);

ALTER TABLE resposta ADD CONSTRAINT fk_resposta_autor
FOREIGN KEY (autor_id) REFERENCES usuarios(id);
