package br.edu.ifsp.controller;

import br.edu.ifsp.dao.JogoDao;
import br.edu.ifsp.model.Jogo;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
@MultipartConfig
@WebServlet(name = "CastroJogoServlet", value = "/cadastrar_jogo")
public class CadastroJogoServlet extends HttpServlet {

    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // code
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        //
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessao = request.getSession(false);
        if (sessao == null || sessao.getAttribute("usuarioLogado") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("{\"mensagem\":\"Acesso não autorizado\"}");
            return;
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String contentType = request.getContentType();
        String titulo = null;
        String desenvolvedor = null;
        String anoLancamento = null;
        String genero = null;
        String sinopse = null;
        String idioma = null;
        String plataforma = null;
        String classificacao = null;
        Part capaPart = request.getPart("capa");
        String capa = null;

        //salva a imagem
        String nomeArquivo = capaPart.getSubmittedFileName();

        if (nomeArquivo.contains("\\")) {
            nomeArquivo = nomeArquivo.substring(
                    nomeArquivo.lastIndexOf("\\") + 1
            );
        }

        nomeArquivo = System.currentTimeMillis() + "_" + nomeArquivo;

        String caminhoImagens =
                getServletContext().getRealPath("/imagens");

        File pasta = new File(caminhoImagens);

        if (!pasta.exists()) {
            pasta.mkdir();
        }

        capaPart.write(
                caminhoImagens
                        + File.separator
                        + nomeArquivo
        );



        if(contentType.contains("application/json")) {
            StringBuilder sb= new StringBuilder();
            BufferedReader br = request.getReader();
            String linha;
            while((linha = br.readLine()) != null) sb.append(linha);

            Jogo j = gson.fromJson(sb.toString(), Jogo.class);
            titulo = j.getTitulo();
            desenvolvedor = j.getDesenvolvedor();
            anoLancamento = j.getAnoLancamento();
            genero = j.getGenero();
            sinopse = j.getSinopse();
            idioma = j.getIdioma();
            plataforma = j.getPlataforma();
            classificacao  = j.getClassIndicativa();
            capa = nomeArquivo;
        }else{
            titulo = request.getParameter("titulo");
            desenvolvedor = request.getParameter("desenvolvedor");
            anoLancamento = request.getParameter("anoLancamento");
            genero = request.getParameter("genero");
            sinopse = request.getParameter("sinopse");
            idioma = request.getParameter("idioma");
            plataforma = request.getParameter("plataforma");
            classificacao = request.getParameter("classificacao");
            capa = nomeArquivo;
        }

        List<String> listaMensagens = new ArrayList<>();

        if(titulo == null || titulo.isBlank()){
            listaMensagens.add("O campo Título deve ser preenchido.");
        }

        if(desenvolvedor == null || desenvolvedor.isBlank()){
            listaMensagens.add("O campo Desenvolvedor deve ser preenchido.");
        }

        if(anoLancamento == null || anoLancamento.isBlank()){
            listaMensagens.add("O campo Ano de Lançamento deve ser preenchido.");
        }

        if(genero == null || genero.isBlank()){
            listaMensagens.add("O campo Gênero deve ser preenchido.");
        }

        if(sinopse == null || sinopse.isBlank()){
            listaMensagens.add("O campo Sinopse deve ser preenchido.");
        }

        if(idioma == null || idioma.isBlank()){
            listaMensagens.add("O campo Idioma deve ser preenchido.");
        }

        if(plataforma == null || plataforma.isBlank()){
            listaMensagens.add("O campo Plataforma deve ser preenchido.");
        }

        if(classificacao == null || classificacao.isBlank()){
            listaMensagens.add("O campo Classificação deve ser preenchido.");
        }

        if (capaPart == null || capaPart.getSize() == 0) {
            listaMensagens.add("O campo capa deve ser preenchido.");

        }


//        System.out.println("titulo = " + titulo);
//        System.out.println("desenvolvedor = " + desenvolvedor);
//        System.out.println("anoLancamento = " + anoLancamento);
//        System.out.println("genero = " + genero);
//        System.out.println("sinopse = " + sinopse);
//        System.out.println("idioma = " + idioma);
//        System.out.println("plataforma = " + plataforma);
//        System.out.println("classificacao = " + classificacao);
//        System.out.println("capa = " + capa);

        Map<String, Object> mensagem = new HashMap<>();
        if(!listaMensagens.isEmpty()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mensagem.put("mensagem", "Houve um problema");
            mensagem.put("problemas", listaMensagens);
        }else{
            JogoDao dao = (JogoDao) this.getServletContext().getAttribute("dao");

            if(dao == null){
                throw new RuntimeException("DAO não encontrado no ServletContext");
            }

            Jogo j = dao.inserir(
                    titulo,
                    desenvolvedor,
                    anoLancamento,
                    genero,
                    sinopse,
                    idioma,
                    plataforma,
                    classificacao,
                    capa
            );
            response.setStatus(HttpServletResponse.SC_OK);
            mensagem.put("mensagem", "Jogo inserido com sucesso");
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        pw.print(gson.toJson(mensagem));

    }
}