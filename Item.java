
/**
 * item
 * @author Brendan VICTOIRE
 * @version 2021.012.11
 */
public class Item
{
    private String aName;
    private String aDescription;
    private float aWeight;

    /**
     * Constructor
     * @param item name
     * @param item description
     * @param item weight 
     */
    public Item( final String pName, final String pDesc, final float pWeight )
    {
        this.aName = pName;
        this.aDescription = pDesc;
        this.aWeight = pWeight;
    } // Item()

    /**
     * @return name of the item
     */
    public String getName(){ return this.aName.toLowerCase(); } // getName()

    /**
     * @return description of the item
     */
    public String getDescription(){ return this.aDescription; } // getDescription()

    /**
     * @return weight of the item
     */
    public float getWeight(){ return this.aWeight; } // getWeight()

    /**
     * @return name + weight + description of the item 
     */
    public String getItemString()
    {
        return this.aName + " ¤ " + "Weight : (" + this.aWeight +" kg)" 
        + " ¤ " + "Description : " + this.aDescription;
    } // getItemString()

} // Item