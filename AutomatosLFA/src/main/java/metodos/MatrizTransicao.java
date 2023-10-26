package metodos;

public class MatrizTransicao {
    private int[][] matriz;

    public MatrizTransicao(int numLinhas, int numColunas) {
        this.matriz = new int[numLinhas][numColunas];
    }

    public void setTransicao(int linha, int coluna, int valor) {
        matriz[linha][coluna] = valor;
    }

    public int getTransicao(int linha, int coluna) {
        return matriz[linha][coluna];
    }
}
