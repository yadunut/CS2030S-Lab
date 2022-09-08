/**
 * The ServiceEndEvent is an Event which handles the end of a service.
 * It handles freeing the counter and also generating a departure event
 * 
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
class ServiceEndEvent extends Event {

  private ServiceCounter counter;
  private Customer customer;
  private Shop shop;

  public ServiceEndEvent(double time, Customer customer, Shop shop, ServiceCounter counter) {
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
    // if there are customers in the counter queue, they will be serviced next.
    // Customers in the shop queue will then be added to the counter queue
    if (!this.counter.isQueueEmpty()) {
      Customer serviceCustomer = this.counter.leaveQueue();

      if (!this.shop.isQueueEmpty()) {
        return new Event[] {
            new DepartureEvent(this.getTime(), this.customer),
            new ServiceBeginEvent(this.getTime(), serviceCustomer, this.shop, this.counter),
            new JoinCounterQueueEvent(this.getTime(), this.shop.leaveQueue(), this.shop, counter)
        };
      }
      return new Event[] {
          new DepartureEvent(this.getTime(), this.customer),
          new ServiceBeginEvent(this.getTime(), serviceCustomer, this.shop, this.counter),
      };
    }
    // There can also be the case where the counter queue may be empty but there are
    // customers in the shop queue.
    if (!this.shop.isQueueEmpty()) {
      return new Event[] {
          new DepartureEvent(this.getTime(), this.customer),
          new ServiceBeginEvent(this.getTime(), this.shop.leaveQueue(), this.shop, this.counter),
      };
    }
    // else there are no more customers in the queue, and the counter can be freed
    this.counter.free();
    return new Event[] { new DepartureEvent(this.getTime(), this.customer) };
  }
}
