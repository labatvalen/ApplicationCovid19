//Readme du projet transverse par Malo Combeau, Félix Froment, Valentin Labat et Camille Poncet



Notez qu'il est très important de suivre toute les étapes de ce protocole (notamment la création du nouvel utilisateur), sans quoi des modifications aux fichiers fournis devront être apportées.


$$ Etape 0 $$

Avant de commencer, placez le dossier 'ProjetTransverse', qui se trouve à l'intérieur du dossier que vous venez de decompresser, à la racine de votre ordinateur


$$ Etape 1 : Partie Base de données $$

  - Se connecter à mysql en mode administrateur : 'sudo mysql -u root -p'
  - Entrer ses identifiants -> Vous êtes dans mysql
  - Creer un nouvel utilisateur : "CREATE USER 'projet'@'localhost' IDENTIFIED BY 'mdp';" (ici, l'identifiant sera 'projet' et le mot de passe sera 'mdp')
  - Donner tous les privileges au nouvel utilisateur : "GRANT ALL PRIVILEGES ON * . * TO 'projet'@'localhost';"
  - Activer les privileges : "FLUSH PRIVILEGES;"
  - Quitter cette session :"quit;"
  - Se connecter avec le nouvel utilisateur créé : "mysql -u projet -p" et entrez le mot de passe 'mdp'


$$ Etape 2 : Partie Java $$

  1) Deplacer le dossier 'ProjetTransverse' vers votre Workspace d'Eclipse
  2) Dans Eclipse, faire : 'fichier/Open project from system file' et ajouter le projet
  3) Si vous n'avez pas utilisisé les mêmes identifiants que ceux décrits plus haut pour votre base de données, veuillez changer votre user et votre password par les identifiants créés précédemment dans chaque fichier java.


$$ Etape 3 : Importation des données $$

  - Si votre eclipse-workspace n'est pas au niveau de votre racine, veuillez changer les chemins situés dans les fichier 'ProjetTransverse/BDD/bdd.sql' et 'ProjetTransverse/BDD/Donnees/Autre/communes_adjacentes_2018.csv' ainsi que dans la commande ci-dessous.

/!\ Avant de faire l'étape suivante : Notez que le fichier va supprimer une base de données du nom de 'Projet'. Vérifiez donc, si vous possédez une base de données de ce nom-là avec cet utilisateur-là, que cette table peut être supprimée sans engendrer de problèmes de perte de données essentielles pour vous. Sinon, modifiez le fichier 'bdd.sql'
  - Dans votre console mysql, importer le fichier de données : 'source eclipse-workspace/ProjetTransverse/BDD/bdd.sql;'

  - Attendez que l'ensemble des requêtes soient effectuées. (La console indique de nouveau 'mysql>')


$$ Etape 4 : Deroulement du scenario $$

  - Ouvrez Eclipsejava et ouvrez le projet
  - Executez la fonction main de la classe myWindow, et laissez-vous guider par l'interface graphique


$$ Explication de l'interface Graphique : 
  - Possibilités d'ajouter, supprimer ou modifier les régions, départements et communes
  - En lancant une sumulation, on simule une epidemmie sur le nombre de jours choisis dans les regions voulues (le code est à récupérer dans la table Region de la base de données), et on affiche ensuite des statistiques présentées sous la forme d'histogrammes
  - Vider l'historique permet de supprimer les données de la simulation précédente
  - le calcul de distance permet d'afficher la distance entre deux villes et d'afficher le plus court chemin d'une ville à une autre en évitant les communes confinées par la simulation (affichage dans le terminal eclipse)


