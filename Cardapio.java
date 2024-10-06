package R.u;

import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cardapio {
    private String[] diasSemana = {"SEGUNDA", "TERÇA", "QUARTA", "QUINTA", "SEXTA"};
    private String[] turnoDia = {"MATUTINO", "NOTURNO"};
    private List<Alimento> alimentos = new ArrayList<>();
    private List<Refeicao> refeicoes = new ArrayList<>();
    private Refeicao[][] cardapioSemanal = new Refeicao[5][2];
    private Scanner teclado = new Scanner(System.in);

    public Cardapio() {
        inicializarAlimentos();
        criarCardapioPadrao(); // Cria o cardápio padrão ao iniciar
    }

    private void inicializarAlimentos() {
        alimentos.add(new Alimento("Arroz"));
        alimentos.add(new Alimento("Feijão"));
        alimentos.add(new Alimento("Bife"));
        alimentos.add(new Alimento("Frango"));
        alimentos.add(new Alimento("Salada"));
        alimentos.add(new Alimento("Batata frita"));
        alimentos.add(new Alimento("Macarrão"));
        alimentos.add(new Alimento("Sopa"));
    }

    private void criarCardapioPadrao() {
        Refeicao refeicao1 = new Refeicao(alimentos.get(0), alimentos.get(4), alimentos.get(5)); // Arroz, Salada, Batata frita
        Refeicao refeicao2 = new Refeicao(alimentos.get(1), alimentos.get(4), alimentos.get(2)); // Feijão, Salada, Bife
        Refeicao refeicao3 = new Refeicao(alimentos.get(3), alimentos.get(4), alimentos.get(1)); // Frango, Salada, Feijão
        Refeicao refeicao4 = new Refeicao(alimentos.get(2), alimentos.get(4), alimentos.get(3)); // Bife, Salada, Frango
        Refeicao refeicao5 = new Refeicao(alimentos.get(0), alimentos.get(4), alimentos.get(1)); // Arroz, Salada, Feijão

        cardapioSemanal[0][0] = refeicao1; // Segunda Matutino
        cardapioSemanal[0][1] = refeicao2; // Segunda Noturno
        cardapioSemanal[1][0] = refeicao3; // Terça Matutino
        cardapioSemanal[1][1] = refeicao4; // Terça Noturno
        cardapioSemanal[2][0] = refeicao5; // Quarta Matutino
        cardapioSemanal[2][1] = refeicao1; // Quarta Noturno
        cardapioSemanal[3][0] = refeicao2; // Quinta Matutino
        cardapioSemanal[3][1] = refeicao3; // Quinta Noturno
        cardapioSemanal[4][0] = refeicao4; // Sexta Matutino
        cardapioSemanal[4][1] = refeicao5; // Sexta Noturno
    }

    public void criarRefeicao() {
        System.out.println("Cadastrar nova refeição ou criar um alimento:");

        while (true) {
            System.out.println("[1] Criar Alimento");
            System.out.println("[2] Criar Refeição");
            System.out.println("[0] Sair");

            int opcao = -1;
            try {
                opcao = teclado.nextInt();
                teclado.nextLine(); // Consumir nova linha
                if (opcao == 0) {
                    System.out.println("Saindo da criação de refeição.");
                    return;
                } else if (opcao == 1) {
                    criarAlimento();
                } else if (opcao == 2) {
                    Alimento prato = escolherAlimento("Escolha um prato principal:");
                    if (prato == null) return;

                    Alimento salada = escolherAlimento("Escolha uma salada:");
                    if (salada == null) return;

                    Alimento acompanhamento = escolherAlimento("Escolha um acompanhamento:");
                    if (acompanhamento == null) return;

                    Refeicao refeicao = new Refeicao(prato, salada, acompanhamento);
                    refeicoes.add(refeicao);
                    System.out.println("Refeição cadastrada com sucesso!");
                    return; // Sair após criar a refeição
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                teclado.nextLine();
            }
        }
    }

    private void criarAlimento() {
        System.out.println("Digite o nome do novo alimento:");
        String nome = teclado.nextLine();
        Alimento alimento = new Alimento(nome);
        alimentos.add(alimento);
        System.out.println("Alimento cadastrado com sucesso: " + alimento.getNome());
    }

    private Alimento escolherAlimento(String mensagem) {
        System.out.println(mensagem);
        exibirAlimentos();
        int idx = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("Digite o índice do alimento ou -1 para sair:");
            try {
                idx = teclado.nextInt();
                teclado.nextLine(); // Consumir nova linha

                if (idx == -1) {
                    System.out.println("Saindo da seleção de alimentos.");
                    return null;
                } else if (idx >= 0 && idx < alimentos.size()) {
                    entradaValida = true;
                } else {
                    System.out.println("Índice inválido. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                teclado.nextLine();
            }
        }

        return alimentos.get(idx);
    }

    private void exibirAlimentos() {
        for (int i = 0; i < alimentos.size(); i++) {
            System.out.println("[" + i + "] " + alimentos.get(i).getNome());
        }
    }

    public void criarCardapio() {
        System.out.println("=== Criar Cardápio ===");
        for (int i = 0; i < diasSemana.length; i++) {
            for (int j = 0; j < turnoDia.length; j++) {
                System.out.println("Escolha a refeição para " + diasSemana[i] + " " + turnoDia[j] + ":");
                exibirRefeicoesCadastradas();
                int refeicaoIdx = -1;

                while (true) {
                    System.out.println("Digite o índice da refeição ou -1 para sair:");
                    try {
                        refeicaoIdx = teclado.nextInt();
                        teclado.nextLine();

                        if (refeicaoIdx == -1) {
                            System.out.println("Saindo da criação do cardápio.");
                            return; // Sai do método se o usuário quiser cancelar
                        } else if (refeicaoIdx >= 0 && refeicaoIdx < refeicoes.size()) {
                            cardapioSemanal[i][j] = refeicoes.get(refeicaoIdx);
                            break; // Sai do loop se uma refeição válida for escolhida
                        } else {
                            System.out.println("Índice inválido. Tente novamente.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        teclado.nextLine();
                    }
                }
            }
        }
        System.out.println("Cardápio criado com sucesso!");
    }

    private void exibirRefeicoesCadastradas() {
        for (int i = 0; i < refeicoes.size(); i++) {
            System.out.println("[" + i + "] " + refeicoes.get(i).toString());
        }
    }

    public void exibirCardapio() {
        System.out.println("=== Cardápio Semanal ===");
        for (int i = 0; i < diasSemana.length; i++) {
            System.out.println(diasSemana[i] + ":");
            for (int j = 0; j < turnoDia.length; j++) {
                if (cardapioSemanal[i][j] != null) {
                    System.out.println("  " + turnoDia[j] + ": " + cardapioSemanal[i][j]);
                } else {
                    System.out.println("  " + turnoDia[j] + ": Não disponível");
                }
            }
        }
    }
}