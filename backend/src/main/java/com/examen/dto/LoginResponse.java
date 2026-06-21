package com.examen.dto;

public class LoginResponse {

    private boolean success;
    private String usuario;
    private String rol;
    private String mensaje;
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(boolean success, String usuario, String rol, String mensaje, String token) {
        this.success = success;
        this.usuario = usuario;
        this.rol = rol;
        this.mensaje = mensaje;
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}