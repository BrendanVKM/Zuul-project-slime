package pkg_game.pkg_command;

import java.util.StringTokenizer;

/**
 * This class is part of "World of Zuul". "World of Zuul" is a simple, text
 * based adventure game.
 *
 * This parser takes user input and tries to interpret it as a "Zuul" command.
 * Every time it is called it takes a line as a String and tries to interpret
 * the line as a two word command. It returns the command as an object of class
 * Command.
 *
 * The parser has a set of known command words. It checks user input against the
 * known commands, and if the input is not one of the known commands, it returns
 * a command object that is marked as an unknown command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2.0 (Jan 2003) DB edited (2019) + BV (feb 2021)
 */

public class Parser {

    private CommandWords aCW; // holds all valid command words

    /**
     * Create a new Parser.
     */
    public Parser() {
        this.aCW = new CommandWords();
    } // Parser()

    /**
     * Get a new command from the user. The command is read by parsing the
     * 'inputLine'.
     * 
     * @param pInputLine the input line in a string format
     * @return the command if there is one available
     */
    public Command getCommand(final String pInputLine) {
        String vWord1 = null;
        String vWord2 = null;

        // Find up to two words on the line.
        StringTokenizer tokenizer = new StringTokenizer(pInputLine);
        if (tokenizer.hasMoreTokens()) {
            vWord1 = tokenizer.nextToken(); // get first word
            if (tokenizer.hasMoreTokens()) {
                vWord2 = tokenizer.nextToken(); // get second word
                // note: we just ignore the rest of the input line.
            }
        }
        Command vCommand = this.aCW.getCommandWord(vWord1);
        vCommand.setSecondWord(vWord2);
        return vCommand;
    } // getCommand(.)

    /**
     * access to all possible command words
     * 
     * @return all possible command words
     */
    public String getCommandString() // was showCommands()
    {
        return this.aCW.getCommandList();
    } // getCommandString()

} // Parser
