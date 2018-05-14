package org.zoumbox.mountyMonsters.parser;


import org.apache.commons.text.StringEscapeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonsterParser {

    public static final Pattern SP_VUE2_PATTERN = Pattern.compile("([0-9]*);(.*);([-]?[0-9]*);([-]?[0-9]*);([-]?[0-9]*)");

    /**
     * Essaye de calculer un maximum d'informations sur un monstre à partir de son nom. Exemple :
     * <pre>Maîtresse Ame-en-peine [Naissante]</pre>
     *
     * @param name le nom brut
     * @return Les informations déduites à propos du monstre
     */
    public static ImmutableMonster fromRawName(String name) {
        String unescaped = StringEscapeUtils.unescapeHtml4(name);
        ImmutableMonster result = MonsterBuilder.fromName(unescaped);
        result = MonsterBuilder.extractTemplate(result);
        result = MonsterBuilder.extractFamilyAndNival(result);
        result = MonsterBuilder.finalizeExtraction(result);
        if (!result.nival().isPresent()) {
            System.err.println("Impossible de trouver le nival : " + result);
        }
        return result;
    }

    /**
     * Essaye de calculer un maximum d'informations sur un monstre à partir de la ligne extraite de Sp_Vue2.php).
     * Exemple :
     * <pre>5864923;Maîtresse Ame-en-peine [Naissante];-74;-40;-78</pre>
     *
     * @param row La ligne brute provenant de Sp_Vue2.php
     * @return Les informations déduites à propos du monstre
     * @see #fromRawName(String)
     */
    public static ImmutableMonster fromSpVue2Row(String row) {
        Matcher matcher = SP_VUE2_PATTERN.matcher(row);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Format invalide: %s", row));
        }

        String name = matcher.group(2);
        ImmutableMonster result = fromRawName(name);
        ImmutablePosition position = ImmutablePosition.builder()
                .x(Integer.valueOf(matcher.group(3)))
                .y(Integer.valueOf(matcher.group(4)))
                .n(Integer.valueOf(matcher.group(5)))
                .build();
        result = ImmutableMonster.builder()
                .from(result)
                .id(Integer.valueOf(matcher.group(1)))
                .position(position)
                .build();
        return result;
    }

}
