import java.util.Scanner;

public class Manager {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Get theater layout parameters
        int rows = getRows();
        int seatsPerRow = getRows();
        System.out.println();

        // Create theater layout in 2D array
        String[][] seating = new String[rows][seatsPerRow];
        updateSeatingArrangement(seating);
        displaySeatingArrangement(seating);

        // Get theater seat preference
        System.out.print("\nEnter a row number: ");
        int userRow = scanner.nextInt();
        System.out.print("Enter a seat number in that row: ");
        int userSeatInRow = scanner.nextInt();
        System.out.println();

        // Calculate ticket price
        System.out.printf("Ticket price: $%d%n", ticketPrice(userRow, seating));

        // Fill theater seat
        seating[userRow - 1][userSeatInRow - 1] = "B";
        displaySeatingArrangement(seating);

        scanner.close();
    }

    public static int getRows() {
        System.out.print("Enter the number of rows: ");
        return scanner.nextInt();
    }

    public static int getSeatsPerRow() {
        System.out.print("Enter the number of seats in each row: ");
        return scanner.nextInt();
    }
    public static void displaySeatingArrangement(String[][] seatingArrangement) {
        System.out.print("Cinema: \n  ");

        for (int i = 1; i <= seatingArrangement[0].length; i++) System.out.print(i + " ");  // row numbers

        for (int i = 0; i < seatingArrangement.length; i++) {
            System.out.printf("%n%d ", i + 1);                                              // column numbers
            for (int j = 0; j < seatingArrangement[i].length; j++) {
                System.out.print(seatingArrangement[i][j] + " ");
            }
        }
        System.out.println();
    }

    public static void updateSeatingArrangement(String[][] seatingArrangement) {
        String emptySeatSymbol = "S";
        for (int i = 0; i < seatingArrangement.length; i++) {
            for (int j = 0; j < seatingArrangement[0].length; j++) {
                seatingArrangement[i][j] = emptySeatSymbol;
            }
        }
    }

    public static int ticketPrice(int userRow, String[][] seatingArrangement) {
        int totalSeats = seatingArrangement.length * seatingArrangement[0].length;

        if (totalSeats <= 60) {                             // low capacity
            return 10;
        }
        else {                                              // high capacity
            if (userRow <= seatingArrangement.length / 2) { // front-half seating
                return 10;
            }
            else {                                          // back-half seating
                return 8;
            }
        }
    }

    public static int calculateProfit(int rows, int seatsPerRow) {
        int totalSeats = rows * seatsPerRow;
        int ticketPrice;
        int total = 0;

        // low capacity
        if (totalSeats <= 60) {
            ticketPrice = 10;
            total = totalSeats * ticketPrice;
        }

        // high capacity
        else {
            // front half seating
            ticketPrice = 10;
            total = (seatsPerRow * (rows / 2)) * ticketPrice;
            // back half seating
            ticketPrice = 8;
            total += (seatsPerRow * (rows - (rows / 2))) * ticketPrice;
        }
        return total;
    }
}