package Ex5;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CostcoSimulation {

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Costco Checkout Simulation");

        System.out.print("Number of customers: ");
        int customers = input.nextInt();

        System.out.print("Number of cashiers: ");
        int cashiers = input.nextInt();

        System.out.print("Queue size: ");
        int queueSize = input.nextInt();

        CheckoutQueue queue = new CheckoutQueue(queueSize);

        ExecutorService customerPool = Executors.newCachedThreadPool();
        ExecutorService cashierPool = Executors.newCachedThreadPool();

        // Start cashiers
        for (int i = 1; i <= cashiers; i++) {
            cashierPool.execute(new Cashier(i, queue));
        }

        // Start customers
        for (int i = 1; i <= customers; i++) {
            customerPool.execute(new Customer(i, queue));
        }

        // Wait for customers
        customerPool.shutdown();
        customerPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        // Stop cashiers
        for (int i = 0; i < cashiers; i++) {
            queue.addCustomer(-1);
        }

        cashierPool.shutdown();
        cashierPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        //Display
        System.out.println("\nSimulation Complete");
        System.out.println("Customers: " + customers);
        System.out.println("Cashiers: " + cashiers);
        System.out.println("Queue Size: " + queueSize);
        System.out.println("Cashier Cost Per Hour: $" + (cashiers * 18));

        input.close();
    }

}
