package gameoflifeconsole;

/**
 * Rappresenta una board del Game of life
 * 
 */
import java.util.Random;

public class Board {
    private Cell matrix[][]; 
    private int rows;
    private int columns;
    /**
     * Costruttore base
     * @param rows the rows of the board
     * @param columns the columns of the board
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new Cell[rows][columns];
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < columns; col++) {
                matrix[row][col] = new Cell(row, col);
            }
        }
    }
    
    /**
     * Inizializza la board
     * @param numberOfAliveCells the number of alive cells in the board 
     */
    public void init(int numberOfAliveCells){
        Random random = new Random();
           
        for(int i = 0; i < numberOfAliveCells;) { 
            int xRandom = random.nextInt(columns);
            int yRandom = random.nextInt(rows);
            if (matrix[yRandom][xRandom].isAlive() == false){
                matrix[yRandom][xRandom].setAlive(true);
                i++;
            }
        }
    }
    
    /**
     * Reset the board, all cells dead
     */
    public void reset(){
        for(int row = 0; row < rows;row++) {
            for(int col = 0; col < columns; col++) {
                matrix[row][col].setAlive(false);
            }
        }
    }
    /**
     * Count neighbors of a cell
     * @param row the row of the cell
     * @param col the column of the cell
     * @return count the number of alive neighbors of the cell
     */
    public int countNeighbors(int row, int col){
        int count = 0;
        int[][] neighborsCoords = new int[8][2];

        neighborsCoords[0][0] = row-1; neighborsCoords[0][1] = col-1; 
        neighborsCoords[1][0] = row-1; neighborsCoords[1][1] = col; 
        neighborsCoords[2][0] = row-1; neighborsCoords[2][1] = col+1; 
        neighborsCoords[3][0] = row; neighborsCoords[3][1] = col-1; 
        neighborsCoords[4][0] = row; neighborsCoords[4][1] = col+1; 
        neighborsCoords[5][0] = row+1; neighborsCoords[5][1] = col-1; 
        neighborsCoords[6][0] = row+1; neighborsCoords[6][1] = col; 
        neighborsCoords[7][0] = row+1; neighborsCoords[7][1] = col+1; 
        
        for(int i=0; i < neighborsCoords.length; i++){
            try{
                if(matrix[neighborsCoords[i][0]][neighborsCoords[i][1]].isAlive()){
                    count ++;
                    }
            }catch(Exception e) {
                }
        }
        return count;
    }
            
    /**
     * Next generation matrix of a given matrix
     * result of the application of the 4 rules for the Conway's Game of Life
     * @return nextMatrix the next matrix
     */
    public Cell[][] transition(){
        int neighbors;
        Cell[][] nextMatrix;
        nextMatrix = new Cell[rows][columns];
        
        // creates new matrix equal to the first
        for(int row=0; row<rows;row++){
            for(int col=0;col<columns;col++){
                nextMatrix[row][col] = new Cell(row, col);
                nextMatrix[row][col].setAlive(matrix[row][col].isAlive()); 
            }
        }
        
        for(int row=0; row<rows;row++){
            for(int col=0; col<columns;col++){
                neighbors = countNeighbors(row, col);
                if(neighbors < 2 || neighbors > 3){
                    nextMatrix[row][col].setAlive(false);
                }
                else if(neighbors == 3){
                    nextMatrix[row][col].setAlive(true);
                }                
            }
        }
        
        if(matrixesAreEqual(matrix, nextMatrix)) System.exit(0);
        return nextMatrix;   
    }
    
    /**
     * Update the board with transition
     * @param updateDelay time to wait for the next step of the game
     */
    public void update(int updateDelay){
        matrix = transition();
        try {
                Thread.sleep(updateDelay);
            } 
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
    }
    /**
     * Compares two matrixes
     * If they are the same, return True
     * @param matrix the current matrix
     * @param nextMatrix the next step matrix
     * @return end end of the game
     */
    public boolean matrixesAreEqual(Cell[][] matrix, Cell[][] nextMatrix){
        boolean end = true;
        for(int row=0; row<rows;){
            for(int col=0; col<columns;){
                if(nextMatrix[row][col].isAlive() != matrix[row][col].isAlive()){
                    end = false;
                    row = rows;
                    col = columns;
                }
                col++;
            }
            row++;
        }
        return end;
    }
    /**
     * Return the board
     * @return b
     */
    public String toString() {
        String b = "";
        
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < columns; col++) {
                b += (matrix[row][col].isAlive()) ? "#" : "°";
                b += " ";
            } 
            b += "\n";
        }
        return b;
    }
}
