    package com.mycompany.crud.service;

    import java.util.List;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import com.mycompany.crud.dao.UsuarioDAO;
    import com.mycompany.crud.modelo.Usuario;

    @Service
    public class UsuarioService {

        @Autowired
        private UsuarioDAO usuarioDao;

        @Transactional
        public void crear(Usuario usuario) {
            usuarioDao.crear(usuario);
        }

        @Transactional
        public void actualizar(Usuario usuario) {
            usuarioDao.actualizar(usuario);
        }

        @Transactional
        public void eliminar(int id) {
            usuarioDao.eliminar(id);
        }

        @Transactional(readOnly = true)
        public List<Usuario> consultar(Usuario usuario) {
            return usuarioDao.consultar(usuario);
        }
    }