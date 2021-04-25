package pkg_game;

import pkg_game.pkg_item.ItemList;
import pkg_game.pkg_item.Item;

/**
 * 
 */
public class Character {

    private String aName;
    protected Room aRoom;
    private ItemList aItemList;

    /**
     * Constructor
     * 
     * @param pName name of the character
     */
    public Character(final String pName, final Room pRoom) {
        this.aName = pName;
        this.aRoom = pRoom;
        this.aItemList = new ItemList();
    } // Character(.)

    /**
     * add item to a room
     * 
     * @param pItem item you want to add in the room
     */
    public void setItem(final Item pItem) {
        this.aItemList.setItem(pItem);
    } // setItem(.)

    /**
     * acces to items of the character
     * 
     * @return items of the character
     */
    public ItemList getItems() {
        return this.aItemList;
    } // getItems()

    /**
     * access to the name of the character
     * 
     * @return name of the character
     */
    public String getName() {
        return this.aName;
    } // getName()

    /**
     * access to the room of the character
     * 
     * @return the current room of the character
     */
    public Room getRoom() {
        return this.aRoom;
    } // getRoom()

    /**
     * String prints when the player enters the room of Veldora
     * 
     * @return String prints when the player enters the room of Veldora
     */
    public String VeldoraRoom() {
        return "Hello little one!\nI am " + this.aName
                + ".\n I'm locked in here since ages.\n I would be grateful to you if you could absorb me";
    } // PlayerEnterRoom()

    /**
      * String prints when the player abvsorb Veldora
     * 
     * @return String prints when the player abvsorb Veldora
     */
    public String PlayerAbsorbVeldora() {
        return "Thank you for fulfilling my request!";
    } // PlayerAbsorbCharacter()

    public String PlayerMeetSlimeWithBeatrix() {
        return "Welcome back Rimuru!!!";
    }
} // Character