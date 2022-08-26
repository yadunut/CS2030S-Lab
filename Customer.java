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
