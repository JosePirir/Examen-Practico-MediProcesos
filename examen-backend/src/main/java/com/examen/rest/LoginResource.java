package com.examen.rest;

import com.examen.dto.LoginRequest;
import com.examen.dto.LoginResponse;
import com.examen.entity.Usuario;
import com.examen.repository.UsuarioRepository;
import com.examen.util.PasswordUtil;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    @EJB
    private UsuarioRepository usuarioRepository;

    @POST
    public Response login(LoginRequest request) {
        if (request.getUsuario() == null || request.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new LoginResponse(false, null, null, "Usuario y password son requeridos"))
                    .build();
        }

        Usuario usuario = usuarioRepository.findByUsuario(request.getUsuario());

        if (usuario == null || !PasswordUtil.verificar(request.getPassword(), usuario.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new LoginResponse(false, null, null, "Credenciales inválidas"))
                    .build();
        }

        String nombreRol = usuario.getRol() != null ? usuario.getRol().getNombre() : null;
        return Response.ok(
                new LoginResponse(true, usuario.getUsuario(), nombreRol, "Login exitoso")
        ).build();
    }
}