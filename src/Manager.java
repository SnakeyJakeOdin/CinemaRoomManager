import java.util.Scanner;

public class Manager {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Get theater layout parameters
        int row = getSeat("Enter the number of rows: ");
        int col = getSeat("Enter the number of seats in each row: ");

        // Create theater layout in 2D array
        String[][] seating = new String[row][col];
        updateSeatingArrangement(seating);
        displaySeatingArrangement(seating);

        // Menu
        boolean isFinished = false;
        int n;
        while (!isFinished) {
            displayMenu();
            n = scanner.nextInt();
            switch (n) {
                case 1:
                    // call method
                    System.out.println("1st case");
                    break;
                case 2:
                    // call method
                    System.out.println("2nd case");
                    break;
                default:
                    System.out.println("Default case");
                    isFinished = true;
            }
        }


        // Get theater seat preference
        row = getSeat("Enter a row number: ");
        col = getSeat("Enter a seat number in that row: ");

        // Calculate ticket price
        System.out.printf("Ticket price: $%d%n", ticketPrice(row, seating));

        // Fill theater seat
        seating[row - 1][col - 1] = "B";
        displaySeatingArrangement(seating);

        scanner.close();
    }

    public static int getSeat(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public static void displayMenu() {
        String option1 = "Show the seats";
        String option2 = "Buy a ticket";
        String option0 = "Exit";
        System.out.printf("1. %s%n2. %s%n0. %s%n", option1, option2, option0);
    }

    public static void displaySeatingArrangement(String[][] seatingArrangement) {
        System.out.print("\nCinema: \n  ");

        for (int i = 1; i <= seatingArrangement[0].length; i++) System.out.print(i + " ");  // row numbers

        for (int i = 0; i < seatingArrangement.length; i++) {
            System.out.printf("%n%d ", i + 1);                                              // column numbers
            for (int j = 0; j < seatingArrangement[i].length; j++) {
                System.out.print(seatingArrangement[i][j] + " ");
            }
        }
        System.out.println("\n");
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