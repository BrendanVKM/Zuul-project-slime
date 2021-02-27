
/**
 * create item
 * @author Brendan VICTOIRE
 * @version 2021.012.11
 */
public class Item
{
    private String aName;
    private String aDescription;
    private double aWeight;
    /**
     * Constructor
     * @param pName item name
     * @param pDesc item description
     * @param pWeight item weight 
     */
    public Item( final String pName, final String pDesc, final double pWeight )
    {
        this.aName = pName;
        this.aDescription = pDesc;
        this.aWeight = pWeight;
    } // Item(.)

    /**
     * acces to the name of the item
     * @return name of the item
     */
    public String getName(){ return this.aName; } // getName()

    /**
     * acces to the description of the item
     * @return description of the item
     */
    public String getDescription(){ return this.aDescription; } // getDescription()

    /**
     * acces to the weight of the item
     * @return weight of the item
     */
    public double getWeight(){ return this.aWeight; } // getWeight()

    /**
     * acces to all the information of the item
     * @return name + weight + description of the item 
     */
    public String getItemString()
    {
        return this.aName + " [" + "Weight : (" + this.aWeight +" kg)" 
        + " ¤ " + "Description : " + this.aDescription +"].";
    } // getItemString()
} // Item