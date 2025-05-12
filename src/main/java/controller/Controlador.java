package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.DAO;
import model.Filme;
import model.Ingressos;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Controlador", urlPatterns = {
    "/", 
    "/cadastroFilmes", 
    "/comprarIngresso", 
    "/listarIngressos",
    "/listarFilmes",
    "/processarFilme",
    "/processarIngresso",
    "/editarFilme", 
    "/deletarFilme",
    "/atualizarFilme"
})
public class Controlador extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        this.dao = new DAO();
        try {
            dao.testarConexao();
        } catch (Exception e) {
            throw new ServletException("Erro ao conectar ao banco de dados", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        try {
            switch (path) {
                case "/":
                    homePage(request, response);
                    break;
                case "/cadastroFilmes":
                    cadastroFilmesPage(request, response);
                    break;
                case "/comprarIngresso":
                    comprarIngressoPage(request, response);
                    break;
                case "/listarIngressos":
                    listarIngressosPage(request, response);
                    break;
                case "/editarFilme":
                    editarFilmePage(request, response);
                    break;
                case "/deletarFilme":
                    deletarFilme(request, response);
                    break;
                case "/listarFilmes":
                    listarFilmesPage(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException e) {
            tratarErroBancoDados(request, response, e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        try {
            switch (path) {
                case "/processarFilme":
                    processarFilme(request, response);
                    break;
                case "/processarIngresso":
                    processarIngresso(request, response);
                    break;
                case "/atualizarFilme":
                    atualizarFilme(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException e) {
            tratarErroBancoDados(request, response, e);
        }
    }

    private void homePage(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void cadastroFilmesPage(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/cadastroFilmes.jsp").forward(request, response);
    }

    private void comprarIngressoPage(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        List<Filme> filmes = dao.listarFilmes();
        request.setAttribute("filmes", filmes); 
        request.getRequestDispatcher("/comprarIngresso.jsp").forward(request, response);
    }

    private void listarIngressosPage(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        List<Ingressos> ingressos = dao.listarIngressos();
        request.setAttribute("ingressos", ingressos);
        request.getRequestDispatcher("/listarIngressos.jsp").forward(request, response);
    }

    private void processarFilme(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        String nomeFilme = request.getParameter("nomeFilme");
        
        if (nomeFilme == null || nomeFilme.trim().isEmpty()) {
            request.setAttribute("erro", "Nome do filme é obrigatório");
            request.getRequestDispatcher("/cadastroFilmes.jsp").forward(request, response);
            return;
        }
        
        Filme filme = new Filme(nomeFilme);
        dao.inserirFilme(filme);
        
        request.setAttribute("mensagem", "Filme cadastrado com sucesso: " + nomeFilme);
        request.getRequestDispatcher("/confirmacao.jsp").forward(request, response);
    }

    private void processarIngresso(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        String filmeNome = request.getParameter("filmeNome");
        String horario = request.getParameter("horario");
        String sala = request.getParameter("sala");
        
        if (filmeNome == null || horario == null || sala == null) {
            request.setAttribute("erro", "Todos os campos são obrigatórios");
            comprarIngressoPage(request, response);
            return;
        }
        
        Ingressos ingresso = new Ingressos(filmeNome, horario, sala);
        dao.inserirIngresso(ingresso);
        
        request.setAttribute("filmeNome", filmeNome);
        request.setAttribute("horario", horario);
        request.setAttribute("sala", sala);
        request.getRequestDispatcher("/confirmacaoIngresso.jsp").forward(request, response);
    }

    private void editarFilmePage(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Filme filme = dao.buscarFilmePorId(id);
        request.setAttribute("filme", filme);
        request.getRequestDispatcher("/editarFilme.jsp").forward(request, response);
    }

    private void atualizarFilme(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");

        Filme filme = new Filme(nome);
        filme.setId(id);
        dao.atualizarFilme(filme);

        List<Filme> filmes = dao.listarFilmes();
        request.setAttribute("filmes", filmes);
        request.getRequestDispatcher("/cadastroFilmes.jsp").forward(request, response);
    }

    private void deletarFilme(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deletarFilme(id);
        response.sendRedirect("listarFilmes");
    }

    private void listarFilmesPage(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        List<Filme> filmes = dao.listarFilmes();
        request.setAttribute("filmes", filmes);
        request.getRequestDispatcher("/listarFilmes.jsp").forward(request, response);
    }

    private void tratarErroBancoDados(HttpServletRequest request, HttpServletResponse response, SQLException e) 
            throws ServletException, IOException {
        e.printStackTrace();
        request.setAttribute("erro", "Erro no banco de dados: " + e.getMessage());
        request.getRequestDispatcher("/erro.jsp").forward(request, response);
    }
}