package org.zoumbox.mountyMonsters.rest;

import com.google.common.base.Splitter;
import org.zoumbox.mountyMonsters.engine.ImmutableMonster;
import org.zoumbox.mountyMonsters.engine.MonsterParser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/parse")
@Produces(MediaType.APPLICATION_JSON)
public class ParserService {

    @GET
    @Path("/fromName")
    public ImmutableMonster fromName(@QueryParam("raw") String raw) {
        ImmutableMonster result = MonsterParser.fromName(raw);
        result = MonsterParser.extractTemplate(result);
        return result;
    }

    @GET
    @Path("/fromSpVue2Row")
    public ImmutableMonster fromSpVue2Row(@QueryParam("row") String row) {
        List<String> strings = Splitter.on(";").splitToList(row);
        String name = strings.get(1);
        ImmutableMonster result = fromName(name);
        result = ImmutableMonster.builder()
                .from(result)
                .id(Integer.valueOf(strings.get(0)))
                .build();
        return result;
    }

}
