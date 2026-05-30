//package br.edu.ifsp;
//
//import br.edu.ifsp.model.Comentario;
//import br.edu.ifsp.model.Jogo;
//import br.edu.ifsp.model.Usuario;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet(name = "ComentarioServlet", value = "/Cadastro_Comentario")
//public class ComentarioServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpSession sessao = request.getSession();
//
//        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
//
//        if (usuario == null) {
//            response.sendRedirect("erro.jsp");
//            return;
//        }
//
//        String texto = request.getParameter("comentario");
//        String jogoId = request.getParameter("jogoId");
//
//        if (texto == null || texto.trim().isEmpty()) {
//            response.sendRedirect("ver_jogo?id=" + jogoId);
//            return;
//        }
//
//        List<Jogo> listaJogos =
//                (List<Jogo>) getServletContext().getAttribute("lista");
//
//        if (listaJogos == null) {
//            throw new RuntimeException("Lista de jogos não encontrada");
//        }
//
//        int idInt;
//        try {
//            idInt = Integer.parseInt(jogoId);
//        } catch (Exception e) {
//            throw new RuntimeException("ID inválido: " + jogoId);
//        }
//
//        Jogo jogoSelecionado = null;
//
//        for (Jogo j : listaJogos) {
//            if (j.getId() == idInt) {
//                jogoSelecionado = j;
//                break;
//            }
//        }
//
//        if (jogoSelecionado == null) {
//            throw new RuntimeException("Jogo não encontrado");
//        }
//
//        List<Comentario> listaComentario =
//                (List<Comentario>) getServletContext().getAttribute("comentarios");
//
//        if (listaComentario == null) {
//            listaComentario = new ArrayList<>();
//        }
//
//        Comentario c = new Comentario(texto, usuario, jogoSelecionado);
//
//        listaComentario.add(c);
//
//        getServletContext().setAttribute("comentarios", listaComentario);
//
//        response.sendRedirect("ver_jogo?id=" + jogoId);
//    }
//}