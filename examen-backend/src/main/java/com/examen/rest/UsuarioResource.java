package com.examen.rest;

import com.examen.util.PasswordUtil;
import com.examen.dto.UsuarioDTO;
import com.examen.entity.Rol;
import com.examen.entity.Usuario;
import com.examen.repository.RolRepository;
import com.examen.repository.UsuarioRepository;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @EJB
    private UsuarioRepository usuarioRepository;

    @EJB
    private RolRepository rolRepository;

    @GET
    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Integer id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Usuario no encontrado\"}")
                    .build();
        }
        return Response.ok(new UsuarioDTO(usuario)).build();
    }

    @POST
    public Response crear(Usuario usuario) {
        if (usuario.getRol() == null || usuario.getRol().getIdRol() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Debe especificar id_rol\"}")
                    .build();
        }
        Rol rol = rolRepository.findById(usuario.getRol().getIdRol());
        if (rol == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"El rol especificado no existe\"}")
                    .build();
        }
        usuario.setRol(rol);
        usuario.setPassword(PasswordUtil.hash(usuario.getPassword()));
        Usuario nuevo = usuarioRepository.create(usuario);
        return Response.status(Response.Status.CREATED).entity(new UsuarioDTO(nuevo)).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Usuario no encontrado\"}")
                    .build();
        }
        if (usuario.getRol() != null && usuario.getRol().getIdRol() != null) {
            Rol rol = rolRepository.findById(usuario.getRol().getIdRol());
            if (rol == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\":\"El rol especificado no existe\"}")
                        .build();
            }
            existente.setRol(rol);
        }
        existente.setNombre(usuario.getNombre());
        existente.setCorreo(usuario.getCorreo());
        existente.setUsuario(usuario.getUsuario());
        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            existente.setPassword(PasswordUtil.hash(usuario.getPassword()));
        }
        Usuario actualizado = usuarioRepository.update(existente);
        return Response.ok(new UsuarioDTO(actualizado)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        Usuario existente = usuarioRepository.findById(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Usuario no encontrado\"}")
                    .build();
        }
        usuarioRepository.delete(id);
        return Response.noContent().build();
    }
}