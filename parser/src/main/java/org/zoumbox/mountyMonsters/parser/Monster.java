package org.zoumbox.mountyMonsters.parser;

import com.google.common.collect.Range;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
public interface Monster {

    Optional<Integer> id();

    String fullName();

    Optional<Position> position();

    Optional<Families> familyEnum();

    Optional<String> family();

    Optional<String> baseName();

    Optional<Range<Integer>> baseNival();

    Optional<Templates> templateEnum();

    Optional<String> template();

    Optional<Integer> templateBonus();

    Optional<String> age();

    Optional<Integer> ageBonus();

    Optional<Range<Integer>> nival();

}
