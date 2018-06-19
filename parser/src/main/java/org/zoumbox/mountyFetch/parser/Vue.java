package org.zoumbox.mountyFetch.parser;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface Vue {

    Position origine();

    int portee();

    List<Troll> trolls();

    List<Monster> monstres();

    List<String> tresors();

    List<String> lieux();

    List<String> champignons();

}
