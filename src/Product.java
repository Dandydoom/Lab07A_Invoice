/**
 * A product that can be sold. It has a name and a unit price.
 *
 * @author Kirby Fortney
 */
public class Product
{
    private String name;
    private double price;

    /**
     * Creates a product.
     *
     * @param name  the product name
     * @param price the price for one unit
     */
    public Product(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the product name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the price for one unit.
     *
     * @return the unit price
     */
    public double getPrice()
    {
        return price;
    }
}
