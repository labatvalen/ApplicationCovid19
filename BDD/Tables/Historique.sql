--
-- Structure de la table Historique
--

CREATE TABLE Historique(
  id int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  id_commune int(10) DEFAULT NULL,
  date_historique varchar(45) DEFAULT NULL,
  nombre_contamines_24h int(11) DEFAULT 0,
  nombre_morts_24h int(11) DEFAULT 0,
  nombre_gueris_24h int(11) DEFAULT 0,
  id_confinee int(10) DEFAULT NULL,
  date_confinement varchar(45) DEFAULT NULL,
  duree_confinement int(10) DEFAULT NULL,
  date_dernier_deconfinement varchar(45) DEFAULT NULL,
  CONSTRAINT fk_id_confinee FOREIGN KEY(id_confinee) REFERENCES Confinee(id),
  CONSTRAINT fk_id_commune FOREIGN KEY(id_commune) REFERENCES Commune(id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
