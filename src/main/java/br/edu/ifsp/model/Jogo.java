package br.edu.ifsp.model;

import java.io.Serializable;

// ctrl + ç para wrap
public class Jogo implements Serializable {

    private static int id_atual = 0;
    private String titulo;
    private String desenvolvedor;
    private String anoLancamento;
    private String genero;
    private String sinopse;
    private String idioma;
    private String plataforma;
    private String classIndicativa;

    private String capa;
    private int id;

    public Jogo(){
        this.id = ++id_atual;

    }
    public Jogo(String titulo, String desenvolvedor, String anoLancamento, String genero, String sinopse, String idioma, String  plataforma, String classIndicativa,String capa, int id){
        this();
        this.titulo = titulo;
        this.desenvolvedor = desenvolvedor;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.sinopse = sinopse;
        this.idioma = idioma;
        this.plataforma = plataforma;
        this.classIndicativa = classIndicativa;
        //this.avaliacaoUser = avaliacaoUser;
        this.capa = capa;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static void setId_atual(int id_atual) {
        Jogo.id_atual = id_atual;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;

    }

    public String getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(String desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getClassIndicativa() {
        return classIndicativa;
    }

    public void setClassIndicativa(String classIndicativa) {
        this.classIndicativa = classIndicativa;
    }
}
