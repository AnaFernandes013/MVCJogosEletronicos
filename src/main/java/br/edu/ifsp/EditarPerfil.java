//package br.edu.ifsp;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "EditarPerfil", value = "/editar_perfil")
//public class EditarPerfil extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
//        request.setAttribute("usuario", usuario);
//
//        request.getRequestDispatcher("/perfil.jsp").forward(request, response);
//    }
//
//
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
//        String novoUsuario = request.getParameter("novoUsuario");
//        String novoEmail = request.getParameter("novoEmail");
//        String novaSenha = request.getParameter("novaSenha");
//
//        boolean temErro = false;
//
//        if (novoUsuario == null || novoUsuario.isEmpty()) {
//            request.setAttribute("erroUsuario", "Informe o usuário");
//            temErro = true;
//        }
//
//        if (novoEmail == null || novoEmail.isEmpty()) {
//            request.setAttribute("erroEmail", "Informe o email");
//            temErro = true;
//        }
//
//        if (novaSenha == null || novaSenha.isEmpty()) {
//            request.setAttribute("erroSenha", "Informe a senha");
//            temErro = true;
//        }
//
//        if (temErro) {
//
//            request.setAttribute("usuario", usuario);
//
//            request.getRequestDispatcher("/perfil.jsp")
//                    .forward(request, response);
//
//            return;
//        }
//
//        usuario.setUsuario(novoUsuario);
//        usuario.setEmail(novoEmail);
//        usuario.setSenha(novaSenha);
//
//        sessao.setAttribute("usuarioLogado", usuario);
//
//        request.setAttribute("sucessoPerfil", "Perfil atualizado com sucesso!");
//        request.setAttribute("usuario", usuario);
//
//        request.getRequestDispatcher("/perfil.jsp")
//                .forward(request, response);
//    }
//}