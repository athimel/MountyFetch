# MountyMonsters

Petit outil utilitaire autour de la détection/positionnement des monstres dans [MountyHall](http://www.mountyhall.com/)

## Démarrage 

### Via Maven

Construction du projet avec :

````shell
mvn clean install
````

Puis exécution avec :

````shell
java -jar rest/target/mounty-monsters-rest-jar-with-dependencies.jar
````


### Via Docker

Construction du projet avec :

````shell
docker build -t zoumbox/mounty-monsters-rest  .
````

Puis exécution avec :


````shell
docker run -p 8080:8080 -t -i zoumbox/mounty-monsters-rest
````

### Vérifier que tout va bien via l'URL

    http://localhost:8080/mountyMonsters/v1/status



## Utilisation

### Parsing à partir d'un nom

    http://localhost:8080/mountyMonsters/v1/parse/fromName?raw=Gowap%20Apprivois%C3%A9%20[Anc%C3%AAtre]

### Parsing à partir d'une ligne extraite de Sp_Vue2

    http://localhost:8080/mountyMonsters/v1/parse/fromSpVue2Row?row=5864923;Ma%C3%AEtresse%20Ame-en-peine%20[Naissante];-74;-40;-78
