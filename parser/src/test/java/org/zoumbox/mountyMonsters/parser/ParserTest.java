package org.zoumbox.mountyMonsters.parser;

import com.google.common.collect.Range;
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
        for (String file : Arrays.asList("/monsters.txt", "/monsters2.txt", "/monsters3.txt")) {
            InputStream stream = this.getClass().getResourceAsStream(file);
            List<String> rows = CharStreams.readLines(new InputStreamReader(stream));
            System.out.println(String.format("%s -> %d lignes", file, rows.size()));
            List<ImmutableMonster> monsters = rows.stream()
                    .map(MonsterParser::fromSpVue2Row)
                    .collect(Collectors.toList());

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

    @Test
    public void testGobelinMagique() {
        String row = "5944172;Gobelin Magique [Légendaire];-132;-133;-7";
        ImmutableMonster monster = MonsterParser.fromSpVue2Row(row);
        Assert.assertEquals(Families.Humanoïde, monster.familyEnum().orElse(null));
        Assert.assertEquals(7, (int)monster.nival().map(Range::lowerEndpoint).orElse(-1));
    }

    @Test
    public void testFromNameWithId() {
        String name = "Pseudo-Dragon de Quatrième Cercle [Suprême] (5863609)";
        ImmutableMonster monster = MonsterParser.fromRawName(name);
        Assert.assertTrue(monster.id().isPresent());
        Assert.assertEquals(5863609, (int)monster.id().orElse(-1));
        Assert.assertEquals(Families.Démon, monster.familyEnum().orElse(null));
        Assert.assertEquals("Pseudo-Dragon", monster.baseName().orElse(null));
        Assert.assertEquals(Templates.deQuatrièmeCercle, monster.templateEnum().orElse(null));
        Assert.assertEquals(15, (int)monster.nival().map(Range::lowerEndpoint).orElse(-1));
        Assert.assertEquals(15, (int)monster.nival().map(Range::upperEndpoint).orElse(-1));
    }

}
