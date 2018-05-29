package org.zoumbox.mountyFetch.parser;

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

    // Id ; Nom ; Race ; Niveau ; Nb de Kills ; Nb de Morts ; Nb de Mouches ; Id Guilde ; Rang Guilde ; Etat Troll ; Intangible (*); PNJ (*) ; Ami de MH (*) ; Date d'Inscription ; Blason
    // 104259;DevelZimZoum;Kastar;59;939;16;62;1;2;OK;0;0;0;2011-01-21 14:07:48;http://zoumbox.org/mh/syndikhd/104259_300.png;
    public static final Pattern TROLLS2_PATTERN = Pattern.compile("([0-9]+);(.*);(.*);([0-9]+);([0-9]+);([0-9]+);([-]?[0-9]+);([0-9]+);([0-9]+);(.*);([0-9]+);([0-9]+);([0-9]+);(.*);.*");
    protected static final Cache<Integer, String> TROLLS2_BY_ID = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES).build();
    protected static final Cache<String, String> TROLLS2_BY_NAME = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES).build();

    public Optional<String> readTrolls2Line(Integer trollId) {
        String line = TROLLS2_BY_ID.getIfPresent(trollId);
        if (StringUtils.isEmpty(line)) {
            try {
                InputStream stream = new URL("http://ftp.mountyhall.com/Public_Trolls2.txt").openStream();
                List<String> lines = CharStreams.readLines(new InputStreamReader(stream));
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

    public Optional<String> readTrolls2Line(String trollName) {
        String line = TROLLS2_BY_NAME.getIfPresent(trollName);
        if (StringUtils.isEmpty(line)) {
            try {
                InputStream stream = new URL("http://ftp.mountyhall.com/Public_Trolls2.txt").openStream();
                List<String> lines = CharStreams.readLines(new InputStreamReader(stream));
                lines.forEach(l -> {
                    Matcher matcher = TROLLS2_PATTERN.matcher(l);
                    if (matcher.matches()) {
                        String name = matcher.group(2);
                        TROLLS2_BY_NAME.put(name, l);
                    } else {
                        System.err.println("Ligne 'Trolls2' invalide: " + l);
                    }
                });

                line = TROLLS2_BY_NAME.getIfPresent(trollName);
            } catch (IOException ioe) {
                throw new UncheckedIOException(ioe);
            }
        }
        return Optional.ofNullable(line);
    }

}
