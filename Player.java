import java.util.Stack;

/**
 * create and modify the player and his inventory
 * @author Brendan VICTOIRE
 * @version 2021.02.23 + 2021.02.25 +2021.02.26
 */
public class Player {
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private ItemList aInventory;
    private UserInterface aGui;
    private Parser aParser;
    private Beamer aBeamer;
    private int ENTERED_COMMAND = 35;
    private double MAX_WEIGHT = 1e10; // a slime can carry anything

    /**
     * Create the player
     * default constructor
     */
    public Player()
    {
        this.aPreviousRooms = new Stack<Room>() ;
        this.aInventory = new ItemList();
        this.aParser = new Parser();
        this.aBeamer = new Beamer();
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
     * access the current room
     * @return Current room
     */
    public Room getCurrentRoom(){ return this.aCurrentRoom; } // getCurrentRoom()

    /**
     * 
     * access the previous room
     * @return Previous room
     */
    public Room getPreviousRoom(){ return this.aPreviousRooms.peek(); } // getPreviousRoom()

    /**
     * access stack of previous rooms 
     * @return stack of previous rooms
     */
    public Stack<Room> getPreviousRooms(){ return this.aPreviousRooms; } // getPreviousRoom()
    
    /**
     * print the content of the invetory
     * @return inventory under String format
     */
    public StringBuilder getInventoryString()
    {
        StringBuilder vInventory = new StringBuilder( "" );
        if (this.aInventory.hasItem() ){
            vInventory.append( "Iventory:" );
            for ( String vItem : this.aInventory.getIventory().keySet() )
            vInventory.append( " " + vItem );
        }
        return vInventory;
    } // getInventoryString()

    /**
     * Set the current room
     * @param pCurrentRoom the current room
     */
    public void setCurrentRoom(final Room pCurrentRoom)
    {
        this.aCurrentRoom = pCurrentRoom ;
    } // setCurrentRoom(.)

    /**
     * set the next room as the current room
     * @param pNextRoom next room
     */
    public void setNextRoom( final Room pNextRoom )
    {
        this.aPreviousRooms.push( this.aCurrentRoom ); 
        this.aCurrentRoom = pNextRoom;
    } // setNextRoom(.)

    /**
     * change the list of previous rooms
     */
    public void setPreviousRooms(){
        this.setNextRoom( this.aPreviousRooms.peek() );
        this.aPreviousRooms.pop();
        this.aPreviousRooms.pop();
    } // setPreviousRooms(.)
    
    /**
     * print location info (current room description + inventory of this player)
     */
    void printLocationInfo()
    {
        this.aGui.println( "You were " + this.getPreviousRoom().getDescription() );
        this.aGui.println( this.aCurrentRoom.getLongDescription() );
        if ( this.aInventory.hasItem() )
            this.aGui.println( "" + this.aCurrentRoom.getItemsString() );
        if ( this.aCurrentRoom.getImageName() != "" )
            this.aGui.showImage( this.aCurrentRoom.getImageName() );
    } // printLocationInfo()

    /**
     * limit the number of command that a player can type before force ending
     * @return true if the limit is reached
     */
    public boolean commandCounter()
    {
        this.ENTERED_COMMAND--;
        return this.ENTERED_COMMAND < 0;
    }
    /**
     * method executed with the command word "go"
     * let you go forward
     * @param pDep command to move to the next
     */
    public void goRoom( final Command pDep )
    {
        if ( !pDep.hasSecondWord() )
            this.aGui.println( "Go where ?" );
        else {
            String vDrct = pDep.getSecondWord(); 
            Room vNextRoom = this.aCurrentRoom.getExit(vDrct);
            Door vDoor = this.getCurrentRoom().getDoors(vDrct);
            if ( vNextRoom == null ) this.aGui.println( "There is no corridor !" );
            else {
                if ( vDoor != null && !vDoor.isOpen()){
                    if ( !this.aInventory.containsItem("dragonSoul") ){
                        this.aGui.println( "You can't pass without the dragonSoul");
                        return;
                    }
                    vDoor.setOpen(true);
                }
                this.setNextRoom( vNextRoom );
                this.printLocationInfo();
            }
        }
    } // goRoom(.)

    /**
     * method executed with the command word "back"
     * let you go backward
     * @param pCom a command
     */
    public void goBack( final Command pCom )
    {
        if ( !pCom.hasSecondWord() ){
            if ( this.getPreviousRoom().getImageName().equals( "" )) 
                this.aGui.println( "You were" + this.getPreviousRoom().getDescription() );
            //if you are in the bottom of the lake you can't go back to the cave you have to go further
            else if ( this.aCurrentRoom.getImageName().equals( "bottomOfLake" ) 
                && this.getPreviousRoom().getImageName().equals( "cave7" ) ){
                    this.aPreviousRooms.clear();
                    this.aGui.println( "You were warned, you can't go back after jumping here." );
                }
            else if ( this.aCurrentRoom.getImageName().equals( "cave5" ) 
                && this.getPreviousRoom().getImageName().equals( "cave8" ) ){
                    this.aPreviousRooms.clear();
                    this.aGui.println( "You can't go back after falling here." );
                }
            else {
                this.setPreviousRooms();
                this.printLocationInfo();
            }
        }
        else this.aGui.println( "Back what ?!" );
    } // goBack(.)

    /**
     * charged the beamer with the current room
     * @param pCom a command
     */
    public void memorize( final Command pCom )
    {
        if ( this.aInventory.containsItem("teleportation") ){
            if ( !pCom.hasSecondWord() ){
                    if ( this.aBeamer.isUsed() )
                        this.aGui.println( "You already used teleport!" );
                    else {
                        this.aBeamer.charge( this.aCurrentRoom );
                        this.aGui.println( "Room memorized.");
                    }
            }
            else this.aGui.println( "You can only memorize your current room" );
        }
        else this.aGui.println( "You cannot use that at the moment");
    } // memorize(.)

    /**
     * teleport the player to the memorized room
     * @param pCom a command
     */
    public void teleport( final Command pCom )
    {
        if ( this.aInventory.containsItem("teleportation") ){
            if ( !pCom.hasSecondWord() ){
                    if ( this.aBeamer.isUsed() )
                        this.aGui.println( "You already used it");
                    else {
                        if ( this.aBeamer.isCharged() ){
                            this.aCurrentRoom = this.aBeamer.getMemorizedRoom();
                            this.aBeamer.fire();
                            this.printLocationInfo();
                        }
                        else this.aGui.println( "You need to memorize a room" );
                    }
            }
            else this.aGui.println( "You can only teleport to your memorized room" );
        }
        else this.aGui.println( "You cannot use that at the moment" );
    } // teleport(.)

    /**
     * method executed with the command word "look"
     * print all the information of the room or the item that you are looking at
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
     * method executed with the command word "eat"
     * if possible eat an item from your inventory
     * @param pCom a command
     */
    public void eat( final Command pCom )
    {
        if ( !pCom.hasSecondWord() )
            this.aGui.println( "What do you want to eat ?");
        else {
            String vSW = pCom.getSecondWord();
            if ( this.aInventory.containsItem(vSW) ){
                if ( vSW.equals("dragonSoul") ){
                    this.aGui.println( "You have eaten the Veldora's soul. Now you can carry more items." );
                    this.MAX_WEIGHT *= 1e10;
                }
                else this.aGui.println( "You have eaten now and you are not hungry any more.");
            this.aInventory.removeItem(vSW);
            }
            else this.aGui.println( "You don't carry this ");
        }
    } // eat(.)

    /**
     * method executed with the command word "items" 
     * print a list of the item carried by the player
     * @param pCom a command
     */
    public void Items( final Command pCom )
    {
        if ( pCom.hasSecondWord())
            this.aGui.println( "Items what?" );
        else if ( this.aInventory.hasItem()){
            StringBuilder vInventory = new StringBuilder( " " );
            if (this.aInventory.hasItem() ){
                vInventory.append( "Iventory:" );
                for ( String vItem : this.aInventory.getIventory().keySet() )
                    vInventory.append( " " + vItem + " (" + this.aInventory.getItem(vItem).getWeight() + ")" );
                this.aGui.println( "" + vInventory + " for a total of "+ this.aInventory.getWeightCarrying() + "kg" );
            }
            else this.aGui.println( "" + vInventory );
        }
        else this.aGui.println( "You aren't carrying anything." );
    } // Items()

    /**
     * method executed with the command word "take" 
     * if possible take an item and put it in the inventory of the player
     * @param pCom a command
     */
    public void take( final Command pCom )
    {
        if ( !pCom.hasSecondWord() )
            this.aGui.println( "Take what ?");
        else {
            String vSW = pCom.getSecondWord();
            if ( this.aCurrentRoom.getItems().containsItem(vSW) ){
                if ( this.aInventory.getWeightCarrying() < this.MAX_WEIGHT)
                    if ( ( this.aInventory.getWeightCarrying() + this.aCurrentRoom.getItems().getItem(vSW).getWeight() ) > this.MAX_WEIGHT )
                        this.aGui.println( "You don't have enough space to take this");
                    else {
                        this.aInventory.setItem( this.getCurrentRoom().getItems().getItem(vSW) );
                        this.aCurrentRoom.getItems().removeItem(vSW);
                        this.aGui.println( "You take " + vSW + "\n"
                            + this.aCurrentRoom.getItemsString() + "\n"
                            +this.getInventoryString() );
                    }
            }
            else this.aGui.println( "What is this ? Is it there ?" );
        }
    } // take(.)

    /**
     * method executed with the command word "drop" 
     * if possible drop an item on the floor of the room
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
                    + this.aCurrentRoom.getItemsString() + "\n"
                    + this.getInventoryString() );
            }
            else this.aGui.println( "What is this ? Do you even have this ?" );
        }
    } // drop(.)
    
    /**
     * method executed with the command word "help" 
     * print help message
     */
    public void printHelp()
    {
        this.aGui.println( "You are lost. You are alone." );
        this.aGui.println( "You wander around at the cave.\n" );
        this.aGui.println( "Your command words are:" );
        this.aGui.println( this.aParser.getCommandString() );
    } // printHelp()

    /**
     * method executed with the command word "quit" 
     * if right executed quit the game
     * @param pCom a command 
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
