package org.zoumbox.mountyFetch.parser;

import com.google.common.base.Preconditions;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;

import static org.zoumbox.mountyFetch.parser.PublicDataProvider.TROLLS2_PATTERN;
import static org.zoumbox.mountyFetch.parser.PublicDataProvider.TROLLS_PATTERN;

/**
 * Chaque méthode de cette classe prend une information incomplète en entrée et essaye de la compléter.
 */
public class TrollBuilder {

    protected static final Function<String, ImmutableTroll> TROLL_TO_OBJECT = line -> {
        Matcher matcher = TROLLS_PATTERN.matcher(line);
        Preconditions.checkState(matcher.matches());
        ImmutableTroll.Builder builder = ImmutableTroll.builder();
        builder.id(Integer.valueOf(matcher.group(1)));
        builder.fullName(matcher.group(2));
        builder.race(Race.valueOf(matcher.group(3)));
        builder.nival(Integer.valueOf(matcher.group(4)));
        builder.kills(Integer.valueOf(matcher.group(5)));
        builder.morts(Integer.valueOf(matcher.group(6)));
        builder.guildeId(Integer.valueOf(matcher.group(7)));
        builder.mouches(Integer.valueOf(matcher.group(8)));
        return builder.build();
    };

    protected static final Function<String, ImmutableTroll> TROLL2_TO_OBJECT = line -> {
        Matcher matcher = TROLLS2_PATTERN.matcher(line);
        Preconditions.checkState(matcher.matches());
        ImmutableTroll.Builder builder = ImmutableTroll.builder();
        builder.id(Integer.valueOf(matcher.group(1)));
        builder.fullName(matcher.group(2));
        builder.race(Race.valueOf(matcher.group(3)));
        builder.nival(Integer.valueOf(matcher.group(4)));
        builder.kills(Integer.valueOf(matcher.group(5)));
        builder.morts(Integer.valueOf(matcher.group(6)));
        builder.mouches(Integer.valueOf(matcher.group(7)));
        return builder.build();
    };

    protected static Optional<ImmutableTroll> tryTransformTroll(String line) {
        Matcher matcher = TROLLS_PATTERN.matcher(line);
        if (matcher.matches()) {
            return Optional.of(TROLL_TO_OBJECT.apply(line));
        }
        return Optional.empty();
    }

    protected static Optional<ImmutableTroll> tryTransformTroll2(String line) {
        Matcher matcher = TROLLS2_PATTERN.matcher(line);
        if (matcher.matches()) {
            return Optional.of(TROLL2_TO_OBJECT.apply(line));
        }
        return Optional.empty();
    }

    public static ImmutableTroll fromId(Integer id) {
        ImmutableTroll result = new PublicDataProvider()
                .readTrollsLine(id)
                .map(TROLL_TO_OBJECT)
                .orElse(null);
        return result;
    }

    public static ImmutableTroll fromName(String name) {
        ImmutableTroll result = new PublicDataProvider()
                .readTrollsLine(name)
                .map(TROLL_TO_OBJECT)
                .orElse(null);
        return result;
    }

}
