package model;

public class Ingressos {
    private int id;
    private String filmeNome;
    private String horario;
    private String sala;
    
    public Ingressos() {}
    
    public Ingressos(String filmeNome, String horario, String sala) {
        this.filmeNome = filmeNome;
        this.horario = horario;
        this.sala = sala;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFilmeNome() { return filmeNome; }
    public void setFilmeNome(String filmeNome) { this.filmeNome = filmeNome; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }
    
    @Override
    public String toString() {
        return "Ingresso [id=" + id + ", filme=" + filmeNome + 
               ", horario=" + horario + ", sala=" + sala + "]";
    }
}