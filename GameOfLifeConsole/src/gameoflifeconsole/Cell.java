package gameoflifeconsole;

/**
 * Rappresenta una cella del gioco Game of life
 * 
 */
public class Cell {
    private boolean alive;
    private int col;
    private int row;
    
    /**
     * Costruttore di base
     * Una cella inizialmente è morta
     * @param col colonna
     * @param row riga
     */
    public Cell(int row, int col) {
        this.col = col;
        this.row = row;
        alive = false;
    }
    /**
     * Ritorna il valore di alive
     * @return alive
     */
    public boolean isAlive() {
        return alive;
    }
    /**
     * Setta il valore di alive
     * @param alive 
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    /**
     * Ritorna il valore di col
     * @return col
     */
    public int getCol() {
        return col;
    }
    /**
     * Ritorna il valore di row
     * @return row
     */
    public int getRow() {
        return row;
    }
    
}
