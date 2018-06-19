package org.zoumbox.mountyFetch.parser;

import com.google.common.base.Functions;
import com.google.common.base.Splitter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VueParser {

    protected static <T> List<T> extractPart(List<String> fullList, String part, Function<String, T> transform) {
        int startIndex = fullList.indexOf(String.format("#DEBUT %s", part));
        int endIndex = fullList.indexOf(String.format("#FIN %s", part));
        List<String> lines = fullList.subList(startIndex + 1, endIndex);
//        long begin = System.currentTimeMillis();
        List<T> result = lines.stream().map(transform).collect(Collectors.toList());
//        long end = System.currentTimeMillis();
//        double average = 0;
//        if (lines.size() > 0) {
//            average = ((double)end-begin)/lines.size();
//        }
//        System.out.println(String.format("%s : %s elements in %dms (%.2fms/each)", part, lines.size(), end-begin, average));
        return result;
    }

    protected static List<String> extractPart(List<String> fullList, String part) {
        return extractPart(fullList, part, Functions.identity());
    }

    public static ImmutableVue readVue(Integer trollId, String password) {
        List<String> raw = new PublicScriptsProvider()
                .fetchVue2(trollId, password);

        ImmutableVue result = parseVue(raw);
        return result;
    }

    public static ImmutableVue parseVue(List<String> raw) {

        List<Troll> trolls = extractPart(raw, "TROLLS", TrollParser::fromSpVue2Row);
        List<Monster> monsters = extractPart(raw, "MONSTRES", MonsterParser::fromSpVue2Row);
        List<String> tresors = extractPart(raw, "TRESORS");
        List<String> lieux = extractPart(raw, "LIEUX");
        List<String> champignons = extractPart(raw, "CHAMPIGNONS");
        String origine = extractPart(raw, "ORIGINE").get(0);
        List<Integer> split = Splitter.on(";").splitToList(origine).stream().map(Integer::valueOf).collect(Collectors.toList());
        Position pos = Position.of(split.get(1), split.get(2), split.get(3));
        int portee = split.get(0);

        ImmutableVue result = ImmutableVue.builder()
                .addAllMonstres(monsters)
                .addAllTrolls(trolls)
                .addAllLieux(lieux)
                .addAllChampignons(champignons)
                .addAllTresors(tresors)
                .origine(pos)
                .portee(portee)
                .build();
        return result;
    }

}
