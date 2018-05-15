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

}
