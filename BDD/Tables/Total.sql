--
-- Structure de la table Total
--

CREATE TABLE Total(
  id_commune int(10) PRIMARY KEY,
  date varchar(45) DEFAULT NULL,
  nombre_sains int(11) DEFAULT 0,
  nombre_contamines int(11) DEFAULT 0,
  nombre_morts_total int(11) DEFAULT 0,
  nombre_gueris_total int(11) DEFAULT 0,
  CONSTRAINT fk_id_communetotale FOREIGN KEY(id_commune) REFERENCES Commune(id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
