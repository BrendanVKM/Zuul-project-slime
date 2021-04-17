package pkg_game;

import java.util.HashMap;
import pkg_game.pkg_item.ItemList;
import pkg_game.pkg_item.Item;

/**
 * create and modify room
 * @author Brendan VICTOIRE
 * @version 2021.01.18 + 2021.02.08
 */
public class Room
{
    
    private String aDescription;
    private HashMap<String, Room> aExits;
    private HashMap<String,Door> aDoors;
    private String aImageName;
    private ItemList aItems;
    private Character aCharacter;
    /**
     * Constructor
     * @param pDescription description of the room
     * @param pImage image name
     */
    public Room(final String pDescription, final String pImage)
    {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aDoors = new HashMap<String, Door>();
        this.aItems = new ItemList();
        this.aImageName = pImage;
        this.aCharacter = null;
    } // Room(.)

    /**
     * add exit to a room
     * @param pDrct direction of the exit
     * @param pNeighbor where the exit leads 
     */
    public void setExit(final String pDrct, final Room pNeighbor) 
    { 
        aExits.put( pDrct, pNeighbor ); 
    } // setExits(.)
    
    /**
     * add a character to the room
     * @param pName name of the character
     */
    public void setCharacter(final String pName)
    {
        this.aCharacter = new Character(pName);
    } // setCharacter(.)

    /**
     * change the image
     * @param pImage name of the file
     */
    public void setImage(final String pImage)
    {
        this.aImageName = pImage;
    } // setImage(.)
    
    /**
     * remove the caracter of the room
     */
    public void removeCharacter()
    {
        this.aCharacter = null;
    } // setCharacter(.)

    /**
     * add a door
     * @param pDrct the direction
     * @param pDoor the door 
     */
    public void setDoors(final String pDrct, final Door pDoor)
    {
        this.aDoors.put(pDrct, pDoor);
    }//setDoors

    /**
     * add item to a room
     * @param pItem item you want to add in the room
     */
    public void setItem(final Item pItem) 
    { 
        this.aItems.setItem( pItem ); 
    } // setItem(.)

    
    /**
     * add item to the character of the room
     * @param pItem item you want to add in the room
     */
    public void setCharacterItem(final Item pItem) 
    { 
        this.aCharacter.setItem( pItem ); 
    } // setItem(.)

    /**
     * access to description 
     * @return a description
     */
    public String getDescription() { return this.aDescription; } // getDescription()

    
    /**
     * acces to the door
     * @param pDrct the direction
     * @return the door
     */
    public Door getDoor(final String pDrct)
    {
        return this.aDoors.get(pDrct);
    }//getDoors

    /**
     * access map items of the rooms  
     * @return map items of the rooms 
     */
    public ItemList getItems() { return this.aItems; } // getItems()

    /**
     * access to the description + the exits
     * @return a string representation of the description
     */
    public String getLongDescription()
    {
        return "You are " + this.aDescription + "\n" + this.getItemsString() + 
                "\n" + this.getExitString() ;
    } // getLongDescription()
    
    /**
     * @return true if the room has at least one exit
     */
    public boolean hasExit() { return !aExits.isEmpty() ; } // hasExit();

    /**
     * @return name of the image of the current room
     */
    public String getImageName(){ return this.aImageName; } // getImageName()

    /**
     * acces to one exit
     * @param pDrct a direction 
     * @return the room where the exit leads
     */
    public Room getExit(final String pDrct)
    {
        if (this.hasExit() )
            return this.aExits.get(pDrct);
        else return null;
    } // getExit(.)
    
    /**
     * acces to all the exit under String format
     * @return exits under String format
     */
    public StringBuilder getExitString()
    {   
        StringBuilder vExits = new StringBuilder( "" );
        if ( this.hasExit() ){
            vExits.append( "Corridor:" );
            for ( String vExit : this.aExits.keySet() )
                vExits.append( " " + vExit );
        }
        return vExits;
    } // getExitString()

    /**
     * acces to the inventory under String format
     * @return inventory under String format
     */
    public StringBuilder getItemsString()
    {
        StringBuilder vInventory = new StringBuilder( "" );
        if (this.aItems.hasItem() ){
            vInventory.append( "Items:" );
            for ( String vItem : this.aItems.getIventory().keySet() )
            vInventory.append( " " + vItem );
        }
        return vInventory;
    } // getItemsString()

    /**
     * acces to the character of the room
     * @return the character of the room
     */
    public Character getCharacter()
    {
        return this.aCharacter;
    } // getCharacter

    /**
     * tells if the ItemList contains the item that we want
     * @param pItemName the name of the key that we want to check
     * @return true if this room has this item
     */
    public boolean containsItem( final String pItemName )
    {
        return this.aItems.containsItem( pItemName );
    } // containsItem(.)

    public boolean containsCharacter( final String pCharacter)
    {
        return this.aCharacter.getName() == pCharacter;
    }

    // Overriding equals() just in case it can be usefull later 
    @Override
    public boolean equals( final Object pObj )
    {  
        if ( pObj == this )
            return true; 
        if ( !( pObj.getClass().equals( this.getClass() ) ) )
            return false; 
        Room vRoom = (Room) pObj; 
        return this.aDescription.equals(vRoom.getDescription() )
                && this.aExits.equals( vRoom.aExits );
    } // equals(.)
} // Room