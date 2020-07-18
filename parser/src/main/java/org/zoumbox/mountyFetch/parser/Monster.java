package org.zoumbox.mountyFetch.parser;

import com.google.common.collect.Range;
import org.immutables.value.Value;

import java.util.Optional;

/**
 * Objet immuable représentant un monstre. Toutes les informations à part le nom sont optionnelles car on peut ne pas
 * réussir à les calculer.
 */
@Value.Immutable
public interface Monster {

    /**
     * @return Identifiant du monstre
     */
    Optional<Integer> id();

    /**
     * @return Nom complet
     */
    String fullName();

    /**
     * @return Position
     */
    Optional<Position> position();

    /**
     * @return La famille de monstre (cf. enum {@link Families})
     */
    Optional<Families> family();

    /**
     * @return Le type de base du monstre (sans template, sans âge, ...)
     * @see Monsters#getLabel()
     */
    Optional<String> baseName();

    /**
     * @return Le niveau lié au monstre de base (sans template, sans âge, ...). On indique un intervalle car dans
     * certains cas (ex. {@link Monsters#Phoenix}) le niveau n'est pas déterministe.
     */
    Optional<Range<Integer>> baseNival();

    /**
     * @return Le template (cf. enum {@link Templates})
     */
    Optional<Templates> template();

    /**
     * @return Le bonus lié au template
     */
    Optional<Integer> templateBonus();

    /**
     * @return L'âge
     */
    Optional<String> age();

    /**
     * @return Le bonus lié à l'âge
     */
    Optional<Integer> ageBonus();

    /**
     * @return Le niveau calculé avec template &amp; âge. On indique un intervalle car dans certains cas (ex.
     * {@link Monsters#Phoenix}) le niveau n'est pas déterministe.
     */
    Optional<Range<Integer>> nival();

}
