<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%if (session.getAttribute("usuarioLogado") != null) {
%><c:import url="/includes/headerLogin.jsp"/><%
} else {
%><c:import url="/includes/header.jsp"/><%}%>

<c:set var="listaAtual" value="${not empty param.termo ? resultados : lista}" />

<main class="conteudo">
    <div class="container">

        <div class="row">
            <h1>Bem vindos!</h1>
        </div>

        <!-- MOSTRA QUANDO ESTÁ BUSCANDO -->
        <c:if test="${not empty param.termo}">
            <h4>Resultados para: "${param.termo}"</h4>
        </c:if>

        <!--  SEM RESULTADO -->
        <c:if test="${not empty param.termo and empty resultados}">
            <p>Nenhum jogo encontrado</p>
        </c:if>

        <!--  SEM JOGOS -->
        <c:if test="${empty lista}">
            <p>Não há jogos adicionados</p>
        </c:if>

        <c:if test="${empty param.termo}">
            <div class="row">
                <h3>Destaques</h3>

                <c:forEach var="j" items="${lista}" begin="0" end="3">
                    <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                        <div class="card h-100">
                            <img src="imagens/${j.capa}" class="card-img-top">

                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title">${j.titulo}</h5>
                                <p class="card-text text-white texto">${j.sinopse}</p>

                                <form action="ver_jogo" method="get">
                                    <input type="hidden" name="id" value="${j.id}">
                                    <button class="btn btn-primary mt-auto">Veja mais</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="row">
                <h3>Lançamentos</h3>

                <c:forEach var="j" items="${lista}">
                    <c:if test="${j.anoLancamento eq '2026'}">
                        <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                            <div class="card h-100">
                                <img src="imagens/${j.capa}" class="card-img-top">

                                <div class="card-body d-flex flex-column">
                                    <h5 class="card-title">${j.titulo}</h5>
                                    <p class="card-text text-white texto">${j.sinopse}</p>

                                    <form action="ver_jogo" method="get">
                                        <input type="hidden" name="id" value="${j.id}">
                                        <button class="btn btn-primary mt-auto">Veja mais</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>

            <div class="row">
                <h3>Recém adicionados</h3>

                <c:forEach var="j" items="${lista}" varStatus="s">
                    <c:if test="${s.index >= fn:length(lista) - 3}">
                        <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                            <div class="card h-100">
                                <img src="imagens/${j.capa}" class="card-img-top">

                                <div class="card-body d-flex flex-column">
                                    <h5 class="card-title">${j.titulo}</h5>
                                    <p class="card-text text-white texto">${j.sinopse}</p>

                                    <form action="ver_jogo" method="get">
                                        <input type="hidden" name="id" value="${j.id}">
                                        <button class="btn btn-primary mt-auto">Veja mais</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>

        </c:if>

        <div class="row">
            <h3>Todos os jogos</h3>

            <c:forEach var="j" items="${not empty param.termo ? resultados : lista}">
                <div class="col-sm-6 col-md-4 col-lg-3 mb-4">

                    <div class="card h-100">
                        <img src="imagens/${j.capa}" class="card-img-top" alt="Capa do jogo">

                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">${j.titulo}</h5>
                            <p class="card-text text-white texto">${j.sinopse}</p>

                            <form action="ver_jogo" method="get">
                                <input type="hidden" name="id" value="${j.id}">
                                <button type="submit" class="btn btn-primary mt-auto">Veja mais</button>
                            </form>
                        </div>
                    </div>

                </div>
            </c:forEach>
        </div>

        <c:if test="${not empty param.termo}">
            <a href="index.jsp" class="btn btn-secondary mt-3">Limpar busca</a>
        </c:if>

    </div>
</main>
<script>
    // Mudamos o seletor para pegar apenas o span com o texto
    const elementos = document.querySelectorAll('.texto');
    const LIMIT = 100;

    elementos.forEach(span => {
        const textoOriginal = span.innerText.trim();

        if (textoOriginal.length > LIMIT) {
            // Substitui apenas o conteúdo do span
            span.innerText = textoOriginal.substring(0, LIMIT) + '...';
        }
    });
</script>
<c:import url="/includes/footer.jsp"/>