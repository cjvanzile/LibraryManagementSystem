// Clay VanZile, CEN 3024C-31774, 05/18/25
// Software Development I
// Entry point of the Library Management System
/*
Introduction

The customer is a local library looking for a new library management system (LMS) to manage its patrons.
The customer needs a software system that allows librarians to add, remove, and display patrons within the system.

Requirements Definition

The librarians need to be able to collect patrons' information, which includes: 7-digit ID number, first and last name,
address, and fine amount. The librarian will be able to import text files containing the patrons' information.
The librarian must be able to add and remove patrons. The librarians will be able to display all the information
provided, and it will be stored directly in the application.
 */

public class Main {
    public static void main(String[] args) {
        // Create a new library system and show the menu
        LibraryManagementSystem system = new LibraryManagementSystem();
        system.displayMenu();
    }
}
