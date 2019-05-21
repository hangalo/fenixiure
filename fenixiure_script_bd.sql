SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

CREATE SCHEMA IF NOT EXISTS `clinica_tadeu` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;

CREATE TABLE IF NOT EXISTS `clinica_tadeu`.`consulta` (
  `id_consulta` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Id da Tabela consulta',
  `data_consulta` DATE NOT NULL COMMENT 'Data da Consulta',
  `hora_consulta` TIME NOT NULL COMMENT 'Hora da Consulta' /* comment truncated */ /*HH:MM*/,
  `valor_consulta` DECIMAL(6,2) NOT NULL COMMENT 'Valor da Consulta' /* comment truncated */ /*Ex.: 1250,50 - 850,00
*/,
  `observacao_consulta` VARCHAR(200) NULL DEFAULT NULL COMMENT 'Observação Sobre a Consul' /* comment truncated */ /*a
Campo não obrigatório*/,
  `paciente_id_paciente` INT(11) NOT NULL,
  `medico_id_medico` INT(11) NOT NULL,
  `medico_especialidade_id_especialidade` INT(11) NOT NULL,
  PRIMARY KEY (`id_consulta`, `paciente_id_paciente`, `medico_id_medico`, `medico_especialidade_id_especialidade`),
  UNIQUE INDEX `id_consulta_UNIQUE` (`id_consulta` ASC),
  INDEX `fk_consulta_paciente_idx` (`paciente_id_paciente` ASC),
  INDEX `fk_consulta_medico1_idx` (`medico_id_medico` ASC, `medico_especialidade_id_especialidade` ASC),
  CONSTRAINT `fk_consulta_paciente`
    FOREIGN KEY (`paciente_id_paciente`)
    REFERENCES `clinica_tadeu`.`paciente` (`id_paciente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_consulta_medico1`
    FOREIGN KEY (`medico_id_medico` , `medico_especialidade_id_especialidade`)
    REFERENCES `clinica_tadeu`.`medico` (`id_medico` , `especialidade_id_especialidade`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `clinica_tadeu`.`especialidade` (
  `id_especialidade` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Id da Tabela especialidade',
  `descricao_especialidade` VARCHAR(40) NOT NULL COMMENT 'Descrição (nome) da Especialidade Médica',
  PRIMARY KEY (`id_especialidade`),
  UNIQUE INDEX `id_especialidade_UNIQUE` (`id_especialidade` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Tabela de Especialidades Médica' /* comment truncated */ /*
*/;

CREATE TABLE IF NOT EXISTS `clinica_tadeu`.`medico` (
  `id_medico` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Id da Tabela medico',
  `nome_medico` VARCHAR(40) NOT NULL COMMENT 'Nome do Médico',
  `crm_medico` VARCHAR(8) NOT NULL COMMENT 'CRM do Médic' /* comment truncated */ /*
Máximo 8 números*/,
  `data_nasc_medico` DATE NOT NULL COMMENT 'Data de Nascimento do Médico',
  `especialidade_id_especialidade` INT(11) NOT NULL,
  PRIMARY KEY (`id_medico`, `especialidade_id_especialidade`),
  UNIQUE INDEX `id_medico_UNIQUE` (`id_medico` ASC),
  INDEX `fk_medico_especialidade1_idx` (`especialidade_id_especialidade` ASC),
  CONSTRAINT `fk_medico_especialidade1`
    FOREIGN KEY (`especialidade_id_especialidade`)
    REFERENCES `clinica_tadeu`.`especialidade` (`id_especialidade`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Tabela de Médicos';

CREATE TABLE IF NOT EXISTS `clinica_tadeu`.`medico_has_especialidade` (
  `especialidade_id_especialidade` INT(11) NOT NULL,
  PRIMARY KEY (`especialidade_id_especialidade`),
  INDEX `fk_medico_has_especialidade_especialidade1_idx` (`especialidade_id_especialidade` ASC),
  CONSTRAINT `fk_medico_has_especialidade_especialidade1`
    FOREIGN KEY (`especialidade_id_especialidade`)
    REFERENCES `clinica_tadeu`.`especialidade` (`id_especialidade`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `clinica_tadeu`.`paciente` (
  `id_paciente` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Id da Tabela paciente',
  `nome_paciente` VARCHAR(40) NOT NULL COMMENT 'Nome do Paciente',
  `cpf_paciente` VARCHAR(14) NOT NULL COMMENT 'CPF do Paciente - Valor Únic' /* comment truncated */ /*
Será utilizado máscara
999.999.999-11*/,
  `data_nasc_paciente` DATE NOT NULL COMMENT 'Data de Nascimento do Paciente' /* comment truncated */ /*99/99/9999*/,
  `telefone_paciente` VARCHAR(16) NOT NULL COMMENT 'Telefone do Paciente' /* comment truncated */ /*Será utilizado máscara
(99) 9 9999-9999*/,
  `endereco_paciente` VARCHAR(80) NOT NULL COMMENT 'Endereço do Paciente',
  `bairro_paciente` VARCHAR(40) NOT NULL COMMENT 'Bairro do Paciente',
  `cidade_paciente` VARCHAR(40) NOT NULL COMMENT 'Cidade do Paciente',
  `uf_paciente` VARCHAR(2) NOT NULL COMMENT 'UF do Paciente' /* comment truncated */ /*Apenas 2 Letras
Ex.: DF*/,
  `peso_paciente` DECIMAL(5,2) NOT NULL COMMENT 'Peso do Paciente' /* comment truncated */ /*Ex.: 75,5 - 110,75*/,
  `altura_paciente` DECIMAL(5,2) NOT NULL COMMENT 'Altura do Paciente',
  `imc_paciente` DECIMAL(5,2) NOT NULL COMMENT 'IMC do Paciente' /* comment truncated */ /*Será calculado pela aplicação
Usuário não entrará com o dado direto*/,
  PRIMARY KEY (`id_paciente`),
  UNIQUE INDEX `id_paciente_UNIQUE` (`id_paciente` ASC),
  UNIQUE INDEX `cpf_paciente_UNIQUE` (`cpf_paciente` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Tabela de Pacientes';

CREATE SCHEMA IF NOT EXISTS `fenixiure2` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`advogado` (
  `id_advogado` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_advogado` VARCHAR(45) NULL DEFAULT NULL,
  `sobrenome_advogado` VARCHAR(45) NULL DEFAULT NULL,
  `data_inicio_funcoes` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id_advogado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`especie_processo` (
  `id_especie_processo` INT(11) NOT NULL AUTO_INCREMENT,
  `especie_processo` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`id_especie_processo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`estado_processo` (
  `id_estado_processo` INT(11) NOT NULL AUTO_INCREMENT,
  `estado_processo` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_estado_processo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`juiz` (
  `id_juiz` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_juiz` VARCHAR(45) NULL DEFAULT NULL,
  `sobrenome_juiz` VARCHAR(45) NULL DEFAULT NULL,
  `data_inicio_funcoes` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id_juiz`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`municipio` (
  `id_municipio` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_municipio` VARCHAR(45) NULL DEFAULT NULL,
  `id_provincia` INT(11) NOT NULL,
  PRIMARY KEY (`id_municipio`),
  INDEX `fk_municipio_provincia_idx` (`id_provincia` ASC),
  CONSTRAINT `fk_municipio_provincia`
    FOREIGN KEY (`id_provincia`)
    REFERENCES `fenixiure2`.`provincia` (`id_provincia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`processo` (
  `id_processo` INT(11) NOT NULL AUTO_INCREMENT,
  `numero_processo` VARCHAR(45) NULL DEFAULT NULL,
  `id_especie_processo` INT(11) NOT NULL,
  `id_requente` INT(11) NOT NULL,
  `id_requerido` INT(11) NOT NULL,
  `data_entrada` DATE NULL DEFAULT NULL,
  `id_juiz` INT(11) NOT NULL,
  PRIMARY KEY (`id_processo`),
  INDEX `fk_processo_requente1_idx` (`id_requente` ASC),
  INDEX `fk_processo_requerido1_idx` (`id_requerido` ASC),
  INDEX `fk_processo_especie_processo1_idx` (`id_especie_processo` ASC),
  INDEX `fk_processo_juiz1_idx` (`id_juiz` ASC),
  CONSTRAINT `fk_processo_especie_processo1`
    FOREIGN KEY (`id_especie_processo`)
    REFERENCES `fenixiure2`.`especie_processo` (`id_especie_processo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_processo_juiz1`
    FOREIGN KEY (`id_juiz`)
    REFERENCES `fenixiure2`.`juiz` (`id_juiz`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_processo_requente1`
    FOREIGN KEY (`id_requente`)
    REFERENCES `fenixiure2`.`requente` (`id_requente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_processo_requerido1`
    FOREIGN KEY (`id_requerido`)
    REFERENCES `fenixiure2`.`requerido` (`id_requerido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`provincia` (
  `id_provincia` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_provincia` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_provincia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`requente` (
  `id_requente` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_requente` VARCHAR(45) NULL DEFAULT NULL,
  `casa_requerente` VARCHAR(45) NULL DEFAULT NULL,
  `rua_requerente` VARCHAR(45) NULL DEFAULT NULL,
  `bairro_requerente` VARCHAR(45) NULL DEFAULT NULL,
  `id_municipio` INT(11) NOT NULL,
  `id_advogado` INT(11) NOT NULL,
  `id_tipo` INT(11) NOT NULL,
  PRIMARY KEY (`id_requente`),
  INDEX `fk_requente_municipio1_idx` (`id_municipio` ASC),
  INDEX `fk_requente_advogado1_idx` (`id_advogado` ASC),
  INDEX `fk_requente_tipo_pessoa1_idx` (`id_tipo` ASC),
  CONSTRAINT `fk_requente_advogado1`
    FOREIGN KEY (`id_advogado`)
    REFERENCES `fenixiure2`.`advogado` (`id_advogado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_requente_municipio1`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `fenixiure2`.`municipio` (`id_municipio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_requente_tipo_pessoa1`
    FOREIGN KEY (`id_tipo`)
    REFERENCES `fenixiure2`.`tipo_pessoa` (`id_tipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`requerido` (
  `id_requerido` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_requerido` VARCHAR(45) NULL DEFAULT NULL,
  `casa_requerido` VARCHAR(45) NULL DEFAULT NULL,
  `rua_requerido` VARCHAR(45) NULL DEFAULT NULL,
  `bairro_requerido` VARCHAR(45) NULL DEFAULT NULL,
  `id_municipio` INT(11) NOT NULL,
  `id_tipo` INT(11) NOT NULL,
  `id_advogado` INT(11) NOT NULL,
  PRIMARY KEY (`id_requerido`),
  INDEX `fk_requente_municipio1_idx` (`id_municipio` ASC),
  INDEX `fk_requerido_tipo_pessoa1_idx` (`id_tipo` ASC),
  INDEX `fk_requerido_advogado1_idx` (`id_advogado` ASC),
  CONSTRAINT `fk_requente_municipio10`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `fenixiure2`.`municipio` (`id_municipio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_requerido_advogado1`
    FOREIGN KEY (`id_advogado`)
    REFERENCES `fenixiure2`.`advogado` (`id_advogado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_requerido_tipo_pessoa1`
    FOREIGN KEY (`id_tipo`)
    REFERENCES `fenixiure2`.`tipo_pessoa` (`id_tipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`tipo_decisao` (
  `id_tipo_decisao` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo_decisao` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo_decisao`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`tipo_pessoa` (
  `id_tipo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_tipo` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`tramitacao` (
  `id_tramitacao` INT(11) NOT NULL AUTO_INCREMENT,
  `data_tramitacao` DATE NULL DEFAULT NULL,
  `observacao_tramitacao` VARCHAR(500) NULL DEFAULT NULL,
  `id_tipo_decisao` INT(11) NOT NULL,
  `id_processo` INT(11) NOT NULL,
  `id_estado_processo` INT(11) NOT NULL,
  PRIMARY KEY (`id_tramitacao`),
  INDEX `fk_tramitacao_tipo_decisao1_idx` (`id_tipo_decisao` ASC),
  INDEX `fk_tramitacao_processo1_idx` (`id_processo` ASC),
  INDEX `fk_tramitacao_estado_processo1_idx` (`id_estado_processo` ASC),
  CONSTRAINT `fk_tramitacao_estado_processo1`
    FOREIGN KEY (`id_estado_processo`)
    REFERENCES `fenixiure2`.`estado_processo` (`id_estado_processo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tramitacao_processo1`
    FOREIGN KEY (`id_processo`)
    REFERENCES `fenixiure2`.`processo` (`id_processo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tramitacao_tipo_decisao1`
    FOREIGN KEY (`id_tipo_decisao`)
    REFERENCES `fenixiure2`.`tipo_decisao` (`id_tipo_decisao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fenixiure2`.`usuario` (
  `id_usuario` INT(11) NOT NULL AUTO_INCREMENT,
  `login_usuario` VARCHAR(45) NULL DEFAULT NULL,
  `senha_usuario` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
