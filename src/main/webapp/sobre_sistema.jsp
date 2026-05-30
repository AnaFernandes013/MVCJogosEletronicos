<%--
  Created by IntelliJ IDEA.
  User: ferna
  Date: 28/03/2026
  Time: 08:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%if (session.getAttribute("usuarioLogado") != null) {
%><c:import url="/includes/headerLogin.jsp"/><%
} else {
%><c:import url="/includes/header.jsp"/><%}%>

<div class="cont">
    <h1>️ Sobre o Sistema</h1>

    <div class="section">
        <h2>Sobre o Sistema</h2>
        <p>
            O sistema de gerenciamento de jogos eletrônicos foi desenvolvido para facilitar o cadastro,
            a organização e a visualização de informações sobre diversos jogos.
        </p>
        <p>
            A plataforma permite que usuários explorem uma coleção de jogos, visualizando detalhes como título,
            desenvolvedor, ano de lançamento, gênero, sinopse, idioma, plataforma, classificação indicativa e
            avaliação dos usuários.
        </p>
    </div>

    <div class="section">
        <h2> Funcionalidades</h2>
        <ul>
            <li>Cadastro de novos jogos</li>
            <li>Visualização de jogos cadastrados</li>
            <li>Edição de informações dos jogos</li>
            <li>Remoção de jogos</li>
            <li>Sistema de login para controle de acesso</li>
            <li>Barra de busca para encontrar jogos facilmente</li>
        </ul>
        <p>
            Apenas usuários autenticados podem adicionar, editar ou excluir jogos, garantindo maior segurança e
            controle das informações.
        </p>
    </div>

    <div class="section">
        <h2> Controle de Acesso</h2>
        <p>
            O sistema conta com um mecanismo de autenticação que restringe ações sensíveis apenas a usuários cadastrados.
        </p>
        <p>
            Caso um usuário tente acessar áreas protegidas sem estar logado, ele será redirecionado para uma página de
            erro com uma mensagem informativa.
        </p>
    </div>

    <div class="section">
        <h2> Página Inicial</h2>
        <p>Na página inicial (Home), são exibidos:</p>
        <ul>
            <li>Lançamentos recentes</li>
            <li>Jogos</li>
        </ul>
        <p>
            Essa organização facilita a navegação e melhora a experiência do usuário.
        </p>
    </div>

    <div class="section">
        <h2>Objetivo do Sistema</h2>
        <p>
            O principal objetivo do sistema é proporcionar uma forma simples, intuitiva e eficiente de gerenciar
            informações sobre jogos eletrônicos, permitindo que usuários tenham acesso rápido e organizado aos dados.
        </p>
    </div>

    <div class="section">
        <h2>Desenvolvedor</h2>
        <p>
            Sistema desenvolvido por <strong>Ana Luiza Fernandes dos Santos & Lara Saquete Carvalho</strong> como parte de um projeto acadêmico.
        </p>
    </div>

</div>
<c:import url="/includes/footer.jsp"/>
