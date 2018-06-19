package org.zoumbox.mountyFetch.rest.v1;

import org.zoumbox.mountyFetch.parser.ImmutableVue;
import org.zoumbox.mountyFetch.parser.VueParser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/vue")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class VueService {

    @GET
    @Path("/")
    public ImmutableVue readVue(@QueryParam("trollId") Integer trollId, @QueryParam("password") String password) {
        ImmutableVue result = VueParser.readVue(trollId, password);
        return result;
    }

}
