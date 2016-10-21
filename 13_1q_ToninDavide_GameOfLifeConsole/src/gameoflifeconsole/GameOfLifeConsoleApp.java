package gameoflifeconsole;

/**
 * Avvia il gioco Game of life
 * @author Tonin Davide
 * @version 3.0 2016-10-21
 */

public class GameOfLifeConsoleApp {
    
    public static void main(String[]args){
        int updateDelay = 1000;
        
        Board board = new Board(9, 16);
        System.out.println("New board: " + "\n" + board);
        
        board.init(100);
        System.out.println("Initialized board: " + "\n" + board);
        
        System.out.println("Game: ");
        for(;;){
            board.update(updateDelay);
            System.out.println(board);
        }
    }
}
