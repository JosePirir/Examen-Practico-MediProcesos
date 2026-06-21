package com.examen.rest;

import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/{path: .*}")
public class CorsOptionsResource {

    @OPTIONS
    public Response preflight() {
        return Response.ok().build();
    }
}