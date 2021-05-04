package pkg_game.pkg_command;

import pkg_game.GameEngine;

public class DropCommand extends Command {
    /**
     * Execution of the Memorize command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        if (!pCom.hasSecondWord())
            pGameEngine.getGui().println("Drop what ?");
        else {
            String vSW = pCom.getSecondWord();
            if (pGameEngine.getPlayer().drop(vSW)) {
                pGameEngine.getPlayer().drop(vSW);
                pGameEngine.getGui().println("You droped " + vSW + "\n" + pGameEngine.getPlayer().getCurrentRoom().getItemsString() + "\n"
                        + pGameEngine.getPlayer().getInventoryString());
            } else
                pGameEngine.getGui().println("What is this ? Do you even have this ?");
        }
    } // execute(.)
} // DropCommand