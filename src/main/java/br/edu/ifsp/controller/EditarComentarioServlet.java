package br.edu.ifsp.controller;

import br.edu.ifsp.dao.ComentarioDao;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
@WebServlet(name = "EditarComentarioServlet", value = "/editar_comentario")
public class EditarComentarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        int id = Integer.parseInt(request.getParameter("id"));
        String texto = request.getParameter("texto");

        ComentarioDao dao = (ComentarioDao) getServletContext().getAttribute("comentarioDao");
        boolean ok = dao.editar(id, texto);

        Map<String, Object> mensagem = new HashMap<>();
        Gson gson = new Gson();
        PrintWriter pw = response.getWriter();

        if (ok) {
            response.setStatus(HttpServletResponse.SC_OK);
            mensagem.put("mensagem", "Comentário editado com sucesso");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mensagem.put("mensagem", "Erro ao editar comentário");
        }

        pw.print(gson.toJson(mensagem));
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}