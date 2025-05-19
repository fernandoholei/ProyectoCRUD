package com.mycompany.crud.dao;

import com.mycompany.crud.modelo.Usuario;
import java.util.List;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public UsuarioDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void crear(Usuario usuario) {
        Session session = sessionFactory.getCurrentSession();
        session.save(usuario);
    }
    
    public void actualizar(Usuario usuario) {
        Session session = sessionFactory.getCurrentSession();
        session.update(usuario);
}
        
    public void eliminar(int id) {
        Session session = sessionFactory.getCurrentSession();
        Usuario usuario = session.byId(Usuario.class).load(id);
        session.delete(usuario);
    }
    
    public List<Usuario> consultar(Usuario usuario) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
        Root<Usuario> root = query.from(Usuario.class);

        List<Predicate> predicates = new ArrayList<>();

        if (usuario.getCodEmpleado() != null && !usuario.getCodEmpleado().isEmpty())
            predicates.add(cb.like(root.get("codEmpleado"), "%" + usuario.getCodEmpleado() + "%"));

        if (usuario.getNomUsuario() != null && !usuario.getNomUsuario().isEmpty())
            predicates.add(cb.like(root.get("nomUsuario"), "%" + usuario.getNomUsuario() + "%"));

        if (usuario.getEmail() != null && !usuario.getEmail().isEmpty())
            predicates.add(cb.like(root.get("email"), "%" + usuario.getEmail() + "%"));

        if (usuario.getPrimerNombre() != null && !usuario.getPrimerNombre().isEmpty())
            predicates.add(cb.like(root.get("primerNombre"), "%" + usuario.getPrimerNombre() + "%"));

        if (usuario.getSegundoNombre() != null && !usuario.getSegundoNombre().isEmpty())
            predicates.add(cb.like(root.get("segundoNombre"), "%" + usuario.getSegundoNombre() + "%"));

        if (usuario.getPrimerApellido() != null && !usuario.getPrimerApellido().isEmpty())
            predicates.add(cb.like(root.get("primerApellido"), "%" + usuario.getPrimerApellido() + "%"));

        if (usuario.getSegundoApellido() != null && !usuario.getSegundoApellido().isEmpty())
            predicates.add(cb.like(root.get("segundoApellido"), "%" + usuario.getSegundoApellido() + "%"));

        if (usuario.getDni() != null && !usuario.getDni().isEmpty())
            predicates.add(cb.like(root.get("dni"), "%" + usuario.getDni() + "%"));

        if (usuario.getCargo() != null && !usuario.getCargo().isEmpty())
            predicates.add(cb.like(root.get("cargo"), "%" + usuario.getCargo() + "%"));

        if (usuario.getCodDepartamento() != null && !usuario.getCodDepartamento().isEmpty())
            predicates.add(cb.like(root.get("codDepartamento"), "%" + usuario.getCodDepartamento() + "%"));

        if (usuario.getNomDepartamento() != null && !usuario.getNomDepartamento().isEmpty())
            predicates.add(cb.like(root.get("nomDepartamento"), "%" + usuario.getNomDepartamento() + "%"));

        if (!predicates.isEmpty()) {
            query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
        } else {
            query.select(root);
        }
        return session.createQuery(query).getResultList();
    }
}