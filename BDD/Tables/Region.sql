--
-- Structure de la table Region
--

CREATE TABLE Region (
  id int(10),
  code varchar(3) PRIMARY KEY,
  nom varchar(255),
  slug varchar(255)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
