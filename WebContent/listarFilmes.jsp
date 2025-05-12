<%@ page import="java.util.List" %>
<%@ page import="model.Filme" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <title>Lista de Filmes</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Lista de Filmes</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Ações</th>
            </tr>
            <% 
                List<Filme> filmes = (List<Filme>) request.getAttribute("filmes");
                if (filmes != null && !filmes.isEmpty()) {
                    for (Filme filme : filmes) {
            %>
            <tr>
                <td><%= filme.getId() %></td>
                <td><%= filme.getNome() %></td>
                <td>
                    <a href="editarFilme?id=<%= filme.getId() %>">Editar</a> | 
                    <a href="deletarFilme?id=<%= filme.getId() %>" 
                       onclick="return confirm('Tem certeza que deseja excluir este filme?')">Excluir</a>
                </td>
            </tr>
            <% 
                    }
                }
            %>
        </table>
        <br>
        <a href="cadastroFilmes">Cadastrar Novo Filme</a> | 
        <a href="index.jsp">Página Inicial</a>
    </div>
</body>
</html>