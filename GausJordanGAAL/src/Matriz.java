import java.util.Scanner;

public class Matriz {
    private int column;
    private int row;
    private float[][] matrix;

    private Matriz(int column, int row){
        this.column = column;
        this.row = row;
        this.matrix = new float[this.row][this.column];
    }

    public static Matriz getInstance(int row, int column){
        if(column > 1 && row > 1)
            return new Matriz(column, row);
        return null;
    }

    // the class's methods
    public void receberMatriz(Scanner scanner){
        System.out.println("\nPREENCHENDO A MATRIZ...");
        for (int i = 0; i < this.row; i++)
            for (int j = 0; j < this.column; j++) {
                System.out.print("Elemento da linha " + (i+1) + " da coluna " + (j+1) + ": ");
                this.matrix[i][j] = scanner.nextFloat();
            }
        imprimirMatriz();
    }

    private void imprimirMatriz(){
        System.out.println("");
        for (int i = 0; i < this.row; i++) {
            String linha = "";
            for (int j = 0; j < this.column; j++) 
                linha += this.matrix[i][j] + "\t";
            System.out.println(linha);
        }
    }

    public void escalonarMatriz(int reference){ // reference row
        // every time the funciton is started, it is need to check whether the column has been summed to 1
        // if so, 'reference' must also be added to 1
        int linhaPivo = this.encontrarPivo(reference); 
        if(linhaPivo != -1) 
            this.trocarLinhas(linhaPivo, reference);
        else
            this.forcarPivo(reference, reference);
        linhaPivo = reference;
        this.zerarLinhasAbaixo(linhaPivo, reference);
        this.imprimirMatriz();
    }

    private int encontrarPivo(int reference){ // reference column
        for (int i = reference; i < this.row; i++)
            if(this.matrix[i][reference] == 1)
                return i;
        return -1;
    }

    private void trocarLinhas(int from, int to){ // 'from' is the pivot's row
        if(from == 0)
            return;
        for (int j = 0; j < this.row; j++) {
            float auxiliar = this.matrix[from][j];                
            this.matrix[from][j] = this.matrix[to][j];
            this.matrix[to][j] = auxiliar;
        }
    }

    private void forcarPivo(int row, int column) { // it means the position where the pivot is waited
        float divisor = this.matrix[row][column];
        if (divisor == 0 && (column+1) < this.column) {
            // O PROBLEMA ESTAH AQUI. A FUNCAO PULA PARA A PROXIMA COLUNA CORRETAMENTE,
            // MAS NAO AVISA O RESTANTE DO CODIGO. FAZENDO APARECER DOIS PIVOS NA MESMA COLUNA
            forcarPivo(row, (column+1)); // search the pivot on the next column
        }
        else if (divisor == 0) // caso o elemento seja nulo na ultima coluna
                return;
            else
                for (int j = column; j < this.column; j++) 
                    this.matrix[row][j] /= divisor;
    }

    private void zerarLinhasAbaixo(int linhaPivo, int column){ // linhaPivo: this function works below it
        if((linhaPivo+1) == this.row || (column+1) == this.column) 
            return; // when is searching at the end of the matrix

        int repeat = (this.matrix.length-1) - linhaPivo;

        for (int i = 1; i <= repeat; i++) {
            float fator = this.matrix[linhaPivo+i][column];
            for (int j = 0; j < this.column; j++) 
                this.matrix[linhaPivo+i][j] -= (fator * this.matrix[linhaPivo][j]);                
        }
    }    

    public void reduzirMatrizPorLinhas(){
        int column = posicaoUltimoPivo(this.row-1);
        if (column >= 0)
            for (int i = column; i > 0; i--){
                zerarLinhasAcima(i);
                this.imprimirMatriz();
            }
    }

    private int posicaoUltimoPivo(int linha){
        for (int i = 0; i < this.column; i++)
            if(matrix[linha][i] == 1)
                return i;
        return posicaoUltimoPivo(linha-1);
    }

    private void zerarLinhasAcima(int position){ // this is the pivot's position on the matrix's columns
        int repeat = this.column-1 - position;

        for (int i = 0; i < repeat; i++) {
            float fator = this.matrix[position-1][position]; 
            for (int j = 0; j < this.column; j++) 
                this.matrix[position-1][j] -= (fator * this.matrix[position][j]);
        }
    }
}

/* 
MATRIZ QUE CAUSA O ERRO NO "FORCAR PIVO"
{{2,6,1},{1,3,6},{3,9,2}}
 */