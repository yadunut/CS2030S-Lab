/**
 * Shop is a data class which holds information about a shop.
 * It stores the list of counters and the queue in which new customers can join.
 * 
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
public class Shop {
  private ServiceCounter[] counters;
  private Queue queue;

  public Shop(int numOfCounters, int queueSize) {
    this.counters = new ServiceCounter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      this.counters[i] = new ServiceCounter();
    }

    this.queue = new Queue(queueSize);
  }

  /**
   * getAvailableCounter returns the first available counter it finds.
   * If there are none, returns null
   * 
   * @return the available counter or null if none found
   */

  public ServiceCounter getAvailableCounter() {
    for (int i = 0; i < this.counters.length; i++) {
      if (this.counters[i].isAvailable()) {
        return counters[i];
      }
    }
    return null;
  }

  public Queue getQueue() {
    return this.queue;
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
    return (Customer) this.queue.deq();
  }

  public String queueString() {
    return this.queue.toString();
  }

}
