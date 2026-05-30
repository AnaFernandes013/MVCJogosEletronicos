package br.edu.ifsp.dao;

import br.edu.ifsp.model.Jogo;

import java.util.List;
public interface JogoDao {
    Jogo inserir(String titulo, String desenvolvedor,String anoLancamento, String genero, String sinopse, String idioma, String plataforma, String classIndicativa, String capa );

    List<Jogo> listar();

    // editar e excluir aquii "inicialização"
}
