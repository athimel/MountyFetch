package org.zoumbox.mountyFetch.parser;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Objet immuable représentant un troll.
 */
@Value.Immutable
public interface Troll {

    /**
     * @return Identifiant du troll
     */
    Integer id();

    /**
     * @return Nom complet
     */
    Optional<String> fullName();

    /**
     * @return La race du troll
     */
    Optional<Race> race();

    /**
     * @return Le nival du troll
     */
    Optional<Integer> nival();

    /**
     * @return Le nombre de kills
     */
    Optional<Integer> kills();

    /**
     * @return Le nombre de morts
     */
    Optional<Integer> morts();

    /**
     * @return Le nombre de mouches
     */
    Optional<Integer> mouches();

    /**
     * @return La guilde
     */
    Optional<Guilde> guilde();

}
