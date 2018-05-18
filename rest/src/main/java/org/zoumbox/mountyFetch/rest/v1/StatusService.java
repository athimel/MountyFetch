package org.zoumbox.mountyFetch.rest.v1;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.nio.charset.Charset;
import java.util.Map;


@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class StatusService {

    /**
     * Pour vérifier que tout va bien http://localhost:8080/mountyMonsters/v1/status
     */
    @GET
    @Path("/status")
    public Map<String, Object> getStatus() {
        String encoding = System.getProperty("file.encoding");
        boolean isUtf8 = Charsets.UTF_8.equals(Charset.forName(encoding));
        return ImmutableMap.of("encoding", encoding, "allOk", isUtf8);
    }

}
