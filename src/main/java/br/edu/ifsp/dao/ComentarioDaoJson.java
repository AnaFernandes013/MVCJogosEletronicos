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
    public Comentario inserir(int idJogo, String texto, String nomeUsuario, String data, int idUsuario){
        Comentario c = null;

        try {
            checkFile(path);
            FileWriter fw = new FileWriter(path,true);
            PrintWriter pw = new PrintWriter(fw);
            c = new Comentario(getProxId(), idJogo, texto, nomeUsuario, data, idUsuario);
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

    @Override
    public boolean editar(int id, String texto) {
        List<Comentario> lista = listar();
        boolean encontrado = false;

        for (Comentario c : lista) {
            if (c.getId() == id) {
                c.setTexto(texto);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) return false;

        try {
            FileWriter fw = new FileWriter(path, false);
            PrintWriter pw = new PrintWriter(fw);
            Gson gson = new Gson();
            for (Comentario c : lista) {
                pw.println(gson.toJson(c));
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public boolean excluir(int id) {
        List<Comentario> lista = listar();
        boolean removido = false;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                lista.remove(i);
                removido = true;
                break;
            }
        }

        if (!removido) return false;

        try {
            FileWriter fw = new FileWriter(path, false);
            PrintWriter pw = new PrintWriter(fw);
            Gson gson = new Gson();
            for (Comentario c : lista) {
                pw.println(gson.toJson(c));
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
