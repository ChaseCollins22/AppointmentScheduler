package Model;

public class Divisions {
    private int divisionID;
    private String divisionName;

    public Divisions(int divisionID, String divisionName) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    @Override
    public String toString() {
        return (getDivisionName());
    }
}
