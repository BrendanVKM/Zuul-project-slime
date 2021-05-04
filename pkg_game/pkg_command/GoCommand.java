package pkg_game.pkg_command;

import java.util.ArrayList;
import java.util.Random;

import pkg_game.GameEngine;

/**
 * Classe GoCom
 *
 * VICTOIRE Brendan 04.05.2021
 */
public class GoCommand extends Command {
    /**
     * Constructor
     */
    public GoCommand() {
    } // GOCommand()

    /**
     * Execution of the Go command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        if (!pCom.hasSecondWord())
            pGameEngine.getGui().println("Go where ?");
        else {
            if (pGameEngine.getPlayer().getCurrentRoom().equals(pGameEngine.getRooms().get("somewhere"))) {
                ArrayList<String> vArray = new ArrayList<String>(pGameEngine.getRooms().keySet());
                String vRR = pGameEngine.getNotRandomRoom();
                if (vRR == null)
                    vRR = vArray.get(new Random().nextInt(vArray.size()));
                pGameEngine.getPlayer().goRandom(pGameEngine.getRooms().get(vRR));
            } else {
                String vDrct = pCom.getSecondWord();
                int vNumber = pGameEngine.getPlayer().goRoom(vDrct);
                if (vNumber == 0)
                    pGameEngine.getGui().println("There is no corridor !");
                else if (vNumber == 1) {
                    pGameEngine.getGui().println("You can't pass without the dragonSoul");
                }
                if (pGameEngine.getVeldora() != null)
                    if (pGameEngine.getPlayer().getCurrentRoom().equals(pGameEngine.getVeldora().getRoom()))
                        pGameEngine.getGui().println(pGameEngine.getVeldora().VeldoraRoom());
            }

            if (pGameEngine.getPlayer().getCurrentRoom().equals(pGameEngine.getRooms().get("meetBeatrix"))) {
                pGameEngine.getGui()
                        .println("\n" + pGameEngine.getMovingCharacter("TabbySlime").PlayerMeetSlimeWithBeatrix());
                pGameEngine.setNumberOfMeeting();
            }

            pGameEngine.slimeFollowing();
        }
    } // execute(.)
}// GoCommand