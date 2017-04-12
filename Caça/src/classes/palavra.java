package classes;

public class palavra {
    private String nome;
    private String dica;
    
    public palavra(String nome,String dica){
        this.nome=nome;
        this.dica=dica;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }
}
