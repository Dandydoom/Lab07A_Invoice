import java.util.ArrayList;

/**
 * An invoice with a billing address and a list of line items.
 *
 * @author Kirby Fortney
 */
public class Invoice
{
    private static final String LINE = "=".repeat(49);

    private Address address;
    private ArrayList<LineItem> items;

    /**
     * Creates an empty invoice.
     *
     * @param address the billing address
     */
    public Invoice(Address address)
    {
        this.address = address;
        this.items = new ArrayList<>();
    }

    /**
     * Adds a line item to the invoice.
     *
     * @param product  the product bought
     * @param quantity how many were bought
     */
    public void addItem(Product product, int quantity)
    {
        items.add(new LineItem(product, quantity));
    }

    /**
     * Calculates the amount due (the sum of all the line totals).
     *
     * @return the amount due
     */
    public double getAmountDue()
    {
        double amountDue = 0;
        for (LineItem item : items)
        {
            amountDue += item.getTotal();
        }
        return amountDue;
    }

    /**
     * Formats the whole invoice as text.
     *
     * @return the formatted invoice
     */
    public String format()
    {
        StringBuilder result = new StringBuilder();
        result.append("                    I N V O I C E\n\n");
        result.append(address.format()).append("\n");
        result.append(LINE).append("\n");
        result.append(String.format("%-22s%5s%11s%11s%n", "Item", "Qty", "Price", "Total"));
        for (LineItem item : items)
        {
            result.append(item.format()).append("\n");
        }
        result.append(LINE).append("\n");
        result.append(String.format("AMOUNT DUE: $%.2f%n", getAmountDue()));
        return result.toString();
    }
}
