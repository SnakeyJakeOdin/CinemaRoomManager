import java.util.Arrays;
import java.util.Scanner;

public class Manager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // get rows and seats-per-row
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of seats in each row: ");
        int seatsPerRow = scanner.nextInt();

        // create 2d array
        String[][] seatingArrangement = returnSeatingArrangement(rows, seatsPerRow);
        displaySeatingArrangement(seatingArrangement);

//        System.out.printf("Total income:%n$%d", calculateProfit(rows, seatsPerRow));

    }
    // TODO: Delete if not used by final project step. We are now using 2d arrays.
    public static void displaySeatingArrangementOld() {
        System.out.print("Cinema: \n  ");
        // display columns
        for (int i = 1; i <= 8; i++) System.out.print(i + " ");
        // display rows
        for (int i = 1; i <= 7; i++) {
            System.out.print("\n" + i + " ");
            for (int j = 1; j <= 8; j++) System.out.print("S ");
        }
        System.out.println();
    }

    public static void displaySeatingArrangement(String[][] seatingArrangement) {
        System.out.print("Cinema: \n  ");
        // row numbers
        for (int i = 1; i <= seatingArrangement[0].length; i++) System.out.print(i + " ");
        // display rows
        for (int i = 0; i < seatingArrangement.length; i++) {
            // column numbers
            System.out.printf("%n%d ", i + 1);
            for (int j = 0; j < seatingArrangement[i].length; j++) {
                // display columns
                System.out.print(seatingArrangement[i][j] + " ");
            }
        }
    }

    public static String[][] returnSeatingArrangement(int rows, int seatsPerRow) {
        String[][] seatingArrangement = new String[rows][seatsPerRow];
        String emptySeatSymbol = "S";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seatingArrangement[i][j] = emptySeatSymbol;
            }
        }
        return seatingArrangement;
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
