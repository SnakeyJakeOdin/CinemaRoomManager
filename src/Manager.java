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
    }
}
