package org.zoumbox.mountyFetch.rest.v1;

import org.zoumbox.mountyFetch.parser.ImmutableTroll;
import org.zoumbox.mountyFetch.parser.TrollBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/v1/trolls")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class TrollsService {

    @GET
    @Path("/{id}")
    public ImmutableTroll fromId(@PathParam("id") Integer id) {
        ImmutableTroll result = TrollBuilder.fromId(id);
        return result;
    }

    @GET
    @Path("/fromName")
    public ImmutableTroll fromId(@QueryParam("name") String name) {
        ImmutableTroll result = TrollBuilder.fromName(name);
        return result;
    }

}
