
class JoinQueueEvent extends Event {
  private Customer customer;
  private Shop shop;

  public JoinQueueEvent(Customer customer, Shop shop) {
    super(customer.getArrivalTIme());
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public Event[] simulate() {
    this.shop.joinQueue(customer);
      return new Event[] {};
  }

  @Override
  public String toString() {
    return String.format("%s: %s joined queue %s",
        super.toString(),
        this.customer, this.shop.queueString());
  }
}
