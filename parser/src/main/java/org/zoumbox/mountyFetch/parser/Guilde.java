package org.zoumbox.mountyFetch.parser;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Objet immuable représentant une guilde.
 */
@Value.Immutable
public interface Guilde {

    /**
     * @return Identifiant du troll
     */
    Integer id();

    /**
     * @return Nom complet
     */
    Optional<String> fullName();

}
