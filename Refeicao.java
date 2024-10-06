package R.u;

public class Refeicao {
    private Alimento pratoPrincipal;
    private Alimento salada;
    private Alimento acompanhamento;

    public Refeicao(Alimento pratoPrincipal, Alimento salada, Alimento acompanhamento) {
        this.pratoPrincipal = pratoPrincipal;
        this.salada = salada;
        this.acompanhamento = acompanhamento;
    }

    @Override
    public String toString() {
        return "Refeição: " + pratoPrincipal.getNome() + ", " + salada.getNome() + ", " + acompanhamento.getNome();
    }
}