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
    private ItemList aItems;
    
    /**
     * Constructor
     * @param pDescription description of the room
     * @param pImage image name
     */
    public Room(final String pDescription, final String pImage)
    {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aItems = new ItemList();
        this.aImageName = pImage;
    } // Room(.)

    /**
     * add exit to a room
     * @param pDrct direction of the exit
     * @param pNeighbor where the exit leads 
     */
    public void setExit(final String pDrct, final Room pNeighbor)
    {
        String vDrct = pDrct.toLowerCase();
        aExits.put( vDrct, pNeighbor );
    } // setExits(.)

    /**
     * access to description 
     * @return a description
     */
    public String getDescription() { return this.aDescription; } // getDescription()

    /**
     * access map items of the rooms  
     * @return map items of the rooms 
     */
    public ItemList getItems() { return this.aItems; } // getItems()

    /**
     * access to the description + the exits
     */
    public String getLongDescription()
    {
        return "You are " + this.aDescription + "\n" + this.aItems.getItemsString() + 
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
        if (this.hasExit() ){
            String vDrct = pDrct.toLowerCase();
            return this.aExits.get(vDrct);
        }
        else return null;
    } // getExit(.)
    
    /**
     * @return exits under String format
     */
    public StringBuilder getExitString()
    {   
        StringBuilder vExits = new StringBuilder( "" );
        if ( this.hasExit() ){
            vExits.append( "Exits:" );
            for ( String vExit : this.aExits.keySet() )
                vExits.append( " " + vExit );
        }
        return vExits;
    } // getExitString()
} // Room