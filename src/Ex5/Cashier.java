package Ex5;

import java.util.concurrent.ThreadLocalRandom;

public class Cashier implements Runnable{
//serves customers
    private final int cashierID;
    private final CheckoutQueue checkoutQueue;

    //Cons
    public Cashier(int cashierID, CheckoutQueue checkoutQueue) {
        this.cashierID = cashierID;
        this.checkoutQueue = checkoutQueue;
    }
    // Cashier serves customers from checkout queue
    @Override
    public void run() {
        try {
            while (true) {
                // Sleep for 0 to 3 seconds
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
                // Get next customer
                int customerID = checkoutQueue.removeCustomer();
                if (customerID == -1) { // -1 stops cashier
                    break;
                }

                System.out.println("Cashier " + cashierID + " serves Customer " + customerID);
            }

        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Cashier " + cashierID + " finished work.");
    }

}
