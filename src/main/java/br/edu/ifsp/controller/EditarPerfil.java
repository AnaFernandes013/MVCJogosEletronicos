package br.edu.ifsp.controller;

import br.edu.ifsp.dao.UsuarioDao;
import br.edu.ifsp.dao.UsuarioDaoJson;
import br.edu.ifsp.model.Usuario;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
@WebServlet(name = "EditarPerfil", value = "/editar_perfil")
public class EditarPerfil extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);

        UsuarioDao dao = (UsuarioDao) getServletContext().getAttribute("usuarioDao");
        Usuario u = dao.buscarPorId(id);

        Gson gson = new Gson();
        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(u));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);
        String usuario = request.getParameter("usuario");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioDao dao = (UsuarioDao) getServletContext().getAttribute("usuarioDao");
        boolean ok = dao.editar(id, usuario, email, senha);

        Map<String, Object> mensagem = new HashMap<>();
        Gson gson = new Gson();
        PrintWriter pw = response.getWriter();

        if (ok) {
            response.setStatus(HttpServletResponse.SC_OK);
            mensagem.put("mensagem", "Perfil atualizado com sucesso");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mensagem.put("mensagem", "Erro ao atualizar perfil");
        }

        pw.print(gson.toJson(mensagem));
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        super.doOptions(req, response);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}