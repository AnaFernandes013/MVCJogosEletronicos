package br.edu.ifsp.controller;

import br.edu.ifsp.dao.JogoDao;
import br.edu.ifsp.dao.JogoDaoJson;
import br.edu.ifsp.dao.UsuarioDao;
import br.edu.ifsp.dao.UsuarioDaoJson;
import br.edu.ifsp.model.Usuario;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet(name = "CadastroUserServlet", value = "/cadastrar_usuario")
public class CadastroUserServlet extends HttpServlet {
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // code
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        //
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setStatus(HttpServletResponse.SC_OK);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String contentType = request.getContentType();
        String nome = null;
        String email = null;
        String senha = null;

        if(contentType.contains("application/json")) {
            StringBuilder sb= new StringBuilder();
            BufferedReader br = request.getReader();
            String linha;
            while((linha = br.readLine()) != null) sb.append(linha);

            Usuario u = gson.fromJson(sb.toString(), Usuario.class);
            nome = u.getUsuario();
            email = u.getEmail();
            senha = u.getSenha();
        }else{
            nome = request.getParameter("usuario");
            email = request.getParameter("email");
            senha = request.getParameter("senha");
        }
        List<String> listaMensagens = new ArrayList<>();

        if(nome == null || nome.isBlank()){
            listaMensagens.add("O campo Usuario deve ser preenchido.");
        }

        if (email == null || email.isBlank()){
            listaMensagens.add("O campo Email deve ser preenchido.");
        }

        if (senha == null || senha.isBlank()){
            listaMensagens.add("O campo Senha deve ser preenchido.");
        }

        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Senha: " + senha);
        System.out.println("Content-Type: " + contentType);

        Map<String, Object> mensagem = new HashMap<>();
        if(!listaMensagens.isEmpty()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mensagem.put("mensagem", "Houve um problema");
            mensagem.put("problemas", listaMensagens);
        }else{
            UsuarioDao dao = (UsuarioDao) getServletContext().getAttribute("dao");
            if(dao == null){
                throw new RuntimeException("DAO nao encontrado no ServletContext");
            }

            Usuario u = dao.cadastrar(nome, email, senha);
            response.setStatus(HttpServletResponse.SC_OK);
            mensagem.put("mensagem", "Cadastrado com sucesso");
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        pw.print(gson.toJson(mensagem));
    }

    @Override
    public void init() throws ServletException {
        super.init();
        UsuarioDao dao = new UsuarioDaoJson(getServletContext().getRealPath("/"));
        getServletContext().setAttribute("dao", dao);
    }
}