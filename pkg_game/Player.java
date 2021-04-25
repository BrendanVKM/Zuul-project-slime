package pkg_game;

import java.util.Stack;
import pkg_game.pkg_item.ItemList;
import pkg_game.pkg_item.Item;
import pkg_game.pkg_item.Beamer;

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
    public Room getPreviousRoom(){ 
        if ( this.aPreviousRooms.empty() ) return null;
        return this.aPreviousRooms.peek(); 
    } // getPreviousRoom()

    /**
     * access stack of previous rooms 
     * @return stack of previous rooms
     */
    public Stack<Room> getPreviousRooms(){ return this.aPreviousRooms; } // getPreviousRoom()
    
    /**
     * access to the player inventory
     * @return the player inventory
     */
    public ItemList getInventory(){ return this.aInventory; } // getInventory()

    /**
     * acces to the beamer
     * @return the beamer
     */
    public Beamer getBeamer(){ return this.aBeamer; } // getBeamer()

    /**
     * acces to the total weight of the ItemList
     * @return the weight the player is carrying
     */
    public double getWeightCarrying()
    {
        if ( this.aInventory.hasItem() ){
            double vWeight = 0;
            for ( Item vItem : this.aInventory.getIventory().values())
                vWeight += vItem.getWeight();
            return vWeight;
        }
        else return 0;
    } // getWeightCarrying()

    /**
     * print the content of the invetory
     * @return inventory under String format
     */
    public StringBuilder getInventoryString()
    {
        StringBuilder vInventory = new StringBuilder( " " );
                if (this.aInventory.hasItem() ){
                    vInventory.append( "Iventory:" );
                for ( String vItem : this.aInventory.getIventory().keySet() )
                    vInventory.append( " " + vItem + " (" + this.aInventory.getItem(vItem).getWeight() + ")" );
                return vInventory.append( "\nfor a total of "+ this.getWeightCarrying() + "kg" );
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
    public void printLocationInfo()
    {
        this.aGui.println( "You were " + this.getPreviousRoom().getDescription() );
        this.aGui.println( this.aCurrentRoom.getLongDescription() );
        if ( this.aInventory.hasItem() )
            this.aGui.println( "" + this.getInventoryString() );
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
    } // commandCounter()

    /**
     * go method
     * @param pDrct the direction
     * @return 1, 2 or 3 depending on the direction
     */
    public int goRoom( final String pDrct )
    {
        Room vNextRoom = this.aCurrentRoom.getExit(pDrct);
        Door vDoor = this.getCurrentRoom().getDoor(pDrct);
        int vNumber = 2;
        if ( vNextRoom == null ) 
            vNumber = 0; 
        else {
            if ( vDoor != null && !vDoor.isOpen()){
                if ( !this.aInventory.containsItem( vDoor.getKey().getName() ) ){
                    return 1;
                }
                vDoor.setOpen(true);
            }
            this.setNextRoom( vNextRoom );
            this.printLocationInfo();
        }
        return vNumber;
    } // goRoom(.)

    /**
    * go method (for random access)
    * @param pRoom the random room you end up
    */
    public void goRandom( final Room pRoom )
    {
        this.setNextRoom( pRoom );
        this.printLocationInfo();
   } // goRandom(.)

    /**
     * back method 
     */
    public void goBack()
    {
        this.setPreviousRooms();
        this.printLocationInfo();
    } // goBack(.)

    /**
     * memorize method 
     * @return true if the beamer has been used, false otherwise
     */
    public boolean memorize()
    {
        if ( this.aBeamer.isUsed() )
            return true;
        this.aBeamer.charge( this.aCurrentRoom );
        return false;
    } // memorize(.)

    /**
     * teleport the player to the memorized room
     * @return true if the beamer is charged, false otherwise
     */
    public boolean teleport()
    {
        if ( this.aBeamer.isCharged() ){
            this.aCurrentRoom = this.aBeamer.getMemorizedRoom();
            this.aBeamer.fire();
            this.printLocationInfo();
            return true;
        }
        return false; 
    } // teleport(.)

    /**
     * look method
     * @param pSW the name of the item
     * @return true if the item is in the room, false otherwise
     */
    public boolean look( final String pSW )
    {
        
        return this.aCurrentRoom.containsItem(pSW);
    } // look(.)

    /**
     * eat method
     * @param pSW the name of the item
     * @return 0, 1 or 2 depending on the situation 
     */
    public int eat( final String pSW )
    {
        int vNumber = 2;
        if ( this.aInventory.containsItem(pSW) ){
            if ( pSW.equals("stone") ){
                this.MAX_WEIGHT *= 1e10;
                vNumber = 0;
            }
            else vNumber = 1;
            this.MAX_WEIGHT -= aInventory.getItem(pSW).getWeight();
            this.aInventory.removeItem(pSW);
        }
        return vNumber;
    } // eat(.)

    /**
     * items method
     * @return the inventory in string format
     */
    public String Items()
    {
        if ( this.aInventory.hasItem()){
            StringBuilder vInventory = new StringBuilder( " " );
            if (this.aInventory.hasItem() ){
                vInventory.append( "Iventory:" );
                for ( String vItem : this.aInventory.getIventory().keySet() )
                    vInventory.append( " " + vItem + " (" + this.aInventory.getItem(vItem).getWeight() + ")" );
                return vInventory.append( " for a total of "+ this.getWeightCarrying() + "kg" ).toString();
            }
            else vInventory.toString();
        }
        return "You aren't carrying anything.";
    } // Items()

    /**
     * change this image of the current room
     * @param pImageName name of the new image
     */
    public void changeImage( final String pImageName )
    {
        this.aCurrentRoom.setImage( pImageName );
        this.aGui.showImage( this.aCurrentRoom.getImageName() );
    } // changeImage(.)

    /**
     * take method
     * @param pSW the name of the item
     * @return 0, 1, 2 or 3 depending on the situation 
     */
    public int take( final String pSW )
    {
        int vInt = 2;
            if ( this.aCurrentRoom.containsItem(pSW) || pSW.equals("Veldora") ){
                if ( this.getWeightCarrying() < this.MAX_WEIGHT){
                    if ( pSW.equals("Veldora") ){
                    this.aInventory.setItem( this.aCurrentRoom.getCharacter().getItems().getItem("dragonSoul") );
                    this.changeImage( "afterAbsorbTempest" );
                    vInt = 3;
                    }
                    else if ( ( this.getWeightCarrying() + this.aCurrentRoom.getItems().getItem(pSW).getWeight() ) > this.MAX_WEIGHT )
                        vInt = 0;
                    else if ( this.aCurrentRoom.containsItem(pSW) ) {
                        this.aInventory.setItem( this.getCurrentRoom().getItems().getItem(pSW) );
                        this.aCurrentRoom.getItems().removeItem(pSW);
                        if ( pSW.equals("magistone") && this.aCurrentRoom.getImageName().equals("onMagistone"))
                            this.changeImage( "takeMagistone" );
                        vInt = 1;
                    }
                }
            }
            return vInt;
    } // take(.)

    /**
     * drop method 
     * @param pSW the name of the item
     * @return true if the item is in the inventory of the player, false otherwise
     */
    public boolean drop( final String pSW )
    {
            if ( this.aInventory.containsItem(pSW) ){
                this.aCurrentRoom.getItems().setItem( this.aInventory.getItem(pSW) );
                this.aInventory.removeItem(pSW);
                return true;
            }
            return false;
    } // drop(.)
} // Player
