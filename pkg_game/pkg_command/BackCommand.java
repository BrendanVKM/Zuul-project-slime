package pkg_game.pkg_command;

import pkg_game.GameEngine;

public class BackCommand extends Command {
    /**
     * Execution of the Back command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        if (!pCom.hasSecondWord()) {
            if (pGameEngine.getPlayer().getPreviousRoom() == null)
                pGameEngine.getGui().println("You can't go back!");
            else if (pGameEngine.getPlayer().getPreviousRoom().getImageName().equals(""))
                pGameEngine.getGui().println("You were" + pGameEngine.getPlayer().getPreviousRoom().getDescription());
            else if (pGameEngine.getPlayer().getCurrentRoom().equals(pGameEngine.getRooms().get("bottomOfLake"))
                    && pGameEngine.getPlayer().getPreviousRoom().equals(pGameEngine.getRooms().get("cave7"))) {
                pGameEngine.getPlayer().getPreviousRooms().clear();
                pGameEngine.getGui().println("You were warned, you can't go back after jumping here.");
            } else if (pGameEngine.getPlayer().getCurrentRoom().equals(pGameEngine.getRooms().get("cave5"))
                    && pGameEngine.getPlayer().getPreviousRoom().equals(pGameEngine.getRooms().get("cave8"))) {
                pGameEngine.getPlayer().getPreviousRooms().clear();
                pGameEngine.getGui().println("You can't go back after falling here.");
            } else {
                pGameEngine.getPlayer().goBack();
                pGameEngine.slimeFollowing();
            }
        } else
            pGameEngine.getGui().println("Back what ?!");
    } // execute(.)
} // BackCommand
