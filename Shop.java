/**
 * Shop is a data class which holds information about a shop.
 * It stores the list of counters and the queue in which new customers can join.
 * 
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
public class Shop {
  private ShopCounter[] counters;
  private Queue queue;

  public Shop(int numOfCounters, int queueSize) {
    this.counters = new ShopCounter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      this.counters[i] = new ShopCounter();
    }

    this.queue = new Queue(queueSize);
  }

  /**
   * getAvailableCounter returns the first available counter it finds.
   * If there are none, returns null
   * 
   * @return the available counter or null if none found
   */

  public ShopCounter getAvailableCounter() {
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

}
