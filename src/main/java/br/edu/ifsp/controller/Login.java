package br.edu.ifsp.controller;

import br.edu.ifsp.dao.UsuarioDao;
import br.edu.ifsp.dao.UsuarioDaoJson;
import br.edu.ifsp.model.Usuario;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet(name = "Login", value = "/login", loadOnStartup = 3)
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        System.out.println("Email: " + email);
        System.out.println("Senha: " + senha);

        // pega o DAO
        UsuarioDao dao = (UsuarioDao) getServletContext().getAttribute("usuarioDao");
        List<Usuario> listaUsuarios = dao.listar();

        Usuario usuarioEncontrado = null;

        for (Usuario u : listaUsuarios) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                usuarioEncontrado = u;
                break;
            }
        }

        Map<String, Object> mensagem = new HashMap<>();
        Gson gson = new Gson();
        PrintWriter pw = response.getWriter();

        if (usuarioEncontrado != null) {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuarioLogado", usuarioEncontrado);

            response.setStatus(HttpServletResponse.SC_OK);
            mensagem.put("mensagem", "Login realizado com sucesso");
            mensagem.put("usuario", usuarioEncontrado.getUsuario());
            mensagem.put("id", usuarioEncontrado.getId());
            mensagem.put("tipo", usuarioEncontrado.getTipo());
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            mensagem.put("mensagem", "Email ou senha inválidos");
        }

        pw.print(gson.toJson(mensagem));
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        super.doOptions(req, response);
        response.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        UsuarioDao dao = new UsuarioDaoJson(getServletContext().getRealPath("/"));
        getServletContext().setAttribute("usuarioDao", dao);
    }
}