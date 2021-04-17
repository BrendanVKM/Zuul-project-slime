package pkg_game.pkg_item;

import java.util.HashMap ;

/**
 * create and modify Itemlist for Player and Room
 * @author Brendan VICTOIRE
 * @version 2021.01.18 + 2021.02.08
 */
public class ItemList {
    private HashMap<String,Item> aInventory ;

    /**
     * default constructor
     */
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
        this.aInventory.remove(pItem);
    } // removeItem(.)

    /**
     * acces to all the inventory
     * @return inventory map
     */
    public HashMap<String, Item> getIventory(){ return this.aInventory; } // getIventory()

    /**
     * tells if the ItemList is empty or not
     * @return true if the room has at least one item
     */
    public boolean hasItem() { return !aInventory.isEmpty() ; } // hasItem()
    
    /**
     * tells if the ItemList contains the item that we want
     * @param pItemName the name of the key that we want to check
     * @return true if this room has this item
     */
    public boolean containsItem( final String pItemName )
    {
        return this.aInventory.containsKey( pItemName );
    } // containsItem(.)

    /**
     * acces to the item that we want
     * @param pItem an item
     * @return the item
     */
    public Item getItem( final String pItem )
    {
        if ( this.hasItem() ) {
            return this.aInventory.get(pItem);
        }
        else return null; 
    } // getItem(.)

    
}
