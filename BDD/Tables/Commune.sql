--
-- Structure de la table Commune
--

CREATE TABLE Commune(
  id int(8) NOT NULL AUTO_INCREMENT,
  departement varchar(3) NOT NULL,
  slug varchar(255) DEFAULT NULL,
  nom varchar(45) DEFAULT NULL,
  nom_simple varchar(45) DEFAULT NULL,
  nom_reel varchar(450) NOT NULL,
  nom_soundex varchar(20) DEFAULT NULL,
  nom_metaphone varchar(22) DEFAULT NULL,
  code_postal varchar(255) DEFAULT NULL,
  commune varchar(3) DEFAULT NULL,
  code_commune varchar(5) NOT NULL,
  arrondissement smallint(3)  DEFAULT NULL,
  canton varchar(4) DEFAULT NULL,
  amdi smallint(5)  DEFAULT NULL,
  population_2010 mediumint(11)  NOT NULL,
  population_1999 mediumint(11)  DEFAULT NULL,
  population_2012 mediumint(10)  DEFAULT NULL,
  densite_2010 int(11) DEFAULT NULL,
  surface double NOT NULL,
  longitude_deg double NOT NULL,
  latitude_deg double NOT NULL,
  longitude_grd varchar(9) DEFAULT NULL,
  latitude_grd varchar(8) DEFAULT NULL,
  longitude_dms varchar(9) DEFAULT NULL,
  latitude_dms varchar(8) DEFAULT NULL,
  zmin mediumint(4) DEFAULT NULL,
  zmax mediumint(4) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_departement FOREIGN KEY(departement) REFERENCES Departement(code)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
