package org.zoumbox.mountyFetch.parser;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Range;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Chaque méthode de cette classe prend une information incomplète en entrée et essaye de la compléter.
 */
public class MonsterBuilder {

    private static final Pattern NAME_WITH_ID_PATTERN = Pattern.compile("(.*)[(]([0-9]+)[ ]?[)]");

    protected static ImmutableMonster fromName(String raw) {

        String unescaped = StringEscapeUtils.unescapeHtml4(raw.trim());
        String name = unescaped.replaceAll("ï¿½", "é");

        ImmutableMonster.Builder builder = ImmutableMonster.builder();

        // Si le nom contient l'identifiant à la fin, on l'extrait
        Matcher matcher = NAME_WITH_ID_PATTERN.matcher(name);
        if (matcher.matches()) {
            name = matcher.group(1).trim();
            Integer id = Integer.valueOf(matcher.group(2));
            builder.id(id);
        }

        builder.fullName(name);

        int ageBeginIndex = name.indexOf("[");
        int ageEndIndex = name.indexOf("]", ageBeginIndex);
        if (ageBeginIndex != -1 && ageEndIndex != -1) {
            String age = name.substring(ageBeginIndex + 1, ageEndIndex).trim();
            builder.age(age);
            String baseName = name.substring(0, ageBeginIndex).trim();
            builder.baseName(baseName);
        } else {
            builder.baseName(name);
        }

        ImmutableMonster result = builder.build();
        return result;
    }

    protected static boolean safeStartsWith(String str, String expectedBeginning) {
        return Strings.nullToEmpty(str).indexOf(expectedBeginning) == 0;
    }

    protected static ImmutableMonster extractTemplate(ImmutableMonster source) {

        ImmutableMonster.Builder builder = ImmutableMonster.builder().from(source);

        Preconditions.checkArgument(source.baseName().isPresent());

        String name = source.baseName().get().trim() + " ";
        if (safeStartsWith(name, "Archi-")) {
            String templateAndBaseName = name.substring(6).trim();
            Optional<Templates> templates = Templates.tryFindByLabel(templateAndBaseName);
            if (templates.isPresent()) {
                builder.template(templates.get());
                builder.baseName(templateAndBaseName);
            }
        } else {
            Templates monsterTemplate = source.template().orElse(null);
            String monsterBaseName = source.baseName().orElse(null);
            Templates[] templates = Templates.values();
            for (int i = 0; i < templates.length; i++) {
                Templates template = templates[i];
                int index = name.indexOf(template.getLabel() + " "); // +" " Pour s'assurer que le mot est complet
                if (index >= 0) {
                    monsterTemplate = template;
                    monsterBaseName = (name.substring(0, index) + name.substring(index + monsterTemplate.getLabel().length())).trim();

                    // Cas particulier du Nécromant/Sorcière (template et nom de monstre)
                    if (monsterBaseName.length() == 0) {
                        monsterTemplate = null;
                        monsterBaseName = name.trim();
                    }

                    // Car particulier de la "Voleuse Sorcière" (template avant le nom)
                    Optional<Templates> templateByBaseName = Templates.tryFindByLabel(monsterBaseName);
                    if ((Templates.Sorcière.equals(monsterTemplate) || Templates.Nécromant.equals(monsterTemplate)) && templateByBaseName.isPresent()) {
                        monsterBaseName = monsterTemplate.getLabel();
                        monsterTemplate = templateByBaseName.get();
                    }

                    // Cas particulier du Frondeur vs Grand Frondeur
                    if (Templates.Frondeur.equals(monsterTemplate) && safeStartsWith(monsterBaseName, "Grand")) {
                        monsterTemplate = Templates.GrandFrondeur;
                        monsterBaseName = name.substring(14).trim();
                    }

                    // Cas particulier de la Frondeuse vs Grande Frondeuse
                    if (Templates.Frondeuse.equals(monsterTemplate) && safeStartsWith(monsterBaseName, "Grande")) {
                        monsterTemplate = Templates.GrandeFrondeuse;
                        monsterBaseName = name.substring(16).trim();
                    }
                    break;
                }
            }
            Optional<Templates> monsterTemplateOptional = Optional.ofNullable(monsterTemplate);
            builder.template(monsterTemplateOptional);
            builder.baseName(monsterBaseName);
        }
        return builder.build();
    }

    protected static ImmutableMonster extractFamilyAndNival(ImmutableMonster source) {

        Preconditions.checkArgument(source.baseName().isPresent());

        String name = source.baseName().get().trim();

        Optional<Monsters> laBete = Monsters.tryFindByLabel(name);

        ImmutableMonster.Builder builder = ImmutableMonster.builder().from(source);
        if (laBete.isPresent()) {

            Families family = laBete.get().getFamily();
            builder.family(family);
            builder.baseNival(laBete.get().getNival());

        } else {
            System.err.println("############################");
            System.err.println("Pas de famille ou de nival ?");
            System.err.println("monster.baseName : " + source.baseName());
            System.err.println("name : " + name);
            System.err.println("monster.family : " + source.family());
        }

        return builder.build();

    }

    protected static ImmutableMonster finalizeExtraction(ImmutableMonster source) {

        Preconditions.checkArgument(source.baseName().isPresent());

        ImmutableMonster.Builder builder = ImmutableMonster.builder().from(source);

        int bonus = 0;

        if (source.family().isPresent()) {
            Families family = source.family().get();

            if (source.age().isPresent()) {
                Optional<Integer> ageBonus = Ages.tryGetAgeBonus(family, source.age().get());
                builder.ageBonus(ageBonus);
                bonus += ageBonus.orElse(0);
            }
        }

        Optional<Integer> templateBonus = source.template().map(Templates::getBonus);
        builder.templateBonus(templateBonus);
        bonus += templateBonus.orElse(0);

        if (source.baseNival().isPresent()) {
            Range<Integer> range = source.baseNival().get();
            Range<Integer> rangeAfterBonus = Range.closed(range.lowerEndpoint() + bonus, range.upperEndpoint() + bonus);
            builder.nival(rangeAfterBonus);
        }
        return builder.build();

    }

}
