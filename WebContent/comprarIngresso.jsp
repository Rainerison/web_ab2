<%@ page import="model.Filme" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Comprar Ingresso</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/comprarIngresso.css">
</head>
<body>
    <div class="container">
        <h2>Comprar Ingresso</h2>
        <form action="processarIngresso" method="POST" class="form-comprar">  

            <label>Escolha o filme:</label><br>
            <select name="filmeNome" required>
                <option value="" disabled selected>Selecione um filme</option>
                <% 
                    List<Filme> filmes = (List<Filme>) request.getAttribute("filmes"); // Recebe a lista de filmes do controlador
                    if (filmes != null && !filmes.isEmpty()) {
                        for (Filme filme : filmes) {
                %>
                <option value="<%= filme.getNome() %>"><%= filme.getNome() %></option>
                <% 
                        }
                    } else {
                %>
                <option value="" disabled>Nenhum filme disponível</option>
                <% } %>
            </select><br><br>
            
            <label>Escolha o Horário:</label><br>
            <select name="horario" required>
                <option value="14:00">14:00</option>
                <option value="16:00">16:00</option>
                <option value="18:00">18:00</option>
                <option value="20:00">20:00</option>
            </select><br><br>
            
            <label>Sala:</label><br>
            <input type="text" name="sala" required><br><br>
            
            <button type="submit">Comprar</button>
        </form>
        <br>
        <a href="index.jsp">Voltar para a página inicial</a>
    </div>
</body>
</html>
