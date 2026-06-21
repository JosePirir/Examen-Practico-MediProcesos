package com.examen.rest;

import com.examen.entity.Rol;
import com.examen.repository.RolRepository;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RolResource {

    @EJB
    private RolRepository rolRepository;

    @GET
    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Integer id) {
        Rol rol = rolRepository.findById(id);
        if (rol == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Rol no encontrado\"}")
                    .build();
        }
        return Response.ok(rol).build();
    }

    @POST
    public Response crear(Rol rol) {
        Rol nuevo = rolRepository.create(rol);
        return Response.status(Response.Status.CREATED).entity(nuevo).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, Rol rol) {
        Rol existente = rolRepository.findById(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Rol no encontrado\"}")
                    .build();
        }
        rol.setIdRol(id);
        Rol actualizado = rolRepository.update(rol);
        return Response.ok(actualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        Rol existente = rolRepository.findById(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Rol no encontrado\"}")
                    .build();
        }
        rolRepository.delete(id);
        return Response.noContent().build();
    }
}