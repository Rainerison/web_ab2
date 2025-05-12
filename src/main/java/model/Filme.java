package model;

public class Filme {
    private int id;
    private String nome;
    
    public Filme() {}
    
    public Filme(String nome) {
        this.nome = nome;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return "Filme [id=" + id + ", nome=" + nome + "]";
    }
}