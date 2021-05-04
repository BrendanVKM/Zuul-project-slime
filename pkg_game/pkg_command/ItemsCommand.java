package pkg_game.pkg_command;

import pkg_game.GameEngine;

public class ItemsCommand extends Command {
    /**
     * Execution of the Memorize command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        if (pCom.hasSecondWord())
            pGameEngine.getGui().println("Items what?");
        else
            pGameEngine.getGui().println(pGameEngine.getPlayer().Items());
    } // execute(.)
} // ItemsCommand