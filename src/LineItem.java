/**
 * One line on an invoice: a product and how many were bought.
 *
 * @author Kirby Fortney
 */
public class LineItem
{
    private Product product;
    private int quantity;

    /**
     * Creates a line item.
     *
     * @param product  the product bought
     * @param quantity how many were bought
     */
    public LineItem(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Calculates the total for this line (unit price times quantity).
     *
     * @return the line total
     */
    public double getTotal()
    {
        return product.getPrice() * quantity;
    }

    /**
     * Formats this line as one row of the invoice.
     *
     * @return the formatted row
     */
    public String format()
    {
        return String.format("%-22s%5d%11s%11s",
                product.getName(), quantity,
                String.format("$%.2f", product.getPrice()),
                String.format("$%.2f", getTotal()));
    }
}
