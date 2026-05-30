package br.edu.ifsp.dao;
import br.edu.ifsp.model.Jogo;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JogoDaoJson implements JogoDao {
    private String path = null;
    private int proxId;

    public JogoDaoJson(String f) {
        this.path = f + "jogo.json";
        this.proxId = this.ultimoId();
    }

    @Override
    public Jogo inserir(String titulo, String desenvolvedor,String anoLancamento, String genero, String sinopse, String idioma, String plataforma, String classIndicativa, String capa ){
        Jogo j = null;
        try {
            checkFile(path);
            FileWriter fw = new FileWriter(path,true);
            PrintWriter pw = new PrintWriter(fw);
            j = new Jogo(titulo, desenvolvedor,anoLancamento,genero,sinopse,idioma,plataforma,classIndicativa,capa, this.getProxId());
            Gson gson = new Gson();
            System.out.println(path);
            System.out.println(gson.toJson(j));
            pw.println(gson.toJson(j));
            pw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return j;
    }

    @Override
    public List<Jogo> listar(){
        List<Jogo> lista=new ArrayList<>();
        try {
            checkFile(path);
            FileReader fr = new FileReader(path);
            BufferedReader reader = new BufferedReader(fr);

            String linha;
            Gson gson = new Gson();
            while((linha = reader.readLine()) != null){
                System.out.println(linha);
                Jogo j = gson.fromJson(linha,Jogo.class);
                lista.add(j);
            }

            reader.close();
            fr.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lista;

    }
    // editar e excluir aquii (implementação)

    private int ultimoId(){
        List<Jogo> lista = this.listar();
        return !lista.isEmpty() ? lista.get(lista.size()-1).getId() : 0;
    }

    private int getProxId(){
        return ++this.proxId;
    }

    private void checkFile(String f){
        File file = new File(f);

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

