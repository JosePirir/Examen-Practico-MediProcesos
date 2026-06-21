package com.examen.rest;

import com.examen.entity.Rol;
import com.examen.entity.Usuario;
import com.examen.util.PasswordUtil;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Singleton
@Startup
public class DataSeeder {

    @PersistenceContext(unitName = "examenPU")
    private EntityManager em;

    @PostConstruct
    public void inicializar() {
        List<Usuario> existentes = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.usuario = :usuario", Usuario.class)
                .setParameter("usuario", "admin")
                .getResultList();

        if (!existentes.isEmpty()) {
            return;
        }

        Rol rolAdmin = obtenerOCrearRol("Administrador", "Acceso total al sistema");
        obtenerOCrearRol("Usuario", "Acceso limitado");

        Usuario admin = new Usuario();
        admin.setNombre("Admin Principal");
        admin.setCorreo("admin@empresa.com");
        admin.setUsuario("admin");
        admin.setPassword(PasswordUtil.hash("admin123"));
        admin.setRol(rolAdmin);

        em.persist(admin);
    }

    private Rol obtenerOCrearRol(String nombre, String descripcion) {
        List<Rol> existentes = em.createQuery(
                "SELECT r FROM Rol r WHERE r.nombre = :nombre", Rol.class)
                .setParameter("nombre", nombre)
                .getResultList();

        if (!existentes.isEmpty()) {
            return existentes.get(0);
        }

        Rol rol = new Rol();
        rol.setNombre(nombre);
        rol.setDescripcion(descripcion);
        em.persist(rol);
        return rol;
    }
}