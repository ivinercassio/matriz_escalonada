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

        // if divisor is zero
        if (divisor == 0) {
            int posicao = this.buscarElementoNaoNulo(column);
            if (posicao != -1) {
                this.trocarLinhas(posicao, row);
                forcarPivo(row, column);

            }else if ((column+1) < this.column) 
                forcarPivo(row, (column+1)); // search the pivot on the next column
        }else{
            for (int j = column; j < this.column; j++)
                this.matrix[row][j] /= divisor;
            this.zerarLinhasAbaixo(row, column);
        }           
    }

    private int buscarElementoNaoNulo(int column){
        for (int i = column; i < this.row; i++)
            if(this.matrix[i][column] != 0)
                return i;
        return -1;
    }

    private void zerarLinhasAbaixo(int linhaPivo, int column){ // linhaPivo: this function works below it
        if((linhaPivo+1) == this.row) 
            return; // when is searching at the end of the matrix

        int repeat = (this.matrix.length-1) - linhaPivo;

        for (int i = 1; i <= repeat; i++) {
            float fator = this.matrix[linhaPivo+i][column];
            for (int j = 0; j < this.column; j++) 
                this.matrix[linhaPivo+i][j] -= (fator * this.matrix[linhaPivo][j]);                
        }
    }    

    public void reduzirMatrizPorLinhas(){
        int[] position = posicaoUltimoPivo(this.row-1);
        if (position[1] >= 1)
            for (int i = position[1]; i > 0; i--){ // andando nas colunas da direita para esquerda
                this.zerarLinhasAcima(position[0], i);
                position[0]--;
                this.imprimirMatriz();
            }
    }

    private int[] posicaoUltimoPivo(int linha){
        for (int i = 0; i < this.column; i++)
            if(matrix[linha][i] == 1){
                int[] position = {linha, i};
                return position;
            }
        return posicaoUltimoPivo(linha-1);
    }

    private void zerarLinhasAcima(int linha, int coluna){ // this is the pivot's position on the matrix's columns
        // coluna is equals count of reapetion
        for (int i = 1; i <= coluna; i++) {      // 4x4
            // for (int i = 2; i <= coluna; i++) {      // 3x3
            float fator = this.matrix[coluna-i][coluna];
            for (int j = 0; j < this.column; j++) 
                this.matrix[coluna-i][j] -= (fator * this.matrix[coluna][j]);    // 4x4                
                // this.matrix[coluna-i][j] -= (fator * this.matrix[linha][j]);    // 3x3            
        }
    }
}