
/**
 * The ArrivalEvent is an Event which handles the arrival of a customer
 * It decides whether a customer should go into queue or go to a counter
 * It also allocates a counter to the customer. This event thus returns either a
 * DepartureEvent or a ServiceBeginEvent
 * 
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
class ArrivalEvent extends Event {

  private Customer customer;
  private Shop shop;

  public ArrivalEvent(Customer customer, Shop shop) {
    super(customer.getArrivalTIme());
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public Event[] simulate() {
    ServiceCounter availableCounter = this.shop.getAvailableCounter();
    // check if counters are available. If available, start service for that
    // customer
    if (availableCounter != null) {
      return new Event[] {
          new ServiceBeginEvent(this.getTime(), customer, shop, availableCounter) };
    }
    // if no counters available, check if queue slots avialable in counters
    availableCounter = this.shop.findCounterWithQueue();
    if (availableCounter != null) {
      return new Event[] { new JoinCounterQueueEvent(this.getTime(), this.customer, this.shop, availableCounter) };
    }
    // if shop queue isn't empty, join shop queue
    if (!this.shop.isQueueFull()) {
      return new Event[] { new JoinShopQueueEvent(customer, shop) };
    }
    return new Event[] { new DepartureEvent(this.getTime(), customer) };

  }

  @Override
  public String toString() {
    return String.format("%s: %s arrived %s",
        super.toString(),
        this.customer,
        this.shop.queueString());
  }
}
