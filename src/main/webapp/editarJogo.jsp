<%--
  Created by IntelliJ IDEA.
  User: ferna
  Date: 19/03/2026
  Time: 08:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%if (session.getAttribute("usuarioLogado") == null) {response.sendRedirect("erro.jsp");return;}%>
<%if (session.getAttribute("usuarioLogado") != null) {
%><c:import url="/includes/headerLogin.jsp"/><%
} else {
%><c:import url="/includes/header.jsp"/><%}%>
<body>

<c:remove var="sucessoJogo" scope="session"/>
<div class="container">

        <h3 class="text-center mb-4">Editar de jogos</h3>

        <form action="editar_jogo" method="post" enctype="multipart/form-data">
            <div class="row">
                <input type="hidden" name="id" value="${jogo.id}">
                <div class="col-md-4">
                    <div class="capa mb-3" id="areaUpload">
                        <img  src="imagens/${jogo.capa}" alt="">

                    </div>

                    <input type="file" id="novoInputImagem" name="capa" accept="image/*">
                </div>

                <!-- CAMPOS -->
                    <div class="col-md-8">

                        <input type="text" name="novoTitulo" class="form-control mb-2" placeholder="Título" value="${jogo.getTitulo()}">

                        <input type="text" name="novoDesenvolvedor" class="form-control mb-2" placeholder="Desenvolvedor" value="${jogo.getDesenvolvedor()}">

                        <input type="number" name="novoAno" class="form-control mb-2" placeholder="Ano de lançamento" value="${jogo.getAnoLancamento()}">

                        <select name="novoGenero" class="form-select mb-2">
                            <option value="">Selecione o genêro</option>
                            <option value="terror" ${jogo.genero == 'terror' ? 'selected' : ''}>Terror</option>
                            <option value="acao" ${jogo.genero == 'acao' ? 'selected' : ''}>Ação</option>
                            <option value="aventura" ${jogo.genero == 'aventura' ? 'selected' : ''}>Aventura</option>
                            <option value="rpg" ${jogo.genero == 'rpg' ? 'selected' : ''}>RPG</option>
                            <option value="estrategia" ${jogo.genero == 'estrategia' ? 'selected' : ''}>Estratégia</option>
                            <option value="simulacao" ${jogo.genero == 'simulacao' ? 'selected' : ''}>Simulação</option>
                            <option value="esporte" ${jogo.genero == 'esporte' ? 'selected' : ''} >Esportes</option>
                            <option value="puzzle" ${jSogo.genero == 'puzzle' ? 'selected' : ''}>Puzzle</option>
                        </select>

                        <textarea name="novoSinopse" class="form-control mb-2" placeholder="Sinopse">${jogo.getSinopse()}</textarea>

                        <input type="text" name="novoIdioma" class="form-control mb-2" placeholder="Idioma" value="${jogo.getIdioma()}">

                        <select name="novoPlataforma" class="form-select mb-2">
                            <option value="">Selecione o genêro</option>
                            <option value="multiplataforma" ${jogo.plataforma == 'multiplataforma' ? 'selected' : ''}>Multiplataforma</option>
                            <option value="pc" ${jogo.plataforma == 'pc' ? 'selected' : ''}>Pc</option>
                            <option value="playstation" ${jogo.plataforma == 'playstation' ? 'selected' : ''}>Playstation</option>
                            <option value="xbox" ${jogo.plataforma == 'xbox' ? 'selected' : ''}>Xbox</option>
                            <option value="nintendo" ${jogo.plataforma == 'nintendo' ? 'selected' : ''}>Nintendo Switch</option>
                        </select>



                        <select name="novoClassificacao" class="form-select mb-2">
                            <option value="">Selecione a classificação</option>
                            <option value="L" ${jogo.classIndicativa == 'L' ? 'selected' : ''}>Livre</option>
                            <option value="10" ${jogo.classIndicativa == '10' ? 'selected' : ''}>10 anos</option>
                            <option value="12" ${jogo.classIndicativa == '12' ? 'selected' : ''}>12 anos</option>
                            <option value="14" ${jogo.classIndicativa == '14' ? 'selected' : ''}>14 anos</option>
                            <option value="16" ${jogo.classIndicativa == '16' ? 'selected' : ''}>16 anos</option>
                            <option value="18" ${jogo.classIndicativa == '18' ? 'selected' : ''}>18 anos</option>
                        </select>

                        <button type="submit" class="btn btn-primary mt-2">Atualizar jogo</button>
                    </div>

            </div>
        </form>
    <br>
</div>
<c:import url="/includes/footer.jsp"/>
