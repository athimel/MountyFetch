package org.zoumbox.mountyMonsters.parser;

import com.google.common.base.Preconditions;
import org.immutables.value.Value;

@Value.Immutable
public interface Position {

    int x();
    int y();
    int n();

    @Value.Check
    default void check() {
        Preconditions.checkState(n() <= 0, "Le N est forcément négatif");
    }
}
