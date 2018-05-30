package org.zoumbox.mountyFetch.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lit et échappe les informations fournies par l'utilisateur (ou MH) et délègue le calcul à {@link TrollBuilder}
 */
public class TrollParser {

    private static final Pattern SP_VUE2_TROLL_PATTERN = Pattern.compile("([0-9]*);([-]?[0-9]*);([-]?[0-9]*);([-]?[0-9]*)");

    /**
     * Charge les informations publiques d'un troll. Exemple :
     * <pre>104259</pre>
     *
     * @param id le nom brut
     * @return Les informations déduites à propos du troll
     */
    public static ImmutableTroll fromId(Integer id) {
        ImmutableTroll result = Troll.of(id);
        result = TrollBuilder.fromId(result);
        result = TrollBuilder.extractGuilde(result);
        return result;
    }

    /**
     * Essaye de calculer un maximum d'informations sur un troll à partir de la ligne extraite de Sp_Vue2.php).
     * Exemple :
     * <pre>108883;-11;20;-78</pre>
     *
     * @param row La ligne brute provenant de Sp_Vue2.php
     * @return Les informations déduites à propos du troll
     * @see #fromId(Integer)
     */
    public static ImmutableTroll fromSpVue2Row(String row) {
        Matcher matcher = SP_VUE2_TROLL_PATTERN.matcher(row);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Format invalide: %s", row));
        }

        int id = Integer.valueOf(matcher.group(1));
        ImmutableTroll result = fromId(id);
        Position position = Position.of(
                Integer.valueOf(matcher.group(2)),
                Integer.valueOf(matcher.group(3)),
                Integer.valueOf(matcher.group(4)));
        result = ImmutableTroll.builder()
                .from(result)
                .position(position)
                .build();
        return result;
    }

}
