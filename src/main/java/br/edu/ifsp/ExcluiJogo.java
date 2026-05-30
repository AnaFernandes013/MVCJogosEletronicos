//package br.edu.ifsp;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "ExcluiJogo", value = "/excluir_jogo")
//public class ExcluiJogo extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        HttpSession sessao = request.getSession();
//
//        if(sessao.getAttribute("usuarioLogado") == null){
//            response.sendRedirect("erro.jsp");
//            return;
//        }
//
//        throw new RuntimeException();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id = request.getParameter("id");
//
//        List<Jogo> lista = (List<Jogo>) getServletContext().getAttribute("lista");
//        Jogo jogoEncontrado =null;
//
//        for (int i = 0; i < lista.size(); i++) {
//            Jogo t = lista.get(i);
//
//            if (String.valueOf(t.getId()).equals(id)) {
//                lista.remove(i);
//                break;
//            }
//        }
//        //request.getRequestDispatcher("listarJogo.jsp").forward(request, response);
//        request.getSession().setAttribute("sucessoExcluir", "Exclusão realizada com sucesso!");
//
//        response.sendRedirect("listarJogo.jsp");
//    }
//}
