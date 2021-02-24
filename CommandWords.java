
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau + B.VICTOIRE
 * @version 2008.03.30 + 2019.09.25 + 2021.01.31 + 2021.01.09
 */
public class CommandWords
{
    // a constant array that will hold all valid command words
    private static final String[] aValidCommands = { 
        "go", "back", "look", "eat", "take", "drop", "help", "quit", "test"
    };
    

    /**
     * Check whether a given String is a valid command word. 
     * @param pString a Command word to check
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand( final String pString )
    {
        for ( int i=0; i< CommandWords.aValidCommands.length; i++) {
            if ( CommandWords.aValidCommands[i].equals(pString))
                return true;
        } // for
        // if we get here, the string was not found in the commands
        return false;
    } // isCommand(.)
    
    /**
     * @return all possible command word
     */
    public String getCommandList()
    {
        String vList = "";
        for ( String vCom : aValidCommands ){
            vList += vCom + " ";
        }
        return vList;
    } // showAll()
} // CommandWords