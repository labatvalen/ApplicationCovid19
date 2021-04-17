--
-- Modification de la table Commune après l'ajout de données
--


  -- Suppression des donnees qui nous serons pas utiles
ALTER TABLE Commune DROP slug;
ALTER TABLE Commune DROP nom_simple;
ALTER TABLE Commune DROP nom;
ALTER TABLE Commune DROP nom_soundex;
ALTER TABLE Commune DROP nom_metaphone;
ALTER TABLE Commune DROP commune;
ALTER TABLE Commune DROP code_commune;
ALTER TABLE Commune DROP arrondissement;
ALTER TABLE Commune DROP canton;
ALTER TABLE Commune DROP amdi;
ALTER TABLE Commune DROP population_1999;
ALTER TABLE Commune DROP population_2012;
ALTER TABLE Commune DROP densite_2010;
ALTER TABLE Commune DROP longitude_grd;
ALTER TABLE Commune DROP latitude_grd;
ALTER TABLE Commune DROP longitude_dms;
ALTER TABLE Commune DROP latitude_dms;
ALTER TABLE Commune DROP zmin;
ALTER TABLE Commune DROP zmax;
ALTER TABLE Commune DROP code_postal;

