package pkg_game.pkg_command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pkg_game.GameEngine;

public class TestCommand extends Command {
    /**
     * Execution of the Memorize command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        if (!pCom.hasSecondWord()) {
            pGameEngine.getGui().println("Test What !?");
            return;
        }
        Scanner vScanner;
        try {
            vScanner = new Scanner(new File("./test/" + pCom.getSecondWord() + ".txt"));
            pGameEngine.setTestMode(true);
            while (vScanner.hasNextLine()) {
                String vLigne = vScanner.nextLine();
                pGameEngine.interpretCommand(vLigne);
            }
            pGameEngine.setTestMode(false);
        } catch (final FileNotFoundException pFNFE) {
            pGameEngine.getGui().println("File not found.");
        }
    } // execute(.)
} // TestCommand