public class ShopCounter {
    final private int id;
    private boolean available;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public ShopCounter(int id) {
        this.id = id;
        this.available = true;
    }

    @Override
    public String toString() {
        return "Counter " + id;
    }

}
