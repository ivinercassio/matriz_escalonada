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
            System.out.print("Informe quantas colunas tem a matriz: "); // generalizando para matrizes nao quadradas
            colunas = scanner.nextInt(); 
        } while (colunas <= 1);

        Matriz matriz = Matriz.getInstance(linhas, colunas);
        matriz.receberMatriz(scanner);

        for (int i = 0; i < linhas; i++) 
            matriz.escalonarMatriz(i);
    }
}
