import java.util.ArrayList;
import java.util.Scanner;

class Train {
    private String name;
    private String source;
    private String destination;
    private String time;
    private int passengerStrength;
    private int trainNumber;

    public Train(String name, String source, String destination, String time, int passengerStrength, int trainNumber) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.time = time;
        this.passengerStrength = passengerStrength;
        this.trainNumber = trainNumber;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getTime() {
        return time;
    }

    public int getPassengerStrength() {
        return passengerStrength;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setPassengerStrength(int passengerStrength) {
        this.passengerStrength = passengerStrength;
    }

    // Add a method to display train details
    public void displayTrainDetails() {
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Train Name: " + name);
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Departure Time: " + time);
        System.out.println("Available Seats: " + passengerStrength);
    }
}

class ReservationSystem {
    private ArrayList<Train> trains;

    public ReservationSystem() {
        trains = new ArrayList<>();
        // Add sample trains
        trains.add(new Train("Vandey Bharat Express", "Bhopal", "Delhi", "09:05", 50, 22010));
        trains.add(new Train("PanchValley Express ", "Bhopal", "Indore", "15:00", 50, 12427));
        trains.add(new Train("Jaipur Bhopal Express", "Gwalior", "Satna", "17:46", 50, 12185));
    }

    public void displayAvailableTrains(String source, String destination) {
        int flag = 1;
        for (Train train : trains) {
            if (train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination)) {
                System.out.println("Train Number: " + train.getTrainNumber());
                System.out.println("Train Name: " + train.getName());
                System.out.println("Departure Time: " + train.getTime());
                System.out.println("Available Seats: " + train.getPassengerStrength());
                System.out.println();
                flag++;
            }
        }
        if (flag == 1) {
            System.out.println("No Train is available from " + source + " to " + destination);
        }
    }

    public int checkSeatAvailability(int trainNumber) {
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                return train.getPassengerStrength();
            }
        }
        return -1;
    }

    public boolean bookTicket(int trainNumber, String passengerName) {
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber && train.getPassengerStrength() > 0) {
                train.setPassengerStrength(train.getPassengerStrength() - 1);
                System.out.println("Ticket booked for " + passengerName + " on Train " + trainNumber);
                return true;
            }
        }
        System.out.println("Sorry, the train is full or not found.");
        return false;
    }

    public boolean cancelTicket(int trainNumber, String passengerName) {
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber && train.getPassengerStrength() < 50) {
                train.setPassengerStrength(train.getPassengerStrength() + 1);
                System.out.println("Ticket for " + passengerName + " on Train " + trainNumber + " canceled.");
                return true;
            }
        }
        System.out.println("Ticket canceling failed. Train not found or invalid passenger.");
        return false;
    }

    public void displayTicketDetails(int trainNumber, String passengerName) {
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                System.out.println("Ticket Details:");
                System.out.println("Train Name: " + train.getName());
                System.out.println("Train Number: " + train.getTrainNumber());
                System.out.println("Passenger Name: " + passengerName);
                System.out.println("Departure Time: " + train.getTime());
                return;
            }
        }
        System.out.println("Ticket details not found. Train not found or invalid passenger.");
    }

    // Add a method to display all train details
    public void displayAllTrainDetails() {
        System.out.println("Available Trains:");
        for (Train train : trains) {
            train.displayTrainDetails();
            System.out.println("----------------------------------------");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationSystem reservationSystem = new ReservationSystem();

        System.out.println("==============================================");
        System.out.println("   Welcome to the Railway Reservation System");
        System.out.println("===============================================");

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Check Available Trains");
            System.out.println("2. Check Seat Availability");
            System.out.println("3. Book a Ticket");
            System.out.println("4. Cancel a Ticket");
            System.out.println("5. Display Ticket Details");
            System.out.println("6. Display Train Details");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter source station: ");
                    String source = scanner.nextLine();
                    System.out.print("Enter destination station: ");
                    String destination = scanner.nextLine();
                    System.out.println("=====================================");
                    reservationSystem.displayAvailableTrains(source, destination);
                    break;
                case 2:
                    System.out.print("Enter the train number to check seat availability: ");
                    int trainNumberToCheck = scanner.nextInt();
                    int availableSeats = reservationSystem.checkSeatAvailability(trainNumberToCheck);
                    System.out.println("=====================================");
                    if (availableSeats != -1) {
                        System.out.println("Available seats for Train " + trainNumberToCheck + ": " + availableSeats);
                    } else {
                        System.out.println("Train not found. Please enter a valid train number.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the train number to book a ticket: ");
                    int trainNumberToBook = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter passenger name: ");
                    String passengerName = scanner.nextLine();
                    System.out.println("=====================================");
                    if (reservationSystem.bookTicket(trainNumberToBook, passengerName)) {
                        System.out.println("Ticket booked successfully!");
                    }
                    break;
                case 4:
                    System.out.print("Enter the train number to cancel a ticket: ");
                    int trainNumberToCancel = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter passenger name: ");
                    String passengerNameToCancel = scanner.nextLine();
                    System.out.println("=====================================");
                    if (reservationSystem.cancelTicket(trainNumberToCancel, passengerNameToCancel)) {
                        System.out.println("Ticket canceled successfully!");
                    }
                    break;
                case 5:
                    System.out.print("Enter the train number to display ticket details: ");
                    int trainNumberToDisplay = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter passenger name: ");
                    String passengerNameToDisplay = scanner.nextLine();
                    System.out.println("=====================================");
                    reservationSystem.displayTicketDetails(trainNumberToDisplay, passengerNameToDisplay);
                    break;
                case 6:
                    System.out.println("=====================================");
                    reservationSystem.displayAllTrainDetails();
                    break;
                case 7:
                    System.out.println("Exiting. Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}