package R.u;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cardapio cardapio = new Cardapio();
        Scanner teclado = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=== Menu ===");
            System.out.println("[1] Criar Refeição");
            System.out.println("[2] Criar Cardápio Semanal");
            System.out.println("[3] Exibir Cardápio Semanal");
            System.out.println("[0] Sair");
            opcao = teclado.nextInt();
            teclado.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    cardapio.criarRefeicao();
                    break;
                case 2:
                    cardapio.criarCardapio();
                    break;
                case 3:
                    cardapio.exibirCardapio();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        } while (opcao != 0);

        teclado.close();
    }
}