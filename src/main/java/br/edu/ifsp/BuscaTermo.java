//package br.edu.ifsp;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static br.edu.ifsp.CadastroJogo.lista;
//
//@WebServlet(name = "BuscaTermo", value = "/buscar")
//public class BuscaTermo extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String termo = request.getParameter("termo");
//
//        List<Jogo> resultados = new ArrayList<>();
//
//        if (termo == null || termo.isEmpty()) {
//            resultados = lista;
//        } else {
//            for (Jogo jogo : lista) {
//                if (jogo.getTitulo().toLowerCase().contains(termo.toLowerCase())) {
//                    resultados.add(jogo);
//                }
//            }
//        }
//
//        request.setAttribute("resultados", resultados);
//
//        //SEMPRE vai pro index
//        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
//        rd.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // code
//    }
//}