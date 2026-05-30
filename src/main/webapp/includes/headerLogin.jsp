<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Trabaio</title>
  <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;700&family=Inter:wght@300;400;700&display=swap" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="./css/estilos.css">
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary" style="background-color: #e3f2fd !important;" data-bs-theme="light">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="sobre_sistema.jsp">Sobre o sistema</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Jogo
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="cadastroJogo.jsp">Cadastrar</a></li>
            <li><a class="dropdown-item" href="listarJogo.jsp">Listar</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="editar_perfil">Meu perfil</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="logout.jsp">Sair</a>
        </li>
      </ul>
      <form class="d-flex" role="search" action="buscar" method="get">
        <input class="form-control me-2" type="search" name="termo" placeholder="Search" />
        <button class="btn-primary" type="submit">Busca</button>
      </form>
    </div>
  </div>
</nav>
<c:if test="${!empty listaMensagens}">
<div class="alert alert-danger" role="alert">
  <ul class="list-group">
    <c:forEach var="msg" items="${listaMensagens}">
      <li>${msg}</li>
    </c:forEach>
  </ul>
</div>
</c:if>


