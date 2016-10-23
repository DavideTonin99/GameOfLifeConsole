package gameoflifeconsole;

/**
 * Avvia il gioco Game of life
 * @author Tonin Davide davide9935@gmail.com
 * @version 1.0 2016-10-21
 */

public class GameOfLifeConsoleApp {
    
    public static void main(String[]args){
        int updateDelay = 300;
        int rows = 10;
        int columns = 40;
        int numberOfAliveCells = 200;
        
        Board board = new Board(rows, columns);
        System.out.println("New board: " + "\n" + board);
        
        board.init(numberOfAliveCells);
        System.out.println("Initialized board: " + "\n" + board);
        
        System.out.println("Game: ");
        for(;;){
            board.update(updateDelay);
            System.out.println(board);
        }
    }
}
