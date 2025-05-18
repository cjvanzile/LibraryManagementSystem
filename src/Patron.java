// Clay VanZile, CEN 3024C-31774, 05/18/25
// Software Development I
// Class to represent a library patron

public class Patron {
    // Fields to store patron information
    private String id;          // 7-digit unique ID
    private String name;        // Full name (first and last)
    private String address;     // Home address
    private double overdueFine; // Overdue fine amount (0-250)

    // Constructor to initialize Patron object
    public Patron(String id, String name, String address, double overdueFine) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.overdueFine = overdueFine;
    }

    // Getter for ID
    public String getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for address
    public String getAddress() {
        return address;
    }

    // Getter for overdue fine
    public double getOverdueFine() {
        return overdueFine;
    }

    // toString method to return a readable string representation of a patron
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Address: " + address + ", Fine: $" + String.format("%.2f", overdueFine);
    }
}
