import java.util.*;

public class Main {
   private static Scanner scanner = new Scanner(System.in);
    private static Theatre theatre = new Theatre(8, 12);

    public static void main(String[] args) {

        printList(theatre.getSeats());

        System.out.println();

        System.out.println("Please, enter your name");
        String name = scanner.nextLine();
        System.out.println("Please, enter your password");
        String password = scanner.nextLine();
        System.out.println("Please, enter your email");
        String email = scanner.nextLine();
        System.out.println("Please, enter your phone number");
        String phoneNumber = scanner.nextLine();
        System.out.println("Are you over 16 years old");
        Boolean isAdult = false;
        while (true) {
            try {
                Character confirmChar = scanner.next().charAt(0);
                if (confirmChar == 'Y' || confirmChar == 'y') {
                    isAdult = true;
                    break;
                } else if (confirmChar == 'N' || confirmChar == 'n') {
                    System.out.println("Sorry, you cannot enter in");
                    System.exit(0);
                } else {
                    System.out.println("You have to press Y or N to continue");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        scanner.nextLine();

        Customer newCustomer = new Customer(name, password, email, phoneNumber, isAdult);

            while (true) {
                showMenu();
                try {
                    String input = scanner.nextLine();

                    switch (input) {
                        case "0":
                            showFreeSeats(theatre.getFreeSeats());
                            break;
                        case "1":
                            reserveASeat(newCustomer);
                            break;
                        case "2":
                            cancelAReservation();
                            break;
                        case "3":
                            handlePayment();
                            break;
                        case "4":
                            exitApp();
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
            }
        }


    }

    private static void handlePayment() {
    }

    private static void exitApp() {
        System.out.println("Bye!");
        System.exit(0);
    }

    private static void cancelAReservation() {
        System.out.println("Please, enter the number of the seat you want to cancel");
        String inputSeatNumber = scanner.nextLine();
        theatre.cancelSeat(inputSeatNumber);
    }

    private static void reserveASeat(Customer customer) {
        System.out.println("Please, enter the number of the seat you want to reserve");
        String inputSeatNumber = scanner.nextLine();
        if(theatre.reserveSeat(inputSeatNumber, customer.getName())) {
            System.out.println("Please pay for seat No: " + inputSeatNumber);
        }
    }

    private static void showMenu() {
        System.out.println("Please, make a selection:");
        System.out.println("0. Show free seats");
        System.out.println("1. Reserve a seat");
        System.out.println("2. Cancel a reservation");
        System.out.println("3. Pay for a reservation");
        System.out.println("4. Quit");
    }

    public static void printList(List<Theatre.Seat> list) {
        System.out.println(theatre.getTheatreName() + " Theatre seats");
        int seatsInARow = 12;
        for(Theatre.Seat seat : list) {
            System.out.print(" " + seat.getSeatNumber() + " $" + seat.getPrice());
            seatsInARow--;
            if (seatsInARow == 0) {
                System.out.println();
                seatsInARow = 12;
            }
        }
        System.out.println();
        System.out.println("===================================================================================================================");
    }

    private static void showFreeSeats(List<Theatre.Seat> list) {
        System.out.println(theatre.getTheatreName() + " Theatre free seats");
        int seatsInARow = 12;
        for(Theatre.Seat seat : list) {
            if (!seat.isReserved()) {
                System.out.print(" " + seat.getSeatNumber() + " $" + seat.getPrice());
                seatsInARow--;
                if (seatsInARow == 0) {
                    System.out.println();
                    seatsInARow = 12;
                }
            } else {
                System.out.print(SeatsColor.ANSI_RED + " " + seat.getSeatNumber() + "->RES");
            }
            System.out.print(SeatsColor.ANSI_RESET);
        }
        System.out.println();
        System.out.println("===================================================================================================================");
    }
}
