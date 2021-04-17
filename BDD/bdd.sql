--
-- Squelette de la Base de Donnees
--


-- Creation d'une nouvelle base de donnees (avec encodage en utf8)
DROP DATABASE IF EXISTS Projet;
CREATE DATABASE Projet DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE Projet;


-- On supprime les tables utilisées si elles existent déjà
DROP TABLE IF EXISTS Adjacent;
DROP TABLE IF EXISTS Total;
DROP TABLE IF EXISTS Historique;
DROP TABLE IF EXISTS Distance;
DROP TABLE IF EXISTS Commune;
DROP TABLE IF EXISTS Departement;
DROP TABLE IF EXISTS Region;
DROP TABLE IF EXISTS Confinee;


--  Création de la structure de la BDD par appel des fichiers
source eclipse-workspace/ProjetTransverse/BDD/Tables/Confinee.sql;
source eclipse-workspace/ProjetTransverse/BDD/Tables/Region.sql;
source eclipse-workspace/ProjetTransverse/BDD/Tables/Departement.sql;
source eclipse-workspace/ProjetTransverse/BDD/Tables/Commune.sql;
source eclipse-workspace/ProjetTransverse/BDD/Tables/Distance.sql;
source eclipse-workspace/ProjetTransverse/BDD/Tables/Historique.sql;
source eclipse-workspace/ProjetTransverse/BDD/Tables/Total.sql;
source eclipse-workspace/ProjetTransverse/BDD/Tables/Adjacent.sql;


--  Ajout des donnees dans la BDD
source eclipse-workspace/ProjetTransverse/BDD/Donnees/Confinee.sql;
source eclipse-workspace/ProjetTransverse/BDD/Donnees/Region.sql;
source eclipse-workspace/ProjetTransverse/BDD/Donnees/Departement.sql;
source eclipse-workspace/ProjetTransverse/BDD/Donnees/Commune.sql;
source eclipse-workspace/ProjetTransverse/BDD/Donnees/Adjacent.sql;


--  Modification de la structure de la BDD après ajout de données
source eclipse-workspace/ProjetTransverse/BDD/Tables/modifRegion.sql;
source eclipse-workspace/ProjetTransverse/BDD/Tables/modifDepartement.sql;
source eclipse-workspace/ProjetTransverse/BDD/Tables/modifCommune.sql;
source eclipse-workspace/ProjetTransverse/BDD/Tables/modifAdjacent.sql;
