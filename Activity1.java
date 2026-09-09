import java.util.Scanner;

public class Activity1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // menu items and their prices
        String[] foodNames = {"Burger", "Pizza", "Pasta", "Sandwich", "Milk Tea"};
        double[] foodPrices = {80.00, 120.00, 100.00, 70.00, 90.00};

        // running totals for the whole transaction
        int totalItems = 0;
        double totalBeforeDiscount = 0.0;
        double totalDiscount = 0.0;

        char orderAgain = 'Y';

        // display the menu
        System.out.println("=====   M E N U   =====");
        for (int i = 0; i < foodNames.length; i++) {
            System.out.printf("%d. %-9s - $%.2f%n", (i + 1), foodNames[i], foodPrices[i]);
        }

        while (orderAgain == 'Y' || orderAgain == 'y') {

            System.out.print("\nEnter item number: ");
            int itemNumber = input.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = input.nextInt();

            // check if the order is valid
            if (itemNumber < 1 || itemNumber > foodNames.length || quantity < 1 || quantity > 10) {
                System.out.println("\nInvalid order! Please enter a valid item and quantity.");
                System.out.print("\nDo you want to order again? (Y/N): ");
                orderAgain = input.next().charAt(0);
                continue;   // skip the rest of this order
            }

            System.out.print("Are you a student? (Y/N): ");
            char studentAnswer = input.next().charAt(0);
            boolean isStudent = (studentAnswer == 'Y' || studentAnswer == 'y');

            double subtotal = foodPrices[itemNumber - 1] * quantity;

            // figure out the deduction rate
            double discountRate;
            if (isStudent && subtotal >= 500) {
                discountRate = 0.15;
            } else if (isStudent) {
                discountRate = 0.10;
            } else if (subtotal >= 500) {
                discountRate = 0.05;
            } else {
                discountRate = 0.0;
            }

            double discount = subtotal * discountRate;
            double orderTotal = subtotal - discount;

            System.out.printf("%nSubtotal: $%.2f%n", subtotal);
            System.out.printf("Discount: $%.2f%n", discount);
            System.out.printf("Order total: $%.2f%n", orderTotal);

            // add this order to the running totals
            totalItems += quantity;
            totalBeforeDiscount += subtotal;
            totalDiscount += discount;

            System.out.print("\nDo you want to order again? (Y/N): ");
            orderAgain = input.next().charAt(0);
            if (orderAgain == 'Y' && orderAgain == 'y') {
                System.out.println("\nInvalid input!");
            } else if (orderAgain == 'N' || orderAgain == 'n') {
                System.out.println("Thank you for ordering!");  // exit the loop if the user doesn't want to order again
            }
        }

        // final computation
        System.out.println("\n=====  ORDER SUMMARY  =====");
        System.out.println("Total items: " + totalItems);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", (totalBeforeDiscount - totalDiscount));
        System.out.println("Thank you for ordering!");

        input.close();
    }
}
