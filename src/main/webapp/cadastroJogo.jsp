<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%if (session.getAttribute("usuarioLogado") == null) {response.sendRedirect("erro.jsp");return;}%>
<%if (session.getAttribute("usuarioLogado") != null) {
%><c:import url="/includes/headerLogin.jsp"/><%
} else {
%><c:import url="/includes/header.jsp"/><%}%>
        <div class="container">
            <h3 class="text-center mb-4">Cadastro de jogos</h3>

            <form action="cadastrar_jogo" method="post" enctype="multipart/form-data">
                <div class="row">


                    <div class="col-md-4">
                        <div class="capa mb-3" id="areaUpload">
                            <img id="preview" src="" alt="" style="display: none;">
                            <span id="texto">Clique para adicionar imagem</span>
                        </div>

                        <input type="file" id="inputImagem" name="capa" accept="image/*" style="opacity: 0; position: absolute; z-index: -1;" >
                        <div class="erro">${erroCapa}</div>
                    </div>


                    <div class="col-md-8">

                        <input type="text" name="titulo" class="form-control mb-2" placeholder="Título" >
                        <div class="erro">${erroTitulo}</div>

                        <input type="text" name="desenvolvedor" class="form-control mb-2" placeholder="Desenvolvedor" >
                        <div class="erro">${erroDesenvolvedor}</div>

                        <input type="number" name="ano" class="form-control mb-2" placeholder="Ano de lançamento" min="1950" max="2026" >
                        <div class="erro">${erroAno}</div>

                        <select name="genero" class="form-select mb-2">
                            <option value="">Selecione o genêro</option>
                            <option value="terror">Terror</option>
                            <option value="acao">Ação</option>
                            <option value="aventura">Aventura</option>
                            <option value="rpg">RPG</option>
                            <option value="estrategia">Estratégia</option>
                            <option value="simulacao">Simulação</option>
                            <option value="esporte">Esportes</option>
                            <option value="puzzle">Puzzle</option>
                        </select>
                        <div class="erro">${erroGenero}</div>

                        <textarea name="sinopse" maxlength="200" class="form-control" placeholder="Sinopse"></textarea>
                        <div class="erro">${erroSinopse}</div>
                        <br>
                        <input type="text" name="idioma" class="form-control mb-2" placeholder="Idioma" >
                        <div class="erro">${erroIdioma}</div>

                        <select name="plataforma" class="form-select mb-2">
                            <option value="">Selecione a plataforma</option>
                            <option value="multiplataforma">Multiplataforma</option>
                            <option value="pc">PC</option>
                            <option value="playstation">Playstation</option>
                            <option value="xbox">Xbox</option>
                            <option value="nintendo">Nintendo Switch</option>
                        </select>
                        <div class="erro">${erroPlataforma}</div>


                        <select name="classificacao" class="form-select mb-2">
                            <option value="">Selecione a classificação</option>
                            <option value="L">Livre</option>
                            <option value="10">10 anos</option>
                            <option value="12">12 anos</option>
                            <option value="14">14 anos</option>
                            <option value="16">16 anos</option>
                            <option value="18">18 anos</option>
                        </select>
                        <div class="erro">${erroClassificacao}</div>

                        <button type="submit" class="btn btn-primary mt-2">Cadastrar</button>
                    </div>

                </div>
            </form>
        </div>


<script src="${pageContext.request.contextPath}/js/cadastro.js"></script>
<c:import url="/includes/footer.jsp"/>
