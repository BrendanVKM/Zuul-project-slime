import java.util.Stack;

public class Player {
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private ItemList aInventory;
    private UserInterface aGui;
    private Parser aParser;

    /**
     * Create the player
     */
    public Player()
    {
        this.aPreviousRooms = new Stack<Room>() ;
        this.aInventory = new ItemList();
        this.aParser = new Parser();
    } // Player()

    /**
     * set the GUI
     * @param pUserInterface the user interface
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
    } // setGUI(.)

    /**
     * @return Current room
     */
    public Room getCurrentRoom(){ return this.aCurrentRoom; } // getCurrentRoom()

    /**
     * @return Previous room
     */
    public Room getPreviousRoom(){ return this.aPreviousRooms.peek(); } // getPreviousRoom()

    /**
     * @return stack of previous rooms
     */
    public Stack<Room> getPreviousRooms(){ return this.aPreviousRooms; } // getPreviousRoom()
    /**
     * Set the current room
     * @param pCurrentRoom the current room
     */
    public void setCurrentRoom(final Room pCurrentRoom)
    {
        this.aCurrentRoom = pCurrentRoom ;
    } // setCurrentRoom(.)

    /**
     * @param vNextRoom next room
     */
    public void nextRoom( final Room pNextRoom )
    {
        this.aPreviousRooms.push( this.aCurrentRoom ); 
        this.aCurrentRoom = pNextRoom;
    } // nextRoom(.)

    /**
     * change the list of previous rooms
     */
    public void previousRooms(){
        nextRoom( this.aPreviousRooms.peek() );
        this.aPreviousRooms.pop();
    } // previousRooms(.)
    
    /**
     * print location info
     */
    void printLocationInfo()
    {
        this.aGui.println( "You were " + this.getPreviousRoom().getDescription() );
        this.aGui.println( this.aCurrentRoom.getLongDescription() );
        if ( this.aInventory.hasItem() )
            this.aGui.println( "" + this.aInventory.getItemsString() );
        if ( this.aCurrentRoom.getImageName() != null )
            this.aGui.showImage( this.aCurrentRoom.getImageName() );
    } // printLocationInfo()

    /**
     * method exected with the command word "go"
     * @param pDep command to move to the next
     */
    public void goRoom( final Command pDep )
    {
        if ( !pDep.hasSecondWord() )
            this.aGui.println( "Go where ?" );
        else {
            String vDrct = pDep.getSecondWord().toLowerCase(); 
            Room vNextRoom = this.aCurrentRoom.getExit(vDrct);
            if ( vNextRoom == null ) this.aGui.println( "There is no door !" );
            else {
                this.nextRoom( vNextRoom );
                this.printLocationInfo();
            }
        }
    } // goRoom(.)

    /**
     * go backward
     * @param pCom a command
     */
    public void goBack( final Command pCom )
    {
        if (this.aCurrentRoom.getDescription().equals( "somewhere in a cave asking yourself where is Beatrix." )
            && this.aPreviousRooms.size() == 1)
                this.aPreviousRooms.clear();

        if ( !pCom.hasSecondWord() ){
            if ( this.aPreviousRooms.empty() ) 
                this.aGui.println( "You can't go back!" );
            //if you are in the bottom of the lake you can't go back to the cave you have to go further
            else if ( this.aCurrentRoom.getDescription().equals( "deep down in a lake." ) 
                && this.getPreviousRoom().getDescription().equals( "somewhere in a cave. Be careful if you jump, you won't be able to come back here." ) )
                    this.aPreviousRooms.clear();
            else if ( this.aPreviousRooms.empty() ) 
                this.aGui.println( "That's where you wake up you can't go back anymore.");
            else {
                this.previousRooms();
                this.printLocationInfo();
            }
        }
        else this.aGui.println( "Back what ?!" );
    } // goBack(.)

    /**
     * look 
     * @param pCom a command
     */
    public void look( final Command pCom )
    {
        if ( pCom.hasSecondWord() ){
            String vSW = pCom.getSecondWord();
            this.aGui.print("You are looking at ");
            if ( this.aCurrentRoom.getItems().containsItem(vSW) ){
                this.aGui.print( "a " + this.aCurrentRoom.getItems().getItem(vSW).getItemString() + "\n");
                return;
            }
            this.aGui.println("... I don't know what you are looking at !");
        }
        else this.aGui.println( this.aCurrentRoom.getLongDescription() );
    } // look(.)

    /**
     * eat
     * @param pCom a command
     */
    public void eat( final Command pCom )
    {
        if ( !pCom.hasSecondWord() )
            this.aGui.println( "What do you want to eat ?");
        else this.aGui.println( "You have eaten now and you are not hungry any more.");
    } // eat(.)

    /**
     * take an item
     * @param pCom a command
     */
    public void take( final Command pCom )
    {
        if ( !pCom.hasSecondWord() )
            this.aGui.println( "Take what ?");
        else {
            String vSW = pCom.getSecondWord();
            if ( this.aCurrentRoom.getItems().containsItem(vSW) ){
                this.aInventory.setItem( this.getCurrentRoom().getItems().getItem(vSW) );
                this.aCurrentRoom.getItems().removeItem(vSW);
                this.aGui.println( "You take " + vSW + "\n"
                    + this.aCurrentRoom.getItems().getItemsString() + "\n"
                    +this.aInventory.getItemsString() );
            }
            else this.aGui.println( "What is this ? Is it there ?" );
        }
    } // take(.)

    /**
     * drop an item
     * @param pItem the name of the item
     */
    public void drop( final Command pCom )
    {
        if ( !pCom.hasSecondWord() )
            this.aGui.println( "Drop what ?");
        else {
            String vSW = pCom.getSecondWord();
            if ( this.aInventory.containsItem(vSW) ){
                this.aCurrentRoom.getItems().setItem( this.aInventory.getItem(vSW) );
                this.aInventory.removeItem(vSW);
                this.aGui.println( "You droped " + vSW + "\n"
                    + this.aCurrentRoom.getItems().getItemsString() + "\n"
                    + this.aInventory.getItemsString() );
            }
            else this.aGui.println( "What is this ? Do you even have this ?" );
        }
    } // drop(.)
    
    /**
     * help message
     */
    public void printHelp()
    {
        this.aGui.println( "You are lost. You are alone." );
        this.aGui.println( "You wander around at the cave.\n" );
        this.aGui.println( "Your command words are:" );
        this.aGui.println( this.aParser.getCommandString() );
    } // printHelp()

    /**
     * quit the game
     * @param pCom a command 
     * @return true if the Command doesn't have a second word
     */
    public void quit( final Command pCom )
    {
        if ( pCom.hasSecondWord() )
                this.aGui.println( "Quit what?" );
        else{
            this.aGui.println( "Thank you for playing.  Good bye." );
            this.aGui.enable( false );
        }
    } // quit(.)
} // Player
