package pkg_game.pkg_command;

import pkg_game.GameEngine;

public class QuitCommand extends Command {
    /**
     * Execution of the Memorize command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        if (pCom.hasSecondWord())
            pGameEngine.getGui().println("Quit what?");
        else {
            pGameEngine.getGui().println("Thank you for playing. Good bye.");
            pGameEngine.getGui().enable(false);
        }
    } // execute(.)
} // QuitCommand