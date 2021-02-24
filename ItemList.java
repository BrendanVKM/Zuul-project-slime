import java.util.HashMap ;

public class ItemList {
    private HashMap<String,Item> aInventory ;

    public ItemList()
    {
        this.aInventory = new HashMap<String, Item>();
    } // itemList

        /**
     * add an item to the room
     * @param pItem an item
     */
    public void setItem( final Item pItem ) 
    {

        this.aInventory.put(pItem.getName(), pItem);
    } // setItem(.)

    /**
     * remove an item
     * @param pItem the name of the item
     */
    public void removeItem( final String pItem ) 
    {
        String vItem = pItem.toLowerCase();
        this.aInventory.remove(vItem);
    } // removeItem(.)

    /**
     * @return inventory map
     */
    public HashMap<String, Item> getIventory(){ return this.aInventory; } // getIventory()

    /**
     * @return true if the room has at least one item
     */
    public boolean hasItem() { return !aInventory.isEmpty() ; } // hasItem()
    
    /**
     * @param pItemName the name of the key that we want to check
     * @return true if this room has this item
     */
    public boolean containsItem( final String pItemName )
    {
        return this.aInventory.containsKey( pItemName );
    } // containsItem(.)

    /**
     * @param pItem an item
     * @return the item
     */
    public Item getItem( final String pItem )
    {
        if ( this.hasItem() ) {
            String vItem = pItem.toLowerCase(); 
            return this.aInventory.get(vItem);
        }
        else return null; 
    } // getItem(.)
    /**
    * @return inventory under String format
     */
    public StringBuilder getItemsString()
    {
        StringBuilder vInventory = new StringBuilder( "" );
        if (this.hasItem() ){
            vInventory.append( "Iventory:" );
            for ( String vItem : this.aInventory.keySet() )
            vInventory.append( " " + vItem );
        }
        return vInventory;
    } // getItemString()
}
