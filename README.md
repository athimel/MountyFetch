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

À partir d'un nom :

    http://localhost:8080/mountyMonsters/v1/parse/fromName?raw=Gowap%20Apprivois%C3%A9%20[Anc%C3%AAtre]

ou d'une ligne extraite de Sp_Vue2 :

    http://localhost:8080/mountyMonsters/v1/parse/fromSpVue2Row?row=5864923;Ma%C3%AEtresse%20Ame-en-peine%20[Naissante];-74;-40;-78

On obtient un JSON contenant les différentes informations qui ont pu êtré déduites

```json
{
  "id":5864923, // Uniquement dans le cas de `fromSpVue2Row`
  "fullName":"Maîtresse Ame-en-peine [Naissante]",
  "position":{"x":-74,"y":-40,"n":-78}, // Uniquement dans le cas de `fromSpVue2Row`
  "familyEnum":"MortVivant",
  "family":"Mort-Vivant", // La famille de monstre
  "baseName":"Ame-en-peine", // Le type de base du monstre (sans template, sans âge, ...)
  "baseNival":{"lowerBound":{"endpoint":8},"upperBound":{"endpoint":8}}, // Le niveau lié au monstre de base (sans template, sans âge, ...)
  "templateEnum":"Maîtresse",
  "template":"Maîtresse", // Le template
  "templateBonus":8, // Le bonus lié au template
  "age":"Naissante", // L'âge
  "ageBonus":0, // Le bonus lié à l'âge
  "nival":{"lowerBound":{"endpoint":16},"upperBound":{"endpoint":16}} // Le niveau calculé avec template & âge
}
```

Pour `baseNival` et `nival`, on indique un intervalle car dans certains cas (ex. `Phoenix`) le niveau n'est pas déterministe

## Démo

Une instance de démo disponible

### Parsing à partir d'un nom

    http://mounty-monsters.zoumbox.org/v1/parse/fromName?raw=Gowap%20Apprivois%C3%A9%20[Anc%C3%AAtre]

### Parsing à partir d'une ligne extraite de Sp_Vue2

    http://mounty-monsters.zoumbox.org/v1/parse/fromSpVue2Row?row=5864923;Ma%C3%AEtresse%20Ame-en-peine%20[Naissante];-74;-40;-78
