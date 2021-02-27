
/**
 * set Door to room
 * @author Brendan VICTOIRE
 * @version 2021.02.26
 */
public class Door {
    private boolean aOpen;

    /**
     * defalt constructor
     * @param pKey the key that you need to open the door
     * @param pOpen true if the door is open false otherwise 
     */
    public Door( final Item pKey, final boolean pOpen ) 
    {
        this.aOpen = pOpen;
    } // Door(.)
    
    /**
     * acces to the status of the door (close or open)
     * @return true if the door is open
     */
    public boolean isOpen()
    {
        return this.aOpen;
    } // isOpen()

    /**
     * acces to the status of the door (close or open)
     * @return true if the door is open
     */
    public void setOpen( final boolean pOpen )
    {
        this.aOpen = pOpen;
    } // setOpen(.)
} // Door
