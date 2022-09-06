
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
    ShopCounter availableCounter = this.shop.getAvailableCounter();
    // check if counters are available. If none, push customer to queue if not full.
    // If full, customer departs
    if (availableCounter == null) {
      if (this.shop.isQueueFull()) {
        return new Event[] { new DepartureEvent(this.getTime(), customer, shop) };
      }
      Queue queue = this.shop.getQueue();
      System.out
          .println(String.format("%s: %s joined queue %s",
              super.toString(),
              this.customer, queue));
      queue.enq(customer);
      return new Event[] {};
    }
    return new Event[] {
        new ServiceBeginEvent(this.getTime(), customer, shop, availableCounter) };

  }

  @Override
  public String toString() {
    return String.format("%s: %s arrived %s",
        super.toString(),
        this.customer,
        this.shop.getQueue());
  }
}
