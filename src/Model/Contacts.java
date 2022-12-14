package Model;

/**
 * This class is the outline for a contact object.
 */
public class Contacts {
    private int contactID;
    private String contactName;

    /**
     * This function creates a contact object.
     * @param contactID Integer id of the contact.
     * @param contactName name of the contact.
     */
    public Contacts(int contactID, String contactName) {
        this.contactID = contactID;
        this.contactName = contactName;
    }

    /**
     * This function gets the contact id.
     * @return A contact objects' contact id number.
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * This function gets the contact name.
     * @return A contact objects' name.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * This function override the toString method.
     * @return A string of the contactID and contactName.
     */
    @Override
    public String toString() {
        return (getContactID() + " " + getContactName());
    }
}
