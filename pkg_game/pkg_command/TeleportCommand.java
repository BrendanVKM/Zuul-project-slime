package pkg_game.pkg_command;

import pkg_game.GameEngine;

public class TeleportCommand extends Command {
    /**
     * Execution of the Memorize command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        if (pGameEngine.getPlayer().getInventory().containsItem("teleportation")) {
            if (!pCom.hasSecondWord()) {
                if (pGameEngine.getPlayer().getBeamer().isUsed())
                    pGameEngine.getGui().println("You already used it");
                else {
                    if (pGameEngine.getPlayer().teleport())
                        pGameEngine.getPlayer().teleport();
                    else
                        pGameEngine.getGui().println("You need to memorize a room");
                }
            } else
                pGameEngine.getGui().println("You can only teleport to your memorized room");
        } else
            pGameEngine.getGui().println("You cannot use that at the moment");
    } // execute(.)
} // TeleportCommand
