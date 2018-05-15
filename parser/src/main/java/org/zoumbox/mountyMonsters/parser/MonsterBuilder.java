package org.zoumbox.mountyMonsters.parser;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Range;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Optional;

/**
 * Chaque méthode de cette classe prend une information incomplète en entrée et essaye de la compléter.
 */
public class MonsterBuilder {

    protected static ImmutableMonster fromName(String raw) {

        String unescaped = StringEscapeUtils.unescapeHtml4(raw.trim());
        String name = unescaped.replaceAll("ï¿½", "é");

        ImmutableMonster.Builder builder = ImmutableMonster.builder();
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
                builder.templateEnum(templates.get());
                builder.template(templates.get().getLabel());
                builder.baseName(templateAndBaseName);
            }
        } else {
            Templates monsterTemplate = source.templateEnum().orElse(null);
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
            builder.templateEnum(monsterTemplateOptional);
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
            builder.familyEnum(family);
            builder.family(family.getLabel());
            builder.baseNival(laBete.get().getNival());

        } else {
            System.err.println("############################");
            System.err.println("Pas de famille ou de nival ?");
            System.err.println("monster.baseName : " + source.baseName());
            System.err.println("name : " + name);
            System.err.println("monster.family : " + source.familyEnum());
        }

        return builder.build();

    }

    protected static ImmutableMonster finalizeExtraction(ImmutableMonster source) {

        Preconditions.checkArgument(source.baseName().isPresent());

        ImmutableMonster.Builder builder = ImmutableMonster.builder().from(source);

        int bonus = 0;

        builder.family(source.familyEnum().map(Families::getLabel));
        if (source.familyEnum().isPresent()) {
            Families family = source.familyEnum().get();

            if (source.age().isPresent()) {
                Optional<Integer> ageBonus = Ages.tryGetAgeBonus(family, source.age().get());
                builder.ageBonus(ageBonus);
                bonus += ageBonus.orElse(0);
            }
        }

        builder.template(source.templateEnum().map(Templates::getLabel));
        Optional<Integer> templateBonus = source.templateEnum().map(Templates::getBonus);
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
