<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%if (session.getAttribute("usuarioLogado") != null) {response.sendRedirect("index.jsp");return;}%>
<%if (session.getAttribute("usuarioLogado") != null) {
%><c:import url="/includes/headerLogin.jsp"/><%
} else {
%><c:import url="/includes/header.jsp"/><%}%>
<c:if test="${not empty sucessoCadastro}">
    <div class="alert alert-success">
            ${sucessoCadastro}
    </div>
    <c:remove var="sucessoCadastro" scope="session"/>
</c:if>

<c:remove var="sucessoCadastro" scope="session"/>

<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <div class="mb-md-5 mt-md-4 pb-5">

                            <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                            <p class="text-white-50 mb-5">Insira seu usuário e senha!</p>

                            <form action="login" method="post">

                                <div class="erro">${erroLogin}</div>

                                <div data-mdb-input-init class="form-outline form-white mb-4">
                                    <input type="text" id="usuarioLogin" name="usuarioLogin" class="form-control form-control-lg" />
                                    <label class="form-label" for="usuarioLogin">Usuário</label>
                                    <div class="erro">${erroUsuarioLogin}</div>
                                </div>


                                <div data-mdb-input-init class="form-outline form-white mb-4">
                                    <input type="password" id="senhaLogin" name="senhaLogin" class="form-control form-control-lg" />
                                    <label class="form-label" for="senhaLogin">Senha</label>
                                    <div class="erro">${erroSenhaLogin}</div>
                                </div>

                                <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>

                                <div class="d-flex justify-content-center text-center mt-4 pt-1">
                                    <a href="#!" class="text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
                                    <a href="#!" class="text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
                                    <a href="#!" class="text-white"><i class="fab fa-google fa-lg"></i></a>
                                </div>
                            </form>
                        </div>

                        <div>
                            <p class="mb-0">Ainda não tem uma conta? <a href="cadastro.jsp" class="text-white-50 fw-bold">Cadastre-se</a>
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Pills content -->
<c:import url="/includes/footer.jsp"/>