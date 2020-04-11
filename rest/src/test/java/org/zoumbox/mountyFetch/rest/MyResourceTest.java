package org.zoumbox.mountyFetch.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyResourceTest {

//    private HttpServer server;
//    private WebTarget target;
//
//    @Before
//    public void setUp() {
//        // start the server
//        server = Main.startServer();
//        // create the client
//        Client c = ClientBuilder.newClient();
//
//        // uncomment the following line if you want to enable
//        // support for JSON in the client (you also have to uncomment
//        // dependency on jersey-media-json module in pom.xml and Main.startServer())
//        // --
//        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());
//
//        target = c.target(Main.BASE_URI);
//    }
//
//    @After
//    public void tearDown() {
//        server.stop();
//    }
//
//    /**
//     * Test to see that the message "Got it!" is sent in the response.
//     */
//    @Test
//    public void testStatus() {
//        String responseMsg = target.path("v1/status").request().get(String.class);
//        Assert.assertTrue(responseMsg.contains("\"allOk\":true"));
//    }
//
//    /**
//     * Test to see that the message "Got it!" is sent in the response.
//     */
//    @Test
//    public void testFromName() {
//        String responseMsg = target.path("v1/monsters/fromName").queryParam("raw", "Gowap Apprivoisé [Ancêtre]").request().get(String.class);
//        Assert.assertTrue(responseMsg.contains("\"age\":\"Ancêtre\""));
//        Assert.assertTrue(responseMsg.contains("\"baseName\":\"Gowap Apprivoisé\""));
//    }
//
//    /**
//     * Test to see that the message "Got it!" is sent in the response.
//     */
//    @Test
//    public void testFromSpVue2Row() {
//        String responseMsg = target.path("v1/monsters/fromSpVue2Row").queryParam("row", "5864923;Maîtresse Ame-en-peine [Naissante];-74;-40;-78").request().get(String.class);
//        Assert.assertTrue(responseMsg.contains("\"age\":\"Naissante\""));
//        Assert.assertTrue(responseMsg.contains("\"id\":5864923"));
//        Assert.assertTrue(responseMsg.contains("\"baseName\":\"Ame-en-peine\""));
//        Assert.assertTrue(responseMsg.contains("\"template\":\"Maîtresse\""));
//    }

}
