--
-- Structure de la table Adjacent;
--

CREATE TABLE Adjacent(
  id varchar(45) PRIMARY KEY,
  nom varchar(450) NOT NULL,
  nb_voisins varchar(45),
  insee_voisins varchar(4500),
  noms_voisins varchar(4500),
  as$ varchar(450)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
