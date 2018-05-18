# MountyFetch

Petit outil utilitaire autour du chargement et traitement des données [MountyHall](http://www.mountyhall.com/) 
(détection/positionnement des monstres, profil des trolls, ...)

## Démarrage 

### Via Maven

Construction du projet avec :

````shell
mvn clean install
````

Puis exécution avec :

````shell
java -jar rest/target/mounty-fetch-rest-jar-with-dependencies.jar
````


### Via Docker

Construction du projet avec :

````shell
docker build -t zoumbox/mounty-fetch-rest  .
````

Puis exécution avec :


````shell
docker run -p 8080:8080 -t -i zoumbox/mounty-fetch-rest
````

### Vérifier que tout va bien via l'URL

    http://localhost:8080/mountyFetch/v1/status



## Utilisation

À partir d'un nom :

    http://localhost:8080/mountyFetch/v1/monsters/fromName?raw=Gowap%20Apprivois%C3%A9%20[Anc%C3%AAtre]

ou d'une ligne extraite de Sp_Vue2 :

    http://localhost:8080/mountyFetch/v1/monsters/fromSpVue2Row?row=5864923;Ma%C3%AEtresse%20Ame-en-peine%20[Naissante];-74;-40;-78

On obtient un JSON contenant les différentes informations qui ont pu êtré déduites

```json
{
  "id":5864923,
  "fullName":"Maîtresse Ame-en-peine [Naissante]",
  "position":{"x":-74,"y":-40,"n":-78},
  "familyEnum":"MortVivant",
  "family":"Mort-Vivant",
  "baseName":"Ame-en-peine",
  "baseNival":{"lowerBound":{"endpoint":8},"upperBound":{"endpoint":8}},
  "templateEnum":"Maîtresse",
  "template":"Maîtresse",
  "templateBonus":8,
  "age":"Naissante",
  "ageBonus":0,
  "nival":{"lowerBound":{"endpoint":16},"upperBound":{"endpoint":16}}
}
```

 Champ          | Description
----------------|-------------------------------------------------------------------
`id`            | Identifiant du monstre (Uniquement dans le cas de `fromSpVue2Row`)
`fullName`      | Nom complet
`position`      | Position (Uniquement dans le cas de `fromSpVue2Row`)
`familyEnum`    | La famille de monstre (valeur de l'[énum Java](/parser/src/main/java/org/zoumbox/mountyFetch/parser/Families.java))
`family`        | La famille de monstre
`baseName`      | Le type de base du monstre (sans template, sans âge, ...)
`baseNival`     | Le niveau lié au monstre de base (sans template, sans âge, ...)
`templateEnum`  | Le template (valeur de l'[énum Java](/parser/src/main/java/org/zoumbox/mountyFetch/parser/Templates.java))
`template`      | Le template
`templateBonus` | Le bonus lié au template
`age`           | L'âge
`ageBonus`      | Le bonus lié à l'âge
`nival`         | Le niveau calculé avec template & âge

Pour `baseNival` et `nival`, on indique un intervalle car dans certains cas (ex. `Phoenix`) le niveau n'est pas déterministe

## Démo

Une instance de démo disponible

### Parsing à partir d'un nom

    http://mounty-fetch.zoumbox.org/v1/monsters/fromName?raw=Gowap%20Apprivois%C3%A9%20[Anc%C3%AAtre]

### Parsing à partir d'une ligne extraite de Sp_Vue2

    http://mounty-fetch.zoumbox.org/v1/monsters/fromSpVue2Row?row=5864923;Ma%C3%AEtresse%20Ame-en-peine%20[Naissante];-74;-40;-78
