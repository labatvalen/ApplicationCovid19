--
-- Donnees de la table Adjacent;
--

-- encodage des imporations en utf8
charset utf8;
-- importation des donnees
LOAD DATA LOCAL INFILE 'eclipse-workspace/ProjetTransverse/BDD/Donnees/Autre/communes_adjacentes_2018.csv' INTO TABLE Adjacent FIELDS TERMINATED BY "," LINES TERMINATED BY '\n';
SHOW WARNINGS;
