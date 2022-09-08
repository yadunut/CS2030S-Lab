/**
 * This is a data class which holds information about a counter. It has a
 * incrementing counter for the conuter Id.
 * 
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
public class ServiceCounter {
  private static int lastId;

  private final int id;
  private boolean available;

  public boolean isAvailable() {
    return available;
  }

  public void occupy() {
    this.available = false;
  }

  public void free() {
    this.available = true;
  }

  public ServiceCounter() {
    this.id = lastId++;
    this.available = true;
  }

  @Override
  public String toString() {
    return "S" + id;
  }

}
