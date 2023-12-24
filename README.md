# Projet UniShop

## Description du Projet
UniShop est une application de commerce électronique développée en Java et servi sous la forme d'un CLI facile d'utilisation. Elle permet de s'enregistrer afin d'acheter ou vendre des produits, de gérer leurs commandes ainsi que de suivre les activités des autres utilisateurs.

## Données de Départ
- Acheteur: Nom d'utilisateur - Buyer#, Mot de passe - Password# (# de 1 a 10)
- Revendeur: Nom d'utilisateur - Seller#, Mot de passe - Password# (# de 1 a 5)

Les Données peuvent être trouver au sein des fichiers products.json et users.json.
Ils comportent: 
- 10 acheteurs, dont Buyer#1 avec un suiveurs.
- 5 revendeurs
- 20 produits, dont 1 de chaque type et le produit ComputerHardware1 avec une évaluation.
- 10 commmandes, dont 1 dans chaque état.

## Installation du Projet
1. Assurez-vous d'avoir Java 21 et Maven installés.
2. Clonez le dépôt à l'aide de `git clone https://github.com/svoglimacci/IFT2255-UniShop`.
3. Les dependencies requises pour le projet sont les suivantes :
  - maven-surefire-plugin
  - mockito-core
  - jackson-databind
  - junit.jupiter

## Exécution du Projet
1. Naviguez vers le répertoire du projet.
2. Exécutez la commande `java -jar .\out\artifacts\Implementation_jar\Implementation.jar` pour démarrer l'application a l'aide du jar fourni.
3. Le fichier contenant le point d'entrée peut être trouvé ici :
`\IFT2255-UniShop\Implementation\src\main\java\org\udem\unishop`
