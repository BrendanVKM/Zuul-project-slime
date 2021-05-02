
import pkg_game.GameEngine;
import pkg_game.UserInterface;

/**
 * launch the game
 * 
 * @author Brendan VICTOIRE
 * @version 2021.01.20 + 2021.02.08 + 2021.02.23
 */
public class Game {
    private UserInterface aGui;
    private GameEngine aGameEngine;

    /**
     * launches the game with BlueJ
     */
    public Game() {
        this.aGameEngine = new GameEngine();
        this.aGui = new UserInterface(this.aGameEngine);
        this.aGameEngine.setGUI(this.aGui);
        // timer
        long vStartTime = System.currentTimeMillis();
        long vTimePast = 0;
        while (vTimePast < 30 * 60 * 1000)
            vTimePast = System.currentTimeMillis() - vStartTime;
        if (this.aGui.isDisabled())
            return;
        this.aGui.println("You have taken to much time!");
        this.aGameEngine.interpretCommand("quit");
    } // Game()

    /**
     * launches the game
     * 
     * @param args because it is mandatory
     */
    public static void main(String[] args) {
        System.out.print(new Game());
    } // main()
}// Game
