package org.zoumbox.mountyMonsters.parser;

import com.google.common.collect.Range;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.OptionalInt;

@Value.Immutable
public interface Monster {

    OptionalInt id();

    Optional<String> nom();

    Optional<String> age();

    Optional<String> baseName();

    Optional<Range<Integer>> baseNival();

    Optional<Families> family();

    default Optional<String> familyLabel() {
        return family().map(Families::getLabel);
    }

    Optional<String> template();

    Optional<Range<Integer>> nival();

    Optional<Position> position();

}
