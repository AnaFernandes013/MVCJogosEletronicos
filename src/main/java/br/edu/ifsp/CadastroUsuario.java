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
//@WebServlet(name = "CadastroUsuario", value = "/cadastrar_usuario")
//public class CadastroUsuario extends HttpServlet {
//
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String usuario =  request.getParameter("usuarioCadastro");
//        String email =  request.getParameter("email");
//        String senha =  request.getParameter("senhaCadastro");
//
//        System.out.println("usuario: " + usuario);
//        System.out.println("email: " + email);
//        System.out.println("senha: " + senha);
//
//        boolean temErro = false;
//
//        // valida usuário
//        if (usuario == null || usuario.isEmpty()) {
//            request.setAttribute("erroUsuarioCadastro", "Informe o usuário");
//            temErro = true;
//        }
//
//
//        // valida email
//        if (email == null || email.isEmpty()) {
//            request.setAttribute("erroEmail", "Informe o email");
//            temErro = true;
//        }
//
//
//        // valida senha
//        if (senha == null || senha.isEmpty()) {
//            request.setAttribute("erroSenhaCadastro", "Informe a senha");
//            temErro = true;
//        }
//
//
//        // se existir qualquer erro: volta pro cadastro.jsp
//        if (temErro) {
//            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
//            return;
//        }
//
//        // pega a lista de usuários que está salva no ServletContext
//        List<Usuario> listaUsuarios = (List<Usuario>) getServletContext().getAttribute("listaUsuarios");
//
//
//        // percorre a lista para verificar se já existe um usuário com esse nome
//        for (Usuario u : listaUsuarios) {
//
//            // compara o usuário digitado com os já cadastrados
//            if (u.getUsuario().equals(usuario)) {
//                request.setAttribute("erroUsuarioCadastro", "Usuário já cadastrado");
//                request.getRequestDispatcher("cadastro.jsp").forward(request, response);
//                return;
//            }
//        }
//
//
//        // cria um novo objeto Usuario com os dados digitados
//        Usuario novoUsuario = new Usuario(usuario, email, senha);
//
//        // adiciona esse novo usuário na lista
//        System.out.println("Cadastroooouu");
//        listaUsuarios.add(novoUsuario);
//
//        // redireciona para a tela de login após cadastro com sucesso
//        request.getSession().setAttribute("sucessoCadastro", "Cadastro realizado com sucesso!");
//
//        response.sendRedirect("login.jsp");
//    }
//
//    @Override
//    public void init() throws ServletException {
//        // cria a lista
//        List<Usuario> lista = new ArrayList<>();
//        // disponibiliza ela para todos
//        getServletContext().setAttribute("listaUsuarios", lista);
//
//    }
//}
