package br.edu.ifsp.dao;
import br.edu.ifsp.model.Comentario;
import br.edu.ifsp.model.Jogo;
import br.edu.ifsp.model.Usuario;

import java.util.List;

public interface ComentarioDao {
    Comentario inserir(String texto, Usuario usuario, Jogo jogo, int id);
    List<Comentario> listar();
}
