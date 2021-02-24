
/**
 * be able to play
 * @author Brendan VICTOIRE
 * @version 2021.01.20 + 2021.02.08 + 2021.02.09
 */
public class Game
{
    private UserInterface aGui;
    private GameEngine aGameEngine;

    /**
     * default Constructor
     */
    public Game() 
    {
        this.aGameEngine = new GameEngine();
        this.aGui = new UserInterface( this.aGameEngine );
        this.aGameEngine.setGUI( this.aGui );
    } // Game()

    //Pour VSC
    public static void main(String[] args) {
        System.out.print ( new Game() );
    } // main()
}// Game
