package br.edu.ifsp.model;

public class Comentario {

    private int id;
    private int idJogo;
    private String texto;
    private String nomeUsuario;
    private String data;
    private int idUsuario;

    public Comentario(int id, int idJogo, String texto, String nomeUsuario, String data, int idUsuario) {
        this.id = id;
        this.idJogo = idJogo;
        this.texto = texto;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
        this.idUsuario = idUsuario;
    }

    public int getId() { return id; }
    public int getIdJogo() { return idJogo; }
    public String getTexto() { return texto; }
    public String getNomeUsuario() { return nomeUsuario; }
    public String getData() { return data; }
    public int getIdUsuario() { return idUsuario; }
    public void setTexto(String texto) {this.texto = texto;}
}