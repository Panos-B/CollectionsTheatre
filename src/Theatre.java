import java.util.*;

public class Theatre {
    private static final String theatreName = "Olympian";
    private List<Seat> seats = new ArrayList<>();
    private List<Seat> freeSeats;

    static final Comparator<Seat> PRICE_ORDER = (seat1, seat2) -> {
        if (seat1.getPrice() < seat2.getPrice()) {
            return -1;
        }
        else if (seat1.getPrice() > seat2.getPrice()) {
            return 1;
        }
        else {
            return 0;
        }
    };

    public Theatre(int numRows, int seatsPerRow) {

        int lastRow = 'A' + (numRows - 1);
        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                double price = 12.00;
                if ((row < 'D') && (seatNum >= 4 && seatNum <= 9)) {
                    price = 14.00;
                }
                else if ((row > 'F') || (seatNum < 4 || seatNum > 9)) {
                    price = 7.00;
                }

                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
                seats.add(seat);
            }
        }

        freeSeats = new ArrayList<>(seats);
    }

    public boolean reserveSeat(String seatNumber, String customerName) {
        Seat requestedSeat = new Seat(seatNumber, 0);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
        if (foundSeat >= 0) {
            return seats.get(foundSeat).reserve(customerName);
        }
        else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }
    }

    public boolean cancelSeat(String seatNumber) {
        Seat seatToCancel = new Seat(seatNumber,0);
        int foundSeat = Collections.binarySearch(seats, seatToCancel, null);
        if (foundSeat >= 0) {
            return seats.get(foundSeat).cancel();
        }
        else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }
    }

    public String getTheatreName() {
        return theatreName;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public List<Seat> getFreeSeats() {
        return freeSeats;
    }

    public static class Seat implements Comparable<Seat> {
        private String seatNumber;
        private double price;
        private boolean reserved = false;
        private String customerReserveName = "";

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        public void setReserved(boolean reserved) {
            this.reserved = reserved;
        }

        public String getCustomerReserveName() {
            return customerReserveName;
        }

        public void setCustomerReserveName(String customerReserveName) {
            this.customerReserveName = customerReserveName;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }

        public boolean isReserved() {
            return reserved;
        }

        public boolean reserve(String customerName) {
            if (!this.reserved) {
                this.reserved = true;
                customerReserveName = customerName;
                System.out.println("Seat " + seatNumber + " reserved, by " + customerReserveName);
                return true;
            }
            else {
                System.out.println("Seat No " + this.seatNumber + " is already reserved. Choose another seat to reserve");
                return false;
            }
        }

        public boolean cancel() {
            if(this.reserved) {
                this.reserved = false;
                System.out.println("Reservation of seat " + seatNumber + " cancelled");
                return true;
            }
            else {
                System.out.println("There is not a reservation on seat " + this.seatNumber);
                return false;
            }
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }
    }
}
