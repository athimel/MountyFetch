package org.zoumbox.mountyMonsters.rest.v1;

import org.zoumbox.mountyMonsters.parser.ImmutableMonster;
import org.zoumbox.mountyMonsters.parser.MonsterParser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/v1/parse")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ParserService {

    @GET
    @Path("/fromName")
    public ImmutableMonster fromName(@QueryParam("raw") String raw) {
        ImmutableMonster result = MonsterParser.fromRawName(raw);
        return result;
    }

    @GET
    @Path("/fromSpVue2Row")
    public ImmutableMonster fromSpVue2Row(@QueryParam("row") String row) {
        ImmutableMonster result = MonsterParser.fromSpVue2Row(row);
        return result;
    }

    @GET
    @Path("/fromSpVue2Rows")
    public List<ImmutableMonster> fromSpVue2Rows(@QueryParam("row") List<String> rows) {
        List<ImmutableMonster> result = rows.stream()
                .map(MonsterParser::fromSpVue2Row)
                .collect(Collectors.toList());
        return result;
    }

}
