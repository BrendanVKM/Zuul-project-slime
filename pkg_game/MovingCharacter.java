package pkg_game;

/**
 * create and modify a moving character
 * 
 * @author Brendan VICTOIRE
 * @version 2021.02.23 + 2021.02.25 +2021.02.26
 */
public class MovingCharacter extends Character {

    /**
     * Constructor
     * 
     * @param pName name of the character
     * @param pRoom room of the character
     */
    MovingCharacter(final String pName, final Room pRoom) {
        super(pName, pRoom);
    } // MovingCharacter(.)

    public void moveCharacter(final Room pRoom) {
        super.aRoom = pRoom;
    } // moveCharacter(.)
}
