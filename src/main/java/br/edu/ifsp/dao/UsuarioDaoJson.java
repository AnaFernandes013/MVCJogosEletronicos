package br.edu.ifsp.dao;

import br.edu.ifsp.model.Usuario;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoJson implements UsuarioDao {
    private String path = null;
    private int proxId;

    public UsuarioDaoJson(String f) {
        this.path = f + "usuario.json";
        this.proxId = this.ultimoId();
    }

    @Override
    public Usuario cadastrar(String usuario, String email, String senha) {
        Usuario u = null;
        try {
            checkFile(path);
            FileWriter fw = new FileWriter(path,true);
            PrintWriter pw = new PrintWriter(fw);
            u = new Usuario(usuario, email, senha, this.getProxId());
            Gson gson = new Gson();
            System.out.println(path);
            System.out.println(gson.toJson(u));
            pw.println(gson.toJson(u));
            pw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return u;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        try {
            checkFile(path);
            FileReader fr = new FileReader(path);
            BufferedReader reader = new BufferedReader(fr);

            String linha;
            Gson gson = new Gson();
            while((linha = reader.readLine()) != null){
                System.out.println(linha);
                Usuario u = gson.fromJson(linha,Usuario.class);
                lista.add(u);
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
        List<Usuario> lista = this.listar();
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
