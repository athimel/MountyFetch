package org.zoumbox.mountyFetch.parser;

import com.google.common.base.Charsets;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.io.CharStreams;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PublicDataProvider {

    protected List<String> fetchPublicFile(String name) throws IOException {
        String url = "http://ftp.mountyhall.com/" + name;
        try (
                InputStream stream = new URL(url).openStream();
                InputStreamReader reader = new InputStreamReader(stream, Charsets.ISO_8859_1)
        ) {
            List<String> lines = CharStreams.readLines(reader);
            return lines;
        }
    }

    // Id ; Nom ; Race ; Niveau ; Nb de Kills ; Nb de Morts ; Id Guilde ; Nb de Mouches
    // 104259;DevelZimZoum;Kastar;59;647;15;1900;62;
    public static final Pattern TROLLS_PATTERN = Pattern.compile("([0-9]+);(.*);(.*);([0-9]+);([0-9]+);([0-9]+);([0-9]+);([-]?[0-9]+);");
    protected static final Cache<Integer, String> TROLLS_BY_ID = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.HOURS).build();

    public Optional<String> readTrollsLine(Integer trollId) {
        String line = TROLLS_BY_ID.getIfPresent(trollId);
        if (StringUtils.isEmpty(line)) {
            try {
                List<String> lines = fetchPublicFile("Public_Trolls.txt");
                lines.forEach(l -> {
                    Matcher matcher = TROLLS_PATTERN.matcher(l);
                    if (matcher.matches()) {
                        Integer id = Integer.valueOf(matcher.group(1));
                        TROLLS_BY_ID.put(id, l);
                    } else {
                        System.err.println("Ligne 'Trolls' invalide: " + l);
                    }
                });

                line = TROLLS_BY_ID.getIfPresent(trollId);
            } catch (IOException ioe) {
                throw new UncheckedIOException(ioe);
            }
        }
        return Optional.ofNullable(line);
    }

    // Id ; Nom ; Race ; Niveau ; Nb de Kills ; Nb de Morts ; Nb de Mouches ; Id Guilde ; Rang Guilde ; Etat Troll ; Intangible (*); PNJ (*) ; Ami de MH (*) ; Date d'Inscription ; Blason
    // 104259;DevelZimZoum;Kastar;59;939;16;62;1;2;OK;0;0;0;2011-01-21 14:07:48;http://zoumbox.org/mh/syndikhd/104259_300.png;
    public static final Pattern TROLLS2_PATTERN = Pattern.compile("([0-9]+);(.*);(.*);([0-9]+);([0-9]+);([0-9]+);([-]?[0-9]+);([0-9]+);([0-9]+);(.*);([0-9]+);([0-9]+);([0-9]+);(.*);.*");
    protected static final Cache<Integer, String> TROLLS2_BY_ID = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.HOURS).build();

    public Optional<String> readTrolls2Line(Integer trollId) {
        String line = TROLLS2_BY_ID.getIfPresent(trollId);
        if (StringUtils.isEmpty(line)) {
            try {
                List<String> lines = fetchPublicFile("Public_Trolls2.txt");
                lines.forEach(l -> {
                    Matcher matcher = TROLLS2_PATTERN.matcher(l);
                    if (matcher.matches()) {
                        Integer id = Integer.valueOf(matcher.group(1));
                        TROLLS2_BY_ID.put(id, l);
                    } else {
                        System.err.println("Ligne 'Trolls2' invalide: " + l);
                    }
                });

                line = TROLLS2_BY_ID.getIfPresent(trollId);
            } catch (IOException ioe) {
                throw new UncheckedIOException(ioe);
            }
        }
        return Optional.ofNullable(line);
    }

    // Id ; Nom ; Nb Membres
    // 1900;Le Syndikat Vitiktroll;34;
    public static final Pattern GUILDES_PATTERN = Pattern.compile("([0-9]+);(.*);([0-9]+);");
    protected static final Cache<Integer, String> GUILDES_BY_ID = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.HOURS).build();

    public Optional<String> readGuildesLine(Integer guildeId) {
        String line = GUILDES_BY_ID.getIfPresent(guildeId);
        if (StringUtils.isEmpty(line)) {
            try {
                List<String> lines = fetchPublicFile("Public_Guildes.txt");
                lines.forEach(l -> {
                    Matcher matcher = GUILDES_PATTERN.matcher(l);
                    if (matcher.matches()) {
                        Integer id = Integer.valueOf(matcher.group(1));
                        GUILDES_BY_ID.put(id, l);
                    } else {
                        System.err.println("Ligne 'Guildes' invalide: " + l);
                    }
                });

                line = GUILDES_BY_ID.getIfPresent(guildeId);
            } catch (IOException ioe) {
                throw new UncheckedIOException(ioe);
            }
        }
        return Optional.ofNullable(line);
    }

}
