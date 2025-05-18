// Clay VanZile, CEN 3024C-31774, 05/18/25
// Software Development I
// Class for managing the library management system

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {
    private ArrayList<Patron> patrons = new ArrayList<>(); // Stores all patrons
    private Scanner scanner = new Scanner(System.in);      // For user input

    // displayMenu
    // Displays menu and handles user choices
    // Inputs: none
    // Output: void
    public void displayMenu() {
        int choice;
        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Patron");
            System.out.println("2. Remove Patron");
            System.out.println("3. Display Patrons");
            System.out.println("4. Load Patrons From File");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine());


            // Handle Choices with if-then-else statements
            if (choice == 1) {
                addPatron();
                displayPatrons();
            } else if (choice == 2) {
                System.out.println("Enter Patron ID to remove: ");
                String ID = scanner.nextLine();
                removePatron(ID);
                displayPatrons();
            } else if (choice == 3) {
                displayPatrons();
            } else if (choice == 4) {
                System.out.println("Enter filename: ");
                String filename = scanner.nextLine();
                loadPatronsFromFile(filename);
                displayPatrons();
            } else if (choice == 5) {
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    // addPatron
    // Adds a new patron manually through user input
    // Inputs: none
    // Output: void
    public void addPatron() {

        // Prompt for unique ID (Must be 7 digits and numeric)

        String id;
        do {
            System.out.print("Enter 7-digit ID: ");
            id = scanner.nextLine();

            if (id.length() == 0)
                return;

            // Validate ID: must be digits and unique

            if (!id.matches("\\d{7}") || !isUniqueId(id)) {
                System.out.println("Invalid or duplicate ID.");
            }
        } while (!id.matches("\\d{7}") || !isUniqueId(id));

        // Prompt for name (must contain a space)
        String name;
        do {
            System.out.print("Enter full name: ");
            name = scanner.nextLine();
            if (!name.trim().contains(" ")) {
                System.out.println("Name must include first and last name.");
            }
        } while (!name.trim().contains(" "));

        // Prompt for address (any input accepted)
        String address;
        do {
            System.out.print("Enter address: ");
            address = scanner.nextLine();
            if (address.length() < 1) {
                System.out.println("Address length must be greater than or equal to 1.");
            }
        } while (address.length() < 1);

        // Prompt for fine (0-250), with error handling
        double fine;
        do {
            System.out.print("Enter fine amount (0 - 250): ");
            try {
                fine = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Fine amount must be a number.");
                fine = -1;  // This will force the while-loop to loop
            }

            if (fine < 0 || fine > 250) {
                System.out.println("Fine must be between 0 and 250.");
            }
        } while (fine < 0 || fine > 250);

        // Add the new patron to the list
        patrons.add(new Patron(id, name, address, fine));
        System.out.println("Patron added.");
    }

    // removePatron
    // Removes a patron by ID
    // Inputs: id - id to be removed
    // Output: void
    public void removePatron(String id) {
        boolean removed = patrons.removeIf(p -> p.getId().equals(id));
        if (removed) {
            System.out.println("Patron removed.");
        } else {
            System.out.println("Patron not found.");
        }
    }

    // displayPatrons
    // Displays all patrons in the system
    // Inputs: none
    // Output: void
    public void displayPatrons() {
        if (patrons.isEmpty()) {
            System.out.println("No patrons in the system.");
        } else {
            for (Patron p : patrons) {
                System.out.println(p);
            }
        }
    }

    // loadPatronsFromFile
    // Loads patron data from a file
    // Inputs: filename
    // Output: void
    public void loadPatronsFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        // Read each line and split data
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int loadedCount = 0;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length != 4) {
                    System.out.println(line + "\n>>>>> Line does not contain correct number of fields.");
                    continue; // Skip invalid lines
                }

                //Get rid of whitespace before and after fields
                String id = parts[0].trim();
                String name = parts[1].trim();
                String address = parts[2].trim();
                double fine;

                // Validate ID and uniqueness
                if (!id.matches("\\d{7}") || !isUniqueId(id)) {
                    System.out.println(line + "\n>>>>> Invalid or duplicate ID.");
                    continue;
                }

                // Validate name
                if (!name.contains(" ")) {
                    System.out.println(line + "\n>>>>> Name must include one space.");
                    continue;
                }

                //Validate address
                if (address.length() < 1) {
                    System.out.println(line + "\n>>>>> Address length must be greater than or equal to 1.");
                    continue;
                }

                // Validate fine
                try {
                    fine = Double.parseDouble(parts[3].trim());
                    if (fine < 0 || fine > 250) {
                        System.out.println(line + "\n>>>>> Invalid fine amount, must be between 0 and 250.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(line + "\n>>>>> Invalid fine amount, must contain only numbers.");
                    continue;
                }

                // Add to list
                patrons.add(new Patron(id, name, address, fine));
                loadedCount++;
            }

            System.out.println(loadedCount + " patrons loaded.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    // isUniqueId
    // Checks if a given ID is unique in the patron list
    // Inputs: id
    // Output: boolean
    private boolean isUniqueId(String id) {
        for (Patron p : patrons) {
            if (p.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }
}
