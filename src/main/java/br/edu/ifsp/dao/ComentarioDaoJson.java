package br.edu.ifsp.dao;

import br.edu.ifsp.model.Comentario;
import br.edu.ifsp.model.Jogo;
import br.edu.ifsp.model.Usuario;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDaoJson implements ComentarioDao {
    private String path = null;
    private int proxId;

    public ComentarioDaoJson(String f) {
        this.path = f + "comentario.json";
        this.proxId = this.ultimoId();
    }

    @Override
    public Comentario inserir(String texto, Usuario usuario, Jogo jogo, int proxId) {
        Comentario c = null;

        try {
            checkFile(path);
            FileWriter fw = new FileWriter(path,true);
            PrintWriter pw = new PrintWriter(fw);
            c = new Comentario(texto, usuario, jogo, proxId);
            Gson gson = new Gson();
            System.out.println(path);
            System.out.println(gson.toJson(c));
            pw.println(gson.toJson(c));
            pw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return c;

    }

    @Override
    public List<Comentario> listar(){
        List<Comentario> lista = new ArrayList<>();
        try {
            checkFile(path);
            FileReader fr = new FileReader(path);
            BufferedReader reader = new BufferedReader(fr);

            String linha;
            Gson gson = new Gson();
            while((linha = reader.readLine()) != null){
                System.out.println(linha);
                Comentario c = gson.fromJson(linha,Comentario.class);
                lista.add(c);
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

    private int ultimoId(){
        List<Comentario> lista = this.listar();
        return !lista.isEmpty() ? lista.get(lista.size()-1).getId() : 0;
    }

    private int getProxId(){
        return ++this.proxId;
    }

    private void checkFile(String f){
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
