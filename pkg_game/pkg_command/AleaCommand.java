package pkg_game.pkg_command;

import pkg_game.GameEngine;

public class AleaCommand extends Command {
    /**
     * Execution of the Memorize command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        if (!pGameEngine.getTestMode())
            pGameEngine.getGui().println("You need to be in the test mode!");
        else if (!pCom.hasSecondWord()) {
            pGameEngine.setNotRandomRoom(null);
            pGameEngine.getGui().println("the randomness of the transporter room has been reset.");
        } else
            pGameEngine.setNotRandomRoom(pCom.getSecondWord());
    } // execute(.)
} // AleaCommand