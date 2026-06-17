package br.edu.ifsp.dao;
import br.edu.ifsp.model.Comentario;
import br.edu.ifsp.model.Jogo;
import br.edu.ifsp.model.Usuario;

import java.util.List;

public interface ComentarioDao {
    Comentario inserir(int idJogo, String texto, String nomeUsuario, String data, int idUsuario);
    List<Comentario> listar();
    boolean excluir(int id);
    boolean editar(int id, String texto);
}
