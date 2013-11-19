================================================================================
IUT de Nice / Département informatique / Module APO-Java / 2012-2013
================================================================================

TrafalgarPlayer

Auteur : Martini Didier / Gauthier Cibert—Volpe
Responsable : Martini Didier
Groupe : S3T G1
Readme : Version : 1.0.0 (Dernière révision 1/12/2012 01:15)

================================================================================

1. Instructions pour l'installation
================================================================================

Deux launcher sont présents pour l’exécution du .jar, un .bat (Windows) et un .sh (Linux)
Le lancement du .jar générera un dossier 'TrafalgarPlayer' contenant les éléments nécessaire (fichiers de configuration + image) 
Le fichier de configuration est en xml. Cela rend possible la modification du fichier de configuration avec seulement un éditeur de texte


================================================================================

2. Fonctionnalités
================================================================================

Voici la liste non exhaustive des fonctionnalités qui peuvent paraître 'caché' présente dans ce trafalgarPlayer, les indications pour les utiliser sont présente dans le fichier HOW_TO_USE.txt du dossier _JAR.
- Dessiner des lignes
- Demander a changer de direction
- Demander a changer de vitesse
- Affichage des informations du navire sélectionné
- Affichage de l'appartenance d'un territoire sélectionné
- Zoom 
- Rotation
- Déplacement

================================================================================

3. Problèmes connus
================================================================================

- Le cercle qui suis le pointeur présente un bug d'affichage lors d'une rotation / zoom / déplacement (il se décale de la position qu'il aurait du avoir). Le cercle se remet automatiquement a la place qu'il devrait avoir des que l'on bouge la souris. Ce bug ne gène en rien les fonctionnalités de trafalgarPlayer ou l'utilisation de trafalgarPlayer. 


================================================================================

4. Idées d’améliorations possibles
================================================================================

La seule limites aux améliorations de trafalgarPlayer me semble être l'imagination. 
Deux choses me semblerait intéressante : 
- Régler le bug mineurs présenté précédemment
- Permettre le zoom et rotation par rapport a l'emplacement du pointeur actuel 

================================================================================

5. Notes et informations
================================================================================

- Utilisation de XML et donc de libraire pour le faire, nous avons choisi : jdom : http://www.jdom.org/

- Utilisation (tres basique) d'un framework de log : log4j: http://logging.apache.org/log4j/2.x/

- TrafalgarPlayer passe sonar avec 98,5 % de rules compliance

- TrafalgarPlayer est bien sur compatible avec les cartes générer par le trafalgarMap de mon binôme

- Les fichiers de configuration et les cartes sont en XML

