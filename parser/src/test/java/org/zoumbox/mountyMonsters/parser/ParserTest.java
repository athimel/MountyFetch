package org.zoumbox.mountyMonsters.parser;

import com.google.common.io.CharStreams;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class ParserTest {

    @Test
    public void testReadAllMonsters() throws IOException {
        InputStream stream = this.getClass().getResourceAsStream("/monsters.txt");
        List<String> rows = CharStreams.readLines(new InputStreamReader(stream));
        List<ImmutableMonster> monsters = rows.stream().map(MonsterParser::fromSpVue2Row).collect(Collectors.toList());
//        ImmutableMap<Integer, ImmutableMonster> index = Maps.uniqueIndex(monsters, input -> input.id().getAsInt());
        monsters.stream().filter(m -> m.nival().isPresent() && m.nival().get().contains(44)).forEach(System.out::println);
    }

    @Test
    public void testReadAllMonsters2() throws IOException {
        InputStream stream = this.getClass().getResourceAsStream("/monsters2.txt");
        List<String> rows = CharStreams.readLines(new InputStreamReader(stream));
        List<ImmutableMonster> monsters = rows.stream().map(MonsterParser::fromSpVue2Row).collect(Collectors.toList());
//        ImmutableMap<Integer, ImmutableMonster> index = Maps.uniqueIndex(monsters, input -> input.id().getAsInt());
        monsters.stream().filter(m -> m.nival().isPresent() && m.nival().get().contains(44)).forEach(System.out::println);
    }

}
