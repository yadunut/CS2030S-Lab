/**
 * This is a data class which holds information about a counter. It has a
 * incrementing counter for the conuter Id.
 * 
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
public class ServiceCounter implements Comparable<ServiceCounter> {
  private static int lastId;

  private final int id;
  private boolean available;
  private Queue<Customer> queue;

  public boolean isAvailable() {
    return available;
  }

  public void occupy() {
    this.available = false;
  }

  public void free() {
    this.available = true;
  }

  public ServiceCounter(int queueSize) {
    this.id = lastId++;
    this.available = true;
    this.queue = new Queue<Customer>(queueSize);
  }

  @Override
  public String toString() {
    return "S" + id;
  }

  @Override
  public int compareTo(ServiceCounter o) {
    if (this.queue.length() < o.queue.length()) {
      return -1;
    }
    if (this.id < o.id) {
      return -1;
    }
    return 1;
  }
}
