package pkg_game.pkg_command;

import pkg_game.GameEngine;

public class HelpCommand extends Command {
    /**
     * Execution of the Memorize command
     */
    @Override
    public void execute(final Command pCom, final GameEngine pGameEngine) {
        pGameEngine.getGui().println("You are lost. You are alone.");
        pGameEngine.getGui().println("You wander around at the cave.\n");
        pGameEngine.getGui().println("Your command words are:");
        pGameEngine.getGui().println(pGameEngine.getParser().getCommandString());
    } // execute(.)
} // HelpCommand