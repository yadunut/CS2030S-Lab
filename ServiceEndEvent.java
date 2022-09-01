/**
 * The ServiceEndEvent is an Event which handles the end of a service.
 * It handles freeing the counter and also generating a departure event
 * 
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
class ServiceEndEvent extends Event {

  private ShopCounter counter;
  private Customer customer;
  private Shop shop;

  public ServiceEndEvent(double time, Customer customer, Shop shop, ShopCounter counter) {
    super(time);
    this.customer = customer;
    this.shop = shop;
    this.counter = counter;
  }

  @Override
  public String toString() {
    return super.toString()
        + String.format(": %s service done (by %s)", this.customer, this.counter);
  }

  @Override
  public Event[] simulate() {
    this.counter.free();
    return new Event[] { new DepartureEvent(this.getTime(), customer, shop, counter) };
  }
}