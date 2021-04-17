package pkg_game;

import pkg_game.pkg_item.Item;
/**
 * set Door to room
 * @author Brendan VICTOIRE
 * @version 2021.02.26
 */
public class Door {
    private boolean aOpen;
    private Item aKey;

    /**
     * defalt constructor
     * @param pKey the key that you need to open the door
     */
    public Door( final Item pKey ) 
    {
        this.aKey = pKey;
        this.aOpen = false;
    } // Door(.)
    
    /**
     * acces to the key
     * @return the key
     */
    public Item getKey() { return aKey; } //getKey()
    /**
     * acces to the status of the door (close or open)
     * @return true if the door is open false otherwise 
     */
    public boolean isOpen()
    {
        return this.aOpen;
    } // isOpen()

    /**
     * acces to the status of the door (close or open)
     * @param pOpen true if the door is open false otherwise 
     */
    public void setOpen( final boolean pOpen )
    {
        this.aOpen = pOpen;
    } // setOpen(.)
} // Door
