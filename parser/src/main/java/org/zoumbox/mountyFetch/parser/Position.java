package org.zoumbox.mountyFetch.parser;

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

    static Position of(int x, int y, int n) {
        ImmutablePosition result = ImmutablePosition.builder()
                .x(x)
                .y(y)
                .n(n)
                .build();
        return result;
    }

}
