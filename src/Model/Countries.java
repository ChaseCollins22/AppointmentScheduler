package Model;

/**
 * This class is the outline for a countries object.
 */
public class Countries {

    private int countryID;
    private String countryName;

    /**
     * This function creates a country object.
     * @param countryID id of the country.
     * @param countryName name of the country.
     */
    public Countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * This function get the country id from a country object.
     * @return Integer country id number.
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * This function gets the country name from a country object.
     * @return Name of the country.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * This function overrides the toString method.
     * @return The country name in a string.
     */
    @Override
    public String toString() {
        return (getCountryName());
    }


}
