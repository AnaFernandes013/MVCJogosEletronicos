package br.edu.ifsp.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String usuario; // identificador
    private String email;
    private String senha;
    private int tipo;
    // ter 3 usuarios
    // 1 - adm
    // 2 - user logado
    // 3 - usur nao logado

    private static int id_atual = 0;
    private int id;

    public Usuario(String texto, Usuario usuario, Jogo jogo, int proxId, int tipo){
        this.id = ++id_atual;
        tipo = 2;
    }

    public int getId() {
        return id;
    }

    public static void setId_atual(int id_atual) {
        Usuario.id_atual = id_atual;
    }

    public Usuario(String usuario, String email, String senha, int id) {
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }



    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
