package org.zoumbox.mountyFetch.parser;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PublicScriptsProvider {

    protected List<String> fetchPublicFile(String script, Map<String, String> args) throws IOException {
//        String url = String.format("http://sp.mountyhall.com/%s?", script);
        String url = "http://127.0.0.1:3000/?id=" + args.get("Numero") + "&";
        url += Joiner.on("&").join(
                        args.entrySet()
                                .stream()
                                .map(e -> e.getKey() + "=" + e.getValue())
                                .collect(Collectors.toList())
        );
        System.out.println("Appel de l'URL " + url);
        try (
                InputStream stream = new URL(url).openStream();
//                InputStreamReader reader = new InputStreamReader(stream, Charsets.ISO_8859_1)
                InputStreamReader reader = new InputStreamReader(stream, Charsets.UTF_8)
        ) {
            List<String> lines = CharStreams.readLines(reader);
            lines.forEach(System.out::println);
            return lines;
        }
    }

    protected List<String> fetchVue2(Integer trollId, String password) {
        try {
            ImmutableMap<String, String> args = ImmutableMap.of(
                    "Numero", String.valueOf(trollId),
                    "Motdepasse", password,
                    "Tresors", "1",
                    "Lieux", "1",
                    "Champignons", "1");
            List<String> lines = fetchPublicFile("SP_Vue2.php", args);
            return lines;
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

}
