package com.examen.dto;

import com.examen.entity.Usuario;

public class UsuarioDTO {

    private Integer idUsuario;
    private String nombre;
    private String correo;
    private String usuario;
    private Integer idRol;
    private String nombreRol;

    public UsuarioDTO() {
    }

    // usuario seguro para compartir
    public UsuarioDTO(Usuario u) {
        this.idUsuario = u.getIdUsuario();
        this.nombre = u.getNombre();
        this.correo = u.getCorreo();
        this.usuario = u.getUsuario();
        if (u.getRol() != null) {
            this.idRol = u.getRol().getIdRol();
            this.nombreRol = u.getRol().getNombre();
        }
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}