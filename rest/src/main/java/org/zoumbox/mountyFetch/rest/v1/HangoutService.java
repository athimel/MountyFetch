package org.zoumbox.mountyFetch.rest.v1;

import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/v1/hangout")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes({MediaType.APPLICATION_JSON + ";charset=utf-8", MediaType.APPLICATION_JSON})
public class HangoutService {

    @POST
    @Path("/")
    public HangoutResult fromName(HangoutPayload payload) {
        Optional<HangoutPayload> optional = Optional.ofNullable(payload);
        HangoutResult result = new HangoutResult();
        if (optional.isPresent() && optional.get().getText().isPresent()) {
            String text = optional.get().getText().get();
            if (text.toLowerCase().startsWith("could you ")) {
                result.text = String.format("Of course I can %s", text.substring(10));
            } else {
                result.text = String.format("Hello, thanks for your message '%s'", text);
            }
        } else {
            result.text = "What ?";
        }
        System.out.println(String.format("Request: %s", new Gson().toJson(payload)));
        System.out.println(String.format("Response: %s", new Gson().toJson(result)));
        return result;
    }

}
