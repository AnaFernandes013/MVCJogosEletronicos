<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/headerLogin.jsp"/>

<main class="conteudo">
  <div class="container d-flex justify-content-center align-items-center">

    <div class="perfil-box">

      <h3 class="text-center mb-4">Meu Perfil</h3>

      <c:if test="${not empty sucessoPerfil}">
        <div class="alert alert-success text-center">
            ${sucessoPerfil}
        </div>
      </c:if>

      <form action="editar_perfil" method="post">

        <div class="mb-3">
          <input type="text" name="novoUsuario" class="form-control"
                 value="${usuario.getUsuario()}" placeholder="Usuário">
          <div class="erro">${erroUsuario}</div>
        </div>

        <div class="mb-3">
          <input type="email" name="novoEmail" class="form-control"
                 value="${usuario.getEmail()}" placeholder="Email">
          <div class="erro">${erroEmail}</div>
        </div>

        <div class="mb-3">
          <input type="password" name="novaSenha" class="form-control"
                 placeholder="Nova senha">
          <div class="erro">${erroSenha}</div>
        </div>

        <button type="submit" class="btn btn-primary w-100">Salvar alterações</button>
      </form>

    </div>

  </div>
</main>

<c:import url="/includes/footer.jsp"/>