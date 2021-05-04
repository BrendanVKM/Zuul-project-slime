package pkg_game.pkg_command;

import pkg_game.GameEngine;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 *
 * This class holds information about a command that was issued by the user. A
 * command currently consists of two parts: a CommandWord and a string (for
 * example, if the command was "take map", then the two parts are TAKE and
 * "map").
 * 
 * The way this is used is: Commands are already checked for being valid command
 * words. If the user entered an invalid command (a word that is not known) then
 * the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is null.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public abstract class Command {
    private CommandWord aCommandWord;
    private String aSecondWord;

    /**
     * Create a command object. First and second words must be supplied, but the
     * second may be null.
     */
    public Command() {
        this.aCommandWord = null;
        this.aSecondWord = null;
    } // Command(.)

    /**
     * Return the command word (the first word) of this command.
     * 
     * @return The command word.
     */
    public CommandWord getCommandWord() {
        return aCommandWord;
    } // getCommandWord()

    /**
     * @return The second word of this command. Returns null if there was no second
     *         word.
     */
    public String getSecondWord() {
        return aSecondWord;
    } // getSecondWord()

    /**
     * Set the second word of this command.
     * 
     * @param pSecondWord second word of this command.
     */
    public void setSecondWord(String pSecondWord) {
        this.aSecondWord = pSecondWord;
    } // setSecondWord(.)

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown() {
        return (aCommandWord == CommandWord.UNKNOWN);
    } // isUnknown()

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord() {
        return (aSecondWord != null);
    } // hasSecondWord()

    /**
     * Define the execution of the command.
     * 
     * @param pCom the command to execute
     * @param pGameEngine game engine
     */
    public abstract void execute(final Command pCom, final GameEngine pGameEngine);
}