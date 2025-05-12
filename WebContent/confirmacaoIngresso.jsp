<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ingresso Comprado</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Ingresso comprado com sucesso!</h2>
        <p><strong>Filme:</strong> ${filmeNome}</p>
        <p><strong>Horário:</strong> ${horario}</p>
        <p><strong>Sala:</strong> ${sala}</p>
        
        <div class="actions">
            <a href="comprarIngresso" class="btn">Comprar outro ingresso</a>
            <a href="index.jsp" class="btn">Página inicial</a>
        </div>
    </div>
</body>
</html>