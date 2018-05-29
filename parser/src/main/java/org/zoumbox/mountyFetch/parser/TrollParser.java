package org.zoumbox.mountyFetch.parser;

/**
 * Lit et échappe les informations fournies par l'utilisateur (ou MH) et délègue le calcul à {@link TrollBuilder}
 */
public class TrollParser {

//    private static final Pattern SP_VUE2_PATTERN = Pattern.compile("([0-9]*);(.*);([-]?[0-9]*);([-]?[0-9]*);([-]?[0-9]*)");

    /**
     * Charge les informations publiques d'un troll. Exemple :
     * <pre>104259</pre>
     *
     * @param id le nom brut
     * @return Les informations déduites à propos du troll
     */
    public static ImmutableTroll fromId(Integer id) {
        ImmutableTroll result = TrollBuilder.fromId(id);
        result = TrollBuilder.extractGuilde(result);
        return result;
    }

    /**
     * Charge les informations publiques d'un troll. Exemple :
     * <pre>DevelZimZoum</pre>
     *
     * @param name le nom brut
     * @return Les informations déduites à propos du troll
     */
    public static ImmutableTroll fromName(String name) {
        ImmutableTroll result = TrollBuilder.fromName(name);
        result = TrollBuilder.extractGuilde(result);
        return result;
    }

//
//    /**
//     * Essaye de calculer un maximum d'informations sur un monstre à partir de la ligne extraite de Sp_Vue2.php).
//     * Exemple :
//     * <pre>5864923;Maîtresse Ame-en-peine [Naissante];-74;-40;-78</pre>
//     *
//     * @param row La ligne brute provenant de Sp_Vue2.php
//     * @return Les informations déduites à propos du monstre
//     * @see #fromRawName(String)
//     */
//    public static ImmutableMonster fromSpVue2Row(String row) {
//        Matcher matcher = SP_VUE2_PATTERN.matcher(row);
//        if (!matcher.matches()) {
//            throw new IllegalArgumentException(String.format("Format invalide: %s", row));
//        }
//
//        String name = matcher.group(2);
//        ImmutableMonster result = fromRawName(name);
//        ImmutablePosition position = ImmutablePosition.builder()
//                .x(Integer.valueOf(matcher.group(3)))
//                .y(Integer.valueOf(matcher.group(4)))
//                .n(Integer.valueOf(matcher.group(5)))
//                .build();
//        result = ImmutableMonster.builder()
//                .from(result)
//                .id(Integer.valueOf(matcher.group(1)))
//                .position(position)
//                .build();
//        return result;
//    }

}
