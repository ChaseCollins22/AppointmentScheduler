package Model;

/**
 * This class is the outline for a Division object.
 */
public class Divisions {
    private int divisionID;
    private String divisionName;

    /**
     * This function creates a division object.
     * @param divisionID Id number of the division.
     * @param divisionName Name of the division.
     */
    public Divisions(int divisionID, String divisionName) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    /**
     * This function gets the ID number of a Division object.
     * @return A division id integer.
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * This function gets the name of a Division object.
     * @return The name of the division.
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * This function overrides the toString method.
     * @return The division name.
     */
    @Override
    public String toString() {
        return (getDivisionName());
    }
}
