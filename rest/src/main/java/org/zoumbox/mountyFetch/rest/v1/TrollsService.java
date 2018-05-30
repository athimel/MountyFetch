package org.zoumbox.mountyFetch.rest.v1;

import org.zoumbox.mountyFetch.parser.ImmutableTroll;
import org.zoumbox.mountyFetch.parser.TrollParser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/v1/trolls")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class TrollsService {

    @GET
    @Path("/{id}")
    public ImmutableTroll fromId(@PathParam("id") Integer id) {
        ImmutableTroll result = TrollParser.fromId(id);
        return result;
    }

    @GET
    @Path("/fromSpVue2Row")
    public ImmutableTroll fromSpVue2Row(@QueryParam("row") String row) {
        ImmutableTroll result = TrollParser.fromSpVue2Row(row);
        return result;
    }

    @GET
    @Path("/fromSpVue2Rows")
    public List<ImmutableTroll> fromSpVue2Rows(@QueryParam("row") List<String> rows) {
        List<ImmutableTroll> result = rows.stream()
                .map(TrollParser::fromSpVue2Row)
                .collect(Collectors.toList());
        return result;
    }

}
