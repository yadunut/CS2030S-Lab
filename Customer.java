/**
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
public class Customer {
    final private int id;

    public Customer(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer " + id;
    }

}
