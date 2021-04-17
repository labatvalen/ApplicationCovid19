--
-- Modification de la table Region après l'ajout de données
--

-- on se debarrasse des donnees inutiles
ALTER TABLE Region DROP slug;
ALTER TABLE Region DROP id;
