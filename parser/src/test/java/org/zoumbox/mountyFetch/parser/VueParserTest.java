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
import java.util.Optional;

public class VueParserTest {

    @Test
    public void testParseVue4() throws IOException {
        for (String file : Arrays.asList("/vue4.txt")) {
            InputStream stream = this.getClass().getResourceAsStream(file);
            List<String> rows = CharStreams.readLines(new InputStreamReader(stream));
            System.out.println(String.format("%s -> %d lignes", file, rows.size()));
            ImmutableVue vue = VueParser.parseVue(rows);

            // On valide la portée et l'origine
            Assert.assertEquals(52, vue.portee());
            Assert.assertEquals(Position.of(-76, 76,-75), vue.origine());

            // On vérifie qu'on trouve bien Devel et les queslques infos
            Optional<Troll> devel = vue.trolls().stream().filter(t -> t.id() == 104259).findAny();
            Assert.assertTrue(devel.isPresent());
            Assert.assertTrue(devel.get().guilde().isPresent());
            Assert.assertEquals(1900, (int)devel.get().guilde().get().id());
            Assert.assertTrue(devel.get().race().isPresent());
            Assert.assertEquals(Race.Kastar, devel.get().race().get());

            // On vérifie qu'on trouve bien le Nécrochore [Récent] ( 6045880 ) et les queslques infos
            Optional<Monster> monster = vue.monstres().stream().filter(m -> m.id().get() == 6045880).findAny();
            Assert.assertTrue(monster.isPresent());
            Assert.assertEquals(Families.MortVivant, monster.get().family().get());
            Assert.assertEquals("Récent", monster.get().age().get());
            Assert.assertEquals(Range.singleton(38), monster.get().nival().get());
        }
    }

}
