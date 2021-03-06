package org.zoumbox.mountyFetch.parser;

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

public class MonsterParserTest {

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
                    .filter(m -> !m.family().isPresent())
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
        Assert.assertEquals(Families.Humanoïde, monster.family().orElse(null));
        Assert.assertEquals(7, (int)monster.nival().map(Range::lowerEndpoint).orElse(-1));
    }

    @Test
    public void testFromNameWithId() {
        String name = "Pseudo-Dragon de Quatrième Cercle [Suprême] (5863609)";
        ImmutableMonster monster = MonsterParser.fromRawName(name);
        Assert.assertTrue(monster.id().isPresent());
        Assert.assertEquals(5863609, (int)monster.id().orElse(-1));
        Assert.assertEquals(Families.Démon, monster.family().orElse(null));
        Assert.assertEquals("Pseudo-Dragon", monster.baseName().orElse(null));
        Assert.assertEquals(Templates.deQuatrièmeCercle, monster.template().orElse(null));
        Assert.assertEquals(15, (int)monster.nival().map(Range::lowerEndpoint).orElse(-1));
        Assert.assertEquals(15, (int)monster.nival().map(Range::upperEndpoint).orElse(-1));
    }

    @Test
    public void testZombiDeTroll() {
        String row = "Zombi de Micko [Antédiluvien] (6035328)";
        ImmutableMonster monster = MonsterParser.fromRawName(row);
        Assert.assertEquals(Families.MortVivant, monster.family().orElse(null));
        Assert.assertEquals(27, (int)monster.nival().map(Range::lowerEndpoint).orElse(-1));
        Assert.assertEquals(27, (int)monster.nival().map(Range::upperEndpoint).orElse(-1));
    }

    @Test
    public void testFromNameLowercase() {
        String name = "effrit";
        ImmutableMonster monster = MonsterParser.fromRawName(name);
        Assert.assertEquals(Families.Monstre, monster.family().orElse(null));
        Assert.assertEquals("Effrit", monster.baseName().orElse(null));
        Assert.assertEquals(27, (int)monster.nival().map(Range::lowerEndpoint).orElse(-1));
        Assert.assertEquals(27, (int)monster.nival().map(Range::upperEndpoint).orElse(-1));
    }

    @Test
    public void testFromNameTooMuchSpaces() {
        String name = "effrit    coriace   [nouveau]   (789102)";
        ImmutableMonster monster = MonsterParser.fromRawName(name);
        Assert.assertTrue(monster.id().isPresent());
        Assert.assertEquals(789102, (int)monster.id().orElse(-1));
        Assert.assertEquals(Families.Monstre, monster.family().orElse(null));
        Assert.assertEquals("Effrit", monster.baseName().orElse(null));
        Assert.assertEquals(Templates.Coriace, monster.template().orElse(null));
        Assert.assertEquals("Nouveau", monster.age().orElse(null));
        Assert.assertEquals(28, (int)monster.nival().map(Range::lowerEndpoint).orElse(-1));
        Assert.assertEquals(28, (int)monster.nival().map(Range::upperEndpoint).orElse(-1));
    }

    @Test
    public void testAragnarokDuChaos() {
        String row = "Aragnarok du Chaos [Larve] (6109392)";
        ImmutableMonster monster = MonsterParser.fromRawName(row);
        Assert.assertEquals(Families.Insecte, monster.family().orElse(null));
        Assert.assertEquals(16, (int)monster.nival().map(Range::lowerEndpoint).orElse(-1));
        Assert.assertEquals(16, (int)monster.nival().map(Range::upperEndpoint).orElse(-1));
    }

    @Test
    public void testAragnarokDuChaosEnflammée() {
        String row = "Aragnarok du Chaos Enflammée [Larve] (6109392)";
        ImmutableMonster monster = MonsterParser.fromRawName(row);
        Assert.assertEquals(Families.Insecte, monster.family().orElse(null));
        Assert.assertEquals(21, (int)monster.nival().map(Range::lowerEndpoint).orElse(-1));
        Assert.assertEquals(21, (int)monster.nival().map(Range::upperEndpoint).orElse(-1));
    }

    @Test
    public void testGeckooMajestueux() {
        String row = "Geck'oo Majestueux [Chef de harde] (6070792)";
        ImmutableMonster monster = MonsterParser.fromRawName(row);
        Assert.assertEquals(Families.Animal, monster.family().orElse(null));
        Assert.assertEquals(45, (int)monster.nival().map(Range::lowerEndpoint).orElse(-1));
        Assert.assertEquals(45, (int)monster.nival().map(Range::upperEndpoint).orElse(-1));
    }

    @Test
    public void testScorpionImago() {
        String row = "Scorpion [Imago] (6162762 )";
        ImmutableMonster monster = MonsterParser.fromRawName(row);
        Assert.assertEquals(Families.Insecte, monster.family().orElse(null));
        Assert.assertEquals(12, (int)monster.nival().map(Range::lowerEndpoint).orElse(-1));
        Assert.assertEquals(12, (int)monster.nival().map(Range::upperEndpoint).orElse(-1));
        Assert.assertEquals(6162762, (int)monster.id().orElse(-1));
    }

}
