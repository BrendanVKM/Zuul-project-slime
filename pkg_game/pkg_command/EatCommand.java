package pkg_game.pkg_command;

import pkg_game.GameEngine;

public class EatCommand extends Command {
    /**
     * Execution of the Memorize command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        if (!pCom.hasSecondWord())
            pGameEngine.getGui().println("What do you want to eat ?");
        else {
            String vSW = pCom.getSecondWord();
            int vNumber = pGameEngine.getPlayer().eat(vSW);
            if (vNumber == 0)
                pGameEngine.getGui().println("You have eaten the Stone. Now you can carry more items.");
            else if (vNumber == 1)
                pGameEngine.getGui().println("You have eaten now and you are not hungry any more.");
            else
                pGameEngine.getGui().println("You don't carry this... if this exist.");
        }
    } // execute(.)
} // EatCommand