<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%if (session.getAttribute("usuarioLogado") == null) {response.sendRedirect("erro.jsp");return;}%>
<%if (session.getAttribute("usuarioLogado") != null) {
%><c:import url="/includes/headerLogin.jsp"/><%
} else {
%><c:import url="/includes/header.jsp"/><%}%>

<c:if test="${not empty msgSucessoJogo}">
    <div class="alert alert-success">
            ${msgSucessoJogo}
    </div>
    <c:remove var="msgSucessoJogo" scope="session"/>
</c:if>

<c:if test="${not empty sucessoJogo}">
    <div class="alert alert-success">
            ${sucessoJogo}
    </div>
    <c:remove var="sucessoJogo" scope="session"/>
</c:if>

<c:if test="${not empty sucessoExcluir}">
    <div class="alert alert-success">
            ${sucessoExcluir}
    </div>
    <c:remove var="sucessoExcluir" scope="session"/>
</c:if>

       <div class="container">
           </br>
           <h1>Lista de jogos</h1>
           </br>
           <div class="table-responsive">
                <table class="table table-info" >
                  <thead>
                    <tr>
                      <th scope="col">id</th>
                      <th scope="col">Titulo</th>
                      <th scope="col">Desenvolvedor</th>
                      <th scope="col">Ano de lançamento</th>
                      <th scope="col">Genêro</th>
                      <th scope="col">Sinopse</th>
                      <th scope="col">Idioma</th>
                      <th scope="col">Plataforma</th>
                      <th scope="col">Classificação</th>
                      <th scope="col">Capa</th>
                      <th scope="col">Ação</th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:if test="${empty lista}">
                    <tr>
                        <td colspan=11>Não há jogos adicionadas</td>
                    </tr>
                  </c:if>
                   <c:forEach var="j" items="${lista}">
                    <tr>
                        <td>${j.getId()}</td>
                        <td>${j.getTitulo()}</td>
                        <td>${j.getDesenvolvedor()}</td>
                        <td>${j.getAnoLancamento()}</td>
                        <td>${j.getGenero()}</td>
                        <td class="sinopse">
                            <span class="resumo-texto">${j.getSinopse()}</span>

                            <span class="badge rounded-pill bg-info text-dark btn-abrir-modal"
                                  style="cursor: pointer; font-weight: 500;"
                                  data-bs-toggle="modal"
                                  data-bs-target="#modalSinopse${j.getId()}">
                                Ler Sinopse
                            </span>

                            <div class="modal fade" id="modalSinopse${j.getId()}" tabindex="-1" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" style="color: black;">${j.getTitulo()} - Sinopse</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body" style="color: #333; white-space: normal;">
                                                ${j.getSinopse()}
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td>${j.getIdioma()}</td>
                        <td>${j.getPlataforma()}</td>
                        <td>${j.getClassIndicativa()}</td>
                        <td>
                            <img src="imagens/${j.capa}"
                                 style="width:50px;height:70px;object-fit:cover;border-radius:4px;">
                        </td>
                        <td class="flex-column">
                            <form action="editar_jogo" method="get">
                                <input type="hidden" name="id" value="${j.id}">
                                <button type="submit" class="btn btn-warning btn-sm w-100">Editar</button>
                            </form>
                            <br>
                            <form action="excluir_jogo" method="post">
                                <input type="hidden" name="id" value="${j.id}">
                                <button type="submit" class="btn btn-danger btn-sm w-100">Excluir</button>
                            </form>
                        </td>

                    </tr>
                   </c:forEach>
                </table>
               <c:if test="${not empty resultados}">
                   <h3>Resultados da busca:</h3>

                   <c:forEach var="j" items="${resultados}">
                       <p>${j.titulo}</p>
                   </c:forEach>
               </c:if>
           </div>
       </div>

       <script src="js/tabela.js"></script>
<c:import url="/includes/footer.jsp"/>
