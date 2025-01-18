CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data TIMESTAMP NOT NULL,
    autor_id BIGINT,
    status VARCHAR(50),
    curso VARCHAR(100) NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);