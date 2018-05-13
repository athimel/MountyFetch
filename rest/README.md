# Exposition REST du parser

Démarrage avec

````shell
mvn exec:java
````

(nécessite d'avoir installé le module `parser`)


## Vérifier que tout va bien

    http://localhost:8080/mountyMonsters/v1/status

## Parsing à partir d'un nom

    http://localhost:8080/mountyMonsters/v1/parse/fromName?raw=Gowap%20Apprivois%C3%A9%20[Anc%C3%AAtre]

## Parsing à partir d'une ligne extraite de Sp_Vue2

    http://localhost:8080/mountyMonsters/v1/parse/fromSpVue2Row?row=5864923;Ma%C3%AEtresse%20Ame-en-peine%20[Naissante];-74;-40;-78
