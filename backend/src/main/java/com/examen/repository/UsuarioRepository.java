package com.examen.repository;

import com.examen.entity.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UsuarioRepository {

    @PersistenceContext(unitName = "examenPU")
    private EntityManager em;

    public List<Usuario> findAll() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public Usuario findById(Integer id) {
        return em.find(Usuario.class, id);
    }

    public Usuario findByUsuario(String usuario) {
        List<Usuario> resultado = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.usuario = :usuario",
                Usuario.class)
                .setParameter("usuario", usuario)
                .getResultList();
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    public Usuario create(Usuario usuario) {
        em.persist(usuario);
        return usuario;
    }

    public Usuario update(Usuario usuario) {
        return em.merge(usuario);
    }

    public void delete(Integer id) {
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.remove(usuario);
        }
    }
}