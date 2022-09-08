/**
 * The DepartureEvent is an Event which handles the end of a service.
 * It handles allocating customers in queue to a counter.
 * 
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
class DepartureEvent extends Event {

  private ServiceCounter counter;
  private Customer customer;
  private Shop shop;

  public DepartureEvent(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  public DepartureEvent(double time, Customer customer, Shop shop, ServiceCounter counter) {
    super(time);
    this.customer = customer;
    this.shop = shop;
    this.counter = counter;
  }

  @Override
  public String toString() {
    return String.format("%s: %s departed", super.toString(), this.customer);
  }

  @Override
  public Event[] simulate() {
    // when customer departs, check if there are customers in queue
    if (this.shop.isQueueEmpty() || this.counter == null) {
      return new Event[] {};
    }
    Customer c = this.shop.leaveQueue();
    // Move this to ServiceEndEvent
    return new Event[] {
        new ServiceBeginEvent(this.getTime(), c, this.shop, this.counter),
    };

  }

}
