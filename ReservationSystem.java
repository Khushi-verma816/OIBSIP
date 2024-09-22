import java.util.ArrayList;
import java.util.Scanner;

public class ReservationSystem {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public Reservation getReservation(String pnr) {
        for (Reservation reservation : reservations) {
            if (reservation.getPnr().equals(pnr)) {
                return reservation;
            }
        }
        return null;
    }

    public void cancelReservation(String pnr) {
        reservations.removeIf(reservation -> reservation.getPnr().equals(pnr));
    }

    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();

        // Using try-with-resources to ensure the Scanner is closed automatically
        try (Scanner scanner = new Scanner(System.in)) {
            // Adding a sample user
            system.addUser(new User("admin", "password"));

            System.out.println("Welcome to the Online Reservation System");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (system.authenticate(username, password)) {
                System.out.println("Login successful!");

                while (true) {
                    System.out.println("1. Make a Reservation");
                    System.out.println("2. Cancel a Reservation");
                    System.out.println("3. View Reservation");
                    System.out.println("4. Exit");
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    if (choice == 1) {
                        System.out.print("Enter PNR: ");
                        String pnr = scanner.nextLine();
                        System.out.print("Enter Train Number: ");
                        String trainNumber = scanner.nextLine();
                        System.out.print("Enter Train Name: ");
                        String trainName = scanner.nextLine();
                        System.out.print("Enter Class Type: ");
                        String classType = scanner.nextLine();
                        System.out.print("Enter Date of Journey: ");
                        String dateOfJourney = scanner.nextLine();
                        System.out.print("Enter From: ");
                        String from = scanner.nextLine();
                        System.out.print("Enter To: ");
                        String to = scanner.nextLine();

                        Reservation reservation = new Reservation(pnr, trainNumber, trainName, classType, dateOfJourney, from, to);
                        system.addReservation(reservation);
                        System.out.println("Reservation made successfully!");

                    } else if (choice == 2) {
                        System.out.print("Enter PNR to cancel: ");
                        String pnr = scanner.nextLine();
                        system.cancelReservation(pnr);
                        System.out.println("Reservation cancelled successfully!");

                    } else if (choice == 3) {
                        System.out.print("Enter PNR to view: ");
                        String pnr = scanner.nextLine();
                        Reservation reservation = system.getReservation(pnr);
                        if (reservation != null) {
                            System.out.println("PNR: " + reservation.getPnr());
                            System.out.println("Train Number: " + reservation.getTrainNumber());
                            System.out.println("Train Name: " + reservation.getTrainName());
                            System.out.println("Class Type: " + reservation.getClassType());
                            System.out.println("Date of Journey: " + reservation.getDateOfJourney());
                            System.out.println("From: " + reservation.getFrom());
                            System.out.println("To: " + reservation.getTo());
                        } else {
                            System.out.println("Reservation not found.");
                        }

                    } else if (choice == 4) {
                        System.out.println("Exiting...");
                        break;
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid username or password.");
            }
        }
    }
}
