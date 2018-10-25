package org.zoumbox.mountyFetch.parser;

import java.util.Optional;

/**
 * La liste des templates connus
 */
public enum Templates implements WithLabel {

    // Affamé
    // Affamée
    Agressif(1),
    Agressive(1),
    Alchimiste(2),
    Alpha(11),
    Archaïque(-1),
    Archiatre(2),
    Attentionné(2),
    Attentionnée(2),
    Barbare(1),
    Berserker(2),
    Berserkere(2),
    Champion(4),
    Championne(4),
    Cogneur(2),
    Cogneuse(2),
    Colossal(7),
    Colossale(7),
    Coriace(1),
    Corrompu(1),
    Corrompue(1),
    Cracheur(2),
    Cracheuse(2),
    dePremierCercle(-1, "de Premier Cercle"),
    deSecondCercle(0, "de Second Cercle"),
    deTroisièmeCercle(2, "de Troisième Cercle"),
    deQuatrièmeCercle(4, "de Quatrième Cercle"),
    deCinquièmeCercle(5, "de Cinquième Cercle"),
    desAbysses(3, "des Abysses"),
    Effrayé(-1),
    Effrayée(-1),
    // Enflammé
    // Enflammée
    // Enorme
    Enragé(3),
    Enragée(3),
    Esculape(2),
    Ethéré(3),
    Ethérée(3),
    // Fabulesque
    Fanatique(2),
    Fou(1),
    Folle(1),
    Fouisseur(0),
    Fouisseuse(0),
    Frénétique(3),
    Frondeur(2),
    Frondeuse(2),
    Fustigateur(2),
    Fustigatrice(2),
    Gardien(20),
    Gardienne(20),
    Gargantuesque(3),
    Gigantesque(1),
    GrandFrondeur(4, "Grand Frondeur"),
    GrandeFrondeuse(4, "Grande Frondeuse"),
    Gros(0),
    Grosse(0),
    Guérisseur(2),
    Guérisseuse(2),
    Guerrier(1),
    Guerrière(1),
    Héros(5),
    Homochrome(2),
    Homomorphe(3),
    Implacable(3),
    Invocateur(5),
    Invocatrice(5),
    Lobotomisateur(2),
    Lobotomisatrice(2),
    Maitre(8),
    Maître(8),
    Maîtresse(8),
    Malade(-1),
    Médicastre(2),
    Mentat(2),
    Morticole(2),
    Mutant(2),
    Mutante(2),
    Nécromant(5),
    Nécromante(5),
    Ouvrier(0),
    Ouvrière(0),
    Parasitus(2),
    Paysan(-1),
    Paysanne(-1),
    Petit(-1),
    Petite(-1),
    Planqué(1),
    Planquée(1),
    Prince(8),
    Princesse(8),
    Psychophage(2),
    Reine(11),
    Ronfleur(2),
    Ronfleuse(2),
    Scout(2),
    Shaman(0),
    Soldat(2),
    Sorcier(0),
    Sorcière(0),
    Spectral(3),
    Spectrale(3),
    Strident(3),
    Stridente(3),
    Traqueur(1),
    Traqueuse(1),
    // Très gros
    // Très grosse
    Voleur(2),
    Voleuse(2),
    Vorace(1);

    private int bonus;
    private Optional<String> label = Optional.empty();

    Templates(int bonus) {
        this.bonus = bonus;
    }

    Templates(int bonus, String label) {
        this(bonus);
        this.label = Optional.of(label);
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public String getLabel() {
        return label.orElse(name());
    }

    public static Optional<Templates> tryFindByLabel(String label) {
        for (Templates template : values()) {
            if (template.getLabel().equals(label)) {
                return Optional.of(template);
            }
        }
        return Optional.empty();
    }

}
