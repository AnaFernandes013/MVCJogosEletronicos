//package br.edu.ifsp;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet(name = "Login", value = "/login")
//public class Login extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String usuario = request.getParameter("usuarioLogin");
//        String senha = request.getParameter("senhaLogin");
//
//        boolean temErro = false;
//
//        // validação
//        if(usuario == null || usuario.isEmpty()){
//            request.setAttribute("erroUsuarioLogin", "Informe o usuário");
//            temErro = true;
//        }
//
//        if(senha == null || senha.isEmpty()){
//            request.setAttribute("erroSenhaLogin", "Informe a senha");
//            temErro = true;
//        }
//
//        // se tiver erro volta pro login.jsp
//        if (temErro) {
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//            return;
//        }
//
//        List<Usuario> listaUsuarios = (List<Usuario>) getServletContext().getAttribute("listaUsuarios");
//
//        Usuario usuarioEncontrado = null;
//
//        for (Usuario u : listaUsuarios) {
//            if (u.getUsuario().equals(usuario) && u.getSenha().equals(senha)) {
//                usuarioEncontrado = u;
//                break;
//            }
//        }
//
//        // procura usuário na lista
//        for (Usuario u : listaUsuarios) {
//            if (u.getUsuario().equals(usuario) && u.getSenha().equals(senha)) {
//                usuarioEncontrado = u;
//                break;
//            }
//        }
//
//        // se encontrou usuário válido
//        if (usuarioEncontrado != null) {
//            // cria a sessão
//            HttpSession sessao = request.getSession();
//
//            sessao.setAttribute("usuarioLogado", usuarioEncontrado);
//
//            response.sendRedirect("index.jsp");
//
//        } else {
//            request.setAttribute("erroLogin", "Usuário ou senha inválidos");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        }
//    }
//}
//
//
