import java.util.Scanner;

public class Manager {
    public static Scanner scanner = new Scanner(System.in);
    static int purchasedTickets = 0;
    static int currentIncome = 0;

    public static void main(String[] args) {
        // Get theater layout parameters
        int row = getSeat("Enter the number of rows: ");
        int col = getSeat("Enter the number of seats in each row: ");

        // Create theater layout in 2D array
        String[][] seating = new String[row][col];
        createSeatingArrangement(seating);
        displaySeatingArrangement(seating);

        // Menu
        boolean isFinished = false;
        int n;
        while (!isFinished) {
            displayMenu();
            n = scanner.nextInt();
            switch (n) {
                case 1:
                    displaySeatingArrangement(seating);
                    break;
                case 2:
                    updateSeatingArrangement(seating);
                    break;
                case 3:
                    displayStatistics(seating);
                    break;
                default:
                    isFinished = true;
            }
        }

        scanner.close();
    }

    public static int getSeat(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public static void displayMenu() {
        String option1 = "Show the seats";
        String option2 = "Buy a ticket";
        String option3 = "Statistics";
        String option0 = "Exit";
        System.out.printf("1. %s%n2. %s%n3. %s%n0. %s%n", option1, option2, option3, option0);
    }

    public static void displayStatistics(String[][] seatingArrangement) {
        int totalIncome = calculateProfit(seatingArrangement.length, seatingArrangement[0].length);
        double percentage = (double) purchasedTickets / (seatingArrangement.length * seatingArrangement[0].length);
        System.out.printf("%nNumber of purchased tickets: %d%n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage * 100);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n%n", totalIncome);
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

    public static void createSeatingArrangement(String[][] seatingArrangement) {
        String emptySeatSymbol = "S";
        for (int i = 0; i < seatingArrangement.length; i++) {
            for (int j = 0; j < seatingArrangement[0].length; j++) {
                seatingArrangement[i][j] = emptySeatSymbol;
            }
        }
    }

    public static void updateSeatingArrangement(String[][] seatingArrangement) {
        int row = 0;
        int col = 0;
        boolean hasValidInput = false;

        while (!hasValidInput) {
            System.out.println();
            row = getSeat("Enter a row number: ");
            col = getSeat("Enter a seat number in that row: ");

            // outside of ticket range
            if (row > seatingArrangement.length || row < 0 || col > seatingArrangement[0].length || col < 0) {
                System.out.println("Wrong input!");
            }
            // already purchased ticket
            else if (seatingArrangement[row - 1][col - 1].equals("B")) {
                System.out.println("That ticket has already been purchased!");
            }
            else {
                hasValidInput = true;
            }

        }
        seatingArrangement[row - 1][col - 1] = "B";
        purchasedTickets++;
        displayPrice(row, seatingArrangement);
    }

    public static void displayPrice(int userRow, String[][] seatingArrangement) {
        int totalSeats = seatingArrangement.length * seatingArrangement[0].length;
        int price;

        if (totalSeats <= 60) {                             // low capacity
            price = 10;
        }
        else {                                              // high capacity
            if (userRow <= seatingArrangement.length / 2) { // front-half seating
                price = 10;
            }
            else {                                          // back-half seating
                price = 8;
            }
        }
        System.out.printf("Ticket price: $%d%n%n", price);
        currentIncome += price;
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