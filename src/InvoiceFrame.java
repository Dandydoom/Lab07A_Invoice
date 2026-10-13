import javax.swing.*;
import java.awt.*;

/**
 * GUI for entering invoice data and displaying the invoice.
 * The user enters the billing address once, then adds line items.
 *
 * @author Kirby Fortney
 */
public class InvoiceFrame extends JFrame
{
    // data
    private Invoice invoice = null;

    // address fields
    private JTextField nameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;

    // line item fields
    private JTextField itemField;
    private JTextField priceField;
    private JTextField quantityField;

    // display
    private JTextArea invoiceArea;

    public InvoiceFrame()
    {
        setTitle("Invoice");
        setLayout(new BorderLayout(5, 5));

        // top: address and item entry side by side
        JPanel entryPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        entryPanel.add(createAddressPanel());
        entryPanel.add(createItemPanel());
        add(entryPanel, BorderLayout.NORTH);

        add(createDisplayPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setSize(750, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createAddressPanel()
    {
        JPanel addressPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        addressPanel.setBorder(BorderFactory.createTitledBorder("Bill To"));

        nameField = new JTextField("Sam's Small Appliances");
        streetField = new JTextField("100 Main Street");
        cityField = new JTextField("Anytown");
        stateField = new JTextField("CA");
        zipField = new JTextField("98765");

        addressPanel.add(new JLabel("Name:"));
        addressPanel.add(nameField);
        addressPanel.add(new JLabel("Street:"));
        addressPanel.add(streetField);
        addressPanel.add(new JLabel("City:"));
        addressPanel.add(cityField);
        addressPanel.add(new JLabel("State:"));
        addressPanel.add(stateField);
        addressPanel.add(new JLabel("Zip:"));
        addressPanel.add(zipField);
        return addressPanel;
    }

    private JPanel createItemPanel()
    {
        JPanel itemPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        itemPanel.setBorder(BorderFactory.createTitledBorder("Line Item"));

        itemField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(ae -> addItem());

        itemPanel.add(new JLabel("Item:"));
        itemPanel.add(itemField);
        itemPanel.add(new JLabel("Unit price:"));
        itemPanel.add(priceField);
        itemPanel.add(new JLabel("Quantity:"));
        itemPanel.add(quantityField);
        itemPanel.add(new JLabel());
        itemPanel.add(addButton);
        return itemPanel;
    }

    private JPanel createDisplayPanel()
    {
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder("Invoice"));

        invoiceArea = new JTextArea(15, 50);
        invoiceArea.setEditable(false);
        invoiceArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        displayPanel.add(new JScrollPane(invoiceArea), BorderLayout.CENTER);
        return displayPanel;
    }

    private JPanel createButtonPanel()
    {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        JButton newButton = new JButton("New Invoice");
        JButton quitButton = new JButton("Quit");

        newButton.addActionListener(ae -> newInvoice());
        quitButton.addActionListener(ae -> quit());

        buttonPanel.add(newButton);
        buttonPanel.add(quitButton);
        return buttonPanel;
    }

    /**
     * Adds the line item from the entry fields to the invoice and shows it.
     */
    private void addItem()
    {
        String itemName = itemField.getText().trim();
        double price;
        int quantity;

        // check the input
        if (itemName.isEmpty())
        {
            showError("Please enter an item name.");
            return;
        }
        try
        {
            price = Double.parseDouble(priceField.getText().trim());
            if (price < 0)
            {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e)
        {
            showError("The unit price must be a number that is 0 or more.");
            return;
        }
        try
        {
            quantity = Integer.parseInt(quantityField.getText().trim());
            if (quantity < 1)
            {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e)
        {
            showError("The quantity must be a whole number of 1 or more.");
            return;
        }

        // the first item creates the invoice with the address that was entered
        if (invoice == null)
        {
            Address address = new Address(nameField.getText().trim(), streetField.getText().trim(),
                    cityField.getText().trim(), stateField.getText().trim(), zipField.getText().trim());
            invoice = new Invoice(address);
            setAddressEditable(false);
        }

        invoice.addItem(new Product(itemName, price), quantity);
        invoiceArea.setText(invoice.format());

        itemField.setText("");
        priceField.setText("");
        quantityField.setText("");
        itemField.requestFocus();
    }

    /**
     * Clears the invoice so a new one can be started.
     */
    private void newInvoice()
    {
        invoice = null;
        invoiceArea.setText("");
        itemField.setText("");
        priceField.setText("");
        quantityField.setText("");
        setAddressEditable(true);
    }

    private void setAddressEditable(boolean editable)
    {
        nameField.setEditable(editable);
        streetField.setEditable(editable);
        cityField.setEditable(editable);
        stateField.setEditable(editable);
        zipField.setEditable(editable);
    }

    private void showError(String message)
    {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    private void quit()
    {
        int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?",
                "Quit", JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
}
