/**
 * This is a data class which holds information about the Customer.
 * It has a incrementing counter for the customer id;
 * 
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
public class Customer {
  private static int lastId = 0;

  private final int id;
  private final double serviceTime;
  private final double arrivalTIme;

  public Customer(double serviceTime, double arrivalTime) {
    this.id = lastId++;
    this.serviceTime = serviceTime;
    this.arrivalTIme = arrivalTime;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }

  public double getArrivalTIme() {
    return arrivalTIme;
  }

  @Override
  public String toString() {
    return "C" + id;
  }

}
