SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema fenixiure
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fenixiure` DEFAULT CHARACTER SET utf8 ;
USE `fenixiure` ;

-- -----------------------------------------------------
-- Table `fenixiure`.`juiz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`juiz` (
  `id_juiz` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_juiz` VARCHAR(125) NULL DEFAULT NULL,
  `sobrenome_juiz` VARCHAR(125) NULL DEFAULT NULL,
  `data_inicio_funcoes` DATE NULL DEFAULT NULL,
  `usuario_juiz` VARCHAR(125) NULL DEFAULT NULL,
  `senha_juiz` VARCHAR(125) NULL DEFAULT NULL,
  `tipo_usuario` VARCHAR(125) NULL DEFAULT 'J',
  PRIMARY KEY (`id_juiz`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`acesso_sistema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`acesso_sistema` (
  `id_acesso_sistema` INT(11) NOT NULL AUTO_INCREMENT,
  `login_juiz` VARCHAR(100) NOT NULL,
  `password_juiz` VARCHAR(100) NOT NULL,
  `id_juiz` INT(11) NOT NULL,
  PRIMARY KEY (`id_acesso_sistema`),
  UNIQUE INDEX `login_juiz` (`login_juiz` ASC),
  UNIQUE INDEX `password_juiz` (`password_juiz` ASC),
  INDEX `fk_acesso_sistema_juiz1_idx` (`id_juiz` ASC),
  CONSTRAINT `fk_acesso_sistema_juiz1`
    FOREIGN KEY (`id_juiz`)
    REFERENCES `fenixiure`.`juiz` (`id_juiz`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`funcionario` (
  `id_funcionario` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_funcionario` VARCHAR(125) NULL DEFAULT NULL,
  `sobrenome_funcionario` VARCHAR(125) NULL DEFAULT NULL,
  `usuario_funcionario` VARCHAR(125) NULL DEFAULT NULL,
  `senha_funcionario` VARCHAR(125) NULL DEFAULT NULL,
  `tipo_usuario` VARCHAR(1) NULL DEFAULT 'F',
  PRIMARY KEY (`id_funcionario`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`acesso_sistema_funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`acesso_sistema_funcionario` (
  `id_acesso_sistema` INT(11) NOT NULL AUTO_INCREMENT,
  `login_funcionario` VARCHAR(45) NULL DEFAULT NULL,
  `password_funcionario` VARCHAR(45) NULL DEFAULT NULL,
  `id_funcionario` INT(11) NOT NULL,
  PRIMARY KEY (`id_acesso_sistema`),
  INDEX `fk_acesso_sistema_funcionario_funcionario1_idx` (`id_funcionario` ASC),
  CONSTRAINT `fk_acesso_sistema_funcionario_funcionario1`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `fenixiure`.`funcionario` (`id_funcionario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`advogado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`advogado` (
  `id_advogado` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_advogado` VARCHAR(125) NULL DEFAULT NULL,
  `sobrenome_advogado` VARCHAR(125) NULL DEFAULT NULL,
  `data_inicio_funcoes` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id_advogado`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`especie_processo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`especie_processo` (
  `id_especie_processo` INT(11) NOT NULL AUTO_INCREMENT,
  `especie_processo` VARCHAR(125) NULL DEFAULT NULL,
  PRIMARY KEY (`id_especie_processo`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`estado_processo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`estado_processo` (
  `id_estado_processo` INT(11) NOT NULL AUTO_INCREMENT,
  `estado_processo` VARCHAR(125) NULL DEFAULT 'EM_DANDAMENTO',
  PRIMARY KEY (`id_estado_processo`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`provincia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`provincia` (
  `id_provincia` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_provincia` VARCHAR(125) NULL DEFAULT NULL,
  PRIMARY KEY (`id_provincia`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`municipio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`municipio` (
  `id_municipio` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_municipio` VARCHAR(125) NULL DEFAULT NULL,
  `id_provincia` INT(11) NOT NULL,
  PRIMARY KEY (`id_municipio`),
  INDEX `fk_municipio_provincia_idx` (`id_provincia` ASC),
  CONSTRAINT `fk_municipio_provincia`
    FOREIGN KEY (`id_provincia`)
    REFERENCES `fenixiure`.`provincia` (`id_provincia`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`tipo_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`tipo_pessoa` (
  `id_tipo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_tipo` VARCHAR(125) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`requente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`requente` (
  `id_requente` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_requente` VARCHAR(125) NULL DEFAULT NULL,
  `casa_requerente` VARCHAR(125) NULL DEFAULT NULL,
  `rua_requerente` VARCHAR(125) NULL DEFAULT NULL,
  `bairro_requerente` VARCHAR(125) NULL DEFAULT NULL,
  `id_municipio` INT(11) NOT NULL,
  `id_tipo` INT(11) NOT NULL,
  `id_advogado` INT(11) NOT NULL,
  PRIMARY KEY (`id_requente`),
  INDEX `fk_requente_municipio1_idx` (`id_municipio` ASC),
  INDEX `fk_requente_tipo_pessoa1_idx` (`id_tipo` ASC),
  INDEX `fk_requente_advogado1_idx` (`id_advogado` ASC),
  CONSTRAINT `fk_requente_advogado1`
    FOREIGN KEY (`id_advogado`)
    REFERENCES `fenixiure`.`advogado` (`id_advogado`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_requente_municipio1`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `fenixiure`.`municipio` (`id_municipio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_requente_tipo_pessoa1`
    FOREIGN KEY (`id_tipo`)
    REFERENCES `fenixiure`.`tipo_pessoa` (`id_tipo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`requerido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`requerido` (
  `id_requerido` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_requerido` VARCHAR(125) NULL DEFAULT NULL,
  `casa_requerido` VARCHAR(125) NULL DEFAULT NULL,
  `rua_requerido` VARCHAR(125) NULL DEFAULT NULL,
  `bairro_requerido` VARCHAR(125) NULL DEFAULT NULL,
  `id_municipio` INT(11) NOT NULL,
  `id_tipo` INT(11) NOT NULL,
  `id_advogado` INT(11) NOT NULL,
  PRIMARY KEY (`id_requerido`),
  INDEX `fk_requente_municipio1_idx` (`id_municipio` ASC),
  INDEX `fk_requerido_tipo_pessoa1_idx` (`id_tipo` ASC),
  INDEX `fk_requerido_advogado1_idx` (`id_advogado` ASC),
  CONSTRAINT `fk_requente_municipio10`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `fenixiure`.`municipio` (`id_municipio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_requerido_advogado1`
    FOREIGN KEY (`id_advogado`)
    REFERENCES `fenixiure`.`advogado` (`id_advogado`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_requerido_tipo_pessoa1`
    FOREIGN KEY (`id_tipo`)
    REFERENCES `fenixiure`.`tipo_pessoa` (`id_tipo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`processo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`processo` (
  `id_processo` INT(11) NOT NULL AUTO_INCREMENT,
  `numero_processo` VARCHAR(100) NOT NULL,
  `id_especie_processo` INT(11) NOT NULL,
  `id_juiz` INT(11) NOT NULL,
  `data_entrada` DATE NULL DEFAULT NULL,
  `id_requente` INT(11) NOT NULL,
  `id_requerido` INT(11) NOT NULL,
  PRIMARY KEY (`id_processo`),
  UNIQUE INDEX `numero_processo_UNIQUE` (`numero_processo` ASC),
  INDEX `fk_processo_especie_processo1_idx` (`id_especie_processo` ASC),
  INDEX `fk_processo_juiz1_idx` (`id_juiz` ASC),
  INDEX `fk_processo_requente1_idx` (`id_requente` ASC),
  INDEX `fk_processo_requerido1_idx` (`id_requerido` ASC),
  CONSTRAINT `fk_processo_especie_processo1`
    FOREIGN KEY (`id_especie_processo`)
    REFERENCES `fenixiure`.`especie_processo` (`id_especie_processo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_processo_juiz1`
    FOREIGN KEY (`id_juiz`)
    REFERENCES `fenixiure`.`juiz` (`id_juiz`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_processo_requente1`
    FOREIGN KEY (`id_requente`)
    REFERENCES `fenixiure`.`requente` (`id_requente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_processo_requerido1`
    FOREIGN KEY (`id_requerido`)
    REFERENCES `fenixiure`.`requerido` (`id_requerido`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 44
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`tipo_decisao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`tipo_decisao` (
  `id_tipo_decisao` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo_decisao` VARCHAR(125) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo_decisao`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fenixiure`.`tramitacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fenixiure`.`tramitacao` (
  `id_tramitacao` INT(11) NOT NULL AUTO_INCREMENT,
  `data_conclusao_tramitacao` DATE NULL DEFAULT NULL,
  `dispacho_tramitacao` VARCHAR(20000) NULL DEFAULT NULL,
  `id_tipo_decisao` INT(11) NOT NULL,
  `id_processo` INT(11) NOT NULL,
  `estado_processo` VARCHAR(125) NULL DEFAULT 'EM_AMDAMENTO',
  `data_termino` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id_tramitacao`),
  INDEX `fk_tramitacao_tipo_decisao1_idx` (`id_tipo_decisao` ASC),
  INDEX `fk_tramitacao_processo1_idx` (`id_processo` ASC),
  CONSTRAINT `fk_tramitacao_processo1`
    FOREIGN KEY (`id_processo`)
    REFERENCES `fenixiure`.`processo` (`id_processo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tramitacao_tipo_decisao1`
    FOREIGN KEY (`id_tipo_decisao`)
    REFERENCES `fenixiure`.`tipo_decisao` (`id_tipo_decisao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 115
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
