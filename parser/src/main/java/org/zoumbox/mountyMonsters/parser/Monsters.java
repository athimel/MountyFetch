package org.zoumbox.mountyMonsters.parser;

import com.google.common.collect.Range;

import java.util.Optional;

/**
 * La liste des monstres connus avec leur famille ({@link Families}) et le niveau de base
 */
public enum Monsters {

    ChauveSouris(Families.Animal, 3, "Chauve-Souris"),
    ChauveSourisGéante(Families.Animal, 4, "Chauve-Souris Géante"),
    ChevalÀDentsDeSabre(Families.Animal, 23, "Cheval à Dents de Sabre"),
    Dindon(Families.Animal, 1),
    DindonDuChaos(Families.Animal, 1, "Dindon du Chaos"),
//    DindonDuGlacier(Families.Animal, 1, "Dindon Du Glacier"),
    Geckoo(Families.Animal, 15, "Geck'oo"),
    GeckooMajestueux(Families.Animal, 40, "Geck'oo majestueux"),
    Glouton(Families.Animal, 20),
    Gnu(Families.Animal, 1),
    GnuSauvage(Families.Animal, 1, "Gnu Sauvage"),
    GnuDomestique(Families.Animal, 1, "Gnu Domestique"),
    Gowap(Families.Animal, 1),
    GowapApprivoisé(Families.Animal, 1, "Gowap Apprivoisé"),
    GowapSauvage(Families.Animal, 1, "Gowap Sauvage"),
    LapinBlanc(Families.Animal, 1, "Lapin Blanc"),
    PoissonRouge(Families.Animal, 0, "Poisson Rouge"),
    Rat(Families.Animal, 1, "Rat"),
    RatGéant(Families.Animal, 2, "Rat Géant"),
    Sagouin(Families.Animal, 3),
    TuberculeTueur(Families.Animal, 14, "Tubercule Tueur"),
    Ankheg(Families.Insecte, 10),
    AnoplourePurpurin(Families.Insecte, 36, "Anoploure Purpurin"),
    AragnarokDuChaos(Families.Insecte, 16, "Aragnarok du Chaos"),
    Araignée(Families.Insecte, 1, "Araignée"),
    AraignéeGéante(Families.Insecte, 2, "Araignée Géante"),
    Coccicruelle(Families.Insecte, 22),
    EssaimCratérien(Families.Insecte, 30, "Essaim Cratérien"),
    EssaimSanguinaire(Families.Insecte, 25, "Essaim Sanguinaire"),
    Foudroyeur(Families.Insecte, 38),
    Labeilleux(Families.Insecte, 26),
    LimaceGéante(Families.Insecte, 10, "Limace Géante"),
    ManteFulcreuse(Families.Insecte, 30, "Mante Fulcreuse"),
    MillePattes(Families.Insecte, 13, "Mille-Pattes"),
    MillePattesGéant(Families.Insecte, 14, "Mille-Pattes Géant"),
    NuageDInsectes(Families.Insecte, 7, "Nuage d'Insectes"),
    NuéeDeVermine(Families.Insecte, 13, "Nuée de Vermine"),
    Pititabeille(Families.Insecte, 0),
    Scarabée(Families.Insecte, 3, "Scarabée"),
    ScarabéeGéant(Families.Insecte, 4, "Scarabée Géant"),
    Scorpion(Families.Insecte, 9, "Scorpion"),
    ScorpionGéant(Families.Insecte, 10, "Scorpion Géant"),
    Strige(Families.Insecte, 2),
    ThriKreen(Families.Insecte, 10, "Thri-kreen"),
    AmeEnPeine(Families.MortVivant, 8, "Ame-en-peine"),
    Banshee(Families.MortVivant, 16),
    Capitan(Families.MortVivant, 35),
    Croquemitaine(Families.MortVivant, 6),
    Ectoplasme(Families.MortVivant, 18),
    Fantôme(Families.MortVivant, 24),
    Goule(Families.MortVivant, 4),
    Liche(Families.MortVivant, 50),
    Mohrg(Families.MortVivant, 35),
    Momie(Families.MortVivant, 4),
    NâHàniymHééé(Families.MortVivant, 0, "Nâ-Hàniym-Hééé"),
    Nécrochore(Families.MortVivant, 37),
    Nécromant(Families.MortVivant, 39),
    Nécrophage(Families.MortVivant, 8),
    Ombre(Families.MortVivant, 2),
    Spectre(Families.MortVivant, 14),
    Squelette(Families.MortVivant, 1),
    Vampire(Families.MortVivant, 29),
    Zombie(Families.MortVivant, 2),
    Amibe(Families.Monstre, 8, "Amibe"),
    AmibeGéante(Families.Monstre, 9, "Amibe Géante"),
    AnacondaDesCatacombes(Families.Monstre, 8, "Anaconda des Catacombes"),
    Basilisk(Families.Monstre, 11),
    Behir(Families.Monstre, 14),
    Beholder(Families.Monstre, 50),
    Bondin(Families.Monstre, 9),
    BoujDla(Families.Monstre, 19, "Bouj'Dla"),
    BoujDlaPlacide(Families.Monstre, 37, "Bouj'Dla Placide"),
    Bulette(Families.Monstre, 19),
    Carnosaure(Families.Monstre, 25),
    Chimère(Families.Monstre, 13),
    Chonchon(Families.Monstre, 24),
    Cockatrice(Families.Monstre, 5),
    Crasc(Families.Monstre, 10),
    CrascMédius(Families.Monstre, 17, "Crasc Médius"),
    CrascMaexus(Families.Monstre, 29, "Crasc Maexus"),
    CrascParasitus(Families.Monstre, 14, "Crasc parasitus"),
    CubeGélatineux(Families.Monstre, 32, "Cube Gélatineux"),
    Djinn(Families.Monstre, 29),
    Effrit(Families.Monstre, 27),
    EspritFollet(Families.Monstre, 16, "Esprit-Follet"),
    Familier(Families.Monstre, 1),
    FeuFollet(Families.Monstre, 20, "Feu Follet"),
    Fungus(Families.Monstre, 8),
    FungusGéant(Families.Monstre, 9, "Fungus Géant"),
    FungusViolet(Families.Monstre, 4, "Fungus Violet"),
    Gargouille(Families.Monstre, 3),
    Gorgone(Families.Monstre, 11),
    Grouilleux(Families.Monstre, 4),
    Grylle(Families.Monstre, 31),
    Harpie(Families.Monstre, 4),
    Hydre(Families.Monstre, 50),
    KilamoEtLaMouche(Families.Monstre, 1, "Kilamo et La Mouche"),
    Lézard(Families.Monstre, 4, "Lézard"),
    LézardGéant(Families.Monstre, 5, "Lézard Géant"),
    Manticore(Families.Monstre, 9),
    Mimique(Families.Monstre, 6),
    MonstreRouilleur(Families.Monstre, 3, "Monstre Rouilleur"),
    Mouchoo(Families.Monstre, 14, "Mouch'oo"),
    MouchooDomestique(Families.Monstre, 14, "Mouch'oo Domestique"),
    MouchooSauvage(Families.Monstre, 14, "Mouch'oo Sauvage"),
    MouchooMajestueux(Families.Monstre, 33, "Mouch'oo Majestueux"),
    MouchooMajestueuxSauvage(Families.Monstre, 33, "Mouch'oo Majestueux Sauvage"),
    Naga(Families.Monstre, 10),
    OmbreDeRoches(Families.Monstre, 13, "Ombre de Roches"),
    Phoenix(Families.Monstre, 32, 36),
    PlanteCarnivore(Families.Monstre, 4, "Plante Carnivore"),
    Slaad(Families.Monstre, 5),
    TertreErrant(Families.Monstre, 20, "Tertre Errant"),
    Trancheur(Families.Monstre, 35),
    Tutoki(Families.Monstre, 4),
    VerCarnivore(Families.Monstre, 11, "Ver Carnivore"),
    VerCarnivoreGéant(Families.Monstre, 12, "Ver Carnivore Géant"),
    Vouivre(Families.Monstre, 33),
    Worg(Families.Monstre, 5),
    AbishaiiBleu(Families.Démon, 19, "Abishaii Bleu"),
    AbishaiiNoir(Families.Démon, 10, "Abishaii Noir"),
    AbishaiiRouge(Families.Démon, 23, "Abishaii Rouge"),
    AbishaiiVert(Families.Démon, 15, "Abishaii Vert"),
    Balrog(Families.Démon, 50),
    Barghest(Families.Démon, 36),
    Behemoth(Families.Démon, 34),
    ChevalierDuChaos(Families.Démon, 20, "Chevalier du Chaos"),
    Daemonite(Families.Démon, 27),
    Diablotin(Families.Démon, 5),
    ElementaireDAir(Families.Démon, 23, "Elementaire d'Air"),
    ElémentaireDAir(Families.Démon, 23, "Elémentaire d'Air"),
    ElementaireDEau(Families.Démon, 17, "Elementaire d'Eau"),
    ElémentaireDEau(Families.Démon, 17, "Elémentaire d'Eau"),
    ElementaireDeFeu(Families.Démon, 21, "Elementaire de Feu"),
    ElémentaireDeFeu(Families.Démon, 21, "Elémentaire de Feu"),
    ElementaireDeTerre(Families.Démon, 21, "Elementaire de Terre"),
    ElémentaireDeTerre(Families.Démon, 21, "Elémentaire de Terre"),
    ElementaireDuChaos(Families.Démon, 26, "Elementaire du Chaos"),
    ElémentaireDuChaos(Families.Démon, 26, "Elémentaire du Chaos"),
    ElementaireMagmatique(Families.Démon, 0, "Elementaire Magmatique"),
    ElémentaireMagmatique(Families.Démon, 0, "Elémentaire Magmatique"),
    Erinyes(Families.Démon, 7),
    GrosseErinyes(Families.Démon, 8, "Grosse Erinyes"),
    Fumeux(Families.Démon, 22),
    Gritche(Families.Démon, 39),
    Hellrot(Families.Démon, 18),
    Incube(Families.Démon, 13),
    Marilith(Families.Démon, 33),
    MolosseSatanique(Families.Démon, 8, "Molosse Satanique"),
    PalefroiInfernal(Families.Démon, 29, "Palefroi Infernal"),
    PseudoDragon(Families.Démon, 5, "Pseudo-Dragon"),
    Shai(Families.Démon, 28),
    Succube(Families.Démon, 13),
    Xorn(Families.Démon, 14),
    Ashashin(Families.Humanoïde, 35),
    Boggart(Families.Humanoïde, 3),
    Caillouteux(Families.Humanoïde, 1),
    ChampiGlouton(Families.Humanoïde, 3, "Champi-Glouton"),
    Ettin(Families.Humanoïde, 8),
    FlagelleurMental(Families.Humanoïde, 33, "Flagelleur Mental"),
    Furgolin(Families.Humanoïde, 10),
    GéantDePierre(Families.Humanoïde, 13, "Géant de Pierre"),
    GéantDesGouffres(Families.Humanoïde, 22, "Géant des Gouffres"),
    Gnoll(Families.Humanoïde, 5),
    GobelinMagique(Families.Humanoïde, 1, "Gobelin Magique"),
    Goblin(Families.Humanoïde, 4),
    Goblours(Families.Humanoïde, 4),
    GolemDecuir(Families.Humanoïde, 1, "Golem de cuir"),
    GolemDemithril(Families.Humanoïde, 1, "Golem de mithril"),
    GolemDemétal(Families.Humanoïde, 1, "Golem de métal"),
    GolemDepapier(Families.Humanoïde, 1, "Golem de papier"),
    GolemDArgile(Families.Humanoïde, 15, "Golem d'Argile"),
    GolemDeChair(Families.Humanoïde, 8, "Golem de Chair"),
    GolemDeFer(Families.Humanoïde, 31, "Golem de Fer"),
    GolemDePierre(Families.Humanoïde, 23, "Golem de Pierre"),
    Gremlins(Families.Humanoïde, 3),
    HommeLézard(Families.Humanoïde, 4, "Homme-Lézard"),
    Hurleur(Families.Humanoïde, 8),
    Kobold(Families.Humanoïde, 2),
    LoupGarou(Families.Humanoïde, 8, "Loup-Garou"),
    Lutin(Families.Humanoïde, 4),
    Méduse(Families.Humanoïde, 6),
    Mégacéphale(Families.Humanoïde, 38),
    Minotaure(Families.Humanoïde, 7),
    Ogre(Families.Humanoïde, 7),
    Orque(Families.Humanoïde, 3),
    OursGarou(Families.Humanoïde, 18, "Ours-Garou"),
    Raquettou(Families.Humanoïde, 1),
    RatGarou(Families.Humanoïde, 3, "Rat-Garou"),
    Rocketeux(Families.Humanoïde, 5),
    Sorcière(Families.Humanoïde, 17),
    Sphinx(Families.Humanoïde, 30),
    TigreGarou(Families.Humanoïde, 12, "Tigre-Garou"),
    Titan(Families.Humanoïde, 26),
    Veskan_duChaos(Families.Humanoïde, 14, "Veskan du Chaos"),
    VeskanDuChaos(Families.Humanoïde, 14, "Veskan Du Chaos"),
    Yéti(Families.Humanoïde, 8),
    YuanTi(Families.Humanoïde, 15, "Yuan-ti");

    private final Range<Integer> nival;
    private final Families family;
    private Optional<String> label = Optional.empty();

    Monsters(Families family, int nival) {
        this.family = family;
        this.nival = Range.closed(nival, nival);
    }

    Monsters(Families family, int nivalMin, int nivalMax) {
        this.family = family;
        this.nival = Range.closed(nivalMin, nivalMax);
    }

    Monsters(Families family, int nival, String label) {
        this(family, nival);
        this.label = Optional.of(label);
    }

    public String getLabel() {
        return label.orElse(name());
    }

    public Range<Integer> getNival() {
        return nival;
    }

    public Families getFamily() {
        return family;
    }

    public static Optional<Monsters> tryFindByLabel(String label) {
        for (Monsters monster : values()) {
            if (monster.getLabel().equals(label)) {
                return Optional.of(monster);
            }
        }
        System.err.println("Monstre introuvable: " + label);
        return Optional.empty();
    }

}
