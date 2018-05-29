package org.zoumbox.mountyFetch.parser;

import com.google.common.base.Strings;

import java.util.Optional;

/**
 * Chaque méthode de cette classe prend une information incomplète en entrée et essaye de la compléter.
 */
public class TrollBuilder {

    public static ImmutableTroll fromId(Integer id) {
        PublicDataProvider provider = new PublicDataProvider();
        Optional<ImmutableTroll> result = provider.readTroll2(id);
        return result.orElse(null);
    }

    public static ImmutableTroll fromName(String name) {
        PublicDataProvider provider = new PublicDataProvider();
        Optional<ImmutableTroll> result = provider.readTroll2ByName(Strings.nullToEmpty(name).trim());
        return result.orElse(null);
    }

}
