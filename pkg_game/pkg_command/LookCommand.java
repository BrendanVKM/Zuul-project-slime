package pkg_game.pkg_command;

import pkg_game.GameEngine;

public class LookCommand extends Command {
    /**
     * Execution of the Memorize command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        if (pCom.hasSecondWord()) {
            String vSW = pCom.getSecondWord();
            pGameEngine.getGui().print("You are looking at ");
            if (pGameEngine.getPlayer().look(vSW))
                pGameEngine.getGui().print("a " + pGameEngine.getPlayer().getCurrentRoom().getItems().getItem(vSW).getItemString() + "\n");
            else
                pGameEngine.getGui().println("... I don't know what you are looking at! Is this in here?");
        } else {
            if (pGameEngine.getPlayer().getCurrentRoom().getImageName().equals("cave6"))
                pGameEngine.getPlayer().changeImage("lookAtTheCave");
            pGameEngine.getGui().println(pGameEngine.getPlayer().getCurrentRoom().getLongDescription());
        }
    } // execute(.)
} // LookCommand