/**
 * The DepartureEvent is an Event which handles the end of a service.
 * It handles allocating customers in queue to a counter.
 * 
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
class DepartureEvent extends Event {

  private Customer customer;

  public DepartureEvent(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  @Override
  public String toString() {
    return String.format("%s: %s departed", super.toString(), this.customer);
  }

  @Override
  public Event[] simulate() {
    // when customer departs, check if there are customers in queue
    return new Event[] {};
  }

}
