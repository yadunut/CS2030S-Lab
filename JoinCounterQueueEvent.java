public class JoinCounterQueueEvent extends Event {
  private Customer customer;
  private Shop shop;
  private ServiceCounter counter;

  public JoinCounterQueueEvent(double time, Customer customer, Shop shop, ServiceCounter counter) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public Event[] simulate() {
    this.counter.joinQueue(customer);
    return new Event[] {};
  }

  @Override
  public String toString() {
    return String.format("%s: %s joined counter queue (at %s)",
        super.toString(),
        this.customer, this.counter);
  }
}
