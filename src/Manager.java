public class Manager {
    public static void main(String[] args) {
        // call method to print seating arrangement
        displaySeatingArrangement();
    }

    public static void displaySeatingArrangement() {
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
