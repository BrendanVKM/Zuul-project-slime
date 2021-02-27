
/**
 * Representations for all the valid command words
 * @author Brendan VICTOIRE
 * @version 2021.02.25
 */
public enum CommandWord {
    UNKNOWN("?"), GO("go"), BACK("back"), MEMORIZE("memorize"), 
    TELEPORT("teleport"),LOOK("look"), ITEMS("items"), 
    TAKE("take"), EAT("eat"), DROP("drop"), HELP("help"), 
    QUIT("quit"), TEST("test");

    // The command string.
    private String commandString;

    /**
      * Initialise with the corresponding command word.
      * @param commandWord The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    /**
    * @return The command word as a string.
    */
    public String toString()
    {
        return commandString;
    }
}
