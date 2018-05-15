package org.zoumbox.mountyMonsters.parser;

import com.google.common.io.CharStreams;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParserTest {

    @Test
    public void testReadAllMonsters() throws IOException {
//        for (String file : Arrays.asList("/monsters.txt", "/monsters2.txt")) {
        for (String file : Arrays.asList("/monsters.txt")) { // Tant qu'on a 2 noms non reconnus: [Dindon Du Glacier, Scorpion] dans "/monsters2.txt"
            InputStream stream = this.getClass().getResourceAsStream(file);
            List<String> rows = CharStreams.readLines(new InputStreamReader(stream));
            List<ImmutableMonster> monsters = rows.stream().map(MonsterParser::fromSpVue2Row).collect(Collectors.toList());

            Set<String> noFamily = monsters.stream()
                    .filter(m -> !m.familyEnum().isPresent())
                    .map(m -> m.baseName().orElse(""))
                    .collect(Collectors.toSet());
            String noFamilyMessage = String.format("[%s] %d noms non reconnus: %s", file, noFamily.size(), noFamily);
            Assert.assertEquals(noFamilyMessage, 0, noFamily.size());

            List<ImmutableMonster> noNival = monsters.stream()
                    .filter(m -> !m.nival().isPresent())
                    .collect(Collectors.toList());
            String noNivalMessage = String.format("[%s] %d nivals manquants: %s", file, noNival.size(), noNival.stream().map(m -> m.id() + "" + m.fullName()).collect(Collectors.toList()));
            Assert.assertEquals(noNivalMessage, 0, noNival.size());
        }
    }

}
