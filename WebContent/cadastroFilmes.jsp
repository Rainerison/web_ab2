<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Filme</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Cadastrar Novo Filme</h2>
        <form action="processarFilme" method="POST">
    <label>Nome do Filme:</label>
    <input type="text" name="nomeFilme" required><br><br>
    <button type="submit">Cadastrar</button>
</form>
        <br>
        <a href="index.jsp">Voltar</a>
    </div>
</body>
</html>