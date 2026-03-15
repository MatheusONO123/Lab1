DROP DATABASE IF EXISTS sistema_locacao;
CREATE DATABASE sistema_locacao CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE sistema_locacao;

CREATE TABLE usuario (
  idUsuario BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(100) NOT NULL UNIQUE,
  senha VARCHAR(100) NOT NULL,
  tipoUsuario VARCHAR(20) NOT NULL COMMENT 'ADMIN ou CLIENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE cliente (
  idCliente BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  telefone VARCHAR(20),
  email VARCHAR(100),
  endereco VARCHAR(255),
  cpf VARCHAR(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE espaco (
  idEspaco BIGINT AUTO_INCREMENT PRIMARY KEY,
  nomeEspaco VARCHAR(100) NOT NULL,
  capacidadeMaxima INT,
  descricao VARCHAR(255),
  valorBase DECIMAL(10,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE servico (
  idServico BIGINT AUTO_INCREMENT PRIMARY KEY,
  tipoAlimento VARCHAR(50) NOT NULL,
  descricao VARCHAR(255),
  limiteMaximo INT,
  valorUnitario DECIMAL(10,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE reserva (
  idReserva BIGINT AUTO_INCREMENT PRIMARY KEY,
  idCliente BIGINT NOT NULL,
  idEspaco BIGINT NOT NULL,
  dataReserva DATE NOT NULL,
  horarioInicio TIME NOT NULL,
  horarioFim TIME NOT NULL,
  numeroConvidados INT,
  decoracao BOOLEAN DEFAULT FALSE,
  precoTotal DECIMAL(10,2),
  FOREIGN KEY (idCliente) REFERENCES cliente(idCliente) ON DELETE CASCADE,
  FOREIGN KEY (idEspaco) REFERENCES espaco(idEspaco) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE itensReserva (
  idServicoReserva BIGINT AUTO_INCREMENT PRIMARY KEY,
  idReserva BIGINT NOT NULL,
  idServico BIGINT NOT NULL,
  quantidade INT,
  tipoServico VARCHAR(50),
  FOREIGN KEY (idReserva) REFERENCES reserva(idReserva) ON DELETE CASCADE,
  FOREIGN KEY (idServico) REFERENCES servico(idServico) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO usuario (email, senha, tipoUsuario) VALUES 
('admin@sistema.com', 'admin123', 'ADMIN');