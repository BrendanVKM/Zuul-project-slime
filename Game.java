
/**
 * launch the game
 * @author Brendan VICTOIRE
 * @version 2021.01.20 + 2021.02.08 + 2021.02.23
 */
public class Game
{
    private UserInterface aGui;
    private GameEngine aGameEngine;

    /**
     * launch the game with BlueJ
     */
    public Game() 
    {
        this.aGameEngine = new GameEngine();
        this.aGui = new UserInterface( this.aGameEngine );
        this.aGameEngine.setGUI( this.aGui );
    } // Game()

    /**
     * launch the game with VS code
     * @param args because it is mandatory
     */
    public static void main(String[] args) {
        System.out.print ( new Game() );
    } // main()
}// Game
