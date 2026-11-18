package Ex5;

import java.util.concurrent.ThreadLocalRandom;

public class Customer implements Runnable{
// shops then joins queue
// to add Start shopping, spend a random amount of time shopping
// Finish shopping join the checkout queue

    private final int customerID;
    private final CheckoutQueue checkoutQueue;

    //Con
    public Customer(int customerID, CheckoutQueue checkoutQueue) {
        this.customerID = customerID;
        this.checkoutQueue = checkoutQueue;
    }

    // Customer shops + joins the checkout queue
    @Override
    public void run() {
        try {
            System.out.println(customerID + " this Customer starts shopping ");

            // Sleep for 0 to 3 seconds
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000));

            System.out.println( customerID +" this Customer finished  ");

            // Add customer to the shared queue
            checkoutQueue.addCustomer(customerID);

        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }


}
