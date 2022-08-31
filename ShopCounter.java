/**
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
public class ShopCounter {
    final private int id;
    private boolean available;

    public boolean isAvailable() {
        return available;
    }

    public void occupy() {
        this.available = false;
    }

    public void free() {
        this.available = true;
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
