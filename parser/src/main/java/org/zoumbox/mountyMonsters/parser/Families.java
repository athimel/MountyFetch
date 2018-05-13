package org.zoumbox.mountyMonsters.parser;

import java.util.Optional;

public enum Families {

    Animal,
    Démon,
    Humanoïde,
    Insecte,
    Monstre,
    MortVivant("Mort-Vivant");

    private Optional<String> label = Optional.empty();

    Families() {}

    Families(String label) {
        this();
        this.label = Optional.of(label);
    }

    public String getLabel() {
        return label.orElse(name());
    }


}
