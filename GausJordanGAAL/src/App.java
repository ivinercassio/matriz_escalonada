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

        int linhaPivo = matriz.encontrarPivoNaColuna(0);
        if(linhaPivo != -1) // encontrei algum pivo na coluna
            matriz.trocarLinhas(linhaPivo, 0);
        else{
            matriz.forcarPivo(0, 0);
            linhaPivo = 0;
        }
        matriz.zerarLinhasAbaixo(linhaPivo, 0);
    }
}
