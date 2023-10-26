package metodos;

import java.util.Arrays;

public class Automato {

    // Atributos da classe
    private char[] letras;                       
    private int[] estadosFinais;                  
    private MatrizTransicao matrizTransicao;     
    private StringBuilder sequenciaEstados;    
    private char[] mapeamentoLetras = {'a', 'b','c'};

    // Construtor da classe
    public Automato(char[] letras, int[] estadosFinais, MatrizTransicao matrizTransicao) {
        this.letras = letras;
        this.estadosFinais = estadosFinais;
        this.matrizTransicao = matrizTransicao;
        this.sequenciaEstados = new StringBuilder("[q0]"); // Inicializa a sequência com o estado inicial [q0]
    }

    // Método para testar se uma palavra é aceita pelo autômato
    public boolean testarPalavra(String palavra) {
        int estadoAtual = 0; // Estado inicial é 0

        for (int i = 0; i < palavra.length(); i++) {
            int letraAtual = Arrays.binarySearch(this.letras, palavra.charAt(i)); // Encontra o índice do caractere no alfabeto
            
            estadoAtual = matrizTransicao.getTransicao(estadoAtual, letraAtual); //
            if (estadoAtual == -1) {
                return false; // Se não houver uma transição definida, a palavra é rejeitada
            }

           sequenciaEstados.append(" -").append(mapeamentoLetras[letraAtual]).append("-> [q").append(estadoAtual).append("]"); // Adiciona o estado atual à sequência

            if (i == palavra.length() - 1 && Arrays.binarySearch(this.estadosFinais, estadoAtual) >= 0) {
                return true; // Se o último estado for um estado final, a palavra é aceita
            }
        }

        return false; // Se não terminar em um estado final, a palavra é rejeitada
    }

    public void verificarPalavra(String palavra) {
        if (testarPalavra(palavra)) {
            System.out.println("Parabéns! Essa palavra foi aceita pelo autômato");
        } else {
            System.out.println("Que pena! Essa palavra não foi aceita pelo autômato");
        }

        System.out.println("Estados percorridos: " + sequenciaEstados.toString());

        sequenciaEstados = new StringBuilder("[q0]"); // Reinicializa a sequência para futuros testes
    }
}
