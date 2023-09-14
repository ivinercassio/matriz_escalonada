import java.util.Scanner;

public class Matriz {
    private int column;
    private int line;
    private float[][] matrix;
    private float determinant;

    private Matriz(int column, int line){
        this.column = column;
        this.line = line;
        this.matrix = new float[this.line][this.column];
    }

    public static Matriz getInstance(int column, int line){
        if(column > 1 && line > 1)
            return new Matriz(column, line);
        return null;
    }

    public void receberMatriz(Scanner scanner){

        System.out.println("PREENCHENDO A MATRIZ...");
        for (int i = 0; i < this.line; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.print("Elemento da linha " + (i+1) + " da coluna " + (j+1) + ": ");
                this.matrix[i][j] = scanner.nextFloat();
            }
        }
        imprimirMatriz();
    }

    private void imprimirMatriz(){

        System.out.println("");
        for (int i = 0; i < this.line; i++) {
            String linha = "";
            for (int j = 0; j < this.column; j++) 
                linha += this.matrix[i][j] + "\t";
            System.out.println(linha);
        }
    }

    public int encontrarPivoNaColuna(int column){ // search on the column
        int line = -1;
        for (int i = 0; i < this.column; i++) {
            if(this.matrix[i][column] == 1){ // column 0
                line = i;
                break;
            }
        }
        return line;
    }

    public void trocarLinhas(int from, int to){ // from is the pivot's line. to's value is 0
        if(from == 0)
            return;
        for (int j = 0; j < this.line; j++) {
            float auxiliar = this.matrix[from][j];                
            this.matrix[from][j] = this.matrix[to][j];
            this.matrix[to][j] = auxiliar;
        }
    }

    public void zerarLinhasAbaixo(int line, int column){ // reference line: this function works below it. reference column
        if((line+1) == this.line) // when 'line' is the last line
            return;

        int repeat = (this.matrix.length-1) - line;

        for (int i = 1; i <= repeat; i++) {
            float fator = this.matrix[line+i][column] / this.matrix[line][column];
            for (int j = 0; j < matrix.length; j++) 
                this.matrix[line+i][j] -= (fator * this.matrix[line][j]);                
        }
        
        this.imprimirMatriz();
    }

    public void forcarPivo(int line, int column){ // references values
        for (int j = 0; j < this.matrix.length; j++) 
            this.matrix[line][j] /= this.matrix[line][column];
    }
}
