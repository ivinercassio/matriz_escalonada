import java.util.Scanner;

public class Matriz {
    private int column;
    private int row;
    private float[][] matrix;
    // private float determinant;

    private Matriz(int column, int row){
        this.column = column;
        this.row = row;
        this.matrix = new float[this.row][this.column];
    }

    public static Matriz getInstance(int column, int row){
        if(column > 1 && row > 1)
            return new Matriz(column, row);
        return null;
    }

    // the class's methods
    public void receberMatriz(Scanner scanner){
        System.out.println("PREENCHENDO A MATRIZ...");
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.print("Elemento da linha " + (i+1) + " da coluna " + (j+1) + ": ");
                this.matrix[i][j] = scanner.nextFloat();
            }
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
        int linhaPivo = this.encontrarPivo(reference); // thinking that all columns have a pivot
        if(linhaPivo != -1) 
            this.trocarLinhas(linhaPivo, reference);
        else
            this.forcarPivo(reference, reference);
        linhaPivo = reference;
        this.zerarLinhasAbaixo(linhaPivo, reference);
        this.imprimirMatriz();
    }

    public int encontrarPivo(int reference){ 
        int position = -1;
        for (int i = reference; i < this.column; i++) {    
            if(this.matrix[i][reference] == 1){ 
                position = i;
                break;
            }
        }
        return position;
    }

    public void trocarLinhas(int from, int to){ // 'from' is the pivot's row
        if(from == 0)
            return;
        for (int j = 0; j < this.row; j++) {
            float auxiliar = this.matrix[from][j];                
            this.matrix[from][j] = this.matrix[to][j];
            this.matrix[to][j] = auxiliar;
        }
    }

    public void zerarLinhasAbaixo(int linhaPivo, int column){ // linhaPivo: this function works below it
        if((linhaPivo+1) == this.row || (column+1) == this.column) 
            return; // when 'linhaPivo' or 'column' is at the end of the matrix

        int repeat = (this.matrix.length-1) - linhaPivo;

        for (int i = 1; i <= repeat; i++) {
            float fator = this.matrix[linhaPivo+i][column] / this.matrix[linhaPivo][column];
            for (int j = 0; j < matrix.length; j++) 
                this.matrix[linhaPivo+i][j] -= (fator * this.matrix[linhaPivo][j]);                
        }
    }

    public void forcarPivo(int row, int column){ 
        float divisor = this.matrix[row][column];
        for (int j = column; j < this.column; j++) 
            this.matrix[row][j] /= divisor;
    }
}
