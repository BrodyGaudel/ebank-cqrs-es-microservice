## Application e-Bank
L'application e-Bank est une solution bancaire en ligne développée en utilisant Java, Spring Boot, Axon Framework, basée sur l'architecture CQRS (Command Query Responsibility Segregation) et Event Sourcing. Elle est composée de trois microservices principaux : gestion des clients, gestion des comptes et gestion des opérations.
<p  align="center">
<img src="https://raw.githubusercontent.com/BrodyGaudel/ebank-cqrs-es-microservice/cf3bb833abbdc7e9b9db83d8d40c1bc73ad9de85/microservice.jpg?token=GHSAT0AAAAAAB54F5TFIUPWQBBQA4IYIPN6ZDMBLWQ">             
<br>

## Microservice : Gestion des clients
Ce microservice est responsable de la gestion des informations des clients de la banque. Il fournit des fonctionnalités telles que la création de nouveaux clients, la mise à jour des informations clients existantes et la récupération des détails des clients.

## Microservice : Gestion des comptes
Ce microservice gère les comptes bancaires des clients. Il permet la création de nouveaux comptes, l'association de comptes à des clients existants et la récupération des informations sur les comptes.

## Microservice : Gestion des opérations
Ce microservice gère les opérations bancaires telles que les dépôts, les retraits et les virements. Il fournit des API pour effectuer ces opérations sur les comptes des clients. Il contient également l'API XE pour la conversion des dévises. vous pouvez obténir vos credential sur https://www.xe.com/xecurrencydata/ 

## Architecture CQRS et Event Sourcing
L'architecture de cette application est basée sur le modèle CQRS et Event Sourcing. Cela signifie que les opérations de lecture et d'écriture sont séparées (CQRS) et que toutes les modifications de l'état de l'application sont enregistrées en tant qu'événements (Event Sourcing).

L'approche CQRS permet une scalabilité et une performance optimisées, car les opérations de lecture et d'écriture peuvent être traitées indépendamment. De plus, l'utilisation de l'Event Sourcing assure la traçabilité complète de tous les événements qui ont modifié l'état de l'application, permettant ainsi de reconstruire l'état actuel à partir de l'historique des événements.
<p  align="center">
<img src="https://user-images.githubusercontent.com/57298219/199542569-0782f133-8b66-4412-a447-a21788b8f735.jpg">             
<br>


## Configuration et déploiement
1. Clonez ce référentiel sur votre machine locale.
2. Assurez-vous d'avoir Java (JDK17 minimum), Maven et MySQL installés.
3. Configurez les informations de la base de données dans chaque microservice.
4. Exécutez chaque microservice individuellement en utilisant les commandes mvn spring-boot:run dans l'ordre suivant: discovery, gateway, gestion-clients, gestion-comptes, gestion-operation.
5. Les microservices seront disponibles sur les ports spécifiés dans leur configuration.

## Démarage de l'application
1. le service ***Eureka Discovery Server*** demare sur le port 8761 accéssible via http://localhost:8761 
2. Gateway demare sur le port 8888 accéssible via http://localhost:8888
3. Le microservice ***gestion des client*** démare sous le port 8801 et l'API swagger-doc est accessible via http://localhost:8801/customer/swagger-ui/index.html
4. Le microservice ***gestion des comptes*** démare sous le port 8802 et l'API swagger-doc est accessible via http://localhost:8802/account/swagger-ui/index.html
5. Le microservice ***gestion des opération*** démare sous le port 8803 et l'API swagger-doc est accessible via http://localhost:8803/operation/swagger-ui/index.html

6. Pour plus d'illustration, allez sur https://github.com/BrodyGaudel/ebank-cqrs-es-microservice/tree/main/images-illustration

## Contribution
Les contributions à cette application sont les bienvenues. Si vous souhaitez contribuer, veuillez suivre les étapes suivantes :

1. Fork ce référentiel.
2. Créez une branche pour votre fonctionnalité ou votre correctif.
3. Effectuez les modifications nécessaires.
4. Soumettez une demande d'extraction.
Nous apprécions vos commentaires et vos suggestions pour améliorer cette application.


## Auteur
Brody Gaudel MOUNANGA BOUKA

N'hésitez pas à me contacter si vous avez des questions ou des commentaires sur cette application e-Bank.