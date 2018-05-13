package org.zoumbox.mountyMonsters.engine;

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
            builder.template(name.substring(6).trim());
            builder.baseName(name.substring(6).trim());
        } else {
            String monsterTemplate = source.template().orElse(null);
            String monsterBaseName = source.baseName().orElse(null);
            Templates[] templates = Templates.values();
            for (int i = 0; i < templates.length; i++) {
                Templates template = templates[i];
                int index = name.indexOf(template.getLabel() + " "); // +" " Pour s'assurer que le mot est complet
                if (index >= 0) {
                    monsterTemplate = template.getLabel();
                    monsterBaseName = (name.substring(0, index) + name.substring(index + monsterTemplate.length())).trim();

                    // Cas particulier du Nécromant/Sorcière (template et nom de monstre)
                    if (monsterBaseName.length() == 0) {
                        monsterTemplate = null;
                        monsterBaseName = name.trim();
                    }

                    // Car particulier de la "Voleuse Sorcière" (template avant le nom)
                    if ((monsterTemplate.equals("Sorcière") || monsterTemplate.equals("Nécromant")) && Templates.tryFindByLabel(monsterBaseName).isPresent()) {
                        String tmp = monsterBaseName;
                        monsterBaseName = monsterTemplate;
                        monsterTemplate = tmp;
                    }

                    // Cas particulier du Frondeur vs Grand Frondeur
                    if (monsterTemplate.equals("Frondeur") && monsterBaseName.substring(0, 5).equals("Grand")) {
                        monsterTemplate = "Grand Frondeur";
                        monsterBaseName = name.substring(14).trim();
                    }

                    // Cas particulier de la Frondeuse vs Grande Frondeuse
                    if (monsterTemplate.equals("Frondeuse") && monsterBaseName.substring(0, 6).equals("Grande")) {
                        monsterTemplate = "Grande Frondeuse";
                        monsterBaseName = name.substring(16).trim();
                    }
                    break;
                }
            }
            builder.template(Optional.ofNullable(monsterTemplate));
            builder.baseName(monsterBaseName);
        }
        return builder.build();
    }

}
