package br.edu.ifsp.dao;

import br.edu.ifsp.model.Usuario;

import java.util.List;

public interface UsuarioDao {
    Usuario cadastrar(String usuario, String email, String senha);
    List<Usuario> listar();
}

