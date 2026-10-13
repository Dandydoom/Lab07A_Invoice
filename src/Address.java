/**
 * A mailing address.
 *
 * @author Kirby Fortney
 */
public class Address
{
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;

    /**
     * Creates an address.
     *
     * @param name   the name of the person or business
     * @param street the street address
     * @param city   the city
     * @param state  the state
     * @param zip    the zip code
     */
    public Address(String name, String street, String city, String state, String zip)
    {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * Formats the address as three lines of text.
     *
     * @return the formatted address
     */
    public String format()
    {
        return name + "\n" + street + "\n" + city + ", " + state + " " + zip;
    }
}
