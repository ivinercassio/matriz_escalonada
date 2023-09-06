import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        int dimensao;
        do {
            System.out.print("Informe qual a ordem da matriz quadrada: ");
            dimensao = scanner.nextInt();       
        } while (dimensao <= 1);

        float[][] matriz = receberMatriz(dimensao, scanner);    
        imprimirMatriz(matriz);

        for (int colunaInteresse = 0; colunaInteresse < dimensao; colunaInteresse++) {

            int linhaPivo = encontrarPivo(matriz, colunaInteresse);
            if(linhaPivo != -1) // jah veio com pivo
                matriz = trocarLinhaComPivo(matriz, linhaPivo, colunaInteresse);

            for (int i = 0; i < dimensao; i++) {
                zerarLinhaDeBaixo(matriz, i, colunaInteresse);
            }
            // if(linhaPivo == -1)
            //     zeraLinhaSemPivo();

            imprimirMatriz(matriz);

        }
    }

    public static float[][] receberMatriz(int dimensao, Scanner scanner){
        System.out.println("PREENCHENDO A MATRIZ...");

        float[][] matriz = new float[dimensao][dimensao];
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                System.out.print("Elemento da linha " + (i+1) + " da coluna " + (j+1) + ": ");
                matriz[i][j] = scanner.nextFloat();
            }
        }
        return matriz;
    }

    public static void imprimirMatriz(float[][] matriz){
        int dimensao = matriz[0].length;
        System.out.println("");

        for (int i = 0; i < dimensao; i++) {
            String linha = "";
            for (int j = 0; j < dimensao; j++) {
                linha += matriz[i][j] + "\t";
            }
            System.out.println(linha);
        }
    }

    public static int encontrarPivo(float[][] matriz, int colunaInteresse){ // bucando pivo na coluna 0
        int dimensao = matriz[colunaInteresse].length;
        int linhaPivo = -1;

        for (int i = 1; i < dimensao; i++) {
            if((matriz[i][colunaInteresse] == 1) && (i >= colunaInteresse)){
                linhaPivo = i;
                break;
            }
        }
        return linhaPivo;
    }

    public static float[][] trocarLinhaComPivo(float[][] matriz, int linhaPivo, int colunaInteresse){
        int dimensao = matriz[0].length;
        int linhaTroca;

        if(linhaPivo != colunaInteresse)
            linhaTroca = colunaInteresse; 
        else    
            return matriz;

        for (int j = 0; j < dimensao; j++) {
            float auxiliar = matriz[linhaPivo][j];                
            matriz[linhaPivo][j] = matriz[linhaTroca][j];
            matriz[linhaTroca][j] = auxiliar;
        }
        return matriz;
    }

    public static float[][] zerarLinhaDeBaixo(float[][] matriz, int linhaInteresse, int colunaInteresse){
        int dimensao = matriz[linhaInteresse].length;
        List<Float> linhaMultiplicada = new ArrayList<Float>();

        for (int i = 0; i < dimensao; i++) {
            if(linhaInteresse+1 < dimensao)
                linhaMultiplicada.add( ((matriz[linhaInteresse+1][colunaInteresse]/matriz[linhaInteresse][colunaInteresse]) * matriz[linhaInteresse][i]) );
        }

        for (int i = 0; i < dimensao; i++) {
                matriz[linhaInteresse][i] -= linhaMultiplicada.get(i); 
        }
        return matriz;
    }
}
