package br.edu.ifsp.controller;

import br.edu.ifsp.dao.JogoDao;
import br.edu.ifsp.model.Jogo;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet(name = "EditarJogoServlet", value = "/editar_jogo")
public class EditarJogoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String id = request.getParameter("id");
        JogoDao dao = (JogoDao) getServletContext().getAttribute("dao");
        List<Jogo> lista = dao.listar();
        Jogo encontrado = null;

        System.out.println("ID recebido: " + id);

        for (Jogo j : lista) {
            System.out.println("Jogo: " + j.getId());

            if (String.valueOf(j.getId()).equals(id)) {
                encontrado = j;
                break;
            }
        }
        Gson gson = new Gson();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().print(
                gson.toJson(encontrado)
        );
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        super.doOptions(req, response);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        Gson gson = new Gson();

        String id = request.getParameter("id");
        // pega os novos dados do forms
        String titulo = request.getParameter("novoTitulo");
        String desenvolvedor = request.getParameter("novoDesenvolvedor");
        String anoLancamento = request.getParameter("novoAno");
        String genero = request.getParameter("novoGenero");
        String sinopse = request.getParameter("novoSinopse");
        String idioma = request.getParameter("novoIdioma");
        String plataforma = request.getParameter("novoPlataforma");
        String classificacao = request.getParameter("novoClassificacao");

        Part capaPart = request.getPart("capa");

        String nomeArquivo = null;

        // Upload da nova capa (se houver)
        if (capaPart != null && capaPart.getSize() > 0) {

            nomeArquivo = capaPart.getSubmittedFileName();

            if (nomeArquivo.contains("\\")) {
                nomeArquivo = nomeArquivo.substring(nomeArquivo.lastIndexOf("\\") + 1);
            }

            nomeArquivo = System.currentTimeMillis() + "_" + nomeArquivo;

            String caminho = getServletContext().getRealPath("/imagens");

            File pasta = new File(caminho);

            if (!pasta.exists()) {
                pasta.mkdir();
            }

            capaPart.write(caminho + File.separator + nomeArquivo);
        }

        List<String> listaMensagens = new ArrayList<>();
        // ve ce ta tudo certinho
        if (titulo == null || titulo.isBlank()) {
            listaMensagens.add("O campo Título deve ser preenchido.");
        }

        if (desenvolvedor == null || desenvolvedor.isBlank()) {
            listaMensagens.add("O campo Desenvolvedor deve ser preenchido.");
        }

        if (anoLancamento == null || anoLancamento.isBlank()) {
            listaMensagens.add("O campo Ano de Lançamento deve ser preenchido.");
        }

        if (genero == null || genero.isBlank()) {
            listaMensagens.add("O campo Gênero deve ser preenchido.");
        }

        if (sinopse == null || sinopse.isBlank()) {
            listaMensagens.add("O campo Sinopse deve ser preenchido.");
        }

        if (idioma == null || idioma.isBlank()) {
            listaMensagens.add("O campo Idioma deve ser preenchido.");
        }

        if (plataforma == null || plataforma.isBlank()) {
            listaMensagens.add("O campo Plataforma deve ser preenchido.");
        }

        if (classificacao == null || classificacao.isBlank()) {
            listaMensagens.add("O campo Classificação deve ser preenchido.");
        }

        Map<String, Object> mensagem = new HashMap<>();

        if (!listaMensagens.isEmpty()) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            mensagem.put("mensagem", "Houve um problema");
            mensagem.put("problemas", listaMensagens);

        } else {

            JogoDao dao = (JogoDao) getServletContext().getAttribute("dao");
            // aqui q acontece a magica da edição
            boolean editado = dao.editar(
                    Integer.parseInt(id),
                    titulo,
                    desenvolvedor,
                    anoLancamento,
                    genero,
                    sinopse,
                    idioma,
                    plataforma,
                    classificacao,
                    nomeArquivo
            );

            // se estiver tudo ok e ele editar aparece a msg
            if (editado) {

                response.setStatus(HttpServletResponse.SC_OK);
                mensagem.put("mensagem", "Jogo editado com sucesso");

            } else {
                // se naoo ele da erro
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                mensagem.put("mensagem", "Jogo não encontrado");
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().print(gson.toJson(mensagem));
    }
}


