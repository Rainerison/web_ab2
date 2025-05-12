<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Filme</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Editar Filme</h2>
        <form action="atualizarFilme" method="POST">
            <input type="hidden" name="id" value="${filme.id}">

            <label>Nome do Filme:</label>
            <input type="text" name="nome" value="${filme.nome}" required><br><br>

            <button type="submit">Atualizar</button>
        </form>
        <br>
        <a href="listarFilmes">Voltar</a>
    </div>
</body>
</html>