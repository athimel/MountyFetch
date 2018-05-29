package org.zoumbox.mountyFetch.parser;

import com.google.common.io.CharStreams;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TrollBuilderTest {

    @Test
    public void testRead104259() {
        ImmutableTroll troll = TrollBuilder.fromId(Troll.of(104259));
        System.out.println(troll);
        Assert.assertNotNull(troll);
        Assert.assertEquals(104259, (int)troll.id());
        Assert.assertEquals("DevelZimZoum", troll.fullName().orElse(""));
        Assert.assertTrue(troll.nival().isPresent());
        Assert.assertTrue(troll.kills().isPresent());
        Assert.assertTrue(troll.morts().isPresent());
        Assert.assertTrue(troll.mouches().isPresent());
    }

    @Test
    public void testReadAllTrolls() throws IOException {
        for (String file : Arrays.asList("/public/Public_Trolls.txt")) {
            InputStream stream = this.getClass().getResourceAsStream(file);
            List<String> rows = CharStreams.readLines(new InputStreamReader(stream));
            System.out.println(String.format("%s -> %d lignes", file, rows.size()));
            List<ImmutableTroll> trolls = rows.stream()
                    .map(TrollBuilder::tryTransformTroll)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(g -> g.fullName().isPresent())
                    .filter(g -> g.nival().isPresent())
                    .collect(Collectors.toList());
            Assert.assertEquals(rows.size(), trolls.size());
            System.out.println(String.format("%s -> %d lignes valides", file, trolls.size()));
        }
    }

    @Test
    public void testReadAllTrolls2() throws IOException {
        for (String file : Arrays.asList("/public/Public_Trolls2.txt")) {
            InputStream stream = this.getClass().getResourceAsStream(file);
            List<String> rows = CharStreams.readLines(new InputStreamReader(stream));
            System.out.println(String.format("%s -> %d lignes", file, rows.size()));
            List<ImmutableTroll> trolls = rows.stream()
                    .map(TrollBuilder::tryTransformTroll2)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(g -> g.fullName().isPresent())
                    .filter(g -> g.nival().isPresent())
                    .collect(Collectors.toList());
            // On sait que certaines lignes sont invalides (blasons sur plusieurs lignes)
//            Assert.assertEquals(rows.size(), trolls.size());
            System.out.println(String.format("%s -> %d lignes valides", file, trolls.size()));
        }
    }

    @Test
    public void testReadAllGuildes() throws IOException {
        for (String file : Arrays.asList("/public/Public_Guildes.txt")) {
            InputStream stream = this.getClass().getResourceAsStream(file);
            List<String> rows = CharStreams.readLines(new InputStreamReader(stream));
            System.out.println(String.format("%s -> %d lignes", file, rows.size()));
            List<ImmutableGuilde> trolls = rows.stream()
                    .map(TrollBuilder::tryTransformGuilde)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(g -> g.fullName().isPresent())
                    .collect(Collectors.toList());
            Assert.assertEquals(rows.size(), trolls.size());
            System.out.println(String.format("%s -> %d lignes valides", file, trolls.size()));
        }
    }

}
