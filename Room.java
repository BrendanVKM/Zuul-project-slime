import java.util.HashMap;

/**
 * create and modify room
 * @author Brendan VICTOIRE
 * @version 2021.01.18 + 2021.02.08
 */
public class Room
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    private String aImageName;
    private HashMap<String, Item> aItems;
    
    /**
     * Constructor
     * @param description of the room
     * @param image name
     */
    public Room(final String pDescription, final String pImage)
    {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aItems = new HashMap<String, Item>();
        this.aImageName = pImage;
    } // Room()

    /**
     * add exit to a room
     * @param direction of the exit
     * @param where the exit leads 
     */
    public void setExit(final String pDrct, final Room pNeighbor)
    {
        String vDrct = pDrct.toLowerCase();
        aExits.put( vDrct, pNeighbor );
    } // setExits()

    /**
     * access to description 
     * @return a description
     */
    public String getDescription() { return this.aDescription; } // getDescription()

    /**
     * access to the description + the exits
     */
    public String getLongDescription()
    {
        return "You are " + this.aDescription + "\n" + getItemString() + "\n" + getExitString() ;
    } // getLongDescription()
    
    /**
     * @return true if the room has at least one item
     */
    public boolean hasItem()
    {
        return !aItems.isEmpty() ; 
    } // has Item;
    
    /**
     * acces to one exit
     * @param a direction 
     * @return the room where the exit leads
     */
    public Room getExit(final String pDrct)
    {
        String vDrct = pDrct.toLowerCase();
        return this.aExits.get(vDrct);
    } // getExit()

    public Item getItem(final String pItem)
    {
        if (this.hasItem() ) {
            String vItem = pItem.toLowerCase(); 
            return this.aItems.get(vItem);
        }
        else return null; 
    } // getItem()
    
    /**
     * @return exits under String format
     */
    public StringBuilder getExitString()
    {   
        StringBuilder vExits = new StringBuilder( "Exits:" );
        for ( String vExit : this.aExits.keySet() )
            vExits.append( " " + vExit );
        return vExits;
    } // getExitString()
    
    /**
     * @return items name under String format
     */
    public StringBuilder getItemString()
    {
        if (this.hasItem() ){
            StringBuilder vItems = new StringBuilder( "Items: " );
            for ( String vItem : this.aItems.keySet() )
                vItems.append( " " + vItem );
            return vItems;
        }
        else return new StringBuilder( "" );
    } // getItemString()
    
    /**
     * @return name of the image of the current room
     */
    public String getImageName(){ return this.aImageName; } // getImageName()
    
    /**
     * add an item to the room
     * @param an item
     */
    public Item setItem( final String pItemName, final Item pItem ) {
        return this.aItems.put(pItemName, pItem);
    } // setItem
} // Room
