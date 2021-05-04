package pkg_game.pkg_command;

import pkg_game.GameEngine;

public class TakeCommand extends Command {
    /**
     * Execution of the Memorize command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        if (!pCom.hasSecondWord())
            pGameEngine.getGui().println("Absorb what ?");
        else {
            String vSW = pCom.getSecondWord();
            int vNumber = pGameEngine.getPlayer().take(vSW);
            if (vNumber == 0)
                pGameEngine.getGui().println("You don't have enough space to absorb this.");
            else if (vNumber == 1) {
                if (pGameEngine.getPlayer().getCurrentRoom().equals(pGameEngine.getRooms().get("onMagistone"))
                        && vSW.equals("magistone")) {
                    pGameEngine.getPlayer().getCurrentRoom().setImage("takeMagistone");
                    pGameEngine.getGui().showImage(pGameEngine.getPlayer().getCurrentRoom().getImageName());
                }
                pGameEngine.getGui()
                        .println("You absorb " + vSW + "\n" + pGameEngine.getPlayer().getCurrentRoom().getItemsString()
                                + "\n" + pGameEngine.getPlayer().getInventoryString());
            } else if (vNumber == 3) {
                if (pGameEngine.getVeldora() != null) {
                    pGameEngine.getPlayer().getInventory()
                            .setItem(pGameEngine.getVeldora().getItems().getItem("dragonSoul"));
                    pGameEngine.getGui().println(pGameEngine.getVeldora().PlayerAbsorbVeldora()
                            + "\n You have acquired the sould of Veldora.");
                    pGameEngine.nullVeldora();
                }
            } else
                pGameEngine.getGui().println("What is this ? Is it there ?");
        }
    } // execute(.)
} // TakeCommand