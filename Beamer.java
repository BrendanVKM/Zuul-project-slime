
/**
 * let you teleport in a room that you have memorized
 * @author Brendan VICTOIRE
 * @version 2021.02.26
 */
public class Beamer extends Item
{
    private Room aMemorizedRoom;
    private boolean aCharged;
    private boolean aUsed;

    public Beamer()
    {
        super( "teleportation", "let you teleport in a room that you have memorized (one-time use)", 0 );
        this.aMemorizedRoom = null;
        this.aCharged = false;
        this.aUsed = false;
    } // Beamer()

    /**
     * access to the memorized room 
     * @return the memorized room
     */
    public Room getMemorizedRoom()
    {
        return this.aMemorizedRoom;
    }
    /**
     * access to aCharged 
     * @return true if the beamer if charged
     */
    public boolean isCharged()
    {
        return this.aCharged;
    } // isCharged()

    /**
     * know if the beamer has already been used or not
     * @return true if the beamer has been used
     */
    public boolean isUsed()
    {
        return this.aUsed;
    } // isUsed()

    /**
     * charge the beamer with a room
     * @param pRoom the room that you want to memorized
     */
    public void charge( final Room pRoom )
    {
        this.aMemorizedRoom = pRoom;
        if ( this.aMemorizedRoom != null )
            this.aCharged = true;
    } // charge(.)

    public void fire()
    {
        this.aMemorizedRoom = null;
        this.aCharged = false;
        this.aUsed = true;
    }
} // Beamer
