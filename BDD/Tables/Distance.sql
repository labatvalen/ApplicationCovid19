--
-- Structure de la table Distance
--

CREATE TABLE Distance(
  id_commune1 int(10),
  id_commune2 int(10),
  distance float DEFAULT NULL,
  CONSTRAINT fk_id_commune1 FOREIGN KEY(id_commune1) REFERENCES Commune(id),
  CONSTRAINT fk_id_commune2 FOREIGN KEY(id_commune2) REFERENCES Commune(id),
  PRIMARY KEY (id_commune1,id_commune2)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
