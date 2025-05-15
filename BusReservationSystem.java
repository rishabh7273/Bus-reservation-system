import java.util.Scanner;

public class BusReservationSystem {
    static String[] buses = {
            "Cardiff Express", "Belfast Express", "Derby Express",
            "Chester Express", "Newport Express", "Truro Express"
    };
    static String[][] passengerNames = new String[buses.length][32];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n\n====================================== WELCOME TO BUS RESERVATION SYSTEM ======================================\n");
            System.out.println("\t\t\t\t\t[1]=> View Bus List");
            System.out.println("\t\t\t\t\t[2]=> Book Tickets");
            System.out.println("\t\t\t\t\t[3]=> Cancel Booking");
            System.out.println("\t\t\t\t\t[4]=> Bus Status Board");
            System.out.println("\t\t\t\t\t[5]=> Exit");
            System.out.print("\t\t\tEnter Your Choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewBusList();
                    break;
                case 2:
                    bookTickets();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    viewStatus();
                    break;
                case 5:
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    public static void viewBusList() {
        System.out.println("\n=========================================== BUS LIST ============================================\n");
        for (int i = 0; i < buses.length; i++) {
            System.out.println("\t\t\t[" + (i + 1) + "]  =>  " + buses[i]);
        }
    }

    public static void bookTickets() {
        System.out.println("=========================================== BOOK TICKETS ============================================\n");
        viewBusList();

        System.out.print("Enter the Bus number: ");
        int busNumber = scanner.nextInt() - 1;

        if (busNumber < 0 || busNumber >= buses.length) {
            System.out.println("Invalid bus number.");
            return;
        }

        System.out.println("Your Bus Number is " + (busNumber + 1) + " - " + buses[busNumber]);
        int availableSeats = calculateAvailableSeats(busNumber);
        System.out.println("\nAvailable Seats: " + availableSeats);

        if (availableSeats == 0) {
            System.out.println("Sorry, no seats are available.");
            return;
        }

        System.out.print("Number of Tickets: ");
        int tickets = scanner.nextInt();

        if (tickets > availableSeats) {
            System.out.println("Not enough seats available.");
            return;
        }

        scanner.nextLine(); // Consume newline

        for (int i = 0; i < tickets; i++) {
            System.out.println("Enter details for ticket no " + (i + 1));

            int seatNumber;
            do {
                System.out.print("Enter the seat number (1–32): ");
                seatNumber = scanner.nextInt() - 1;
                scanner.nextLine(); // Consume newline

                if (seatNumber < 0 || seatNumber >= 32) {
                    System.out.println("Invalid seat number. Try again.");
                } else if (passengerNames[busNumber][seatNumber] != null) {
                    System.out.println("Seat is already booked. Choose another seat.");
                } else {
                    break;
                }
            } while (true);

            System.out.print("Enter the name of the person: ");
            String name = scanner.nextLine();

            // Store the passenger information
            passengerNames[busNumber][seatNumber] = name;
        }

        System.out.println("Total booking amount: " + (200 * tickets));
    }

    public static void cancelBooking() {
        System.out.println("=========================================== CANCEL BOOKING ============================================\n");
        System.out.print("Enter the Bus number: ");
        int busNumber = scanner.nextInt() - 1;

        if (busNumber < 0 || busNumber >= buses.length) {
            System.out.println("Invalid bus number.");
            return;
        }

        System.out.print("Enter the seat number to cancel: ");
        int seatNumber = scanner.nextInt() - 1;

        if (seatNumber < 0 || seatNumber >= 32 || passengerNames[busNumber][seatNumber] == null) {
            System.out.println("Invalid or unbooked seat number.");
            return;
        }

        passengerNames[busNumber][seatNumber] = null;
        System.out.println("Booking canceled successfully.");
    }

    public static void viewStatus() {
        System.out.println("=========================================== BUS STATUS ============================================\n");
        System.out.print("Enter the Bus number to view status: ");
        int busNumber = scanner.nextInt() - 1;

        if (busNumber < 0 || busNumber >= buses.length) {
            System.out.println("Invalid bus number.");
            return;
        }

        System.out.println("Bus Number: " + (busNumber + 1) + " - " + buses[busNumber]);
        System.out.println("Seat Status:");
        for (int i = 0; i < 32; i++) {
            if (passengerNames[busNumber][i] != null) {
                System.out.println("Seat " + (i + 1) + ": " + passengerNames[busNumber][i]);
            } else {
                System.out.println("Seat " + (i + 1) + ": Empty");
            }
        }
    }

    public static int calculateAvailableSeats(int busNumber) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (passengerNames[busNumber][i] == null) {
                count++;
            }
        }
        return count;
    }
}
