package org.zoumbox.mountyMonsters.rest.v1;

import com.google.common.base.Splitter;
import org.zoumbox.mountyMonsters.parser.ImmutableMonster;
import org.zoumbox.mountyMonsters.parser.ImmutablePosition;
import org.zoumbox.mountyMonsters.parser.MonsterParser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/parse")
@Produces(MediaType.APPLICATION_JSON)
public class ParserService {

    /**
     * Essaye de calculer un maximum d'informations sur un monstre à partir de son nom. Exemple :
     * <pre>Maîtresse Ame-en-peine [Naissante]</pre>
     *
     * @param raw le nom brut
     * @return Les informations déduites à propos du monstre
     */
    @GET
    @Path("/fromName")
    public ImmutableMonster fromName(@QueryParam("raw") String raw) {
        ImmutableMonster result = MonsterParser.fromName(raw);
        result = MonsterParser.extractTemplate(result);
        result = MonsterParser.extractFamilyAndNival(result);
        return result;
    }

    /**
     * Essaye de calculer un maximum d'informations sur un monstre à partir de la ligne extraite de Sp_Vue2.php).
     * Exemple :
     * <pre>5864923;Maîtresse Ame-en-peine [Naissante];-74;-40;-78</pre>
     *
     * @param row La ligne brute provenant de Sp_Vue2.php
     * @return Les informations déduites à propos du monstre
     * @see #fromName(String)
     */
    @GET
    @Path("/fromSpVue2Row")
    public ImmutableMonster fromSpVue2Row(@QueryParam("row") String row) {

        List<String> strings = Splitter.on(";").splitToList(row);
        String name = strings.get(1);
        ImmutableMonster result = fromName(name);
        ImmutablePosition position = ImmutablePosition.builder()
                .x(Integer.valueOf(strings.get(2)))
                .y(Integer.valueOf(strings.get(3)))
                .n(Integer.valueOf(strings.get(4)))
                .build();
        result = ImmutableMonster.builder()
                .from(result)
                .id(Integer.valueOf(strings.get(0)))
                .position(position)
                .build();
        return result;
    }

}
