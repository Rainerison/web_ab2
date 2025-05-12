<%@ page import="java.util.List" %>
<%@ page import="model.Ingressos" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <title>Ingressos Comprados</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Ingressos Comprados</h2>
        <table border="1">
            <tr>
                <th>Filme</th>
                <th>Horário</th>
                <th>Sala</th>
            </tr>
            <% 
                List<Ingressos> ingressos = (List<Ingressos>) request.getAttribute("ingressos");
                if (ingressos != null && !ingressos.isEmpty()) {
                    for (Ingressos ingresso : ingressos) {
            %>
            <tr>
                <td><%= ingresso.getFilmeNome() %></td>
                <td><%= ingresso.getHorario() %></td>
                <td><%= ingresso.getSala() %></td>
            </tr>
            <% 
                    }
                }
            %>
        </table>
        <br>
        <a href="index.jsp">Voltar</a>
    </div>
</body>
</html>
