package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/cine_penedo";
    private String user = "root";
    private String password = "root123";

    private Connection conectar() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }

    // Testar conexão
    public void testarConexao() {
        try (Connection con = conectar()) {
            System.out.println("Conexão OK! Banco: " + con.getMetaData().getDatabaseProductName());
        } catch (Exception e) {
            System.out.println("Erro na conexão:");
            e.printStackTrace();
        }
    }

    // CRUD para Filmes
    public void inserirFilme(Filme filme) throws SQLException {
        String sql = "INSERT INTO filmes (nome) VALUES (?)";
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pst.setString(1, filme.getNome());
            pst.executeUpdate();
            
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    filme.setId(rs.getInt(1));
                }
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver não encontrado", e);
        }
    }

    public List<Filme> listarFilmes() throws SQLException {
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM filmes ORDER BY nome";
        
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getInt("id"));
                filme.setNome(rs.getString("nome"));
                filmes.add(filme);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver não encontrado", e);
        }
        return filmes;
    }

    // CRUD para Ingressos
    public void inserirIngresso(Ingressos ingresso) throws SQLException {
        String sql = "INSERT INTO ingressos (filme_nome, horario, sala) VALUES (?, ?, ?)";
        
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, ingresso.getFilmeNome());
            pst.setString(2, ingresso.getHorario());
            pst.setString(3, ingresso.getSala());
            pst.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver não encontrado", e);
        }
    }

    public List<Ingressos> listarIngressos() throws SQLException {
        List<Ingressos> ingressos = new ArrayList<>();
        String sql = "SELECT * FROM ingressos ORDER BY horario";
        
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                Ingressos ingresso = new Ingressos();
                ingresso.setId(rs.getInt("id"));
                ingresso.setFilmeNome(rs.getString("filme_nome"));
                ingresso.setHorario(rs.getString("horario"));
                ingresso.setSala(rs.getString("sala"));
                ingressos.add(ingresso);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver não encontrado", e);
        }
        return ingressos;
    }

public void atualizarFilme(Filme filme) throws SQLException {
    String sql = "UPDATE filmes SET nome = ? WHERE id = ?";
    
    try (Connection con = conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setString(1, filme.getNome());
        pst.setInt(2, filme.getId());
        pst.executeUpdate();
    } catch (ClassNotFoundException e) {
        throw new SQLException("Driver não encontrado", e);
    }
}

public void deletarFilme(int id) throws SQLException {
    String sql = "DELETE FROM filmes WHERE id = ?";
    
    try (Connection con = conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setInt(1, id);
        pst.executeUpdate();
    } catch (ClassNotFoundException e) {
        throw new SQLException("Driver não encontrado", e);
    }
}
public Filme buscarFilmePorId(int id) throws SQLException {
    String sql = "SELECT * FROM filmes WHERE id = ?";
    Filme filme = null;
    
    try (Connection con = conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setInt(1, id);
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                filme = new Filme();
                filme.setId(rs.getInt("id"));
                filme.setNome(rs.getString("nome"));
            }
        }
    } catch (ClassNotFoundException e) {
        throw new SQLException("Driver não encontrado", e);
    }
    return filme;
}
}