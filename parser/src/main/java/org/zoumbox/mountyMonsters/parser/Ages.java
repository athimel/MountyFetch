package org.zoumbox.mountyMonsters.parser;

import com.google.common.collect.ImmutableMap;

import java.util.Optional;

public class Ages {

    private static final ImmutableMap<Families, ImmutableMap<String, Integer>> AGES;
    static {
        ImmutableMap.Builder<Families, ImmutableMap<String, Integer>> builder = ImmutableMap.builder();
        {
            ImmutableMap.Builder<String, Integer> familyBuilder = ImmutableMap.builder();
            familyBuilder.put("Bébé", 0);
            familyBuilder.put("Enfançon", 1);
            familyBuilder.put("Jeune", 2);
            familyBuilder.put("Adulte", 3);
            familyBuilder.put("Mature", 4);
            familyBuilder.put("Chef de Harde", 5);
            familyBuilder.put("Chef de harde", 5);
            familyBuilder.put("Ancien", 6);
            familyBuilder.put("Ancienne", 6);
            familyBuilder.put("Ancêtre", 7);
            builder.put(Families.Animal, familyBuilder.build());
        }
        {
            ImmutableMap.Builder<String, Integer> familyBuilder = ImmutableMap.builder();
            familyBuilder.put("Initial", 0);
            familyBuilder.put("Initiale", 0);
            familyBuilder.put("Novice", 1);
            familyBuilder.put("Mineur", 2);
            familyBuilder.put("Mineure", 2);
            familyBuilder.put("Favori", 3);
            familyBuilder.put("Favorite", 3);
            familyBuilder.put("Majeur", 4);
            familyBuilder.put("Majeure", 4);
            familyBuilder.put("Supérieur", 5);
            familyBuilder.put("Supérieure", 5);
            familyBuilder.put("Suprême", 6);
            familyBuilder.put("Ultime", 7);
            builder.put(Families.Démon, familyBuilder.build());
        }
        {
            ImmutableMap.Builder<String, Integer> familyBuilder = ImmutableMap.builder();
            familyBuilder.put("Nouveau", 0);
            familyBuilder.put("Nouvelle", 0);
            familyBuilder.put("Jeune", 1);
            familyBuilder.put("Adulte", 2);
            familyBuilder.put("Vétéran", 3);
            familyBuilder.put("Briscard", 4);
            familyBuilder.put("Briscarde", 4);
            familyBuilder.put("Doyen", 5);
            familyBuilder.put("Doyenne", 5);
            familyBuilder.put("Légendaire", 6);
            familyBuilder.put("Mythique", 7);
            builder.put(Families.Humanoïde, familyBuilder.build());
        }
        {
            ImmutableMap.Builder<String, Integer> familyBuilder = ImmutableMap.builder();
            familyBuilder.put("Larve", 0);
            familyBuilder.put("Immature", 1);
            familyBuilder.put("Juvénile", 2);
            familyBuilder.put("Imago", 3);
            familyBuilder.put("Développé", 4);
            familyBuilder.put("Développée", 4);
            familyBuilder.put("Mûr", 5);
            familyBuilder.put("Mûre", 5);
            familyBuilder.put("Accompli", 6);
            familyBuilder.put("Accomplie", 6);
            familyBuilder.put("Achevé", 7);
            familyBuilder.put("Achevée", 7);
            builder.put(Families.Insecte, familyBuilder.build());
        }
        {
            ImmutableMap.Builder<String, Integer> familyBuilder = ImmutableMap.builder();
            familyBuilder.put("Nouveau", 0);
            familyBuilder.put("Nouvelle", 0);
            familyBuilder.put("Jeune", 1);
            familyBuilder.put("Adulte", 2);
            familyBuilder.put("Vétéran", 3);
            familyBuilder.put("Briscard", 4);
            familyBuilder.put("Briscarde", 4);
            familyBuilder.put("Doyen", 5);
            familyBuilder.put("Doyenne", 5);
            familyBuilder.put("Légendaire", 6);
            familyBuilder.put("Mythique", 7);
            builder.put(Families.Monstre, familyBuilder.build());
        }
        {
            ImmutableMap.Builder<String, Integer> familyBuilder = ImmutableMap.builder();
            familyBuilder.put("Naissant", 0);
            familyBuilder.put("Naissante", 0);
            familyBuilder.put("Récent", 1);
            familyBuilder.put("Récente", 1);
            familyBuilder.put("Ancien", 2);
            familyBuilder.put("Ancienne", 2);
            familyBuilder.put("Vénérable", 3);
            familyBuilder.put("Séculaire", 4);
            familyBuilder.put("Antique", 5);
            familyBuilder.put("Ancestral", 6);
            familyBuilder.put("Ancestrale", 6);
            familyBuilder.put("Antédiluvien", 7);
            familyBuilder.put("Antédiluvienne", 7);
            builder.put(Families.MortVivant, familyBuilder.build());
        }
        AGES = builder.build();
    }

    public static Optional<Integer> tryGetAgeBonus(Families family, String age) {
        ImmutableMap<String, Integer> map = AGES.get(family);
        Integer integer = map.get(age);
        if (integer == null) {
            System.err.println("Age non dispo: " + family + " -> " + age);
        }
        Optional<Integer> result = Optional.ofNullable(integer);
        return result;
    }
}
