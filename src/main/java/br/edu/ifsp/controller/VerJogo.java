package br.edu.ifsp.controller;

import br.edu.ifsp.dao.JogoDao;
import br.edu.ifsp.dao.JogoDaoJson;
import br.edu.ifsp.model.Jogo;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@MultipartConfig
@WebServlet(name = "VerJogo", value = "/detalhes_jogo")
public class VerJogo extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");

        String id = request.getParameter("id");

        JogoDao dao =
                new JogoDaoJson(getServletContext().getRealPath("/"));

        int idInt = Integer.parseInt(id);

        for (Jogo j : dao.listar()) {
            if (j.getId() == idInt) {

                Gson gson = new Gson();

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                response.getWriter().print(gson.toJson(j));
                return;
            }
        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
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
        // code
    }
}