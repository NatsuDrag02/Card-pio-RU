package R.u;

public class Alimento {
    private String nome;

    public Alimento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}