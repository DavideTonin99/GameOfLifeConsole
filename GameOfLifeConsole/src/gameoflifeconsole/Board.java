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
        // x is the column, y is the row
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
     * @param r the row of the cell
     * @param c the column of the cell
     * @return nAliveNeighbors the number of alive neighbors of the cell
     */
    public int countNeighbors(int r, int c){
        int nAliveNeighbors = 0;
        int[][] neighbors = new int[8][2];
        int row =r;
        int col = c;

        neighbors[0][0] = --row; neighbors[0][1] = --col; 
        row = r; col = c;
        neighbors[1][0] = --row; neighbors[1][1] = col; 
        row = r; col = c;
        neighbors[2][0] = --row; neighbors[2][1] = ++col; 
        row = r; col = c; 
        neighbors[3][0] = row; neighbors[3][1] = --col; 
        row = r; col = c;
        neighbors[4][0] = row; neighbors[4][1] = ++col; 
        row = r; col = c;
        neighbors[5][0] = ++row; neighbors[5][1] = --col; 
        row = r; col = c;
        neighbors[6][0] = ++row; neighbors[6][1] = col; 
        row = r; col = c;
        neighbors[7][0] = ++row; neighbors[7][1] = ++col; 
        row = r; col = c;
        
        for(int _row=0; _row < neighbors.length; _row++){
            if(col >= 0 && col < columns && row >= 0 && row < rows){ 
                try{
                    if(matrix[neighbors[_row][0]][neighbors[_row][1]].isAlive()){
                        nAliveNeighbors ++;
                        }
                }
                catch(Exception e) {
                }
            }
        }
        return nAliveNeighbors;
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
     * Return the board
     * @return b
     */
    public String toString() {
        String b = "";
        
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < columns; col++) {
                b += (matrix[row][col].isAlive()) ? 1 : 0;
                b += " ";
            } 
            b += "\n";
        }
        return b;
    }
}
