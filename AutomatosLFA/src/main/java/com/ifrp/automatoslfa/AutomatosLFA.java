package com.ifrp.automatoslfa;
/*
Autores: Lincoln Martins
                Vitor Sérgio
*/
import java.util.Scanner;
import metodos.Automato;
import metodos.LeitorAutomato;

public class AutomatosLFA {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;

        do {
            imprimirOpcoesAutomato(); 
            System.out.print("Digite aqui sua opção:");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha pendente
            
            if (escolha == 0) {
                imprimirManual(); 
                continue;
            }

            System.out.println("Digite uma palavra para testar: ");
            String palavra = lerPalavraValida(scanner); 

            String caminhoDoAutomato = selecionarAutomato(escolha); // Seleciona o arquivo do autômato com base na escolha
            Automato automato = LeitorAutomato.lerAutomato(caminhoDoAutomato); // Lê as informações do autômato a partir do arquivo

            if (automato != null) {
                automato.verificarPalavra(palavra); // Verifica se a palavra é aceita pelo autômato
            } else {
                System.out.println("Erro ao ler o autômato. Verifique o arquivo.");
            }

            System.out.println("Deseja testar outra palavra? (s/n): ");
            char resposta = scanner.nextLine().charAt(0);
            continuar = (resposta == 's'); 
        } while (continuar);

        System.out.println("Encerrando o programa.");
    }

    public static void imprimirOpcoesAutomato() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-< Menu de Opcoes >-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("\t -> Escolha o autômato que deseja testar:");
        System.out.println("\t1. L = {w | w possui aa ou bb como subpalavra}");
        System.out.println("\t2. L = {w | entre dois a's de w, há quantidade par de b's}");
        System.out.println("\t3. L = {w | w tem aa ou aba, como subpalavra}");
        System.out.println("\t4. L = {w | entre dois b's de w, há quantidade ímpar de a's}");
        System.out.println("\t5. L = { w possua entre a’s quantidade impar de b e entre b’s quantidade par de c}");
        System.out.println("\t0. Ver manual");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-<    >-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    
    public static void imprimirManual() {
        System.out.println("\n\t-=-=-=-=-=-=-=-=-=-=-=-< Manual >-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("\t Este programa permite testar palavras em autômatos finitos determinísticos.");
        System.out.println("\t O alfabeto aceito é {a, b}.");
        System.out.println("\t Você pode escolher um autômato para ser testado e inserir uma palavra para verificação.");
        System.out.println("\t O programa verificará se a palavra é aceita pelo autômato selecionado.");
        System.out.println("\t Você pode testar várias palavras e optar por continuar ou encerrar o programa.");
        System.out.println("\t Certifique-se de que sua entrada consiste apenas nas letras 'a' ou 'b'.");
        System.out.println("\t github: https://github.com/VS-7 / https://github.com/Lincoln-MO");
        System.out.println("\t-=-=-=-=-=-=-=-=-=-=-=-=-=-=-<    >-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
    }

    public static String selecionarAutomato(int escolha) {
        
        switch (escolha) {
            case 1:
                return "/home/vitorsergio/NetBeansProjects/AutomatosLFA/src/automatos/automato1.csv";
            case 2:
                return "/home/vitorsergio/NetBeansProjects/AutomatosLFA/src/automatos/automato2.csv";
            case 3:
                return "/home/vitorsergio/NetBeansProjects/AutomatosLFA/src/automatos/automato3.csv";
            case 4:
                return "/home/vitorsergio/NetBeansProjects/AutomatosLFA/src/automatos/automato4.csv";
            case 5:
                return "/home/vitorsergio/NetBeansProjects/AutomatosLFA/src/automatos/automato5.csv";
            default:
                System.out.println("Escolha inválida, usando autômato padrão (1)");
                return "/home/vitorsergio/NetBeansProjects/AutomatosLFA/src/automatos/automato1.csv";
        }
    }

    public static String lerPalavraValida(Scanner scanner) {
        while (true) {
            String palavra = scanner.nextLine();
            if (palavra.matches("[abc]*")) { 
                return palavra;
            } else {
                    System.out.println("Palavra inválida. Use apenas as letras 'a' ou 'b' e apenas na opção 5 utilize o 'c'. Tente novamente:");
            }
        }
     }
}
