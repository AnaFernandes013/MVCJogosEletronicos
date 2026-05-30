<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp"/>
    <body>
        <h1>Página de Obrigado!</h1>

        <div class="container">
            <p class="h3">Exemplo: ${tarefa.nome}</p>
            <p class="h3">Tarefa: ${tarefa.desc}</p>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
<c:import url="/includes/footer.jsp"/>