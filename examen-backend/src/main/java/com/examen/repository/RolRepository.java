package com.examen.repository;

import com.examen.entity.Rol;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RolRepository {

    @PersistenceContext(unitName = "examenPU")
    private EntityManager em;

    public List<Rol> findAll() {
        return em.createQuery("SELECT r FROM Rol r", Rol.class).getResultList();
    }

    public Rol findById(Integer id) {
        return em.find(Rol.class, id);
    }

    public Rol create(Rol rol) {
        em.persist(rol);
        return rol;
    }

    public Rol update(Rol rol) {
        return em.merge(rol);
    }

    public void delete(Integer id) {
        Rol rol = em.find(Rol.class, id);
        if (rol != null) {
            em.remove(rol);
        }
    }
}