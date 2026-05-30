//package br.edu.ifsp;
//
//import br.edu.ifsp.model.Comentario;
//import br.edu.ifsp.model.Jogo;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet(name = "VerJogo", value = "/ver_jogo")
//public class VerJogo extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String id = request.getParameter("id");
//
//        List<Jogo> lista =
//                (List<Jogo>) getServletContext().getAttribute("lista");
//
//        List<Comentario> listaComentarios =
//                (List<Comentario>) getServletContext().getAttribute("comentarios");
//
//        if (listaComentarios == null) {
//            listaComentarios = new ArrayList<>();
//        }
//
//        int idInt = Integer.parseInt(id);
//
//        for (Jogo j : lista) {
//
//            if (j.getId() == idInt) {
//
//                request.setAttribute("jogo", j);
//
//                List<Comentario> comentariosDoJogo = new ArrayList<>();
//
//                for (Comentario c : listaComentarios) {
//
//                    if (c.getJogo().getId() == j.getId()) {
//                        comentariosDoJogo.add(c);
//                    }
//                }
//
//                request.setAttribute("comentarios", comentariosDoJogo);
//                break;
//            }
//        }
//
//        request.getRequestDispatcher("/verJogo.jsp").forward(request, response);
//    }
//}