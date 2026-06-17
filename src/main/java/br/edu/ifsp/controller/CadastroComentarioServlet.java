package br.edu.ifsp.controller;

import br.edu.ifsp.dao.*;
import br.edu.ifsp.model.Comentario;
import br.edu.ifsp.model.Usuario;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet(name = "CadastroComentarioServlet", value = "/comentario", loadOnStartup = 4)
public class CadastroComentarioServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        int idJogo = Integer.parseInt(request.getParameter("idJogo"));

        ComentarioDao dao = (ComentarioDao) getServletContext().getAttribute("comentarioDao");
        List<Comentario> todos = dao.listar();

        // filtra so os comentários do jogo especifico
        List<Comentario> doJogo = new ArrayList<>();
        for (Comentario c : todos) {
            if (c.getIdJogo() == idJogo) {
                doJogo.add(c);
            }
        }

        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(doJogo));
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String texto = request.getParameter("texto");
        String nomeUsuario = request.getParameter("nomeUsuario");
        int idJogo = Integer.parseInt(request.getParameter("idJogo"));
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        List<String> listaMensagens = new ArrayList<>();

        if (texto == null || texto.isBlank()) {
            listaMensagens.add("O campo comentário deve ser preenchido.");
        }

        Map<String, Object> mensagem = new HashMap<>();

        if (!listaMensagens.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mensagem.put("mensagem", "Houve um problema");
            mensagem.put("problemas", listaMensagens);
        } else {
            ComentarioDao dao = (ComentarioDao) getServletContext().getAttribute("comentarioDao");
            dao.inserir(idJogo, texto, nomeUsuario, data, idUsuario);
            response.setStatus(HttpServletResponse.SC_OK);
            mensagem.put("mensagem", "Comentário enviado com sucesso");
        }

        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(mensagem));
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        super.doOptions(req, response);
        response.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ComentarioDao dao = new ComentarioDaoJson(getServletContext().getRealPath("/"));
        getServletContext().setAttribute("comentarioDao", dao);
    }
}