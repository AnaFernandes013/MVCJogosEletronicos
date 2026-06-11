<%--<jsp:useBean id="jogo" scope="request" type="br.edu.ifsp.Jogo"/>--%>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%if (session.getAttribute("usuarioLogado") != null) {--%>
<%--%><c:import url="/includes/headerLogin.jsp"/><%--%>
<%--} else {--%>
<%--%><c:import url="/includes/header.jsp"/><%}%>--%>

<%--<body>--%>
<%--<div class="container py-5">--%>
<%--    <div class="row">--%>

<%--        <!-- IMAGEM -->--%>
<%--        <div class="col-md-4">--%>
<%--            <div class="capa mb-3">--%>
<%--                <img src="imagens/${jogo.capa}" alt="Capa do jogo">--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <!-- INFORMAÇÕES -->--%>
<%--        <div class="col-md-8">--%>

<%--            <h2 class="mb-3">${jogo.titulo}</h2>--%>

<%--            <p><strong>Desenvolvedor:</strong> ${jogo.desenvolvedor}</p>--%>

<%--            <p><strong>Ano de lançamento:</strong> ${jogo.anoLancamento}</p>--%>

<%--            <p><strong>Gênero:</strong> ${jogo.genero}</p>--%>

<%--            <p><strong>Idioma:</strong> ${jogo.idioma}</p>--%>

<%--            <p><strong>Plataforma:</strong> ${jogo.plataforma}</p>--%>

<%--            <p><strong>Classificação:</strong> ${jogo.classIndicativa}</p>--%>

<%--            <hr>--%>

<%--            <h5>Sinopse</h5>--%>
<%--            <p>${jogo.sinopse}</p>--%>

<%--        </div>--%>

<%--        </br>--%>
<%--        </br>--%>

<%--        <div class="container">--%>
<%--            <h5>Comentários</h5>--%>

<%--            <c:if test="${empty comentarios}">--%>
<%--                <p>Nenhum comentário ainda.</p>--%>
<%--            </c:if>--%>


<%--            <c:forEach var="c" items="${comentarios}">--%>
<%--                <strong>--%>
<%--                    <p>${c.usuario.getUsuario()}</p>--%>
<%--                </strong>--%>
<%--                <div class="mb-2 p-2 border rounded">--%>
<%--                    <p>${c.texto}</p>--%>
<%--                </div>--%>
<%--            </c:forEach>--%>

<%--            <!-- apenas usuarios logados -->--%>
<%--            <c:if test="${not empty sessionScope.usuarioLogado}">--%>

<%--                <form action="Cadastro_Comentario" method="post">--%>
<%--                    <input type="hidden" name="jogoId" value="${jogo.id}">--%>

<%--                    <textarea name="comentario" class="form-control" required></textarea>--%>

<%--                    <button type="submit" class="btn btn-primary mt-2">Comentar</button>--%>
<%--                </form>--%>
<%--            </c:if>--%>

<%--        </div>--%>


<%--    </div>--%>

<%--    <div class="mt-4">--%>
<%--        <a href="index.jsp" class="btn btn-secondary">Voltar</a>--%>
<%--    </div>--%>

<%--</div>--%>
<%--</body>--%>

<%--<c:import url="/includes/footer.jsp"/>--%>
