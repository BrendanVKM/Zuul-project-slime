package pkg_game.pkg_command;

/**
 * Representations for all the valid command words
 * 
 * @author Brendan VICTOIRE
 * @version 2021.02.25
 */
public enum CommandWord {
    UNKNOWN("?", null), GO("go", new GoCommand()), BACK("back", new BackCommand()),
    MEMORIZE("memorize", new MemorizeCommand()), TELEPORT("teleport", new TeleportCommand()),
    LOOK("look", new LookCommand()), ITEMS("items", new ItemsCommand()), TAKE("absorb", new TakeCommand()),
    EAT("eat", new EatCommand()), DROP("drop", new DropCommand()), HELP("help", new HelpCommand()),
    QUIT("quit", new QuitCommand()), TEST("test", new TestCommand()), ALEA("alea", new AleaCommand()),;

    // The command string.
    private String aCommandString;
    private Command aCommand;

    /**
     * Initialise with the corresponding command word.
     * 
     * @param pCommandString The command string.
     * @param pCommand the command 
     */
    CommandWord(final String pCommandString, final Command pCommand) {
        this.aCommandString = pCommandString;
        this.aCommand = pCommand;
    }

    /**
     * access to the command
     * 
     * @return the command
     */
    public Command getCommand() {
        return this.aCommand;
    } // getCommand()

    /**
     * @return The command word as a string.
     */
    public String toString() {
        return aCommandString;
    } // toString
}
