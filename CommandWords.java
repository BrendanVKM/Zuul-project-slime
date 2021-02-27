import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau + B.VICTOIRE
 * @version 2008.03.30 + 2019.09.25 + 2021.01.31 + 2021.01.09 + 2021.02.24 + 2021.02.26
 */
public class CommandWords
{
    private HashMap<String, CommandWord> aValidCommands;

    /**
     * Constructor
     */
    public CommandWords()
    {
        this.aValidCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values())
            if(command != CommandWord.UNKNOWN)
                this.aValidCommands.put(command.toString(), command);
    } // CommandWords()

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *      if it is not a valid command word.
     */
    public CommandWord getCommandWord( final String commandWord )
    {
        CommandWord command = this.aValidCommands.get( commandWord );
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    } // getCommandWord(.)

    /**
     * Check whether a given String is a valid command word. 
     * @param pString a Command word to check
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand( final String pString )
    {
        return this.aValidCommands.containsKey( pString );
    } // isCommand(.)
    
    /**
     * acces to all the valid commands words
     * @return all possible command words
     */
    public String getCommandList()
    {
        String vList = "";
        for(String vCom : this.aValidCommands.keySet()) {
            vList += vCom + " ";
        }
        return vList;
    } // showAll()
} // CommandWords