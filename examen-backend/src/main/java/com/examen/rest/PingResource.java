package com.examen.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/ping")
public class PingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String ping() {
        return "{\"status\":\"ok\",\"mensaje\":\"Backend funcionando\"}";
    }
}