/**
 * The ServiceBeginEvent is an Event which handles the starting of a service.
 * It handles occupying a counter and also generating a serviceEndEvent
 * 
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
class ServiceBeginEvent extends Event {

  private ServiceCounter counter;
  private Customer customer;
  private Shop shop;

  public ServiceBeginEvent(double time, Customer customer, Shop shop, ServiceCounter counter) {
    super(time);
    this.customer = customer;
    this.shop = shop;
    this.counter = counter;
  }

  @Override
  public String toString() {
    return super.toString()
        + String.format(": %s service begin (by %s)", this.customer, this.counter);
  }

  @Override
  public Event[] simulate() {
    this.counter.occupy();
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] {
        new ServiceEndEvent(endTime, this.customer, this.shop, this.counter) };
  }
}
