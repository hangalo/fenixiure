CREATE DATABASE  IF NOT EXISTS `fenixiure` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fenixiure`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: fenixiure
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `advogado`
--

DROP TABLE IF EXISTS `advogado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advogado` (
  `id_advogado` int(11) NOT NULL AUTO_INCREMENT,
  `nome_advogado` varchar(45) DEFAULT NULL,
  `sobrenome_advogado` varchar(45) DEFAULT NULL,
  `data_nascimento_advogado` date DEFAULT NULL,
  `data_inicio_funcoes` date DEFAULT NULL,
  PRIMARY KEY (`id_advogado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advogado`
--

LOCK TABLES `advogado` WRITE;
/*!40000 ALTER TABLE `advogado` DISABLE KEYS */;
/*!40000 ALTER TABLE `advogado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especie_processo`
--

DROP TABLE IF EXISTS `especie_processo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `especie_processo` (
  `id_especie_processo` int(11) NOT NULL AUTO_INCREMENT,
  `especie_processo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_especie_processo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especie_processo`
--

LOCK TABLES `especie_processo` WRITE;
/*!40000 ALTER TABLE `especie_processo` DISABLE KEYS */;
/*!40000 ALTER TABLE `especie_processo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_processo`
--

DROP TABLE IF EXISTS `estado_processo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_processo` (
  `id_estado_processo` int(11) NOT NULL AUTO_INCREMENT,
  `estado_processo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_estado_processo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_processo`
--

LOCK TABLES `estado_processo` WRITE;
/*!40000 ALTER TABLE `estado_processo` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_processo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juiz`
--

DROP TABLE IF EXISTS `juiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `juiz` (
  `id_juiz` int(11) NOT NULL AUTO_INCREMENT,
  `nome_juiz` varchar(45) DEFAULT NULL,
  `sobrenome_juiz` varchar(45) DEFAULT NULL,
  `data_nascimento_juiz` date DEFAULT NULL,
  `data_inicio_funcoes` date DEFAULT NULL,
  PRIMARY KEY (`id_juiz`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juiz`
--

LOCK TABLES `juiz` WRITE;
/*!40000 ALTER TABLE `juiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `juiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `municipio`
--

DROP TABLE IF EXISTS `municipio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `municipio` (
  `id_municipio` int(11) NOT NULL AUTO_INCREMENT,
  `nome_municipio` varchar(45) DEFAULT NULL,
  `id_provincia` int(11) NOT NULL,
  PRIMARY KEY (`id_municipio`),
  KEY `fk_municipio_provincia_idx` (`id_provincia`),
  CONSTRAINT `fk_municipio_provincia` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id_provincia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipio`
--

LOCK TABLES `municipio` WRITE;
/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processo`
--

DROP TABLE IF EXISTS `processo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processo` (
  `id_processo` int(11) NOT NULL AUTO_INCREMENT,
  `numero_processo` varchar(45) DEFAULT NULL,
  `id_especie_processo` int(11) NOT NULL,
  `id_requente` int(11) NOT NULL,
  `id_requerido` int(11) NOT NULL,
  `id_advogado` int(11) NOT NULL,
  `id_juiz` int(11) NOT NULL,
  `data_entrada` date DEFAULT NULL,
  `id_estado_processo` int(11) NOT NULL,
  `data_conclusao` date DEFAULT NULL,
  `resumo_despacho` varchar(200) DEFAULT NULL,
  `id_tipo_decisao` int(11) NOT NULL,
  PRIMARY KEY (`id_processo`),
  KEY `fk_processo_requente1_idx` (`id_requente`),
  KEY `fk_processo_requerido1_idx` (`id_requerido`),
  KEY `fk_processo_estado_processo1_idx` (`id_estado_processo`),
  KEY `fk_processo_especie_processo1_idx` (`id_especie_processo`),
  KEY `fk_processo_advogado1_idx` (`id_advogado`),
  KEY `fk_processo_juiz1_idx` (`id_juiz`),
  KEY `fk_processo_tipo_decisao1_idx` (`id_tipo_decisao`),
  CONSTRAINT `fk_processo_advogado1` FOREIGN KEY (`id_advogado`) REFERENCES `advogado` (`id_advogado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_processo_especie_processo1` FOREIGN KEY (`id_especie_processo`) REFERENCES `especie_processo` (`id_especie_processo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_processo_estado_processo1` FOREIGN KEY (`id_estado_processo`) REFERENCES `estado_processo` (`id_estado_processo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_processo_juiz1` FOREIGN KEY (`id_juiz`) REFERENCES `juiz` (`id_juiz`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_processo_requente1` FOREIGN KEY (`id_requente`) REFERENCES `requente` (`id_requente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_processo_requerido1` FOREIGN KEY (`id_requerido`) REFERENCES `requerido` (`id_requerido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_processo_tipo_decisao1` FOREIGN KEY (`id_tipo_decisao`) REFERENCES `tipo_decisao` (`id_tipo_decisao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processo`
--

LOCK TABLES `processo` WRITE;
/*!40000 ALTER TABLE `processo` DISABLE KEYS */;
/*!40000 ALTER TABLE `processo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincia` (
  `id_provincia` int(11) NOT NULL AUTO_INCREMENT,
  `nome_provincia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_provincia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requente`
--

DROP TABLE IF EXISTS `requente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requente` (
  `id_requente` int(11) NOT NULL AUTO_INCREMENT,
  `nome_requente` varchar(45) DEFAULT NULL,
  `sobrenome_requerente` varchar(45) DEFAULT NULL,
  `n_bi_requerente` varchar(45) DEFAULT NULL,
  `casa_requerente` varchar(45) DEFAULT NULL,
  `rua_requerente` varchar(45) DEFAULT NULL,
  `bairro_requerente` varchar(45) DEFAULT NULL,
  `id_municipio` int(11) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  PRIMARY KEY (`id_requente`),
  KEY `fk_requente_municipio1_idx` (`id_municipio`),
  KEY `fk_requente_tipo_pessoa1_idx` (`id_tipo`),
  CONSTRAINT `fk_requente_municipio1` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id_municipio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_requente_tipo_pessoa1` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_pessoa` (`id_tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requente`
--

LOCK TABLES `requente` WRITE;
/*!40000 ALTER TABLE `requente` DISABLE KEYS */;
/*!40000 ALTER TABLE `requente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requerido`
--

DROP TABLE IF EXISTS `requerido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requerido` (
  `id_requerido` int(11) NOT NULL AUTO_INCREMENT,
  `nome_requerido` varchar(45) DEFAULT NULL,
  `sobrenome_requerido` varchar(45) DEFAULT NULL,
  `n_bi_requerido` varchar(45) DEFAULT NULL,
  `casa_requerido` varchar(45) DEFAULT NULL,
  `rua_requerido` varchar(45) DEFAULT NULL,
  `bairro_requerido` varchar(45) DEFAULT NULL,
  `id_municipio` int(11) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  PRIMARY KEY (`id_requerido`),
  KEY `fk_requente_municipio1_idx` (`id_municipio`),
  KEY `fk_requerido_tipo_pessoa1_idx` (`id_tipo`),
  CONSTRAINT `fk_requente_municipio10` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id_municipio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_requerido_tipo_pessoa1` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_pessoa` (`id_tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requerido`
--

LOCK TABLES `requerido` WRITE;
/*!40000 ALTER TABLE `requerido` DISABLE KEYS */;
/*!40000 ALTER TABLE `requerido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_decisao`
--

DROP TABLE IF EXISTS `tipo_decisao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_decisao` (
  `id_tipo_decisao` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_decisao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_decisao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_decisao`
--

LOCK TABLES `tipo_decisao` WRITE;
/*!40000 ALTER TABLE `tipo_decisao` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_decisao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_pessoa`
--

DROP TABLE IF EXISTS `tipo_pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_pessoa` (
  `id_tipo` int(11) NOT NULL AUTO_INCREMENT,
  `nome_tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_pessoa`
--

LOCK TABLES `tipo_pessoa` WRITE;
/*!40000 ALTER TABLE `tipo_pessoa` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_pessoa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-12  0:53:39
