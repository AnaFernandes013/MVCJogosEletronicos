package br.edu.ifsp.controller;

import br.edu.ifsp.dao.JogoDao;
import br.edu.ifsp.model.Jogo;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BuscaTermo", value = "/buscar")
public class BuscarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String termo = request.getParameter("termo");

        JogoDao dao = (JogoDao) getServletContext().getAttribute("dao");
        List<Jogo> lista = dao.listar();

        List<Jogo> resultados = new ArrayList<>();

        if (termo == null || termo.isEmpty()) {
            resultados = lista;
        } else {
            for (Jogo jogo : lista) {
                if (jogo.getTitulo().toLowerCase().contains(termo.toLowerCase())) {
                    resultados.add(jogo);
                }
            }
        }

        Gson gson = new Gson();
        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(resultados));
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // code
    }
}