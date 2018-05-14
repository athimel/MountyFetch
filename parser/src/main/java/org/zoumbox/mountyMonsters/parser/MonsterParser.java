package org.zoumbox.mountyMonsters.parser;

import com.google.common.base.Preconditions;

import java.util.Optional;

public class MonsterParser {

    public static ImmutableMonster fromName(String raw) {
        String name = raw.trim();
        ImmutableMonster.Builder builder = ImmutableMonster.builder();
        builder.nom(name);

        int ageBeginIndex = name.indexOf("[");
        int ageEndIndex = name.indexOf("]", ageBeginIndex);
        String age = name.substring(ageBeginIndex + 1, ageEndIndex).trim();
        builder.age(age);
        String baseName = name.substring(0, ageBeginIndex).trim();
        builder.baseName(baseName);

        ImmutableMonster result = builder.build();
        return result;
    }

    public static ImmutableMonster extractTemplate(ImmutableMonster source) {

        ImmutableMonster.Builder builder = ImmutableMonster.builder().from(source);

        Preconditions.checkArgument(source.baseName().isPresent());

        String name = source.baseName().get().trim() + " ";
        if (name.substring(0, 6).equals("Archi-")) {
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
                    if (Templates.Frondeur.equals(monsterTemplate) && monsterBaseName.substring(0, 5).equals("Grand")) {
                        monsterTemplate = Templates.GrandFrondeur;
                        monsterBaseName = name.substring(14).trim();
                    }

                    // Cas particulier de la Frondeuse vs Grande Frondeuse
                    if (Templates.Frondeuse.equals(monsterTemplate) && monsterBaseName.substring(0, 6).equals("Grande")) {
                        monsterTemplate = Templates.GrandeFrondeuse;
                        monsterBaseName = name.substring(16).trim();
                    }
                    break;
                }
            }
            Optional<Templates> monsterTemplateOptional = Optional.ofNullable(monsterTemplate);
            builder.templateEnum(monsterTemplateOptional);
            builder.template(monsterTemplateOptional.map(Templates::getLabel));
            builder.baseName(monsterBaseName);
        }
        return builder.build();
    }

    public static ImmutableMonster extractFamilyAndNival(ImmutableMonster source) {

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
            // WTF blablabla
//            if (!monster.family || monster.baseNival === undefined) {
//                console.error("############################");
//                console.error("Pas de famille ou de nival ?");
//                console.error("monster.baseName : " + monster.baseName);
//                console.error("name : " + name);
//                console.error("monster.family : " + monster.family);
//                if (monster.family) {
//                    console.error("monsters.families[monster.family] : ", monsters.families[monster.family]);
//                    if (monsters.families[monster.family]) {
//                        console.error("monsters.families[monster.family][name] : ", monsters.families[monster.family][name]);
//                    }
//                }
//            }
        }

        return builder.build();

    }

}
