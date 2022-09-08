/**
 * Shop is a data class which holds information about a shop.
 * It stores the list of counters and the queue in which new customers can join.
 * 
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
public class Shop {
  private Array<ServiceCounter> counters;
  private Queue<Customer> queue;

  public Shop(int numOfCounters, int shopQueueSize, int counterQueueSize) {
    this.counters = new Array<ServiceCounter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      this.counters.set(i, new ServiceCounter(counterQueueSize));
    }

    this.queue = new Queue<Customer>(shopQueueSize);
  }

  /**
   * getAvailableCounter returns the first available counter it finds.
   * If there are none, returns null
   * 
   * @return the available counter or null if none found
   */

  public ServiceCounter getAvailableCounter() {
    // Check if this logic can be moved elsewhere
    /*
    for (int i = 0; i < this.counters.length; i++) {
      if (this.counters[i].isAvailable()) {
        return counters[i];
      }
    }
    */
    return null;
  }

  public boolean isQueueFull() {
    return this.queue.isFull();
  }
  public boolean isQueueEmpty() {
    return this.queue.isEmpty();
  }

  public void joinQueue(Customer customer) {
    this.queue.enq(customer);
  }

  public Customer leaveQueue() {
    return this.queue.deq();
  }

  public String queueString() {
    return this.queue.toString();
  }

}
