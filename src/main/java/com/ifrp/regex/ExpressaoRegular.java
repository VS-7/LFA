package com.ifrp.regex;

/**
 *
 * @author vitorsergio
 */
public class ExpressaoRegular {

    public boolean sentencaAceita;
    
    public String TIPOS;
    public String BRANCO, BRANCOS;
    public String DIGITO, DIGITOS;
    public String LETRA, LETRAS;
    public String NOME_VARIAVEL;
    public String EXPONENCIAL;
    public String REAL;
    public String INTEIRO;
    public String NUMEROS;
    public String SIMBOLOS;
    public String ATRIBUICAO;
    public String ARGUMENTO;
    public String PARAMETRO_FUNCAO;
    public String ASSINATURA_FUNCAO;
    public String VARIAVEL;
    public String EXPRESSAO_MATEMATICA;
    public String OP_LOGICO, OP_COMPARADOR, OP_MATEMATICO;
    public String CONDICIONAL;

    public String BINARIO;
    public String BINARIO_POSITIVO_IMPAR;
    public String BINARIO_NEGATIVO_PAR;
    public String BINARIO_3_OU_MAIS_ALGARISMOS;
    public String BINARIO_DIFERENTE_DE_3;

    /*
     * *****************************************
     * Trabalho 02
     *
     * Faças as Expressoes Regulares para reconhecer: 1. Assinatura de funções
     * ex: void funcao1(int a, float b) ex: String funcao2()
     *
     * 2. Parametros de funções ex: int a, float b ex: float media, String nome
     *
     * 3. Condicional ex: if(ano < 1990) ex: if(3*a != 4+t)
     *
     * 4. Expressão matemática ex: 3+media/3 ex: -4+beta*media[1].x ex: soma(a,
     * b)/4*vetor[5].idade
     * ******************************************
     */
    public ExpressaoRegular() {
        //' ' (espaço), '\t' TAB, '\n' new line, '\r' volta o cursos para o inicio da linha, '\f' avanço de pagina, '\v' vertical TAB - (usado em configuraçoes de impressora)
        sentencaAceita = false;
        
        //Expressões com Letras e Simbolos
        TIPOS = "(boolean|byte|char|double|float|int|long|short|String|void)" + "(\\[\\])*";
        BRANCO = "(\\s)"; // [ \t\n\r\f\v]
        BRANCOS = BRANCO + "*";
        LETRA = "([A-Za-z])";
        LETRAS = LETRA + "*";
        SIMBOLOS = "[\\$_]";
        
        //Expressões com Numeros
        DIGITO = "([0-9])";
        DIGITOS = DIGITO + "*";
        EXPONENCIAL = "(E(\\+|-)" + DIGITOS + ")";
        REAL = "(\\-?" + DIGITOS + "\\.?" + DIGITOS + EXPONENCIAL + "?)";
        INTEIRO = "((\\-?" + DIGITOS + ")" + EXPONENCIAL + "?)";
        NUMEROS = "(" + INTEIRO + "|" + REAL + ")";
        
        //Expressões Mistas: Contém numeros, simbolos e letras
        NOME_VARIAVEL = "((" + SIMBOLOS + "|" + LETRA + ")" + "(" + LETRA + "|" + DIGITO + "|" + SIMBOLOS + ")*)";
        ARGUMENTO = "(" + NOME_VARIAVEL + "|" + NUMEROS + ")";
        
        //Variável: Aceita sentenças como: "beta" ou "media[1].x" ou  "soma(a,b)" ou  "vetor[5].idade"
        VARIAVEL = NOME_VARIAVEL + "(\\." + NOME_VARIAVEL + "(\\((" + BRANCOS + ARGUMENTO + BRANCOS + "(,("
                + BRANCOS + ARGUMENTO + BRANCOS + ")*)*)?\\))?" + "|\\[" + DIGITO + "\\])*" + "|" + "\\((" + BRANCOS + ARGUMENTO + BRANCOS + "(,("
                + BRANCOS + ARGUMENTO + BRANCOS + ")*)*)?\\)";
        
        //Parametro de Função = Aceita sentenças como "(int a, float b)" ou "(float media, String nome)"
        PARAMETRO_FUNCAO = BRANCOS + "(\\(" + BRANCOS + TIPOS + BRANCOS + NOME_VARIAVEL + BRANCOS + "(," + BRANCOS + TIPOS + BRANCOS + NOME_VARIAVEL + BRANCOS
                + ")*\\)|\\(" + BRANCOS + "\\))" + BRANCOS; //Pronto
        
        //Assinatura de Função: Aceita sentenças como "void funcao1(int a, float b) ;" e "String funcao2();"
        ASSINATURA_FUNCAO = BRANCOS + TIPOS + BRANCOS + NOME_VARIAVEL + BRANCOS + PARAMETRO_FUNCAO + BRANCOS + ";" + BRANCOS;//Pronto

        OP_LOGICO = "(&&|\\|\\||!)";
        OP_COMPARADOR = "(>|<|=|!=|>=|<=)";
        OP_MATEMATICO = "(\\+|-|/|%|\\*)";
        
        //Expressão Matemática: Aceita sentenças como "3 + media/3" ou "-4 + beta * media[1+FUMCAO(9,0)*PESSOA.ALTURA].x * soma(a,b)/4 * vetor[5].idade"
        EXPRESSAO_MATEMATICA = "(" + BRANCOS + "(" + ARGUMENTO + "|" + VARIAVEL + ")" + BRANCOS
                + "(" + BRANCOS + OP_MATEMATICO + BRANCOS + "(" + ARGUMENTO + "|" + VARIAVEL + ")" + BRANCOS + ")*)*" + BRANCOS;//Pronto
        
        //Expressões de Condição: Aceita sentenças como "if(ano < 1990) " ou "if(3*a != 4+t)"
        CONDICIONAL = BRANCOS + "if" + BRANCOS + "\\(" + BRANCOS + "!?" + EXPRESSAO_MATEMATICA
                + "((" + OP_COMPARADOR + BRANCOS + "|" + OP_LOGICO + BRANCOS + ")"
                + EXPRESSAO_MATEMATICA + BRANCOS + ")*" + BRANCOS + "\\)" + BRANCOS;
        
        BINARIO = "[01]";
        BINARIO_POSITIVO_IMPAR = BINARIO + "*1";
        BINARIO_NEGATIVO_PAR = "1" + BINARIO + "*0";
        BINARIO_3_OU_MAIS_ALGARISMOS = BINARIO + BINARIO + BINARIO + "+";

    }

  public void confere(String exp, String sentenca) {
    if ((sentenca != null) && !sentenca.isEmpty()) {
        if (sentenca.matches(exp)) {
            System.out.println("W:'" + sentenca + "'........ ACEITA!");
            sentencaAceita = true; // Atualiza a variável para indicar que a sentença foi aceita
        } else {
            System.err.println("W:'" + sentenca + "'........ rejeitada.");
            sentencaAceita = false; // Atualiza a variável para indicar que a sentença foi rejeitada
        }
    } else {
        System.err.println("Sentença vazia.");
        sentencaAceita = false; // Atualiza a variável para indicar que a sentença foi rejeitada
    }
}

}
