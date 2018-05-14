package org.zoumbox.mountyMonsters.parser;

import com.google.common.collect.Range;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.OptionalInt;

@Value.Immutable
public interface Monster {

    OptionalInt id();

    String fullName();

    Optional<Position> position();

    Optional<Families> familyEnum();

    Optional<String> family();

    Optional<String> baseName();

    Optional<Range<Integer>> baseNival();

    Optional<Templates> templateEnum();

    Optional<String> template();

    OptionalInt templateBonus();

    Optional<String> age();

    OptionalInt ageBonus();

    Optional<Range<Integer>> nival();

}
