package br.edu.ifsp.model;

import java.io.Serializable;

public class Comentario implements Serializable {

    private static int id_atual = 0;
    private int id;
    private String texto;

    private Usuario usuario;
    private Jogo jogo;

    public Comentario(String texto, Usuario usuario, Jogo jogo, int id) {
        this.id = ++id_atual;
        this.texto = texto;
        this.usuario = usuario;
        this.jogo = jogo;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Jogo getJogo() {
        return jogo;
    }
}
