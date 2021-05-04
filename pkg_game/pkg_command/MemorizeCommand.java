package pkg_game.pkg_command;

import pkg_game.GameEngine;

public class MemorizeCommand extends Command {
    /**
     * Execution of the Memorize command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        if (pGameEngine.getPlayer().getInventory().containsItem("teleportation"))
            if (!pCom.hasSecondWord()) {
                if (pGameEngine.getPlayer().memorize())
                    pGameEngine.getGui().println("You already used teleport!");
                else {
                    pGameEngine.getPlayer().memorize();
                    pGameEngine.getGui().println("Room memorized.");
                }
            } else
                pGameEngine.getGui().println("You can only memorize your current room");
        else
            pGameEngine.getGui().println("You cannot use that at the moment");
    } // execute(.)
} // MemorizeCommand
