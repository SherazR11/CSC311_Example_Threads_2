package Ex5;

import java.util.concurrent.ArrayBlockingQueue;

public class CheckoutQueue {
// holds customer IDs -waiting to check out
    private final ArrayBlockingQueue<Integer> queue;

    //Constructor
    public CheckoutQueue(int queueSize) {
        queue = new ArrayBlockingQueue<>(queueSize);
    }
    //Add customer to queue
   /* public void addCustomer(int customerID) throws InterruptedException {
        queue.put(customerID);
        System.out.println("Customer joined queue" + customerID);
    }

    */
    public void addCustomer(int customerID) throws InterruptedException {
        queue.put(customerID);

        if (customerID != -1) {
            System.out.println( "Customer" + customerID+ " joined queue: ");
        }
    }
    //Remove customer from queue
    public int removeCustomer() throws InterruptedException {
        return queue.take();
    }
    //num of customers waiting
    public int getQueueSize() {
        return queue.size();
    }
}
