import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        int dimensao;
        do {
            System.out.print("Informe qual a ordem da matriz quadrada: ");
            dimensao = scanner.nextInt();       
        } while (dimensao <= 1);

        Matriz matriz = Matriz.getInstance(dimensao, dimensao);
        matriz.receberMatriz(scanner);

        for (int i = 0; i < dimensao; i++) {
            matriz.escalonarMatriz(i);
        }
    }
}
