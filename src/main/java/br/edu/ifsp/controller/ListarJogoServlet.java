package br.edu.ifsp.controller;

import br.edu.ifsp.dao.JogoDao;
import br.edu.ifsp.dao.JogoDaoJson;
import br.edu.ifsp.model.Jogo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@MultipartConfig
@WebServlet(name = "ListarJogoServlet", value = "/ver_jogo")
public class ListarJogoServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JogoDao dao = (JogoDao) getServletContext().getAttribute("dao");
        List<Jogo> lista = dao.listar();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(lista));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // code
    }

    @Override
    public void init() throws ServletException {
        super.init();
        JogoDao dao = new JogoDaoJson(getServletContext().getRealPath("/"));
        getServletContext().setAttribute("dao", dao);
    }
}