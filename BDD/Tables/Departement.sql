--
-- Structure de la table Departement
--

CREATE TABLE Departement (
  id int(10),
  region_code varchar(3),
  code varchar(3) PRIMARY KEY,
  name varchar(255),
  slug varchar(255),
  CONSTRAINT fk_region_code FOREIGN KEY(region_code) REFERENCES Region(code)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

