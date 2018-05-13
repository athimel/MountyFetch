package org.zoumbox.mountyMonsters.parser;

import org.immutables.value.Value;

import java.util.Optional;
import java.util.OptionalInt;

@Value.Immutable
public interface Monster {

    OptionalInt id();

    Optional<String> nom();

    Optional<String> age();

    Optional<String> baseName();

    OptionalInt nival();

    Optional<String> template();

    Optional<Position> position();

}
