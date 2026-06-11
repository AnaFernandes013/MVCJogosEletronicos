package br.edu.ifsp.controller;

import br.edu.ifsp.dao.*;
import br.edu.ifsp.model.Comentario;
import br.edu.ifsp.model.Jogo;
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

@WebServlet(name = "CadastroComentarioServlet", value = "/cadastrar_comentario")
public class CadastroComentarioServlet extends HttpServlet {
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

        String comentario = null;

        if(contentType != null && contentType.startsWith("application/json")) {
            StringBuilder sb= new StringBuilder();
            BufferedReader br = request.getReader();
            String linha;
            while((linha = br.readLine()) != null) sb.append(linha);

            Comentario c =  gson.fromJson(sb.toString(), Comentario.class);
            comentario = c.getTexto();

        }else{
            comentario = request.getParameter("texto");
        }

        List<String> listaMensagens = new ArrayList<>();

        if(comentario == null || comentario.isBlank() ) {
            listaMensagens.add("O campo comentario deve ser preenchido.");
        }

        System.out.println(comentario);

        Map<String, Object> mensagem = new HashMap<>();
        if(!listaMensagens.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mensagem.put("mensagem", "Houve um problema");
            mensagem.put("problema", listaMensagens);
        }else{
            ComentarioDao dao = (ComentarioDao) getServletContext().getAttribute("dao");

            if(dao == null) {
                throw new RuntimeException("Dao nao encontrado");

            }
//            Comentario u = dao.inserir();
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
        ComentarioDao dao = new ComentarioDaoJson(getServletContext().getRealPath("/"));
        getServletContext().setAttribute("dao", dao);
    }
}