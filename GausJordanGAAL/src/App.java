import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        int linhas, colunas;

        do {
            System.out.print("Informe quantas linhas tem a matriz: ");
            linhas = scanner.nextInt();   
        } while (linhas <= 1);
        do {
            System.out.print("Informe quantas colunas tem a matriz: ");
            colunas = scanner.nextInt(); 
        } while (colunas <= 1);

        Matriz matriz = Matriz.getInstance(linhas, colunas);
        matriz.receberMatriz(scanner);

        System.out.println("\nEscalonamento da matriz\n");
        for (int i = 0; i < linhas; i++) 
            matriz.escalonarMatriz(i);

        System.out.println("\nRedução da matriz por linhas\n");
        matriz.reduzirMatrizPorLinhas();
    }
}

// MATRIZ 4X3 APRESENTOU PROBLEMAS
// MATRIZ 3X4 OK
// CÓDIGO 4X4 RESOLVE QUASE TDS CASOS DE MATRIZES
// MATRIZ {{2,6,1},{1,3,6},{3,9,2}} AINDA APRESENTA PROBLEMA (SOH FUNCIONA NO CÓDIGO 3X3)