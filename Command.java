
/**
 * commands
 * @author Brendan VICTOIRE
 * @version 2021.01.18
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     * Constructor
     * @param a command
     * @param instruction
     */
    public Command(final String pCommand, final String pSecond)
    {
        this.aCommandWord = pCommand;
        this.aSecondWord = pSecond;
    } // Command()
    
    /**
     * access to a command
     * @return a command
     */
    public String getCommandWord() {return this.aCommandWord; } // getCommand()
    
    /**
     * access to the instruction
     * @return the instruction
     */
    public String getSecondWord() { return this.aSecondWord; } //getSecond()
    
    /**
     * Verify the instruction existence
     * @return true if there is a second instruction
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord != null;
    } // hasSecondWord()
    
    /**
     * Verify the command existence
     * @return true if the command is unknown
     */
    public boolean isUnknown()
    {
        return this.aCommandWord == null;
    } // isUnknown
} // Command
